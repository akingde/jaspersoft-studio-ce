/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model;

import java.util.HashSet;

import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Class used to support the refresh system of the elements only when 
 * one of its graphical property is changed. The hasChangedProperty method is used 
 * to know when an element must be refreshed and it can be set. It provide the method
 * to ask the element which are its graphical properties it uses.
 * 
 * @author Orlandin Marco
 *
 */
public interface IGraphicalPropertiesHandler {

	/**
	 * Set the actual state of the property change flag
	 */
	public void setChangedProperty(boolean value);

	/**
	 * Set the actual state of the property change flag, but the changes was requested by the style.
	 * When something change in a style it notify to all the elements using it that it is changed.
	 * This elements then could do the correct action to refresh
	 */
	public void setStyleChangedProperty();
	
	/**
	 * True if some graphical property is changed for the element, false otherwise
	 */
	public boolean hasChangedProperty();
	
	/**
	 * Return the graphical property for the actual type of element. If the are stored 
	 * inside the cache then the cached version is returned. Otherwise they are calculated,
	 * cached an returned 
	 * 
	 * @return an hashset of string that contains the graphical properties of the actual type of element. 
	 * The graphical properties of an element are those properties that affect the appearance of an element
	 * when changed
	 */
	public HashSet<String> getGraphicalProperties();
	
	/**
	 * Return the absolute position of the element inside the report
	 * 
	 * @return a not null rectangle
	 */
	public Rectangle getAbsoluteBounds();
}
