/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model;

import com.jaspersoft.studio.editor.layout.ILayout;

import net.sf.jasperreports.engine.JRPropertiesHolder;

/**
 * Interface implemented for the containers that can have a layout for the children inside
 *
 * @author Orlandin Marco
 *
 */
public interface IContainerLayout {
	
	/**
	 * Get the properties holder of the current element
	 * 
	 * @return a not null array of properties holder.
	 */
	public JRPropertiesHolder[] getPropertyHolder();
	
	/**
	 * Return the default layout used for this element when no other
	 * layout is defined
	 * 
	 * @return the layout to be used by default, if it return null then 
	 * the FreeLayout is used
	 */
	public ILayout getDefaultLayout();
}
