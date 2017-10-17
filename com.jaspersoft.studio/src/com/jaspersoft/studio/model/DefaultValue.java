/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model;

/**
 * Define a default value for a property
 * 
 * @author Orlandin MArco
 *
 */
public class DefaultValue {

	/**
	 * The default value of the property
	 */
	private Object value;

	/**
	 * flag used to know if the property is also nullable
	 */
	private boolean isNullable;
	
	/**
	 * Flag to know if the instance define a default value
	 */
	private boolean hasDefault = true;
	
	/**
	 * Create the class
	 * 
	 * @param value the default value for a property, can be null
	 * @param isNullable true if the value is nullable, false otherwise
	 */
	public DefaultValue(Object value, boolean isNullable){
		this.value = value;
		this.isNullable = isNullable;
	}
	
	/**
	 * Create an entry for a value that has not a default but it can be nullable
	 * 
	 * @param isNullable true if the value is nullable, false otherwise
	 */
	public DefaultValue(boolean isNullable){
		hasDefault = false;
		this.isNullable = isNullable;
	}
	
	public Object getValue(){
		return value;
	}
	
	public boolean isNullable(){
		return isNullable;
	}
	
	/**
	 * Return if the value has a default. A value has a default when it 
	 * has a default defined and if it is null then it must not be nullable.
	 * In other words the nullable precede the default.
	 * 
	 * @return true if the value has a default, false otherwise
	 */
	public boolean hasDefault(){
		return !(isNullable && value == null) && hasDefault;
	}
}
