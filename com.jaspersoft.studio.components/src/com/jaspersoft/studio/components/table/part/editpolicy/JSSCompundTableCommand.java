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

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.SetValueCommand;

import net.sf.jasperreports.components.table.StandardBaseColumn;

/**
 * Command used to set the property of one or more table cells. It disable
 * the autoresize property if it was enable before to execute the resize.
 * This is done to avoid continuous calculation of the table fill during
 * the resize operations.
 * If the option was enabled before the operations then it is re-enabled at 
 * the end and this will trigger the correct table fill procedure. But in 
 * this way it is executed only once at the end.
 * 
 * Allow also to update the span of all the cells in the table when the command
 * is executed, undone o redone
 * 
 * @author Orlandin Marco
 *
 */
public class JSSCompundTableCommand extends JSSCompoundCommand {

	/**
	 * The table containing the resized columns
	 */
	private MTable table;
	
	/**
	 * Flag used to know if on the execute the span of the cells should be updated
	 */
	private boolean updateTableSpan = false;
	
	/**
	 * Create the command for the resize
	 * 
	 * @param table a not null table containing the resized columns
	 * @param updateTableSpan if set to true will update the span of 
	 * all the cells in the table when the command is executed, undone o redone
	 */
	public JSSCompundTableCommand(MTable table, boolean updateTableSpan){
		super("Change Cell Size", table);
		this.table = table;
		this.updateTableSpan = updateTableSpan;
	}
	
	/**
	 * Create the command for the resize
	 * 
	 * @param table a not null table containing the resized columns
	 */
	public JSSCompundTableCommand(MTable table){
		this(table, false);
	}
	
	/**
	 * Create a general command
	 * 
	 * @param commandText the textual name of the command
	 * @param table a not null table containing the resized columns
	 * @param updateTableSpan if set to true will update the span of 
	 * all the cells in the table when the command is executed, undone o redone
	 */
	public JSSCompundTableCommand(String commandText, MTable table, boolean updateTableSpan){
		super(commandText, table);
		this.table = table;
		this.updateTableSpan = updateTableSpan;
	}
	
	/**
	 * Set the width of a column
	 * 
	 * @param model the not null model of the column
	 * @param width the new width of the column
	 */
	public void setWidth(APropertyNode model, int width){
		SetValueCommand setCommand = new SetValueCommand();
		setCommand.setTarget(model);
		setCommand.setPropertyId(StandardBaseColumn.PROPERTY_WIDTH);
		setCommand.setPropertyValue(width);
		add(setCommand);
	}
	
	@Override
	public void execute() {
		if (table.hasColumnsAutoresizeProportional()){
			table.setPropertyValue(MTable.PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL, false);
			super.execute();
			table.setPropertyValue(MTable.PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL, true);
		} else {
			super.execute();
		}
		if (updateTableSpan){
			table.getTableManager().updateTableSpans();
		}
	}
	
	@Override
	public void undo() {
		if (table.hasColumnsAutoresizeProportional()){
			table.setPropertyValue(MTable.PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL, false);
			super.undo();
			table.setPropertyValue(MTable.PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL, true);
		} else {
			super.undo();
		}
		if (updateTableSpan){
			table.getTableManager().updateTableSpans();
		}
	}
	
	@Override
	public void redo() {
		if (table.hasColumnsAutoresizeProportional()){
			table.setPropertyValue(MTable.PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL, false);
			super.redo();
			table.setPropertyValue(MTable.PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL, true);
		} else {
			super.redo();
		}
		if (updateTableSpan){
			table.getTableManager().updateTableSpans();
		}
	}
}
