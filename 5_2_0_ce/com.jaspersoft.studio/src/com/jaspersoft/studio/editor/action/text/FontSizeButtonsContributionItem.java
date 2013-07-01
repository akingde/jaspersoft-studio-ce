/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.editor.action.text;

import java.text.MessageFormat;
import java.util.Iterator;

import net.sf.jasperreports.engine.base.JRBaseFont;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.toolitems.ISelectionContributionItem;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.SetValueCommand;

/**
 * 
 * This class add two buttons in the toolbar to increment and decrement the 
 * size of the font
 * 
 * 
 * @author Orlandin Marco
 *
 */
public class FontSizeButtonsContributionItem extends ContributionItem implements ISelectionContributionItem, Listener {

	/**
	 * The workbench
	 */
	private IWorkbenchPart workbenchPart;
	
	/**
	 * The selected element
	 */
	private ISelection selection;
	
	/**
	 * The buttons toolbar
	 */
	private ToolBar buttons;
	
	/**
	 * The image for the button of increment
	 */
	private Image imageValueIncrement;
	
	/**
	 * The image for the button of decrement
	 */
	private Image imageValueDecrement;
	
	/**
	 * % factor for the increment\decrement
	 */
	public static Integer factor = 10;
	
	/**
	 * Tooltip message for the increment button
	 */
	private String messageIncrement;
	
	/**
	 * The item that will placed in the toolbar
	 */
	private ToolItem toolitem;
	
	/**
	 * Tooltip message for the decrement button
	 */
	private String messageDecrement;

	/**
	 * The model of the selected element
	 */
	protected APropertyNode model;
	
	
	public FontSizeButtonsContributionItem() {
		messageIncrement = MessageFormat.format(Messages.SPButon_Size_Increment, new Object[]{factor.toString()});
		imageValueIncrement = JaspersoftStudioPlugin.getInstance().getImage("/icons/resources/edit-size-up.png"); 
		messageDecrement = MessageFormat.format(Messages.SPButon_Size_Decrement, new Object[]{factor.toString()}); 
		imageValueDecrement = JaspersoftStudioPlugin.getInstance().getImage("/icons/resources/edit-size-down.png"); 
	}
	
	/**
	 * Create a command to change a property. The change is done if the new value of the property
	 * is different from its previous value
	 * @param property the property to change
	 * @param newValue the new value for the property
	 * @param n the element to modify
	 * @return the command
	 */
	protected Command getChangePropertyCommand(Object property, Object newValue, APropertyNode n) {
		Object oldValue = n.getPropertyValue(property);
		if (((oldValue == null && newValue != null) || (oldValue != null && newValue == null) || (newValue != null && !newValue
				.equals(oldValue))) && workbenchPart!= null) {
			SetValueCommand setCommand = new SetValueCommand(n.getDisplayText());
			setCommand.setTarget(n);
			setCommand.setPropertyId(property);
			setCommand.setPropertyValue(newValue);
			return setCommand;
		}
		return null;
	}
	
	/**
	 * Create the command to change the font size
	 * 
	 * @param increment true if you want to increment the font, false otherwise
	 */
	protected void createCommand(boolean increment){
		Object fontSize = model.getPropertyValue(JRBaseFont.PROPERTY_FONT_SIZE);
		if (fontSize.equals("")) fontSize = model.getPropertyActualValue(JRBaseFont.PROPERTY_FONT_SIZE);
		Integer newValue = 2;
		if (fontSize != null && fontSize.toString().length()>0){
			newValue = Integer.valueOf(fontSize.toString());
			Integer plus = null;
			if (increment) plus = Math.round((new Float(newValue) / 100)*factor)+1;
			else plus =  Math.round((new Float(newValue) / 100)*-factor)-1;
			if ((newValue+plus)>99) newValue = 99;
			else if ((newValue+plus)>0) newValue += plus;
			
			Command c = getChangePropertyCommand(JRBaseFont.PROPERTY_FONT_SIZE, newValue.toString(), model);
			if (c != null) {
				CommandStack cs = getCommandStack();
				cs.execute(c);
			}
		}
	}
	
	/**
	 * 
	 * Crate the  control 
	 * 
	 * @param parent
	 * @return a composite with a label and the combo preview inside
	 */
	@SuppressWarnings("unchecked")
	protected void createControl(Composite parent) {
		if (selection != null) {
			StructuredSelection ss = (StructuredSelection) selection;
			for (Iterator<Object> it = ss.iterator(); it.hasNext();) {
				Object obj = it.next();
				if (obj instanceof EditPart)
					obj = ((EditPart) obj).getModel();
				if (obj instanceof APropertyNode) {
					model = (APropertyNode) obj;
				}
			}
		}
		if (model != null){
			buttons = new ToolBar(parent, SWT.FLAT | SWT.WRAP);
			createButton(true);
			createButton(false);
		}
	}
	
	/**
	 * Create a single button into the toolbar
	 * 
	 * @param increment true if the button should be used for increment, false otherwise
	 */
	private void createButton(final boolean increment){
		Image imageValue;
		String message;
		if (increment){
			imageValue = imageValueIncrement;
			message = messageIncrement;
		} else {
			imageValue = imageValueDecrement;
			message = messageDecrement;
		}
		ToolItem button = new ToolItem(buttons, SWT.PUSH);
		button.setImage(imageValue);
		button.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				createCommand(increment);
			}

		});
		button.setToolTipText(message);		
	}
	
	/**
	 * Insert the element inside the toolbar
	 */
	@Override
	public void fill(ToolBar parent, int index) {
		toolitem = new ToolItem(parent, SWT.SEPARATOR, index);
		createControl(parent);
		toolitem.setWidth(50);
		toolitem.setControl(buttons);
	}
	
	
	/**
	 * Returns the editor's command stack. This is done by asking the workbench part for its CommandStack via
	 * {@link org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)}.
	 * 
	 * @return the command stack
	 */
	protected CommandStack getCommandStack() {
		return (CommandStack) workbenchPart.getAdapter(CommandStack.class);
	}
	
	@Override
	public void handleEvent(Event event) {
	}

	@Override
	public void setSelection(ISelection selection) {
		this.selection = selection;
		model = null;
	}

	@Override
	public void setWorkbenchPart(IWorkbenchPart workbenchPart) {
		this.workbenchPart = workbenchPart;		
	}

}
