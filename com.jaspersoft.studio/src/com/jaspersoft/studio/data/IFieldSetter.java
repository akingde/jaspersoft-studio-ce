/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data;

import java.util.List;

import net.sf.jasperreports.engine.design.JRDesignField;

public interface IFieldSetter {
	
	/**
	 * Sets the specified fields, old ones get discarded.
	 * 
	 * @param fields
	 */
	public void setFields(List<JRDesignField> fields);
	
	/**
	 * Adds new fields to existing ones, if any.
	 * 
	 * @param fields new fields to add
	 */
	public void addFields(List<JRDesignField> fields);
	
	/**
	 * Clears the list of existing fields.
	 */
	public void clearFields();
	
}
