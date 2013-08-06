package com.jaspersoft.studio.property.infoList;

public class ElementDescription {
	private String name;
	
	private String description;
	
	public ElementDescription(String name, String description){
		this.name = name;
		this.description = description;
	}
	
	public String getName(){
		return name;
	}
	
	public String getDescription(){
		return description;
	}
}
