/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
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
