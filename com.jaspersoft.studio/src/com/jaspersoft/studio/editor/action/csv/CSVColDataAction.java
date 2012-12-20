package com.jaspersoft.studio.editor.action.csv;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.base.JRBaseStaticText;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.text.MStaticText;
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
	public void createCommand(MStaticText columnName, APropertyNode columnValue, CompoundCommand commandStack){
		SetValueCommand setColDataCommand = new SetValueCommand();
		SetValueCommand setColNameCommand = new SetValueCommand();
		//Creation of the column name command
		setColNameCommand.setTarget(columnName);
		setColNameCommand.setPropertyId(MGraphicElement.PROPERTY_MAP);
		setColDataCommand.setTarget(columnValue);
		setColDataCommand.setPropertyId(MGraphicElement.PROPERTY_MAP);
		JRPropertiesMap colNameMap = (JRPropertiesMap)columnName.getPropertyValue(MGraphicElement.PROPERTY_MAP);
		boolean hasColName = false;	
		if (colNameMap == null){
			colNameMap = new JRPropertiesMap();
		} else {
			hasColName = colNameMap.containsProperty(CSVAction.COL_NAME);
			colNameMap.removeProperty(CSVAction.COL_NAME);
		}
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
		if (!(hasColData && hasColName)) {
			String colNameText = columnName.getPropertyValue(JRBaseStaticText.PROPERTY_TEXT).toString();
			colDataMap.setProperty(CSVAction.COL_DATA, null);
			colDataMap.setProperty(CSVAction.COL_NAME, colNameText);
			colNameMap.setProperty(CSVAction.COL_NAME, colNameText);
		}
		setColNameCommand.setPropertyValue(colNameMap);
		setColDataCommand.setPropertyValue(colDataMap);
		commandStack.add(setColNameCommand);
		commandStack.add(setColDataCommand);
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

	
	/**
	 * Returns the list of editparts which will participate in PDF Editing.
	 * 
	 * @return the list of parts which will be aligned
	 */
	@Override
	protected Command createAlignmentCommand() {
		List<?> editparts = getSelectedObjects();
		ArrayList<MStaticText> columnNames = new ArrayList<MStaticText>();
		ArrayList<APropertyNode> columnValues = new ArrayList<APropertyNode>();
		if (editparts.isEmpty() || !(editparts.get(0) instanceof EditPart)){
			return null;
		} 
		CompoundCommand command = new CompoundCommand();
		command.setDebugLabel(getText());
		for (int i = 0; i < editparts.size(); i++) {
			EditPart editpart = (EditPart) editparts.get(i);
			if (editpart.getModel() instanceof APropertyNode){
				if (editpart.getModel() instanceof MStaticText)
					columnNames.add((MStaticText)editpart.getModel());
				else 
					columnValues.add((APropertyNode)editpart.getModel());
			}
		}
		if (columnNames.size() == columnValues.size()){
			for(int i=0; i<columnNames.size(); i++){
				MStaticText columnName = columnNames.get(i);
				APropertyNode columnValue = columnValues.get(i);
				createCommand(columnName, columnValue, command);
			}
		}
		return command;
	}
}
