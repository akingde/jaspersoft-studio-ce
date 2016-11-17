/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.model.column.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.components.table.TableManager;
import com.jaspersoft.studio.components.table.model.column.MColumn;

import net.sf.jasperreports.components.table.BaseColumn;
import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.components.table.StandardColumnGroup;
import net.sf.jasperreports.components.table.StandardTable;

/**
 * Set the width of a table column, it will adjust parent and children of the columns
 * to fit the enw column width
 * 
 * @author Orlandin Marco
 *
 */
public class SetColumnWidthCommand extends Command {
	
	/**
	 * The table in which the column is placed
	 */
	private StandardTable table;
	
	/**
	 * The column to resize
	 */
	private BaseColumn column;
	
	/**
	 * The new width of the column
	 */
	private int newWidth;
	
	/**
	 * Map of every column resized in the execute of the command, with their original widths,
	 * used on the undo
	 */
	private HashMap<StandardBaseColumn, Integer> undoWidths = new HashMap<StandardBaseColumn, Integer>();
	
	/**
	 * Create the command
	 * 
	 * @param column the model of the column to resize
	 * @param newWidth the new width of the column
	 */
	public SetColumnWidthCommand(MColumn column, int newWidth){
		this.newWidth = newWidth;
		this.column = column.getValue();
		this.table = column.getTable().getStandardTable();
	}
	
	/**
	 * Set the width of the cloumn
	 */
	protected void setWidth() {
		int delta = newWidth - column.getWidth();
		
		//Set the width of the resize column
		setColumnWidth((StandardBaseColumn)column, newWidth);
		
		//if the column is a group adjsut the size of every child column
		if (column instanceof StandardColumnGroup){
			setWidthOnChildren((StandardColumnGroup) column,  newWidth);		
		}

		//Search every ancestor of the column and adjust also their width
		BaseColumn currentCol = column;
		StandardColumnGroup currentParent = TableManager.getParent(table, currentCol);
		while(currentParent != null){
			setColumnWidth(currentParent, currentParent.getWidth() + delta);
			currentCol = currentParent;
			currentParent = TableManager.getParent(table, currentCol);
		}
	}
	
	/**
	 * Set the width of the passed column, but before store its original
	 * width in the undo map
	 * 
	 * @param col the column to resize
	 * @param width the new width of the column
	 */
	protected void setColumnWidth(StandardBaseColumn col, int width){
		undoWidths.put(col, col.getWidth());
		col.setWidth(width);
	}
	
	/**
	 * Set recursively the width of every children column of a specific group 
	 * 
	 * @param group the group containing the columns to resize
	 * @param newSize the new size of the columns 
	 */
	protected void setWidthOnChildren(StandardColumnGroup group, int newSize){
		//calculate the new sizes
		int[] childrenNewWidths = getColumnsProportionalWidth(group.getColumns(), newSize);
		int index = 0;
		for(BaseColumn child : group.getColumns()){
			int newChildWidth = childrenNewWidths[index];
			if (child instanceof StandardColumnGroup){
				setWidthOnChildren((StandardColumnGroup)child, newChildWidth);
			}
			setColumnWidth((StandardBaseColumn)child, newChildWidth);
			index++;
		}
	}
	
	/**
	 * Calculate the new width of a set of column to fit a new width of their parent. The width of the column
	 * resized is calculated propertionally to their original width
	 * 
	 * @param columns the columns to resize
	 * @param newWidth the new width the columns should occupy
	 * @return and array with the same number of entry of the passed columns. Each value of the array is the size the 
	 * corrispective column should have to fit the available space
	 */
	private int[] getColumnsProportionalWidth(List<BaseColumn> columns, int newWidth){
		int[] proportionalWidths = new int[columns.size()];
		int index = 0;
		int currentColumnsWidth = 0;
		for(BaseColumn col : columns){
			currentColumnsWidth += col.getWidth();
		}
		
		//Phase 1: change proportionally the width of each column
		int columnsTotalWidth = 0;			
		for(BaseColumn col : columns){
			float proportionalFactor = (float)col.getWidth() / (float)currentColumnsWidth;
			//casting to int is the same to do the floor operation, since it drop the decimal
			int proportionalWidth = (int)(proportionalFactor * newWidth);
			proportionalWidths[index] = proportionalWidth;
			columnsTotalWidth += proportionalWidth;	
			index ++;
		}
		
		//Phase 2: reassign what remains
		int remains = newWidth - columnsTotalWidth;
		index = 0;
		while (remains > 0 && proportionalWidths.length > 0){
			proportionalWidths[index]++;
			remains--;	
			index++;
			if (index == proportionalWidths.length){
				index = 0;
			}
		}
		return proportionalWidths;
	}
	
	@Override
	public void undo() {
		for(Entry<StandardBaseColumn, Integer> undoEntry : undoWidths.entrySet()){
			undoEntry.getKey().setWidth(undoEntry.getValue());
		}
	}

	@Override
	public void execute() {
		undoWidths.clear();
		setWidth();
	}
	
	@Override
	public boolean canExecute() {
		return newWidth >= 0 && column != null && table != null;
	}
}
