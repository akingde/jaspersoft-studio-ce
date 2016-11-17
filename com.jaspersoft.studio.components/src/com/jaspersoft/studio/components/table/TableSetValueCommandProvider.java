/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.components.table.model.column.command.SetColumnWidthCommand;
import com.jaspersoft.studio.components.table.part.editpolicy.JSSCompoundTableCommand;
import com.jaspersoft.studio.property.ISetValueCommandProvider;
import com.jaspersoft.studio.property.SetValueCommand;

import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.engine.design.JRDesignElement;

/**
 * SetValueCommandProvider for the table element and its cells. When a SetValueCommand is requested for the table,
 * if the property changed is the width, then embed the command into a {@link JSSCompoundTableCommand}, to allow
 * the table autoresize if the fill property is enabled. Otherwise it will generate a standard command
 * 
 * @author Orlandin Marco
 *
 */
public class TableSetValueCommandProvider implements ISetValueCommandProvider {

	/**
	 * Since this class can be used without be instanced everytime, this static instance can recalled when 
	 * needed
	 */
	public static TableSetValueCommandProvider INSTANCE = new TableSetValueCommandProvider();
	
	@Override
	public Command getSetValueCommand(IPropertySource source, String commandName, Object propertyId, Object newVal) { 
		if (StandardBaseColumn.PROPERTY_WIDTH.equals(propertyId) ||  JRDesignElement.PROPERTY_WIDTH.equals(propertyId)){
			if (source instanceof MColumn){
				MColumn column = (MColumn)source;
				JSSCompoundTableCommand compound = new JSSCompoundTableCommand(((MColumn)source).getMTable());
				SetColumnWidthCommand setColumnWidth = new SetColumnWidthCommand(column, (Integer)newVal);
				compound.add(setColumnWidth);
				return compound;
			} else if (source instanceof MTable){
				JSSCompoundTableCommand compound = new JSSCompoundTableCommand((MTable)source);
				SetValueCommand setCommand = new SetValueCommand(commandName);
				setCommand.setPropertyId(propertyId);
				setCommand.setTarget(source);
				setCommand.setPropertyValue(newVal);
				compound.add(setCommand);
				return compound;	
			}
		} 
		SetValueCommand setCommand = new SetValueCommand(commandName);
		setCommand.setPropertyId(propertyId);
		setCommand.setTarget(source);
		setCommand.setPropertyValue(newVal);
		return setCommand;
	}

}
