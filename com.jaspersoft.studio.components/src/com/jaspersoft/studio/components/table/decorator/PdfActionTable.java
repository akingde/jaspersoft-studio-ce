/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.decorator;

import java.util.List;

import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.editor.action.CustomSelectionAction;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.property.SetValueCommand;

/**
 * The Class PdfActionAbstract implements the write of the property to handle the table AutoTag
 * It is instanced three times to reperesent the status enabled, disabled, default
 */
public class PdfActionTable extends CustomSelectionAction {
		
	/**
	 * The id of the action when it is instanced as default
	 */
	public static final String ID_DEFAULT = "net.sf.jasperreports.components.table.generate.pdf.tags.default"; //$NON-NLS-1$
	
	/**
	 * The id of the action when it is instanced as enabled
	 */	
	public static final String ID_ENABLED = "net.sf.jasperreports.components.table.generate.pdf.tags.enabled"; //$NON-NLS-1$
	
	/**
	 * The id of the action when it is instanced as disabled
	 */
	public static final String ID_DISABLED = "net.sf.jasperreports.components.table.generate.pdf.tags.disabled"; //$NON-NLS-1$
	
	/**
	 * The property of JR this action write
	 */
	public static final String JR_PROPERTY = "net.sf.jasperreports.components.table.generate.pdf.tags"; //$NON-NLS-1$
	
	/**
	 * The possible type of this action 
	 */
	public static enum TYPE{ENABLED,DISABLED,DEFAULT}

	/**
	 * The current type of this action
	 */
	private TYPE status;
	
	public PdfActionTable(IWorkbenchPart part, TYPE status) {
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
		List<Object> graphicalElements = editor.getSelectionCache().getSelectionModelForType(MTable.class);
		if  (graphicalElements.size() > 0) return true;
		graphicalElements = editor.getSelectionCache().getSelectionModelForType(MReport.class);
		return graphicalElements.size() > 0;
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
				Object oldValue = v.getProperty(JR_PROPERTY);
				if (status.equals(TYPE.DEFAULT)){
					if (oldValue != null){
						result = false;
						break;
					}
				} else if (oldValue != null){
					if (status.equals(TYPE.ENABLED)){
						if (!Boolean.TRUE.toString().equalsIgnoreCase(oldValue.toString())){
							result = false;
							break;
						}
					} else if (status.equals(TYPE.DISABLED)){
						if (!Boolean.FALSE.toString().equalsIgnoreCase(oldValue.toString())){
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
		case DEFAULT:
			setId(ID_DEFAULT);
			setText(Messages.PdfActionTable_defaultLabel);
			break;
		case ENABLED:
			setId(ID_ENABLED);
			setText(Messages.PdfActionTable_enabledLabel);
			break;
		case DISABLED:
			setId(ID_DISABLED);
			setText(Messages.PdfActionTable_disabledLabel);
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
		case DEFAULT:
			v.removeProperty(JR_PROPERTY);
			break;
		case ENABLED:
			v.setProperty(JR_PROPERTY, Boolean.TRUE.toString());
			break;
		case DISABLED:
			v.setProperty(JR_PROPERTY, Boolean.FALSE.toString());
			break;
		}
		cmd.setPropertyValue(v);
		return cmd;
	}

	@Override
	protected Command createCommand() {
		List<Object> graphicalElements = editor.getSelectionCache().getSelectionModelForType(MTable.class);
		JSSCompoundCommand command = new JSSCompoundCommand(getText(), null);
		for (Object element : graphicalElements) {
			MGraphicElement grModel = (MGraphicElement) element;
			command.setReferenceNodeIfNull(grModel);
			command.add(createCommand(grModel));
		}
		graphicalElements = editor.getSelectionCache().getSelectionModelForType(MReport.class);
		for (Object element : graphicalElements) {
			MReport grModel = (MReport) element;
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
	
	/**
	 * If the menu is opened on a table the default label is changed to
	 * show what is the current default at report level, if any
	 */
	@Override
	public String getText() {
		if (status.equals(TYPE.DEFAULT)){
			List<Object> nodes = editor.getSelectionCache().getSelectionModelForType(MTable.class);
			for(Object node : nodes){
				APropertyNode aNode = (APropertyNode)node;
				JasperDesign jd = aNode.getJasperDesign();
				if(jd != null && jd.getPropertiesMap() != null){
					Object value = jd.getPropertiesMap().getProperty(JR_PROPERTY);
					if (value == null){
						break;
					} else {
						if (Boolean.TRUE.toString().equalsIgnoreCase(value.toString())){
							return super.getText() + " " + Messages.PdfActionTable_enableInheritLabel;
						} else {
							return super.getText() + " " + Messages.PdfActionTable_disableInheritLabel;
						}
					}
				}
			}
		}
		return super.getText();
	}

}
