package com.jaspersoft.studio.help.ovverriders;

/**
 * Overrider for the attributes with name net.sf.jasperreports.compiler."some name of a language"
 * The attributes that match this overrider will be redirected to the reference of "net.sf.jasperreports.compiler.{language}"
 * 
 * @author Orlandin Marco
 *
 */
public class LanguageOverrider implements IHelpOverrider{

	@Override
	public String getPropertyURL(String propertyName) {
		return PREFIX.concat("net.sf.jasperreports.compiler.{language}");
	}

	@Override
	public boolean isOverrided(String propertyName) {
			return propertyName.startsWith("net.sf.jasperreports.compiler.") &&
					(propertyName.endsWith(".groovy") || propertyName.endsWith(".java") || propertyName.endsWith(".bsh") 
					 || propertyName.endsWith(".javascript"));
	}
	
}