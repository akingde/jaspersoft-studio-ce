/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.style.inerithance;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.model.APropertyNode;

/**
 * Class to manage the events of the mouse click on the delete button, used to delete
 * an attribute, on an attribute of the selected element
 * 
 * @author Orlandin Marco
 * 
 */
public class ElementClickListener extends MouseAdapter{

	/**
	 * Key of the attribute to remove
	 */
	private String property;
	
	/**
	 * The parent section
	 */
	private StylesListSection parentSection;
	
	/**
	 * 
	 * Create the handler for the delete button on the StylesListSection for the attribute
	 * of the selected element
	 * 
	 * @param property Key of the attribute to remove
	 * @param parentSection The parent section
	 */
	public ElementClickListener(String property, StylesListSection parentSection) {
		this.property = property;
		this.parentSection = parentSection;
	}
	
	/**
	 * Resolve a full path of an attribute to set its values also if it is a nested element
	 * 
	 * @param baseElement the upper level element
	 * @param fullProperty the attribute to set with it's path
	 * @return the element where the last segment of the attribute must be set
	 */
	public static APropertyNode getRealElement(APropertyNode baseElement,String fullProperty){
		String[] properties = fullProperty.split("\\.");
		APropertyNode element = baseElement;
		for(int i=0; i<properties.length-1; i++){
			element = (APropertyNode)element.getPropertyValue(properties[i]);
		}
		return element;
	}

	/**
	 * Set the property of the element binded to this event to null, using the manipulation commands (so the operation
	 * can be undone)
	 */
	public void mouseUp(MouseEvent e) {
		List<APropertyNode> selectedElements = parentSection.getElements();
		JSSCompoundCommand cc = new JSSCompoundCommand("Set " + property, selectedElements.get(0)); //$NON-NLS-1$
		for (APropertyNode targetElement : selectedElements) {
			String propertyName = property;
			int lastSegment = propertyName.lastIndexOf(".");
			if (lastSegment != -1) {
				propertyName = propertyName.substring(lastSegment + 1);
			}
			Command c = parentSection.generateSetAttributeCommand(getRealElement(targetElement, property), propertyName);
			if (c != null)
				cc.add(c);
		}
		if (!cc.getCommands().isEmpty()) {
			parentSection.executeAndRefresh(cc);
		}
	}
}
