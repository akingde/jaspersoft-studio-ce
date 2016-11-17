/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.style.inerithance;

import java.util.HashMap;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MGraphicElementLineBox;
import com.jaspersoft.studio.model.MLineBox;
import com.jaspersoft.studio.model.text.MTextElement;

/**
 * Open a contextual menu when a button is pressed. This listener can
 * be used to provided additional action in the style list section
 * 
 * @author Orlandin Marco
 *
 */
public abstract class AbstractContextualMenu extends SelectionAdapter {

	/**
	 * The menu
	 */
	private Menu dropMenu = null;
	
	/**
	 * The list of item of the extra actions
	 */
	private List<MenuItem> items = null;
	
	/**
	 * The parent section
	 */
	protected StylesListSection parentSection = null;
	
	/**
	 * Create the SelectionListener to open the menu
	 * 
	 * @param parentSection the parent section
	 */
	public AbstractContextualMenu(StylesListSection parentSection){
		this.parentSection = parentSection;
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		Control button = (Control)e.getSource();
		//Create the menu if it wasn't created before
		if (dropMenu == null) {
			dropMenu = new Menu(button);
			items = getItems(dropMenu);
		}
		//Make the menu visible if it is not, or hide if it it's necessary (double click on the control)
		if (dropMenu != null && !dropMenu.isDisposed()) {
			if (dropMenu.isVisible()) {
				dropMenu.setVisible(false);
			} else {
				locatePopupMenu(button);
				//Update the menu items text if necessary
				for(MenuItem item : items){
					String text = getItemText(item);
					if (text != null) item.setText(text);
				}
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
	 * and put them inside a container. It go down also to the nested elements
	 * 
	 * @param cCommand the container of the commands
	 * @param element element to reset to default
	 */
	protected void createElementNullCommand(JSSCompoundCommand cCommand, APropertyNode element){
		HashMap<String, Object> descriptors = element.getStylesDescriptors();
		for(String property : descriptors.keySet()){
			
			if (property.equals(MGraphicElementLineBox.LINE_BOX)){
				createElementNullCommand(cCommand, (APropertyNode)element.getPropertyValue(MGraphicElementLineBox.LINE_BOX));
			} else if (property.equals(MLineBox.LINE_PEN)){
				createElementNullCommand(cCommand, (APropertyNode)element.getPropertyValue(MLineBox.LINE_PEN));
			} else if (property.equals(MLineBox.LINE_PEN_BOTTOM)){
				createElementNullCommand(cCommand, (APropertyNode)element.getPropertyValue(MLineBox.LINE_PEN_BOTTOM));
			} else if (property.equals(MLineBox.LINE_PEN_LEFT)){
				createElementNullCommand(cCommand, (APropertyNode)element.getPropertyValue(MLineBox.LINE_PEN_LEFT));
			} else if (property.equals(MLineBox.LINE_PEN_RIGHT)){
				createElementNullCommand(cCommand, (APropertyNode)element.getPropertyValue(MLineBox.LINE_PEN_RIGHT));
			} else if (property.equals(MLineBox.LINE_PEN_TOP)){
				createElementNullCommand(cCommand, (APropertyNode)element.getPropertyValue(MLineBox.LINE_PEN_TOP));
			} else if (property.equals(MTextElement.PARAGRAPH)){
				createElementNullCommand(cCommand, (APropertyNode)element.getPropertyValue(MTextElement.PARAGRAPH));
			} else {
				Command c = parentSection.generateSetAttributeCommand(element, property);
				if (c != null){
					cCommand.add(c);
				}
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
		items = null;
	}
	
	/**
	 * Get the label text for a MenuItem, can be used to change the text
	 * of an item before the menu is opened
	 * 
	 * @param item the menu item
	 * @return the text of the menu item or null to leave it unchanged
	 */
	protected String getItemText(MenuItem item){
		return null;
	}
	
	/**
	 * Return the list of MenuItem corresponding to the provided 
	 * additional actions
	 * 
	 * @param parent Menu where the action can be placed
	 * @return a not null list of additional actions
	 */
	protected abstract List<MenuItem> getItems(Menu parent);
}
