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
	
	/**
	 * List of the properties name
	 */
	private String[] labels;
	
	/**
	 * List of the property ids associated 1:1 with the labels, must have the same size of labels
	 */
	private Object[] ids;
	
	/**
	 * Create an instance of the class. The length of the two array must be the same
	 * since every properties name must have its id on the same position in of the 
	 * second array
	 * 
	 * @param labels list of the properties name available
	 * @param ids list of ids associated with the properties name
	 */
	public PropertiesContainer(String[] labels, Object[] ids){
		this.labels = labels;
		this.ids = ids;
		assert ids.length == labels.length : "There must be the same number of properties names and associated ids";
	}
	
	/**
	 * Return the number of properties
	 * 
	 * @return number of properties
	 */
	public int getSize(){
		//Its assumed that ids and labels have the same size
		return ids.length;
	}
	
	/**
	 * Return the array of stored labels
	 * 
	 * @return an array of string
	 */
	public String[] getLabels(){
		return labels;
	}
	
	/**
	 * Return the array of stored ids
	 * 
	 * @return an array of object
	 */
	public Object[] getIds(){
		return ids;
	}
}