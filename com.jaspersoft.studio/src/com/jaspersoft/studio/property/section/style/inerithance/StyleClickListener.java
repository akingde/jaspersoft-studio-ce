/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.style.inerithance;

import net.sf.jasperreports.engine.JRStyle;

import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.MessageBox;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.editor.gef.parts.EditableFigureEditPart;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;

/**
 * Class to manage the events of the mouse click on the delete button, used to delete
 * an attribute, on an attribute of a style in the element hierarchy (both internal or external)
 * 
 * @author Orlandin Marco
 * 
 */
public class StyleClickListener extends MouseAdapter {

	/**
	 * Element binded to this event
	 */
	private APropertyNode targetElement;

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
	 * of a style
	 * 
	 * @param styleElement the style node
	 * @param property Key of the attribute to remove
	 * @param parentSection The parent section
	 */
	public StyleClickListener(APropertyNode styleElement, String property, StylesListSection parentSection) {
		this.targetElement = styleElement;
		this.property = property;
		this.parentSection = parentSection;
	}

	/**
	 * Set the property of the style binded to this event to null, using the manipulation commands (so the operation
	 * can be undone)
	 */
	public void mouseUp(MouseEvent e) {
		//External section
		if (!targetElement.isEditable()) {
			MessageBox messageBox = new MessageBox(e.widget.getDisplay().getActiveShell(), SWT.ICON_QUESTION | SWT.YES| SWT.NO);
			messageBox.setText(Messages.StylesListSection_NotEditable_Title);
			messageBox.setMessage(Messages.StylesListSection_NotEditable_Message);
			int response = messageBox.open();
			//Check if the external style mus be opened into an external editor
			if (response == SWT.YES) {
				StyleContainer styleReference = parentSection.styleMaps.get(((JRStyle) targetElement.getValue()).getName());
				DefaultEditDomain domain = (DefaultEditDomain) parentSection.getEditDomain();
				EditableFigureEditPart.openEditor(styleReference.getTemplateValue(), domain.getEditorPart(), styleReference.getTemplate());
			}
		} else {
			//Ask to remove the attribute from the style
			MessageBox messageBox = new MessageBox(e.widget.getDisplay().getActiveShell(), SWT.ICON_WARNING | SWT.YES| SWT.NO);
			messageBox.setText(Messages.StylesListSection_Warining_Box_Title);
			messageBox.setMessage(Messages.StylesListSection_Warning_Box_Message);
			int buttonID = messageBox.open();
			if (buttonID == SWT.YES){
				JSSCompoundCommand cc = new JSSCompoundCommand("Set " + property, targetElement);
				String propertyName = property;
				int lastSegment = propertyName.lastIndexOf(".");
				if (lastSegment != -1) {
					propertyName = propertyName.substring(lastSegment + 1);
				}
				Command c = parentSection.generateSetAttributeCommand(ElementClickListener.getRealElement(targetElement, property), propertyName);
				if (c != null) cc.add(c);
				if (!cc.getCommands().isEmpty()) {
					parentSection.executeAndRefresh(cc);
				}
			}
		}
	}
}
