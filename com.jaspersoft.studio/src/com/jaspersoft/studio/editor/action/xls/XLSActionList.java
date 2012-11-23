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
package com.jaspersoft.studio.editor.action.xls;

import java.util.List;

import net.sf.jasperreports.engine.JRPropertiesMap;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.editor.action.pdf.PropertiesList;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.property.SetValueCommand;

/**
 * This type of action can be viewed as an aggregate of more action, it can set more than on attribute
 * 
 * @author Orlandin Marco
 * 
 */
public class XLSActionList extends SelectionAction {

	/**
	 * The value to set on the attributes
	 */
	private String[] values;

	/**
	 * Ids of the attributes to set
	 */
	private String[] attributeIds;

	public XLSActionList(IWorkbenchPart part, String actionId, String[] attributeIds, String value, String actionName) {
		super(part);
		this.attributeIds = attributeIds;
		setId(actionId);
		initializeValuesArray(value, attributeIds.length);
		// the property need to be registered
		PropertiesList.AddItem(actionId);
		setText(actionName);
	}
	
	public XLSActionList(IWorkbenchPart part, String actionId, String[] attributeIds, String[] values, String actionName) {
		super(part);
		this.attributeIds = attributeIds;
		this.values = values;
		setId(actionId);
		// the property need to be registered
		PropertiesList.AddItem(actionId);
		setText(actionName);
	}
	
	
	/**
	 * Inizialize the values array with a single value
	 * @param value the value to put into the array
	 * @param lenght the lenght of the array
	 */
	private void initializeValuesArray(String value, int lenght){
		values = new String[lenght];
		for(int i=0; i<lenght; i++)
			values[i] = value;
	}

	/**
	 * Create the command for the selected action
	 * 
	 * @param model
	 *          Model of the selected item
	 * @return the command to execute
	 */
	public Command createCommand(MGraphicElement model) {
		SetValueCommand cmd = new SetValueCommand();
		cmd.setTarget(model);
		cmd.setPropertyId(MGraphicElement.PROPERTY_MAP);
		JRPropertiesMap v = (JRPropertiesMap) model.getPropertyValue(MGraphicElement.PROPERTY_MAP);
		if (v == null) {
			v = new JRPropertiesMap();
			for (int i=0; i<attributeIds.length; i++)
				v.setProperty(attributeIds[i], values[i]);
		} else {
			for (int i=0; i<attributeIds.length; i++) {
				String name = attributeIds[i];
				String value = values[i];
				v.removeProperty(name);
				//Add the property only if it has a value
				if (value != null) v.setProperty(name, value);
			}
		}
		cmd.setPropertyValue(v);
		return cmd;
	}

	/**
	 * Performs the create action on the selected objects.
	 */
	@Override
	public void run() {
		execute(createAlignmentCommand());
	}

	/**
	 * Returns the list of editparts which will participate in PDF Editing.
	 * 
	 * @return the list of parts which will be aligned
	 */
	private Command createAlignmentCommand() {
		List<?> editparts = getSelectedObjects();
		if (editparts.isEmpty() || !(editparts.get(0) instanceof GraphicalEditPart)) {
			return null;
		}
		CompoundCommand command = new CompoundCommand();
		command.setDebugLabel(getText());
		for (int i = 0; i < editparts.size(); i++) {
			EditPart editpart = (EditPart) editparts.get(i);
			if (editpart.getModel() instanceof MGraphicElement) {
				command.add(createCommand((MGraphicElement) editpart.getModel()));
			}
		}
		return command;
	}

	@Override
	protected boolean calculateEnabled() {
		Command cmd = createAlignmentCommand();
		if (cmd == null)
			return false;
		return cmd.canExecute();
	}

}
