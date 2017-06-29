/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.model.column.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.components.Activator;
import com.jaspersoft.studio.components.table.TableManager;
import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.components.table.model.column.command.SetColumnWidthCommand;
import com.jaspersoft.studio.components.table.part.editpolicy.JSSCompoundTableCommand;
import com.jaspersoft.studio.editor.action.ACachedSelectionAction;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;

import net.sf.jasperreports.components.table.BaseColumn;
import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.engine.design.JRDesignElement;

/**
 * Make the selected column of a table of the same width
 * 
 * @author Orlandin Marco
 *
 */
public class ColumnsEqualWidthAction extends ACachedSelectionAction {
	
	/**
	 * The id of the action
	 */
	public static final String ID = "com.jaspersoft.studio.components.table.action.columnsEqualWidth";  //$NON-NLS-1$
	
	
	public ColumnsEqualWidthAction(IWorkbenchPart part) {
		super(part);
		setText(Messages.ColumnsEqualWidthAction_actionName);
		setId(ID);
		setToolTipText(Messages.ColumnsEqualWidthAction_actionTooltip);
		setImageDescriptor(Activator.getDefault().getImageDescriptor("icons/resources/equal.gif")); //$NON-NLS-1$
	}

	/**
	 * The action is enable only if enabled if and only if one of the edit part of the selection 
	 * has as model type an MTable
	 */
	@Override
	protected boolean calculateEnabled() {
		return canExecute(editor.getSelectionCache().getLastRawSelection());
	}
	
	public boolean canExecute(ISelection selectionSet){
		return getSelectionSet(selectionSet).size() > 1;
	}
	
	/**
	 * Return all the columns in the current selection. The set returned is of 
	 * unique and existing column
	 * 
	 * @param selection the current selection, must be a structured selection
	 * @return return a list of the unique columns to resize
	 */
	private List<MColumn> getSelectionSet(ISelection selection){
		HashMap<StandardBaseColumn, MColumn> result = new HashMap<StandardBaseColumn, MColumn>();
		StructuredSelection structSelection = (StructuredSelection)selection;
		if (structSelection != null) {
			for(Object element : structSelection.toList()){
				if (element instanceof EditPart){
					EditPart part = (EditPart)element;
					if (part.getModel() instanceof MColumn){
						MColumn colModel = (MColumn)part.getModel();
						if (colModel.getValue() != null && !result.containsKey(colModel.getValue())){
							result.put(colModel.getValue(), colModel);
						}
					}
				}
			}
		}
		return new ArrayList<MColumn>(result.values());
	}
	
	/**
	 * Calculate the size of the selected columns and set their width to have 
	 * them all of the same width. The space divided between columns is the same
	 * space the columns were occupying when this action is executed
	 * 
	 * @param selecition the current selection, should be a structured selection
	 */
	public void run(ISelection selecition) {
		List<MColumn> columns = getSelectionSet(selecition);
		MTable table = TableManager.getTableNode((ANode)columns.get(0));
		int selectedColumnsWidth = 0;
		for(Object column : columns){
			APropertyNode node = (APropertyNode)column;
			int colWidth = (Integer)node.getPropertyValue(JRDesignElement.PROPERTY_WIDTH);
			selectedColumnsWidth+=colWidth;
		}
		int selectedColumnsNewWidth = selectedColumnsWidth / columns.size();
		int remains = selectedColumnsWidth % columns.size();
		JSSCompoundTableCommand cmd = new JSSCompoundTableCommand(table);
		cmd.setLayoutTableContent(true);
		HashSet<BaseColumn> fixedColumns = new HashSet<BaseColumn>();
		for(MColumn column : columns){
			int newWidth = selectedColumnsNewWidth;
			if (remains > 0){
				newWidth++;
				remains--;
			}
			SetColumnWidthCommand setCommand = new SetColumnWidthCommand(column, newWidth);	
			fixedColumns.add(column.getValue());
			cmd.add(setCommand);
		}
		cmd.setFixedColumns(fixedColumns);
		execute(cmd);
	}

	/**
	 * Execute the action
	 */
	@Override
	public void run() {
		run(getSelection());
	}
}
