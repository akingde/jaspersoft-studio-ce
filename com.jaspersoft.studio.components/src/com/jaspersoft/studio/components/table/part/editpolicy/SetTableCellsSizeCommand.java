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

import net.sf.jasperreports.components.table.StandardBaseColumn;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.SetValueCommand;

/**
 * Command used to set the width of one or more table cells. It disable
 * the autoresize property if it was enable before to execute the resize.
 * This is done to avoid continuous calculation of the table fill during
 * the resize operations.
 * If the option was enabled before the operations then it is re-enabled at 
 * the end and this will trigger the correct table fill procedure. But in 
 * this way it is executed only once at the end
 * 
 * @author Orlandin Marco
 *
 */
public class SetTableCellsSizeCommand extends JSSCompoundCommand {

	/**
	 * The table containing the resized columns
	 */
	private MTable table;
	
	/**
	 * Create the resize command
	 * 
	 * @param table s not null table containing the resized columns
	 */
	public SetTableCellsSizeCommand(MTable table){
		super("Change Cell Size", table);
		this.table = table;
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
	}
	
}
