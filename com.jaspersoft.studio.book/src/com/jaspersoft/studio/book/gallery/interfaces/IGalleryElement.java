/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
