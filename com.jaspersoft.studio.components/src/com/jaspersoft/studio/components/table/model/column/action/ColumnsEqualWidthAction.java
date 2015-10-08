/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.components.table.model.column.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.components.Activator;
import com.jaspersoft.studio.components.crosstab.model.cell.MCell;
import com.jaspersoft.studio.components.table.TableManager;
import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.editor.action.ACachedSelectionAction;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.SetValueCommand;

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
		for(Object element : structSelection.toList()){
			if (element instanceof EditPart){
				EditPart part = (EditPart)element;
				if (part.getModel() instanceof MColumn && !(part.getModel() instanceof MCell)){
					MColumn colModel = (MColumn)part.getModel();
					if (colModel.getValue() != null && !result.containsKey(colModel.getValue())){
						result.put(colModel.getValue(), colModel);
					}
				}
			}
		}
		return new ArrayList<MColumn>(result.values());
	}
	
	/**
	 * Calculate the size of the selected columns and set their width to have 
	 * them all of the same width, occupying all the available space
	 * 
	 * @param selecition the current selection, should be a structured selection
	 */
	public void run(ISelection selecition) {
		List<MColumn> columns = getSelectionSet(selecition);
		MTable table = TableManager.getTableNode((ANode)columns.get(0));
		int tableWidth = table.getValue().getWidth();
		int columnsWidth = table.getTableManager().getColumnsTotalWidth();
		int selectedColumnsWidth = 0;
		for(Object column : columns){
			APropertyNode node = (APropertyNode)column;
			int colWidth = (Integer)node.getPropertyValue(JRDesignElement.PROPERTY_WIDTH);
			selectedColumnsWidth+=colWidth;
		}
		int extraSpace = tableWidth - columnsWidth > 0 ? tableWidth - columnsWidth : 0;
		int selectedColumnsNewWidth = (selectedColumnsWidth + extraSpace) / columns.size();
		int remains =  (selectedColumnsWidth + extraSpace) % columns.size();
		JSSCompoundCommand cmd = new JSSCompoundCommand(table);
		for(Object column : columns){
			int newWidth = selectedColumnsNewWidth;
			if (remains > 0){
				selectedColumnsNewWidth++;
				remains--;
			}
			SetValueCommand setCommand = new SetValueCommand();
			setCommand.setPropertyId(JRDesignElement.PROPERTY_WIDTH);
			setCommand.setTarget((APropertyNode)column);
			setCommand.setPropertyValue(newWidth);
			cmd.add(setCommand);
		}
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
