/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.properties.internal;

import java.util.List;

/**
 * This interface must be implemented by a section if it expose the handled properties.
 * In this way the section can be inspected to search a specific property
 * 
 * @author Orlandin Marco
 *
 */
public interface IWidgetsProviderSection {
	
	/**
	 * Return the actually selected element
	 * 
	 */
	public Object getSelectedElement();
	
	/**
	 * Return a list of all the properties managed by the section
	 * 
	 * @return a not null list of all the property ids of the managed properties
	 */
	public List<Object> getHandledProperties();
	
	/**
	 * Return a widget associated with a specific property id, the widget must implement
	 * the interface to be highlighted
	 * 
	 * @param propertyId the id of the property
	 * @return the widget to hightlight, can be null
	 */
	public IHighlightPropertyWidget getWidgetForProperty(Object propertyId);
	
	/**
	 * Return the descriptor for a specific widget
	 * 
	 * @param propertyId id of the property associated to the widget
	 * @return descriptor for the widget, could be null
	 */
	public WidgetDescriptor getPropertyInfo(Object propertyId);
	
	/**
	 * Since a section could have its element not visible (for example because 
	 * they are hidden into and expandable composite), this method is called 
	 * to make and element with a specific id visible. In the case of a expandable
	 * composite this means to expand it
	 * 
	 * @param propertyId the id of the element to unhide, if it is hidden
	 */
	public void expandForProperty(Object propertyId);
}
