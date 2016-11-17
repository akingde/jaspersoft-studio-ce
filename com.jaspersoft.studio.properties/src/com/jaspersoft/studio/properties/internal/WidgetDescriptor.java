/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.properties.internal;

import java.util.ArrayList;
import java.util.List;

/**
 * Descriptor for a property associated with a widget, it can simply have a 
 * name and a list of alias, like the description for example
 * 
 * @author Orlandin Marco
 *
 */
public class WidgetDescriptor {
	
	/**
	 * Name of the property
	 */
	private String name;
	
	/**
	 * List of aliases, with this names the property match as its standard name
	 */
	private List<String> aliases;
	
	public WidgetDescriptor(String name){
		this.name = name;
		aliases = new ArrayList<String>();
	}
	
	public String getName(){
		return name;
	}
	
	public void addAlias(String alias){
		aliases.add(alias);
	}
	
	public List<String> getAliases(){
		return aliases;
	}
}
