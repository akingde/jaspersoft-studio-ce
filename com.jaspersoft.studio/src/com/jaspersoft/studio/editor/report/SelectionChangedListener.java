/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.report;

/**
 * 
 * Interface for a listener that can be added on the common selection cache to 
 * notify when the selection change
 * 
 * @author Orlandin Marco
 *
 */
public interface SelectionChangedListener {

	/**
	 * Method called when the selection change
	 */
	public void selectionChanged();
	
}
