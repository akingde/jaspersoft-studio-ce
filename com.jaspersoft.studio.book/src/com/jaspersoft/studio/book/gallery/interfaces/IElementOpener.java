/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.gallery.interfaces;

/**
 * Interface to provide an open action to the gallery
 * 
 * @author Orlandin Marco
 *
 */
public interface IElementOpener {

	/**
	 * Name of the action
	 * 
	 * @return a text used to show in the UI the action, can't be null
	 */
	public String getActionText();
	
	/**
	 * A list of element to add to the gallery when the add action is
	 * chosen. Can't be null but it could be empty
	 * 
	 * @return a not null array of element to add to the gallery
	 */
	public IGalleryElement[] openResources();
}
