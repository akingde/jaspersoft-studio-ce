/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.model.columngroup.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.model.column.MCell;
import com.jaspersoft.studio.components.table.model.column.command.RefreshColumnNamesCommand;

import net.sf.jasperreports.components.table.BaseColumn;
import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.components.table.StandardColumnGroup;

/**
 * Action to move a column inside a group. The column is not removed from the current parent
 * so it must be removed first by another command
 * 
 * @author Orlandin Marco
 */
public class MoveColumnIntoGroupCommand extends Command {
	
	/**
	 * Map of the group where the width is changed because of the new column, this map
	 * store the original width of every column group, to restore it if the undo operation
	 * is requested
	 */
	private HashMap<StandardColumnGroup, Integer> oldGroupsWidth = new HashMap<StandardColumnGroup, Integer>();

	/**
	 * Map of the cell where the height is changed because of the new column, this map
	 * store the original height of every changed cell, to restore it if the undo operation
	 * is requested
	 */
	private HashMap<DesignCell, Integer> oldCellSizes = new HashMap<DesignCell, Integer>();

	/**
	 * The new parent of the moved column
	 */
	private StandardColumnGroup newParent;
	
	/**
	 * The moved column
	 */
	private StandardBaseColumn movedColumn;
	
	/**
	 * The table node where the new parent is contained
	 */
	private MTable tableNode;
	
	/**
	 * A command used to refresh the table column names
	 */
	private RefreshColumnNamesCommand refreshNameCommand;

	/**
	 * Instantiate the command to move a column inside a group
	 * 
	 * @param newParent the group where the column should be moved
	 * @param movedElement the model of the moved column
	 */
	public MoveColumnIntoGroupCommand(StandardColumnGroup newParent, MCell movedElement) {
		super(Messages.ReorderColumnGroupCommand_reorder_column_group);
		this.newParent = newParent;
		this.movedColumn = movedElement.getValue();
		this.tableNode = movedElement.getMTable();
		refreshNameCommand = new RefreshColumnNamesCommand(tableNode, true, true);
	}

	/**
	 * Move the column inside the group and adjust the size of it to contains the column
	 */
	private void moveColumn(){
		
		//Move the column
		newParent.addColumn(movedColumn);
		
		//adjust recursively the group width to contains the new column
		setGroupWidth2Top(tableNode.getStandardTable().getColumns(), movedColumn, movedColumn.getWidth());
		
		//Adjust the header and footer cells height of the group
		
		if (newParent.getTableHeader() != null && movedColumn.getTableHeader() != null){
			DesignCell groupCell = (DesignCell)newParent.getTableHeader();
			DesignCell movedCell = (DesignCell)movedColumn.getTableHeader();
			oldCellSizes.put(movedCell, movedCell.getHeight());
			int newHeight = movedCell.getHeight() - groupCell.getHeight();
			if (newHeight < 0) newHeight = 0;
			movedCell.setHeight(newHeight);
		}

		if (newParent.getTableFooter() != null && movedColumn.getTableFooter() != null){
			DesignCell groupCell = (DesignCell)newParent.getTableFooter();
			DesignCell movedCell = (DesignCell)movedColumn.getTableFooter();
			oldCellSizes.put(movedCell, movedCell.getHeight());
			int newHeight = movedCell.getHeight() - groupCell.getHeight();
			if (newHeight < 0) newHeight = 0;
			movedCell.setHeight(newHeight);
		}
		
		if (newParent.getColumnHeader() != null && movedColumn.getColumnHeader() != null){
			DesignCell groupCell = (DesignCell)newParent.getColumnHeader();
			DesignCell movedCell = (DesignCell)movedColumn.getColumnHeader();
			oldCellSizes.put(movedCell, movedCell.getHeight());
			int newHeight = movedCell.getHeight() - groupCell.getHeight();
			if (newHeight < 0) newHeight = 0;
			movedCell.setHeight(newHeight);
		}
		
		if (newParent.getColumnFooter() != null && movedColumn.getColumnFooter() != null){
			DesignCell groupCell = (DesignCell)newParent.getColumnFooter();
			DesignCell movedCell = (DesignCell)movedColumn.getColumnFooter();
			oldCellSizes.put(movedCell, movedCell.getHeight());
			int newHeight = movedCell.getHeight() - groupCell.getHeight();
			if (newHeight < 0) newHeight = 0;
			movedCell.setHeight(newHeight);
		}
		
		tableNode.getTableManager().updateTableSpans();
		//tableNode.getTableManager().update();
		refreshNameCommand.execute();
	}
	
	/**
	 * Undo the operation, remove the column from the new parent and restore
	 * the old widths and heights
	 */
	private void restoreColumn(){
		newParent.removeColumn(movedColumn);
		
		for(Entry<StandardColumnGroup, Integer> entry : oldGroupsWidth.entrySet()){
			entry.getKey().setWidth(entry.getValue());
		}
		for(Entry<DesignCell, Integer> entry : oldCellSizes.entrySet()){
			entry.getKey().setHeight(entry.getValue());
		}
		
		oldGroupsWidth.clear();
		oldCellSizes.clear();
		tableNode.getTableManager().updateTableSpans();
		//tableNode.getTableManager().update();
		refreshNameCommand.undo();
	}
	
	/**
	 * Recursively search the group where the column is contained, and every other group that contains it 
	 * in cascade. Then resize each of this group to contains the new column
	 * 
	 * @param cols a not null list of column, when called the first time these are the columns inside the table,
	 * then on the iteration are the columns inside each group found
	 * @param searchedColumn the column inserted into the group
	 * @param delta how much the new group and every group containing it should be increase/decreased
	 * @return used for the recursion, true if in the current col was found the insterd column, false otherwise
	 */
	protected boolean setGroupWidth2Top(List<BaseColumn> cols, StandardBaseColumn searchedColumn, int delta) {
		for (BaseColumn bc : cols) {
			if (bc == searchedColumn) {
				return true;
			}
			if (bc instanceof StandardColumnGroup) {
				StandardColumnGroup group = (StandardColumnGroup)bc;
				boolean columnFound = setGroupWidth2Top(group.getColumns(), searchedColumn, delta);
				if (columnFound) {			
					oldGroupsWidth.put(group, group.getWidth());
					group.setWidth(group.getWidth() + delta);
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Can execute if a new parent is defined and the table node
	 * of the new parent exist
	 */
	@Override
	public boolean canExecute() {
		return newParent != null && tableNode != null;
	}

	/**
	 * Can undo if a new parent is defined and the table node
	 * of the new parent exist
	 */
	@Override
	public boolean canUndo() {
		return newParent != null && tableNode != null;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		if (tableNode.hasColumnsAutoresizeProportional()){
			tableNode.setPropertyValue(MTable.PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL, false);
			moveColumn();
			tableNode.setPropertyValue(MTable.PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL, true);
		} else {
			moveColumn();
		}	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		if (tableNode.hasColumnsAutoresizeProportional()){
			tableNode.setPropertyValue(MTable.PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL, false);
			restoreColumn();
			tableNode.setPropertyValue(MTable.PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL, true);
		} else {
			restoreColumn();
		}	
	}
}


