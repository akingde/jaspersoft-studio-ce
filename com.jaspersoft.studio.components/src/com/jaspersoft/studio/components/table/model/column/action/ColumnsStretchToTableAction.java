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

import net.sf.jasperreports.components.table.BaseColumn;
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
import com.jaspersoft.studio.property.SetValueCommand;

/**
 * Make the selected columns to fill a table. The action is visible only if there is space to
 * enlarge the columns or if the columns can be resized until they fit the table
 * 
 * @author Orlandin Marco
 *
 */
public class ColumnsStretchToTableAction extends ACachedSelectionAction {
	
	/**
	 * The id of the action
	 */
	public static final String ID = "com.jaspersoft.studio.components.table.action.columnsStretchToTable";  //$NON-NLS-1$

	public ColumnsStretchToTableAction(IWorkbenchPart part) {
		super(part);
		setText(Messages.ColumnsStretchToTableAction_actionName);
		setId(ID);
		setToolTipText(Messages.ColumnsStretchToTableAction_actionDescription);
		setImageDescriptor(Activator.getDefault().getImageDescriptor("icons/resources/stretchToTable.png")); //$NON-NLS-1$
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
		List<MColumn> selectedCols = getSelectionSet(selectionSet);
		if (selectedCols.size() > 0){
			MTable table = TableManager.getTableNode(selectedCols.get(0));
			if (table != null && !table.hasColumnsAutoresizeProportional()){
				int currentColumnsWidth = 0;
				for(BaseColumn col : table.getStandardTable().getColumns()){
					currentColumnsWidth += col.getWidth();
				}
				int availableSpace = table.getValue().getWidth() - currentColumnsWidth;
				if (availableSpace == 0){
					//the columns are already filling the table
					return false;
				} else if (availableSpace > 0){
					return true;
					//there is free space to occupy
				} else {
					//check if resizing the columns they can fit the table
					int selectedColumnsWidth = 0;
					for(MColumn col : selectedCols){
						selectedColumnsWidth += col.getValue().getWidth();
					}
					return (table.getValue().getWidth() - (currentColumnsWidth - selectedColumnsWidth) > 0);
				}
			}
		}
		return false;
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
	
	private int[] getColumnsProportionalWidth(List<MColumn> columns, int newWidth){
		int[] proportionalWidths = new int[columns.size()];
		int index = 0;
		int currentColumnsWidth = 0;
		for(MColumn col : columns){
			currentColumnsWidth += col.getValue().getWidth();
		}
		//Phase 1: change proportionally the width of each column
		int columnsTotalWidth = 0;			
		for(MColumn col : columns){
			float proportionalFactor = (float)col.getValue().getWidth() / (float)currentColumnsWidth;
			//casting to int is the same to do the floor operation, since it drop the decimal
			int proportionalWidth = (int)(proportionalFactor * newWidth);
			proportionalWidths[index] = proportionalWidth;
			columnsTotalWidth += proportionalWidth;
			index ++;
		}
		
		//Phase 2: reassign what remains
		int remains = newWidth - columnsTotalWidth;
		index = 0;
		while (remains > 0){
			proportionalWidths[index]++;
			index++;
			remains--;
			if (index == proportionalWidths.length){
				index = 0;
			}
		}
		return proportionalWidths;
	}
	
	/**
	 * Calculate the size of the selected columns and set their width to have 
	 * them all of the same width. The space divided between columns is the same
	 * space the columns were occupying when this action is executed
	 * 
	 * @param selection the current selection, should be a structured selection
	 */
	public void run(ISelection selection) {
		List<MColumn> selectedCols = getSelectionSet(selection);
		if (selectedCols.size() > 0){
			int[] newWidths = null;
			MTable table = TableManager.getTableNode(selectedCols.get(0));
			if (table != null && !table.hasColumnsAutoresizeProportional()){
				int currentColumnsWidth = 0;
				for(BaseColumn col : table.getStandardTable().getColumns()){
					currentColumnsWidth += col.getWidth();
				}
				//check if resizing the columns they can fit the table
				int selectedColumnsWidth = 0;
				for(MColumn col : selectedCols){
					selectedColumnsWidth += col.getValue().getWidth();
				}
				int freeTableSpace = table.getValue().getWidth() - currentColumnsWidth;
				
				if (table.getValue().getWidth() - currentColumnsWidth > 0){
					//there is free space to occupy
					int availableSpace = selectedColumnsWidth + freeTableSpace;
					newWidths = getColumnsProportionalWidth(selectedCols, availableSpace);
				} else {
					int availableSpace = table.getValue().getWidth() - (currentColumnsWidth - selectedColumnsWidth); 
					newWidths = getColumnsProportionalWidth(selectedCols, availableSpace);		
				}
			}
			if (newWidths != null){
				JSSCompoundCommand cmd = new JSSCompoundCommand(table);
				int index = 0;
				for(MColumn col : selectedCols){
					SetValueCommand setCommand = new SetValueCommand();
					setCommand.setPropertyId(JRDesignElement.PROPERTY_WIDTH);
					setCommand.setTarget(col);
					setCommand.setPropertyValue(newWidths[index]);
					index ++;
					cmd.add(setCommand);
				}
				execute(cmd);	
			}
		}
	}

	/**
	 * Execute the action
	 */
	@Override
	public void run() {
		run(getSelection());
	}
}
