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

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.base.JRBaseStaticText;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.editor.gef.decorator.csv.NameChooserDialog;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.text.MStaticText;
import com.jaspersoft.studio.model.text.MTextElement;
import com.jaspersoft.studio.property.SetValueCommand;

/**
 * This class implement a CSV action that create a new column in the CSV. This action to 
 * be performed need that half of the element selected are of the type static text. This because
 * the value of these text will be used as the column header
 * 
 * @author Orlandin Marco
 *
 */
public class CSVColDataAction extends CSVAction {
	
		public CSVColDataAction(IWorkbenchPart part, String actionName){
			super(part, CSVAction.COL_DATA, null, actionName);
		}
	
	
	/**
	 * Create the command for the selected action, the static text elements will be use to create the column header and the 
	 * other elements will be used data to fill the columns
	 * @param model Model of the selected item
	 * @return the command to execute
	 */
	public void createCommand(String columnName, APropertyNode columnValue, CompoundCommand commandStack){
		SetValueCommand setColDataCommand = new SetValueCommand();
		//SetValueCommand setColNameCommand = new SetValueCommand();
		//Creation of the column name command
		//setColNameCommand.setTarget(columnName);
		//setColNameCommand.setPropertyId(MGraphicElement.PROPERTY_MAP);
		setColDataCommand.setTarget(columnValue);
		setColDataCommand.setPropertyId(MGraphicElement.PROPERTY_MAP);
		/*JRPropertiesMap colNameMap = (JRPropertiesMap)columnName.getPropertyValue(MGraphicElement.PROPERTY_MAP);
		boolean hasColName = false;	
		if (colNameMap == null){
			colNameMap = new JRPropertiesMap();
		} else {
			hasColName = colNameMap.containsProperty(CSVAction.COL_NAME);
			colNameMap.removeProperty(CSVAction.COL_NAME);
		}*/
		//Read the column value map
		JRPropertiesMap colDataMap = (JRPropertiesMap)columnValue.getPropertyValue(MGraphicElement.PROPERTY_MAP);
		boolean hasColData = false;	
		if (colDataMap == null){
			colDataMap = new JRPropertiesMap();
		} else {
			hasColData = colDataMap.containsProperty(CSVAction.COL_DATA);
			colDataMap.removeProperty(CSVAction.COL_DATA);
			colDataMap.removeProperty(CSVAction.COL_NAME);
		}
		//Set the header and the data only if one of the was not set before
		if (!(hasColData)) {
			//String colNameText = columnName.getPropertyValue(JRBaseStaticText.PROPERTY_TEXT).toString();
			colDataMap.setProperty(CSVAction.COL_DATA, null);
			colDataMap.setProperty(CSVAction.COL_NAME, columnName);
			//colNameMap.setProperty(CSVAction.COL_NAME, colNameText);
		}
		//setColNameCommand.setPropertyValue(colNameMap);
		setColDataCommand.setPropertyValue(colDataMap);
		//commandStack.add(setColNameCommand);
		if (setColDataCommand.canExecute()){
			commandStack.add(setColDataCommand);
			JRPropertiesMap rootMap = (JRPropertiesMap)((APropertyNode)columnValue.getRoot()).getPropertyValue(MGraphicElement.PROPERTY_MAP);
			if (rootMap == null)
				rootMap = new JRPropertiesMap();
			String colNames = rootMap.getProperty(CSVAction.COL_NAMES);
			if (colNames == null) colNames = columnName;
			else colNames.concat(","+columnName);
			SetValueCommand setRootNames = new SetValueCommand();
			//the property is set on the root
			setRootNames.setTarget((APropertyNode)columnValue.getRoot());
			rootMap.setProperty(CSVAction.COL_NAMES, colNames);
			setRootNames.setPropertyId(MGraphicElement.PROPERTY_MAP);
			setRootNames.setPropertyValue(rootMap);
			commandStack.add(setRootNames);
		}
	}
	
	/**
	 * Performs the create action on the selected objects.
	 */
	@Override
	public void run() {
		NameChooserDialog dialog = new NameChooserDialog(Display.getCurrent().getActiveShell());
		int dialogResult = dialog.open();
		if (dialogResult == NameChooserDialog.OK)
			execute(createAlignmentCommand(dialog.getName()));
	}
	
	
	public boolean isChecked() {
		List<?> editparts = getSelectedObjects();
		if (editparts.isEmpty() || !(editparts.get(0) instanceof EditPart)){
			return false;
		}
		ArrayList<MStaticText> columnNames = new ArrayList<MStaticText>();
		ArrayList<APropertyNode> columnValues = new ArrayList<APropertyNode>();
		for (int i = 0; i < editparts.size(); i++) {
			EditPart editpart = (EditPart) editparts.get(i);
			if (editpart.getModel() instanceof MGraphicElement){
				MGraphicElement model = (MGraphicElement)editpart.getModel();
				if (model instanceof MStaticText)
					columnNames.add((MStaticText)model);
				else 
					columnValues.add(model);
			}
		}
		if (columnNames.size() != columnValues.size()) return false;
		for(int i=0; i<columnNames.size(); i++){
			MStaticText columnName = columnNames.get(i);
			APropertyNode columnValue = columnValues.get(i);
			JRPropertiesMap colNameMap = (JRPropertiesMap)columnName.getPropertyValue(MGraphicElement.PROPERTY_MAP);
			JRPropertiesMap colDataMap = (JRPropertiesMap)columnValue.getPropertyValue(MGraphicElement.PROPERTY_MAP);
			boolean hasColName = false;
			if (colNameMap != null) hasColName = colNameMap.containsProperty(CSVAction.COL_NAME);
			boolean hasColData = false; 
			if (colDataMap != null) hasColData = colDataMap.containsProperty(CSVAction.COL_DATA);
			if (!(hasColData && hasColName)) return false;
		}
		return true;
	}
	
	@Override
	protected Command createAlignmentCommand() {
		return createAlignmentCommand("");
	}

	
	/**
	 * Returns the list of editparts which will participate in PDF Editing.
	 * 
	 * @return the list of parts which will be aligned
	 */
	protected Command createAlignmentCommand(String columnName) {
		List<?> editparts = getSelectedObjects();
		if (editparts.isEmpty() || !(editparts.get(0) instanceof EditPart) || editparts.size()>1){
			return null;
		} 
		CompoundCommand command = new CompoundCommand();
		command.setDebugLabel(getText());
		EditPart editpart = (EditPart) editparts.get(0);
		if (editpart.getModel() instanceof MTextElement){
			APropertyNode columnValue = (APropertyNode)editpart.getModel();
			createCommand(columnName, columnValue, command);
		}
		return command;
	}
}
