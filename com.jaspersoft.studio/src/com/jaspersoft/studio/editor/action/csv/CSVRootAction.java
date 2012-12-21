/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.csv;

import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JRStaticText;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.property.SetValueCommand;

/**
 * This action is performed on a static text element but the property is not set on the element
 * but on the root of the document. The value of the attribute is the textual value of the element
 * 
 * @author Orlandin Marco
 *
 */
public class CSVRootAction extends CSVAction {
	
	public CSVRootAction(IWorkbenchPart part,String actionId, String value, String actionName){
		super(part,actionId,actionId,value,actionName);
	}
	
	/**
	 * Create the command for the selected action
	 * @param model Model of the selected item
	 * @return the command to execute
	 */
	public Command createCommand(APropertyNode model){
		SetValueCommand cmd = new SetValueCommand();
		//the property is set on the root
		cmd.setTarget((APropertyNode)model.getRoot());
		cmd.setPropertyId(MGraphicElement.PROPERTY_MAP);
		JRPropertiesMap v = (JRPropertiesMap)model.getPropertyValue(MGraphicElement.PROPERTY_MAP);
		Object oldValue = null;
		if (v == null){
			v = new JRPropertiesMap();
		} else {
			oldValue = v.getProperty(attributeId);
			v.removeProperty(attributeId);
		}
		JRStaticText textElement = (JRStaticText)model.getValue(); 
		value = textElement.getText();
		if (value != null  && !value.equals(oldValue)) {
			v.setProperty(attributeId, value);
			removeAttributes(v);
		}
		cmd.setPropertyValue(v);
		return cmd;
	}
}
