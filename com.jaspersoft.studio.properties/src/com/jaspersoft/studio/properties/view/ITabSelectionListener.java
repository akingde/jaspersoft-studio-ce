/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.properties.view;

/**
 * A listener interested in tab selection events that occur for the tabbed
 * property sheet page.
 * 
 * @author Anthony Hunter 
 */
public interface ITabSelectionListener {

	/**
	 * Notifies this listener that the selected tab has changed.
	 * 
	 * @param tabDescriptor
	 *            the selected tab descriptor.
	 * @since 3.4
	 */
	public void tabSelected(ITabDescriptor tabDescriptor);
}
