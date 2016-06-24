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
package com.jaspersoft.studio.property.section.graphic.borders;

import com.jaspersoft.studio.property.section.graphic.borders.LineBoxDrawer.Location;

/**
 * The selection event of a border
 * 
 * @author Orlandin Marco
 *
 */
public class BorderSelectionEvent {

	/**
	 * The location of the selected border
	 */
	private Location clickedBorder;
	
	/**
	 * Flag to know if the border was selected or deselected
	 */
	private boolean selected;
	
	/**
	 * Create the event
	 * 
	 * @param clickedBorder the location of the clicked border
	 * @param selected true if the border was selected, false otherwise
	 */
	public BorderSelectionEvent(Location clickedBorder, boolean selected){
		this.clickedBorder = clickedBorder;
		this.selected = selected;
	}
	
	/**
	 * Check if the border was selected or deselected
	 * 
	 * @return true if the border was selected, false otherwise
	 */
	public boolean isSelected(){
		return selected;
	}
	
	/**
	 * Return the selected border
	 * 
	 * @return the location of the clicked border
	 */
	public Location getClickedBorder(){
		return clickedBorder;
	}
}
