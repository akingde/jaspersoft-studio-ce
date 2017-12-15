/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.help.ovverriders;

import java.util.Collections;
import java.util.List;

/**
 * This overrider use one or more regular expressions to identify the attributes
 * which link is override. It must match all the regular expressions
 * 
 * @author Orlandin Marco
 *
 */
public class RegularExpressionOverrider implements IHelpOverrider {

	/**
	 * A list of regular expressions
	 */
	protected List<String> regularExpressions;
	
	/**
	 * the substitution
	 */
	protected String substitutionString;
	
	/**
	 * Build an instance of the class
	 * 
	 * @param regEx the regular expression that will be matched with the attribute
	 * @param substitution substitution for the link, the substitution is done by concatenating 
	 * the standard prefix with the substitution String
	 */
	public RegularExpressionOverrider(String regEx, String substitution){
		this(Collections.singletonList(regEx),substitution);
	}
	
	public RegularExpressionOverrider(List<String> regEx, String substitution){
		substitutionString = substitution;
		regularExpressions = regEx;
	}
	
	@Override
	public String getPropertyURL(String propertyName) {
		return PREFIX.concat(substitutionString);
	}

	
	@Override
	public boolean isOverrided(String propertyName) {
		for(String expression : regularExpressions){
			if (!propertyName.matches(expression)) return false;
		}
		return true;
	}
	
}
