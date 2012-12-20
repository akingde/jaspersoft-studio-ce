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

import java.util.List;

import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JRStaticText;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.editor.action.pdf.PropertiesList;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.property.SetValueCommand;

/**
 * Action used to set an CSV attribute
 * @author Orlandin Marco
 *
 */
public class CSVAction extends SelectionAction{
	
	/** Embedded attributes ids*/
	public static String COL_NAME = "net.sf.jasperreports.export.csv.column.name";
	
	public static String COL_DATA = "net.sf.jasperreports.export.csv.data";
	
	public static String FIELD_DELIMITER = "net.sf.jasperreports.export.csv.field.delimiter";
	
	public static String RECORD_DELIMITER = "net.sf.jasperreports.export.csv.record.delimiter";
	
	protected String value;
	
	protected String attributeId;
	
	private String[] attributeToRemove;
	
	public CSVAction(IWorkbenchPart part,String actionId, String value, String actionName){
		this(part,actionId,actionId,value,actionName);
	}
	
	public CSVAction(IWorkbenchPart part,String actionId, String attributeId, String value, String actionName){
		super(part, IAction.AS_CHECK_BOX);
		setId(actionId);
		this.attributeId = attributeId;
		this.value = value;
		//the property need to be registered
		PropertiesList.AddItem(actionId);
		setText(actionName);
		attributeToRemove = null;
	}
	
	/**
	 * Uses the attribute to remove parameter to define the attribute that should be removed when the attributeId is set. This is 
	 * done to define attribute mutually exclusives with the others
	 */
	public 	CSVAction(IWorkbenchPart part,String actionId, String attributeId, String value, String actionName, String[] attributeToRemove){
		this(part,actionId,actionId,value,actionName);
		this.attributeToRemove = attributeToRemove;
	}
	
	public boolean isChecked() {
		List<?> editparts = getSelectedObjects();
		if (editparts.isEmpty() || !(editparts.get(0) instanceof EditPart)){
			return false;
		} 
		for (int i = 0; i < editparts.size(); i++) {
			EditPart editpart = (EditPart) editparts.get(i);
			if (editpart.getModel() instanceof MGraphicElement){
				MGraphicElement model = (MGraphicElement)editpart.getModel();
				JRPropertiesMap v = (JRPropertiesMap)model.getPropertyValue(MGraphicElement.PROPERTY_MAP);
				if (v == null) return false;
				else {
					 Object oldValue = v.getProperty(attributeId);
					 if (oldValue == null || !oldValue.equals(value)) return false;
				}
			}
		}
		return true;
	}

	/**
	 * Remove from the property map all the attributes in the attributeToRemove array
	 * @param map
	 */
	protected void removeAttributes(JRPropertiesMap map){
		if (attributeToRemove != null){
			for(String attributeName : attributeToRemove)
				map.removeProperty(attributeName);
		}
	}
	
	/**
	 * Create the command for the selected action
	 * @param model Model of the selected item
	 * @return the command to execute
	 */
	public Command createCommand(APropertyNode model){
		SetValueCommand cmd = new SetValueCommand();
		cmd.setTarget(model);
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
	protected Command createAlignmentCommand() {
		List<?> editparts = getSelectedObjects();
		if (editparts.isEmpty() || !(editparts.get(0) instanceof EditPart)){
			return null;
		} 
		CompoundCommand command = new CompoundCommand();
		command.setDebugLabel(getText());
		for (int i = 0; i < editparts.size(); i++) {
			EditPart editpart = (EditPart) editparts.get(i);
			if (editpart.getModel() instanceof APropertyNode){
				APropertyNode model = (APropertyNode)editpart.getModel(); 
				if (model.getValue() instanceof JRStaticText)
					command.add(createCommand(model));
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
