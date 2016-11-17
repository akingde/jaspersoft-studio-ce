/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.help.ovverriders;

/**
 * Generic overrider, receive a String to search and a substitution. If an attribute starts with
 * the string to search then its help reference link is the substitution
 * 
 * @author Orlandin Marco
 *
 */
public class GenericOverrider implements IHelpOverrider{
	
	/**
	 * The string to search
	 */
	protected String searchedString;
	
	/**
	 * the substitution
	 */
	protected String substitutionString;

	/**
	 * build the class
	 * 
	 * @param searchedString string used to search the attribute. The Match is done when 
	 * the attribute name starts with this string
	 * @param substitutionString substitution for the link, the substitution is done by concatenating 
	 * the standard prefix with the substitution String
	 */
	public GenericOverrider(String searchedString, String substitutionString){
		this.searchedString = searchedString;
		this.substitutionString = substitutionString;
	}
	
	@Override
	public boolean isOverrided(String propertyName) {
		return propertyName.startsWith(searchedString);
	}

	@Override
	public String getPropertyURL(String propertyName) {
		return PREFIX.concat(substitutionString);
	}

}
