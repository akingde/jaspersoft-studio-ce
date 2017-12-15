/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.gallery.interfaces;

import org.eclipse.swt.graphics.Image;

/**
 * Interface to provide an element to the gallery
 * 
 * @author Orlandin Marco
 *
 */
public interface IGalleryElement {

	/**
	 * Return the image of the element
	 * 
	 * @return a not null image for the element
	 */
	public Image getImage();
	
	/**
	 * Return the text to show under the image
	 * 
	 * @return a not null text for the image
	 */
	public String getTitle();
	
	/**
	 * Optional data that can be stored inside the element
	 * 
	 * @return return the optional data of the element, this can
	 * be null, depends on the implementation
	 */
	public Object getData();
}
