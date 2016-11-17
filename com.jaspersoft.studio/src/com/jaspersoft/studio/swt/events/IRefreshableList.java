/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.swt.events;

/**
 * This interface enables the "refresh list" feature for all its implementors.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public interface IRefreshableList {

	/**
	 * Forces the refresh of the internal graphical representation of a generic list of elements.
	 * Usually it can be a list of properties for a specific component.
	 * <p>
	 * 
	 * This could be needed, for example, when an update operation to the set of properties/items currently associated
	 * occurs and the UI must be properly redrawn.
	 */
	void refreshList();
}
