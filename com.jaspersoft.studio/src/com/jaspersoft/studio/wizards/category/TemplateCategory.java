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
package com.jaspersoft.studio.wizards.category;

/**
 * Container for a template category. It contains the category key
 * and the category localized name, that can be provided from the 
 * template bundle of the template
 * 
 * @author Orlandin Marco
 */
public class TemplateCategory {

	/**
	 * The key of the category
	 */
	private String categoryKey;
	
	/**
	 * The localized and human readable name of the category 
	 */
	private String categoryName;
	
	/**
	 * Create the class
	 * 
	 * @param categoryKey The key of the category
	 * @param categoryName The localized and human readable name of the category 
	 */
	public TemplateCategory(String categoryKey, String categoryName){
		this.categoryKey = categoryKey;
		this.categoryName = categoryName;
	}
	
	/**
	 * Return the category key
	 * 
	 * @return a not null string for the category key, typically read from the metadata file of the template
	 */
	public String getCategoryKey() {
		return categoryKey;
	}

	/**
	 * Return the category name
	 * 
	 * @return a not null string for the category name, typically provided by the template bundle as localized string
	 */
	public String getCategoryName() {
		return categoryName;
	}
}
