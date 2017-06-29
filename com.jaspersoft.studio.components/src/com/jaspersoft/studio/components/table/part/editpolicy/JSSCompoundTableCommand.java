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
package com.jaspersoft.studio.components.table.part.editpolicy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.components.table.BaseColumn;
import net.sf.jasperreports.components.table.ColumnGroup;
import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.components.table.StandardColumnGroup;
import net.sf.jasperreports.components.table.StandardTable;

/**
 * Command used to set the property of one or more table cells. It will do 
 * the autoresize of the table after executing the commands contained in this 
 * one, if the flag is enabled. Also it can layout the whole table if set 
 * the flag layoutTableContent to true.
 * 
 * Allow also to update the span of all the cells in the table when the command
 * is executed, undone o redone
 * 
 * Before to execute the inner commands store the width of every columns of the table
 * and restore if on the undo
 * 
 * @author Orlandin Marco
 *
 */
public class JSSCompoundTableCommand extends JSSCompoundCommand {

	/**
	 * The table containing the resized columns
	 */
	private MTable table;
	
	/**
	 * Flag used to know if on the execute the span of the cells should be updated
	 */
	private boolean updateTableSpan = false;
	
	/**
	 * Map to store the columns size before to set the flag, it is used on the undo
	 */
	private HashMap<BaseColumn, Integer> originalColumnsSize = null;
	
	/**
	 * This flag allow to layout the table content after the inner commands are executed
	 */
	private boolean layoutTableContent = false;
	
	private HashSet<BaseColumn> fixedColumns = null;
	
	/**
	 * Create the command for the resize
	 * 
	 * @param table a not null table containing the resized columns
	 * @param updateTableSpan if set to true will update the span of 
	 * all the cells in the table when the command is executed, undone o redone
	 */
	public JSSCompoundTableCommand(MTable table, boolean updateTableSpan){
		super("Change Cell Size", table);
		this.table = table;
		this.updateTableSpan = updateTableSpan;
	}
	
	/**
	 * Create the command for the resize
	 * 
	 * @param table a not null table containing the resized columns
	 */
	public JSSCompoundTableCommand(MTable table){
		this(table, false);
	}
	
	/**
	 * Create the command for the resize
	 * 
	 * @param table a not null table containing the resized columns
	 * @param table a not null table containing the resized columns
	 */
	public JSSCompoundTableCommand(String commandText, MTable table){
		this(commandText, table, false);
	}
	
	/**
	 * Create a general command
	 * 
	 * @param commandText the textual name of the command
	 * @param table a not null table containing the resized columns
	 * @param updateTableSpan if set to true will update the span of 
	 * all the cells in the table when the command is executed, undone o redone
	 */
	public JSSCompoundTableCommand(String commandText, MTable table, boolean updateTableSpan){
		super(commandText, table);
		this.table = table;
		this.updateTableSpan = updateTableSpan;
	}
	
	@Override
	public void execute() {
		if (table.hasColumnsAutoresizeProportional()){
			//Store the current column widths
			storeColumnsSize();
			//execute the innter command
			super.execute();
			
			boolean changed = false;
			
			if (fixedColumns == null){
				int currentTableWidth = table.getValue().getWidth();
				HashSet<BaseColumn> modifiedColumn = getModifiedColumns();
				boolean widthChanged = modifiedColumn.size() > 0;
				addGroup(modifiedColumn, table.getStandardTable());
				//fill the space		
				changed = table.getTableManager().fillSpace(currentTableWidth, true, modifiedColumn, widthChanged);
			} else {
				int currentTableWidth = table.getValue().getWidth();
				//fill the space		
				addGroup(fixedColumns, table.getStandardTable());
				changed = table.getTableManager().fillSpace(currentTableWidth, true, fixedColumns);
			}

			if (!changed){
				//The size was already right (probably because of the restoreColumnsSize) so the cells was not
				//layouted after the undo, trigger a manual layout
				JSSCompoundCommand layoutCommands = table.getTableManager().getLayoutCommand();
				layoutCommands.setReferenceNodeIfNull(table);
				layoutCommands.execute();
			}
		} else {
			super.execute();
			if (layoutTableContent){
				//The size was already right (probably because of the restoreColumnsSize) so the cells was not
				//layouted after the undo, trigger a manual layout
				JSSCompoundCommand layoutCommands = table.getTableManager().getLayoutCommand();
				layoutCommands.setReferenceNodeIfNull(table);
				layoutCommands.execute();
			}
		}
		if (updateTableSpan){
			table.getTableManager().updateTableSpans();
		}
	}

	/**
	 * Iterate all the child recursively of the current group. If they are all
	 * in the exclusion set add also the group in the exclusion set
	 * 
	 * @param excludedColumns the current set of the excluded child
	 * @param currentGroup the current group
	 */
	protected void addGroup(HashSet<BaseColumn> excludedColumns, StandardColumnGroup currentGroup){
		boolean allContained = true;
		List<BaseColumn> currentColumns = currentGroup.getColumns();
		for(BaseColumn col : currentColumns){
			if (col instanceof StandardColumnGroup){
				StandardColumnGroup childGroup = (StandardColumnGroup)col;
				addGroup(excludedColumns, childGroup);
			}
			if (!excludedColumns.contains(col)){
				allContained = false;
				break;
			}
		}
		if (allContained){
			excludedColumns.add(currentGroup);
		}
	}
	
	/**
	 * Add to the exluded columns the group columns if all their children are
	 * already in the exclusion set
	 * 
	 * @param excludedColumns the current set of the excluded child
	 * @param the table
	 */
	protected void addGroup(HashSet<BaseColumn> excludedColumns, StandardTable table){
		if (table != null){
			for(BaseColumn col : table.getColumns()){
				if (col instanceof StandardColumnGroup){
					addGroup(excludedColumns, (StandardColumnGroup)col);
				}
			}
		}
	}
	
	@Override
	public void undo() {
		if (table.hasColumnsAutoresizeProportional()){
			//Undo the original command
			super.undo();
			//Restore the original size
			restoreColumnsSize(table.getStandardTable().getColumns());
			//If the table still doesn't fit the width then update it
			boolean changed = table.getTableManager().fillSpace(table.getValue().getWidth(), true, new HashSet<BaseColumn>());
			if (!changed){
				//The size was already right (probably because of the restoreColumnsSize) so the cells was not
				//layouted after the undo, trigger a manual layout
				JSSCompoundCommand layoutCommands = table.getTableManager().getLayoutCommand();
				layoutCommands.setReferenceNodeIfNull(table);
				layoutCommands.execute();
			}
		} else {
			super.undo();
			if (layoutTableContent){
				JSSCompoundCommand layoutCommands = table.getTableManager().getLayoutCommand();
				layoutCommands.setReferenceNodeIfNull(table);
				layoutCommands.execute();
			}
		}
		if (updateTableSpan){
			table.getTableManager().updateTableSpans();
		}
	}
	
	@Override
	public void redo() {
		if (table.hasColumnsAutoresizeProportional()){
			storeColumnsSize();
			super.redo();
			
			//fill the space	
			HashSet<BaseColumn> modifiedColumn = getModifiedColumns();
			boolean widthChanged = modifiedColumn.size() > 0;
			table.getTableManager().fillSpace(table.getValue().getWidth(), true, modifiedColumn, widthChanged);		
		} else {
			super.redo();
			if (layoutTableContent){
				//The size was already right (probably because of the restoreColumnsSize) so the cells was not
				//layouted after the undo, trigger a manual layout
				JSSCompoundCommand layoutCommands = table.getTableManager().getLayoutCommand();
				layoutCommands.setReferenceNodeIfNull(table);
				layoutCommands.execute();
			}
		}
		if (updateTableSpan){
			table.getTableManager().updateTableSpans();
		}
	}
	
	/**
	 * Store the width of every column in the table in the width map
	 */
	protected void storeColumnsSize(){
		List<BaseColumn> columns = getAllColumns(table.getStandardTable().getColumns());
		originalColumnsSize = new HashMap<BaseColumn, Integer>();
		for(BaseColumn column : columns){
			originalColumnsSize.put(column, column.getWidth());
		}
	}
	
	protected HashSet<BaseColumn> getModifiedColumns(){
		HashSet<BaseColumn> result = new HashSet<BaseColumn>();
		for(Entry<BaseColumn, Integer> columnEntry : originalColumnsSize.entrySet()){
			if (!ModelUtils.safeEquals(columnEntry.getKey().getWidth(), columnEntry.getValue())){
				result.add(columnEntry.getKey());
				//addColumnToList(result, columnEntry.getKey());
			}
		}
		return result;
	}
	
	protected void addColumnToList(List<BaseColumn> list, BaseColumn columnToAdd){
		list.add(columnToAdd);
		if (columnToAdd instanceof ColumnGroup){
			ColumnGroup group = (ColumnGroup) columnToAdd;
			for(BaseColumn groupColumn : group.getColumns()){
				addColumnToList(list, groupColumn);
			}
		}
	}
	
	/**
	 * Restore the width of every columns in the table with the one cached
	 * 
	 * @param columns the current set of columns, since it is a recursive method
	 */
	protected void restoreColumnsSize(List<BaseColumn> columns){
		if (originalColumnsSize != null){
			for(BaseColumn column : columns){
				if (column instanceof StandardColumnGroup){
					StandardColumnGroup groupColumn = (StandardColumnGroup)column;
					restoreColumnsSize(groupColumn.getColumns());
				}
				Integer originalWidth = originalColumnsSize.get(column);
				if (originalWidth != null){
					((StandardBaseColumn)column).setWidth(originalWidth);
				}
			}
		}
	}
	
	/**
	 * Return a list of every columns in the table, considering also the
	 * ColumnGroup
	 * 
	 * @param cols the current set of columns, it is a recursive method
	 * @return the list of columns contained in the passed parameter (considering
	 * also the subcolumns contained by the columns groups)
	 */
	protected List<BaseColumn> getAllColumns(List<BaseColumn> cols) {
		List<BaseColumn> lst = new ArrayList<BaseColumn>();
		for (BaseColumn bc : cols) {
			if (bc instanceof ColumnGroup){
				lst.addAll(getAllColumns(((ColumnGroup) bc).getColumns()));
			} 
			lst.add(bc);
		}
		return lst;
	}
	
	/**
	 * Set the layout table content flag. This flag allow to layout
	 * the table content after the inner commands are executed. By 
	 * default this is false
	 * 
	 * @param value true if the contents should be layouted, false otherwise
	 */
	public void setLayoutTableContent(boolean value){
		this.layoutTableContent = value;
	}

	/**
	 * Can execute only if the table reference exist
	 */
	@Override
	public boolean canExecute() {
		return table != null && super.canExecute();
	}
	
	/**
	 * Can undo only if the table reference exist
	 */
	@Override
	public boolean canUndo() {
		return table != null;
	}
	
	public void setFixedColumns(HashSet<BaseColumn> fixedColumns){
		this.fixedColumns = fixedColumns;
	}
}
