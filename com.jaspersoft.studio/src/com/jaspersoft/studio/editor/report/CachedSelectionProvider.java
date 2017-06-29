/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.report;

/**
 * Interface to implement to provide a selection cache. The selection cache
 * can be used from the action (actually both toolbar and contextual) to cache
 * similar request of a subset of the selection and improve the performances
 * 
 * @author Orlandin Marco
 *
 */
public interface CachedSelectionProvider {

	/**
	 * Return the common selection provider
	 * 
	 * @return a not null selection provider. Remember that the elements inside the cache
	 * must be updated with the selectionChange method when the selection change
	 */
	public CommonSelectionCacheProvider getSelectionCache();
	
}
