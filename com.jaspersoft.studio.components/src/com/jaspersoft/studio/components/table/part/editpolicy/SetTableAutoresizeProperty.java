/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.part.editpolicy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.components.table.BaseColumn;
import net.sf.jasperreports.components.table.ColumnGroup;
import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.components.table.StandardColumnGroup;

/**
 * Command to set the table autoresize flag. It also will trigger
 * the atuoresize of the table itself
 *
 * @author Orlandin Marco
 *
 */
public class SetTableAutoresizeProperty extends Command {

	/**
	 * The table containing the resized columns
	 */
	private MTable table;
	
	/**
	 * Flag used to know if on the execute the span of the cells should be updated
	 */
	private boolean setAutoresize = false;
	
	/**
	 * Keep track if the value was changed in the executed. If the table has already the same
	 * value set for the flag this will be false since the execute doesn't need to do anything.
	 * In this case also the undo will not do anything
	 */
	private boolean valueChanged = false;
	
	/**
	 * Map to store the columns size before to set the flag, it is used on the undo
	 */
	private HashMap<BaseColumn, Integer> originalColumnsSize = null;
	
	/**
	 * Create the command for the resize
	 * 
	 * @param table a not null table containing the resized columns
	 * @param setAutoresize if the autoresize should be set to true or false by this command
	 */
	public SetTableAutoresizeProperty(MTable table, boolean setAutoresize){
		super("Set Table Autoresize");
		this.table = table;
		this.setAutoresize = setAutoresize;
	}
	
	@Override
	public void execute() {
		if (!ModelUtils.safeEquals(setAutoresize, table.hasColumnsAutoresizeProportional())){
			valueChanged = true;
			if (setAutoresize){
				storeColumnsSize();
				table.setPropertyValue(MTable.PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL, true);
				table.getTableManager().fillSpace(table.getValue().getWidth(), true, new HashSet<BaseColumn>());
			} else {
				table.setPropertyValue(MTable.PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL, false);
			}
		}
	}

	
	@Override
	public void undo() {
		if (valueChanged){
			if (setAutoresize){
				table.setPropertyValue(MTable.PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL, false);
				restoreColumnsSize(table.getStandardTable().getColumns());
				//Now the flag is false, so simply layout the content after the restore of the original sizes
				JSSCompoundCommand layoutCommands = table.getTableManager().getLayoutCommand();
				layoutCommands.setReferenceNodeIfNull(table);
				layoutCommands.execute();
			} else {
				restoreColumnsSize(table.getStandardTable().getColumns());
				table.setPropertyValue(MTable.PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL, true);
				//now the flag is true, trigger and autofill
				boolean changed = table.getTableManager().fillSpace(table.getValue().getWidth(), true, new HashSet<BaseColumn>());
				if (!changed){
					//The size was already right (probably because of the restoreColumnsSize) so the cells was not
					//layouted after the undo, trigger a manual layout
					JSSCompoundCommand layoutCommands = table.getTableManager().getLayoutCommand();
					layoutCommands.setReferenceNodeIfNull(table);
					layoutCommands.execute();
				}
			}
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
	 * Can execute only if the table reference exist
	 */
	@Override
	public boolean canExecute() {
		return table != null;
	}
	
	/**
	 * Can undo only if the table reference exist
	 */
	@Override
	public boolean canUndo() {
		return table != null;
	}
}
