/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.properties.view;

/**
 * Represents a list of tabs. Used by a section that overrides the tabs that are
 * provided by the tabbed property registry with a new list of tabs.
 * <p>
 * The overridable tab list is a content provider that provides both the
 * sections and the tab labels.
 * 
 * @author Anthony Hunter
 * @since 3.4
 */
public interface IOverridableTabList {

	/**
	 * Returns the list of tabs.
	 * 
	 * @return the list of tabs.
	 */
	public ITabItem[] getTabs();

	/**
	 * Select the tab at the provided index.
	 * 
	 * @param index
	 *            the index in the list of tabs to select.
	 */
	public void selectTab(int index);
}
