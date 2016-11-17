/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.properties.internal;

/**
 * 
 * Class that contains all the properties selectable 
 * for a specific type of item
 * 
 * @author Orlandin Marco
 *
 */
public class PropertiesContainer{
	
	private PropertyContainer[] properties;
	
	/**
	 * Create an instance of the class. The length of the two array must be the same
	 * since every properties name must have its id on the same position in of the 
	 * second array
	 * 
	 * @param labels list of the properties name available
	 * @param ids list of ids associated with the properties name
	 */
	public PropertiesContainer(PropertyContainer[] properties){
		this.properties = properties;
	}
	
	/**
	 * Build an empty container with no properties inside
	 */
	public PropertiesContainer(){
		this.properties = new PropertyContainer[0];
	}
	
	/**
	 * Return the number of properties
	 * 
	 * @return number of properties
	 */
	public int getSize(){
		//Its assumed that ids and labels have the same size
		return properties.length;
	}
	
	/**
	 * Return the array of stored labels
	 * 
	 * @return an array of string
	 */
	public PropertyContainer[] getPrperties(){
		return properties;
	}
}
