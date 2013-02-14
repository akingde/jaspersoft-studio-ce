package com.jaspersoft.studio.help.ovverriders;

/**
 * This overrider use a regular expression to identify the attributes
 * which link is override.
 * 
 * @author Orlandin Marco
 *
 */
public class RegularExpressionOverrider extends GenericOverrider {

	/**
	 * Build an instance of the class
	 * 
	 * @param regEx the regular expression that will be matched with the attribute
	 * @param substitution substitution for the link, the substitution is done by concatenating 
	 * the standard prefix with the substitution String
	 */
	public RegularExpressionOverrider(String regEx, String substitution){
		super(regEx, substitution);
	}
	
	@Override
	public boolean isOverrided(String propertyName) {
		return propertyName.matches(searchedString);
	}
	
}
