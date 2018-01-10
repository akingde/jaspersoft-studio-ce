/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.model.columngroup.command;

import java.util.HashMap;
import java.util.Map.Entry;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.model.column.MCell;
import com.jaspersoft.studio.components.table.model.column.command.RefreshColumnNamesCommand;
import com.jaspersoft.studio.components.table.model.columngroup.MColumnGroup;
import com.jaspersoft.studio.components.table.model.columngroup.MColumnGroupCell;

import net.sf.jasperreports.components.table.BaseColumn;
import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.components.table.StandardColumn;
import net.sf.jasperreports.components.table.StandardColumnGroup;
import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.components.table.util.TableUtil;

/**
 * Action to move a column from a group to the table top level. The column is not removed 
 * from the current parent so it must be removed first by another command
 * 
 * @author Orlandin Marco
 */
public class MoveColumnOutsideGroupCommand extends Command {

	/**
	 * Map of the cell where the height is changed because of the new column, this map
	 * store the original height of every changed cell, to restore it if the undo operation
	 * is requested
	 */
	private HashMap<DesignCell, Integer> oldCellSizes = new HashMap<DesignCell, Integer>();

	/**
	 * The new parent of the moved column
	 */
	private StandardTable table;
	
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
	 * The index where the column should be inserted
	 */
	private int newIndex;
	
	/**
	 * Instantiate the command to move a column outside a group
	 * 
	 * @param oldPafrent the group where the column was
	 * @param movedElement the model of the moved column
	 * @param newIndex index where the column should be moved
	 */
	public MoveColumnOutsideGroupCommand(MColumnGroupCell parent, MCell movedElement, int newIndex) {
		this((StandardColumnGroup)parent.getValue(), movedElement, newIndex);
	}
	
	/**
	 * Instantiate the command to move a column outside a group
	 * 
	 * @param oldPafrent the group where the column was
	 * @param movedElement the model of the moved column
	 * @param newIndex index where the column should be moved
	 */
	public MoveColumnOutsideGroupCommand(MColumnGroup parent, MCell movedElement, int newIndex) {
		this(parent.getValue(), movedElement, newIndex);
	}
	
	/**
	 * Instantiate the command to move a column outside a group
	 * 
	 * @param oldPafrent the group where the column was
	 * @param movedElement the model of the moved column
	 * @param newIndex index where the column should be moved
	 */
	public MoveColumnOutsideGroupCommand(StandardColumnGroup parent, MCell movedElement, int newIndex) {
		super(Messages.ReorderColumnGroupCommand_reorder_column_group);
		this.movedColumn = movedElement.getValue();
		this.tableNode = movedElement.getMTable();
		this.table = tableNode.getStandardTable();
		if (newIndex < 0) newIndex = 0;
		else if (newIndex >= table.getColumns().size()) newIndex = table.getColumns().size() - 1;
		this.newIndex = newIndex;
		refreshNameCommand = new RefreshColumnNamesCommand(tableNode, true, true);
	}
	
	
	/**
	 * Calculate the height for a cell into a specific column, basing the calculation
	 * on another column of the table on the top level
	 * 
	 * @param column a column of the table
	 * @param section the identifier of the section 
	 * @return the height for a cell on the specified section on the top level of the table
	 */
	private int getHeight(BaseColumn column, int section){
		int currentHeight = 0;
		if (section == TableUtil.TABLE_HEADER && column.getTableHeader() != null){
			currentHeight += column.getTableHeader().getHeight();
		} else if (section == TableUtil.TABLE_FOOTER && column.getTableFooter() != null){
			currentHeight += column.getTableFooter().getHeight();
		} else if (section == TableUtil.COLUMN_HEADER && column.getColumnHeader() != null){
			currentHeight += column.getColumnHeader().getHeight();
		} else if (section == TableUtil.COLUMN_FOOTER && column.getColumnFooter() != null){
			currentHeight += column.getColumnFooter().getHeight();
		} else if (section == TableUtil.COLUMN_DETAIL && column instanceof StandardColumn){
			currentHeight += ((StandardColumn)column).getDetailCell().getHeight();
		}
		if (column instanceof StandardColumnGroup){
			currentHeight += getHeight(((StandardColumnGroup)column).getColumns().get(0), section);
		}
		return currentHeight;
	}

	/**
	 * Move the column inside the table and fix the height of cells
	 */
	private void moveColumn(){
		
		BaseColumn sibling = table.getColumns().size() > 0 ? table.getColumns().get(0) : null;
		
		//Move the column
		table.addColumn(newIndex, movedColumn);
		
		//Adjust the header and footer cells height of the group
		if (movedColumn.getTableHeader() != null){
			DesignCell movedCell = (DesignCell)movedColumn.getTableHeader();
			oldCellSizes.put(movedCell, movedCell.getHeight());
			if (sibling != null) {
				movedCell.setHeight(getHeight(sibling, TableUtil.TABLE_HEADER));	
			}
		}
		

		if (movedColumn.getTableFooter() != null){
			DesignCell movedCell = (DesignCell)movedColumn.getTableFooter();
			oldCellSizes.put(movedCell, movedCell.getHeight());
			if (sibling != null) {
				movedCell.setHeight(getHeight(sibling, TableUtil.TABLE_FOOTER));	
			}
		}
		
		if (movedColumn.getColumnHeader() != null){
			DesignCell movedCell = (DesignCell)movedColumn.getColumnHeader();
			oldCellSizes.put(movedCell, movedCell.getHeight());
			if (sibling != null) {
				movedCell.setHeight(getHeight(sibling, TableUtil.COLUMN_HEADER));	
			}
		}
		
		if (movedColumn.getColumnFooter() != null){
			DesignCell movedCell = (DesignCell)movedColumn.getColumnFooter();
			oldCellSizes.put(movedCell, movedCell.getHeight());
			if (sibling != null) {
				movedCell.setHeight(getHeight(sibling, TableUtil.COLUMN_FOOTER));	
			}
		}
		
		tableNode.getTableManager().updateTableSpans();
		refreshNameCommand.execute();
	}
	
	/**
	 * Undo the operation, remove the column from the new parent and restore
	 * the old heights
	 */
	private void restoreColumn(){
		table.removeColumn(movedColumn);
		
		for(Entry<DesignCell, Integer> entry : oldCellSizes.entrySet()){
			entry.getKey().setHeight(entry.getValue());
		}
		
		oldCellSizes.clear();
		tableNode.getTableManager().updateTableSpans();
		//tableNode.getTableManager().update();
		refreshNameCommand.undo();
	}
	
	/**
	 * Can execute if a new parent is defined and the table node
	 * of the new parent exist and the parent has more than one column
	 * (because a group can't be without columns)
	 */
	@Override
	public boolean canExecute() {
		return table != null && tableNode != null;
	}

	/**
	 * Can undo if a new parent is defined and the table node
	 * of the new parent exist
	 */
	@Override
	public boolean canUndo() {
		return table != null && tableNode != null;
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


