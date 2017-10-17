/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework;

import net.sf.jasperreports.engine.JRExpression;

/**
 * Item editors can implement this interface in order to provide support for the operations on 
 * element. The editor act as a layer to read and write the property from the element to the 
 * widget. In this way the widget work only with the editor, that can be implemented with the 
 * logic to read and write the value on the element
 * 
 * @author Orlandin Marco
 */
public interface IPropertyEditor {
	
	/**
	 * Gets the value string for the property.
	 * 
	 * @param propertyName the property name
	 * @return the value string if any, <code>null</code> otherwise
	 */
	String getPropertyValue(String propertyName);
	
	/**
	 * Gets the value expression for the property.
	 * 
	 * @param propertyName the property name
	 * @return the value expression if any, <code>null</code> otherwise
	 */
	JRExpression getPropertyValueExpression(String propertyName);
	
	/**
	 * Creates or updates a property with the specified details.
	 * 
	 * @param propertyName the property name
	 * @param value the value string
	 * @param valueExpression the value expression
	 */
	void createUpdateProperty(String propertyName, String value, JRExpression valueExpression);
	
	/**
	 * Removes a property from the existing ones.
	 * 
	 * @param property the property to be removed
	 */
	void removeProperty(String propertyName);
}
