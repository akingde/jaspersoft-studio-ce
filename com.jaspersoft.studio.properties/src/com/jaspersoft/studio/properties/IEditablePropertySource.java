/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.properties;

/**
 * Interface to implement to know if a node in the AdvancedPropertiesView
 * is editable or not
 */
public interface IEditablePropertySource {

	/**
	 * Return if the node is editable
	 */
	public boolean isEditable();
	
	/**
	 * Set the node editable or not
	 */
	public void setEditable(boolean editable);
	
}
