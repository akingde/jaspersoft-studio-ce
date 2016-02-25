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
package com.jaspersoft.studio.components.table;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.model.column.MColumn;
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
		SetValueCommand setCommand = new SetValueCommand(commandName);
		setCommand.setPropertyId(propertyId);
		setCommand.setTarget(source);
		setCommand.setPropertyValue(newVal);
		if (StandardBaseColumn.PROPERTY_WIDTH.equals(propertyId) ||  JRDesignElement.PROPERTY_WIDTH.equals(propertyId)){
			if (source instanceof MColumn){
				JSSCompoundTableCommand compound = new JSSCompoundTableCommand(((MColumn)source).getMTable());
				compound.add(setCommand);
				return compound;
			} else if (source instanceof MTable){
				JSSCompoundTableCommand compound = new JSSCompoundTableCommand((MTable)source);
				compound.add(setCommand);
				return compound;	
			}
		}
		return setCommand;
	}

}
