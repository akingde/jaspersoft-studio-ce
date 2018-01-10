/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.properties.view;

import org.eclipse.ui.views.properties.IPropertySheetEntry;

/**
 * Essentially this is a standard {@link IPropertySheetEntry} with the addition of 
 * the possibility to apply a more sophisticated behavior for the reset of the children or
 * of the node itself
 * 
 * @author Orlandin Marco
 *
 */
public interface IResettablePropertySheetEntry extends IPropertySheetEntry {
	
	/**
	 * The possible reset type available on this entry
	 * NO_RESET means that the the entry or its children have not the possibility to be reset,
	 * RESET_ELEMENT allow the reset only of the entry but not of eventual children. RESET_CHILDREN
	 * allow the reset of the children of the entry but not of the entry itself. RESET_BOTH allow
	 * the reset of both the entry and its children
	 */
	public enum RESET_TYPE {NO_RESET, RESET_ELEMENT, RESET_CHILDREN, RESET_BOTH}
	
	/**
	 * Return the reset type available on this entry.
	 * 
	 * @param a not null {@link RESET_TYPE}
	 */
	public RESET_TYPE getAvailableReset();
}
