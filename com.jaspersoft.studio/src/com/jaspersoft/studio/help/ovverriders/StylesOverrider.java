/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.help.ovverriders;

/**
 * Overrider for the attributes with name that start with net.sf.jasperreports.style. and does not end with ".evaluation.time.enabled", since 
 * for this last attribute there is a dedicated reference.
 * The attributes that match this overrider will be redirected to the reference of "net.sf.jasperreports.style.{style_property_suffix}"
 * 
 * @author Orlandin Marco
 *
 */
public class StylesOverrider implements IHelpOverrider{

	@Override
	public String getPropertyURL(String propertyName) {
		return PREFIX.concat("net.sf.jasperreports.style.{style_property_suffix}");
	} 

	@Override
	public boolean isOverrided(String propertyName) {
			return (propertyName.startsWith("net.sf.jasperreports.style.") 
							&& !propertyName.endsWith(".evaluation.time.enabled"));
	}
	
}
