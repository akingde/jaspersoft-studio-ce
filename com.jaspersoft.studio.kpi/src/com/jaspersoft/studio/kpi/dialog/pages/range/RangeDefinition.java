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
package com.jaspersoft.studio.kpi.dialog.pages.range;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.runtime.Assert;

/**
 * Container with the informations for a KPI range
 * 
 * @author Orlandin Marco
 *
 */
public class RangeDefinition{
	
	/**
	 * Hashmap to associate a color to a range type
	 */
	private static final HashMap<String, Color> colorTypesMap = new HashMap<String, Color>();
	
	/**
	 * List of all the embedded range types
	 */
	private static final List<String> availableTypes = new ArrayList<String>();
	
	/**
	 * Initialize the range types with a paired color
	 */
	static{
		availableTypes.add("good");
		colorTypesMap.put(availableTypes.get(availableTypes.size()-1), Color.GREEN);
		availableTypes.add("normal");
		colorTypesMap.put(availableTypes.get(availableTypes.size()-1), Color.BLACK);
		availableTypes.add("bad");
		colorTypesMap.put(availableTypes.get(availableTypes.size()-1), Color.RED);
	}
	
	/**
	 * The start value of the range
	 */
	private int min;
	
	/**
	 * The end value of the range
	 */
	private int max;
	
	/**
	 * The type of the range, should be one of those inside the list returned using
	 * the static method {@link #getNames()}
	 */
	private String type;
	
	/**
	 * Define a new container for the range
	 * 
	 * @param min The start value of the range
	 * @param max The end value of the range
	 * @param type The type of the range, should be one of those inside the list returned using
	 * the static method {@link #getNames()}. Must be not null however
	 */
	public RangeDefinition(int min, int max, String type){
		Assert.isNotNull(type);
		this.min = min;
		this.max = max;
		this.type = type;
	}

	/**
	 * Return the from value of the range
	 * 
	 * @return an integer
	 */
	public int getMin() {
		return min;
	}

	/**
	 * Return the to value of the range
	 * 
	 * @return an integer
	 */
	public int getMax() {
		return max;
	}
	
	/**
	 * return The type of the range, should be one of those inside the list returned using
	 * the static method {@link #getNames()}.
	 * 
	 * @return a not null string
	 */
	public String getType(){
		return type;
	}
	
	/**
	 * Return a list of the default range types. At the moment
	 * they are good, bad and normal
	 * 
	 * @return a not null list of types
	 */
	public static List<String> getNames(){
		return availableTypes;
	}
}