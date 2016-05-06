package com.jaspersoft.studio.model;

public class DefaultValue {

	private Object value;
	
	private boolean isNullable;
	
	public DefaultValue(Object value, boolean isNullable){
		this.value = value;
		this.isNullable = isNullable;
	}
	
	public Object getValue(){
		return value;
	}
	
	public boolean isNullable(){
		return isNullable;
	}
}
