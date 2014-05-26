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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.type.HorizontalAlignEnum;
import net.sf.jasperreports.engine.type.VerticalAlignEnum;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.toolitems.ISelectionContributionItem;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.SetValueCommand;

/**
 * 
 * This class add the buttons on the toolbar to handle the text alignment inside a text element
 * 
 * 
 * @author Orlandin Marco
 *
 */
public class TextAlignmentContributionItem extends ContributionItem implements ISelectionContributionItem, Listener {

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
	 * Images of the toolbuttons
	 */
	private Image[] images;

	/**
	 * The model of the selected element
	 */
	protected List<APropertyNode> models;
	
	/**
	 * The item that will placed in the toolbar
	 */
	private ToolItem toolitem;
	
	/**
	 * Selection listener that create the right command when a button is pushed
	 */
	private SelectionAdapter buttonPressed = new SelectionAdapter() {
		public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
			createCommand(e.widget.getData());
		};
	};
	
	/**
	 * Create the class and load the buttons images
	 */
	public TextAlignmentContributionItem() {
		images = new Image[] { 
				JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/left_align.gif"),
				JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/center_align.gif"),
				JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/right_align.gif"),
				JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/justified_align.gif"),
				JaspersoftStudioPlugin.getInstance().getImage("icons/resources/formatting/edit-vertical-alignment-top.png"),
				JaspersoftStudioPlugin.getInstance().getImage("icons/resources/formatting/edit-vertical-alignment-middle.png"),
				JaspersoftStudioPlugin.getInstance().getImage("icons/resources/formatting/edit-vertical-alignment.png") };
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
	 * Create the command to change the text alignment
	 * 
	 * @param data a VerticalAlignEnum if it is changed the vertical alignment of the text, 
	 * or an HorizontalAlignEnum if it is changed the horizontal alignment, otherwise it will 
	 * return withoud to nothing
	 */
	protected void createCommand(Object data){
		JSSCompoundCommand changeSizeCommands = new JSSCompoundCommand(null);
		String property = "";
		if (data instanceof VerticalAlignEnum) property = JRBaseStyle.PROPERTY_VERTICAL_ALIGNMENT;
		else if (data instanceof HorizontalAlignEnum) property = JRBaseStyle.PROPERTY_HORIZONTAL_ALIGNMENT;
		else return;
		for (APropertyNode model : models){	
			changeSizeCommands.setReferenceNodeIfNull(model);
			Command c = getChangePropertyCommand(property, data, model);
			if (c != null) {
				changeSizeCommands.add(c);
			}
		}
		CommandStack cs = getCommandStack();
		cs.execute(changeSizeCommands);
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
		models.clear();
		if (selection != null) {
			StructuredSelection ss = (StructuredSelection) selection;
			for (Iterator<Object> it = ss.iterator(); it.hasNext();) {
				Object obj = it.next();
				if (obj instanceof EditPart)
					obj = ((EditPart) obj).getModel();
				if (obj instanceof APropertyNode) {
					models.add((APropertyNode) obj);
				}
			}
		}
		if (models.size()>0){
			buttons = new ToolBar(parent, SWT.FLAT | SWT.WRAP);
			createButtons(buttons);
		}
	}
	
	/**
	 * Create all the buttons for the toolbar
	 * 
	 * @param increment true if the button should be used for increment, false otherwise
	 */
	private void createButtons(ToolBar parent){
		ToolItem alignButton = new ToolItem(parent, SWT.PUSH);
		alignButton.setImage(images[0]);
		alignButton.setData(HorizontalAlignEnum.LEFT);
		alignButton.addSelectionListener(buttonPressed);
		
		alignButton = new ToolItem(parent, SWT.PUSH);
		alignButton.setImage(images[1]);
		alignButton.setData(HorizontalAlignEnum.CENTER);
		alignButton.addSelectionListener(buttonPressed);
		
		alignButton = new ToolItem(parent, SWT.PUSH);
		alignButton.setImage(images[2]);
		alignButton.setData(HorizontalAlignEnum.RIGHT);
		alignButton.addSelectionListener(buttonPressed);
		
		alignButton = new ToolItem(parent, SWT.PUSH);
		alignButton.setImage(images[3]);
		alignButton.setData(HorizontalAlignEnum.JUSTIFIED);
		alignButton.addSelectionListener(buttonPressed);
		
		new ToolItem(parent, SWT.SEPARATOR);
		
		alignButton = new ToolItem(parent, SWT.PUSH);
		alignButton.setImage(images[4]);
		alignButton.setData(VerticalAlignEnum.TOP);
		alignButton.addSelectionListener(buttonPressed);
		
		alignButton = new ToolItem(parent, SWT.PUSH);
		alignButton.setImage(images[5]);
		alignButton.setData(VerticalAlignEnum.MIDDLE);
		alignButton.addSelectionListener(buttonPressed);
		
		alignButton = new ToolItem(parent, SWT.PUSH);
		alignButton.setImage(images[6]);
		alignButton.setData(VerticalAlignEnum.BOTTOM);
		alignButton.addSelectionListener(buttonPressed);
	}
	
	/**
	 * Insert the element inside the toolbar
	 */
	@Override
	public void fill(ToolBar parent, int index) {
		toolitem = new ToolItem(parent, SWT.SEPARATOR, index);
		createControl(parent);
		toolitem.setWidth(175);
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
		models = new ArrayList<APropertyNode>();
	}

	@Override
	public void setWorkbenchPart(IWorkbenchPart workbenchPart) {
		this.workbenchPart = workbenchPart;		
	}

}
