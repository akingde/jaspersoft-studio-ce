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
package com.jaspersoft.studio.components.list.decorator;

import java.util.List;

import net.sf.jasperreports.engine.JRPropertiesMap;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.components.list.model.MList;
import com.jaspersoft.studio.editor.action.CustomSelectionAction;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.property.SetValueCommand;

/**
 * The Class PdfActionAbstract implements the write of the property to handle the table AutoTag
 * It is instanced three times to represent the status enabled, disabled, default
 */
public class PdfActionList extends CustomSelectionAction {
		
	/**
	 * The id of the action when it is instanced as full
	 */
	public static final String ID_FULL = "net.sf.jasperreports.components.list.generate.pdf.tags.full"; //$NON-NLS-1$
	
	/**
	 * The id of the action when it is instanced as start
	 */	
	public static final String ID_START = "net.sf.jasperreports.components.list.generate.pdf.tags.start"; //$NON-NLS-1$
	
	/**
	 * The id of the action when it is instanced as end
	 */
	public static final String ID_END = "net.sf.jasperreports.components.list.generate.pdf.tags.end"; //$NON-NLS-1$
	
	/**
	 * The id of the action when it is instanced as disabled
	 */
	public static final String ID_NONE = "net.sf.jasperreports.components.list.generate.pdf.tags.none"; //$NON-NLS-1$
	
	/**
	 * The property of JR this action write
	 */
	public static final String JR_PROPERTY = "net.sf.jasperreports.export.pdf.tag.l"; //$NON-NLS-1$
	
	/**
	 * The possible type of this action 
	 */
	public static enum TYPE{FULL,START,END, NONE}

	/**
	 * The current type of this action
	 */
	private TYPE status;
	
	public PdfActionList(IWorkbenchPart part, TYPE status) {
		super(part, IAction.AS_CHECK_BOX);
		this.status = status;
		initUI();
	}

	/**
	 * Calculate if the action is enabled
	 * 
	 * @return check if a report or a table are selected
	 */
	@Override
	protected boolean calculateEnabled() {
		List<Object> graphicalElements = editor.getSelectionCache().getSelectionModelForType(MList.class);
		return  (graphicalElements.size() > 0);
	}
	
	@Override
	public boolean isChecked() {
		List<Object> graphicalElements = editor.getSelectionCache().getSelectionModelForType(APropertyNode.class);
		boolean result = true;
		if (graphicalElements.isEmpty()) {
			return false;
		} else {
			for (Object element : graphicalElements) {
				APropertyNode model = (APropertyNode) element;
				JRPropertiesMap v = (JRPropertiesMap) model.getPropertyValue(MGraphicElement.PROPERTY_MAP);
				if (v == null) return false;
				Object oldValue = v.getProperty(getProperty());
				if (status.equals(TYPE.NONE)){
					result = false;
					break;
				} else if (oldValue != null){
					if (status.equals(TYPE.FULL)){
						if (!oldValue.toString().equalsIgnoreCase("full")){
							result = false;
							break;
						}
					} else if (status.equals(TYPE.START)){
						if (!oldValue.toString().equalsIgnoreCase("start")){
							result = false;
							break;
						}
					} else if (status.equals(TYPE.END)){
						if (!oldValue.toString().equalsIgnoreCase("end")){
							result = false;
							break;
						}
					}
				} else {
					result = false;
					break;
				}
			}
		}
		return result;
	}

	/**
	 * Create the contextual menu with the label
	 */
	protected void initUI() {
		setToolTipText(null);
		setImageDescriptor(null); //$NON-NLS-1$
		setDisabledImageDescriptor(null); //$NON-NLS-1$
		
		switch (status) {
		case END:
			setId(getIdEnd());
			setText("End");
			break;
		case FULL:
			setId(getIdFull());
			setText("Full");
			break;
		case START:
			setId(getIdStart());
			setText("Start");
			break;
		case NONE:
			setId(getIdNone());
			setText("None");
			break;
		}
	}

	/**
	 * Create the command for the selected action
	 * 
	 * @param model
	 *          Model of the selected item
	 * @return the command to execute
	 */
	public Command createCommand(APropertyNode model) {
		SetValueCommand cmd = new SetValueCommand();
		cmd.setTarget(model);
		cmd.setPropertyId(MGraphicElement.PROPERTY_MAP);
		JRPropertiesMap v = (JRPropertiesMap) model.getPropertyValue(MGraphicElement.PROPERTY_MAP);
		if (v == null) {
			v = new JRPropertiesMap();
		} 
		switch(status){
		case NONE:
			v.removeProperty(getProperty());
			break;
		case FULL:
			v.setProperty(getProperty(), "full");
			break;
		case START:
			v.setProperty(getProperty(), "start");
			break;
		case END:
			v.setProperty(getProperty(), "end");
			break;
		}
		cmd.setPropertyValue(v);
		return cmd;
	}

	@Override
	protected Command createCommand() {
		List<Object> graphicalElements = editor.getSelectionCache().getSelectionModelForType(MList.class);
		JSSCompoundCommand command = new JSSCompoundCommand(getText(), null);
		for (Object element : graphicalElements) {
			MGraphicElement grModel = (MGraphicElement) element;
			command.setReferenceNodeIfNull(grModel);
			command.add(createCommand(grModel));
		}
		return command;
	}

	/**
	 * Performs the create action on the selected objects.
	 */
	public void run() {
		execute(createCommand());
	}
	
	protected String getProperty(){
		return JR_PROPERTY;
	}
	
	protected String getIdNone(){
		return ID_NONE;
	}
	
	protected String getIdFull(){
		return ID_FULL;
	}
	
	protected String getIdEnd(){
		return ID_END;
	}
	
	protected String getIdStart(){
		return ID_START;
	}
}
