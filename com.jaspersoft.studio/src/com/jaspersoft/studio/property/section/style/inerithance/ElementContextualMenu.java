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
package com.jaspersoft.studio.property.section.style.inerithance;

import java.util.HashMap;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;

/**
 * Open a contextual menu when a button is pressed. This menu offer 
 * the option to reset all the attribute of the actually selected
 * nodes to their default. This menu can be opened from the styles
 * list section
 * 
 * @author Orlandin Marco
 *
 */
public class ElementContextualMenu extends SelectionAdapter {

	/**
	 * The menu
	 */
	private Menu dropMenu = null;
	
	/**
	 * The item to select to reset the attributes of one or more elements
	 */
	private MenuItem resetElementsItem = null;
	
	/**
	 * The parent section
	 */
	private StylesListSection parentSection = null;
	
	/**
	 * Create the SelectionListener to open the menu
	 * 
	 * @param parentSection the parent section
	 */
	public ElementContextualMenu(StylesListSection parentSection){
		this.parentSection = parentSection;
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		Control button = (Control)e.getSource();
		//Create the menu if it wasn't created before
		if (dropMenu == null) {
			dropMenu = new Menu(button);
			resetElementsItem = new MenuItem(dropMenu, SWT.PUSH);
			//Add to the item the listener to reset the selection
			resetElementsItem.addSelectionListener(new SelectionAdapter() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					List<APropertyNode> selectedElements = parentSection.getElements();
					JSSCompoundCommand cc = new JSSCompoundCommand("Reset Elements", selectedElements.get(0)); //$NON-NLS-1$
					for(APropertyNode element : selectedElements){
						createElementNullCommand(cc, element);
					}
					if (!cc.getCommands().isEmpty()) {
						parentSection.executeAndRefresh(cc);
					}
				}
				
			});
		}
		//Make the menu visible if it is not, or hide if it it's necessary (double click on the control)
		if (dropMenu != null && !dropMenu.isDisposed()) {
			if (dropMenu.isVisible()) {
				dropMenu.setVisible(false);
			} else {
				locatePopupMenu(button);
				//Set the right action name based on the number of selected elements
				resetElementsItem.setText(parentSection.getElements().size()>1 ? Messages.ElementContextualMenu_contextualResetPlural : Messages.ElementContextualMenu_econtextualResetSingular);
				dropMenu.setVisible(true);
			}
		}
	}

	/**
	 * Set the menu in the right location, under the button
	 * 
	 * @param menu
	 */
	protected void locatePopupMenu(Control control) {
		Rectangle r;
		r = control.getBounds();
		r.x = r.y = 0;
		Point loc = control.toDisplay(r.x, r.y);
		loc.y += r.height;
		dropMenu.setLocation(loc);
	}
	
	/**
	 * Create the commands to set to null all the styles dependent attribute of an element
	 * and put them inside a container
	 * 
	 * @param cCommand the container of the commands
	 * @param element element to reset to default
	 */
	private void createElementNullCommand(JSSCompoundCommand cCommand, APropertyNode element){
		HashMap<String, Object> descriptors = element.getStylesDescriptors();
		for(String property : descriptors.keySet()){
			Command c = parentSection.generateSetAttributeCommand(element, property);
			if (c != null){
				cCommand.add(c);
			}
		}
	}
	
	/**
	 * Dispose the popup menu opened by this listener if it wasen't already
	 * disposed
	 */
	public void dispose(){
		if (dropMenu != null && !dropMenu.isDisposed()) {
			dropMenu.dispose();
		}
		dropMenu = null;
	}
}
