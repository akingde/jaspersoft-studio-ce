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
package com.jaspersoft.studio.property;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.views.properties.IPropertySource;

/**
 * Interface used as getAdapter inside a node to provide custom commands instead of SetValueCommand.
 * This because some times the SetValueCommand is not enough, but is the typical command generated. 
 * Some node could need complex commands, and in this case this can be used. This interface is searched
 * when creating a SetValueCommand from the PropertySection or from the Advanced Properties View. 
 * 
 * @author Orlandin Marco
 *
 */
public interface ISetValueCommandProvider {

	/**
	 * Generate a custom command to set the value of a property on an element that return this as adapter
	 * object
	 * 
	 * @param source the object where the value must be set
	 * @param commandName the name of the command
	 * @param propertyId the property set
	 * @param newVal the value of the property
	 * @return a not null command to set the proeprty to the specified value
	 */
	public Command getSetValueCommand(IPropertySource source, String commandName, Object propertyId, Object newVal);
	
}
