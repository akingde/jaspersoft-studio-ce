/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.expression;

/**
 * Information about the sorting of {@link ExpObject} items.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * @see ExpObject
 *
 */
public class ExpObjectSortingInfo {

	private int type;
	private boolean sortIncreaseItems;
	private boolean sortDecreaseItems;

	public ExpObjectSortingInfo(int type){
		this(type,false,false);
	}
	
	public ExpObjectSortingInfo(int type, boolean sortDecreaseItems, boolean sortIncreaseItems) {
		this.type = type;
		this.sortDecreaseItems = sortDecreaseItems;
		this.sortIncreaseItems = sortIncreaseItems;
	}

	/**
	 * @return the type of the ExpObject item
	 */
	public int getType() {
		return type;
	}

	
	/**
 	 * @return <code>true</code> if the items should be shown in increasing alphabetical order, <code>false</code>
	 *         otherwise.
	 */
	public boolean isSortIncreaseItems() {
		return sortIncreaseItems;
	}

	/**
	 * @return <code>true</code> if the items should be shown in decreasing alphabetical order, <code>false</code>
	 *         otherwise.
	 */
	public boolean isSortDecreaseItems() {
		return sortDecreaseItems;
	}
	
	/**
	 * Changes the flag that decides if items should be shown in increasing alphabetical order.
	 * 
	 * @return the new status
	 */
	public boolean toggleSortIncreaseItems() {
		sortIncreaseItems = !sortIncreaseItems;
		sortDecreaseItems = false;
		return sortIncreaseItems;
	}

	/**
	 * Changes the flag that decides if items should be shown in decreasing alphabetical order.
	 * 
	 * @return the new status
	 */
	public boolean toggleSortDecreaseItems() {
		sortDecreaseItems = !sortDecreaseItems;
		sortIncreaseItems = false;
		return sortDecreaseItems;
	}
	
}
