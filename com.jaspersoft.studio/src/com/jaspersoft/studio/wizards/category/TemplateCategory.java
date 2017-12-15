/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
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
