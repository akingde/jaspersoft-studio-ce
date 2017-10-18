/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.customadapters;

import net.sf.jasperreports.data.DataAdapter;

/**
 * Interface to implement to define a custom handler to set and get the properties
 * of a Configurable data adapter, without using the reflection 
 * 
 * @author Orlandin Marco
 *
 */
public interface IAdapterPropertyHandler {
	
	/**
	 * Return the value of a property of the data adapter
	 * 
	 * @param propertyName the name of the property
	 * @param adapter the adapter from where the property is read
	 * @return the value defined as string
	 */
	public String getPropertyValue(String propertyName, DataAdapter adapter);
	
	/**
	 * Set the value of a property of the data adapter 
	 * 
	 * @param propertyName the name of the property to set
	 * @param value the value of the property, as string
	 * @param adapter the {@link DataAdapter} where the property is set
	 */
	public void setPropertyValue(String propertyName, String value, DataAdapter adapter);
	
	/**
	 * Set to null the property of a {@link DataAdapter}
	 * 
	 * @param propertyName the name of the property
	 * @param adapter {@link DataAdapter} where the property is set
	 */
	public void removeProperty(String propertyName, DataAdapter adapter);
	
	/**
	 * Return if the current handler support the get/set of a specific property
	 * 
	 * @param propertyName the name of the property
	 * @param adapter the {@link DataAdapter} where the property is get or set
	 * @return true if this handler will handle the set/get of the property, false otherwise
	 */
	public boolean isSupportedProperty(String propertyName, DataAdapter adapter);
	
}
