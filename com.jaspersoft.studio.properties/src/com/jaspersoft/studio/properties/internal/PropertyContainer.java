/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.properties.internal;

public class PropertyContainer implements Comparable<PropertyContainer>{
	
	private String name;
	
	private Object id;
	
	private Class<?> sectionType;
	
	public PropertyContainer(String name, Object id, Class<?> sectionType){
		this.name = name;
		this.id = id;
		this.sectionType = sectionType;
	}
	
	public String getName(){
		return name;
	}
	
	public Object getId(){
		return id;
	}
	
	public Class<?> getSectionType(){
		return sectionType;
	}
	

	@Override
	public int compareTo(PropertyContainer o) {
		return name.compareTo(o.getName());
	}
}
