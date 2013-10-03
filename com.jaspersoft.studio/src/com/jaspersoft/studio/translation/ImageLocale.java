/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.translation;

import java.util.Locale;

import org.eclipse.swt.graphics.Image;

/**
 * Simple container to bind a Locale to an image that represent that locale.
 * For example the image could be a flag representing the language of the locale
 * 
 * @author Orlandin Marco
 *
 */
public class ImageLocale {
	
	/**
	 * The locale
	 */
	private Locale locale;
	
	/**
	 * The image to associate to the locale
	 */
	private Image image;
	
	/**
	 * Create an instance of the class
	 * 
	 * @param locale The locale
	 * @param image image to associate to the locale
	 */
	public ImageLocale(Locale locale, Image image){
		this.locale = locale;
		this.image = image;
	}
	
	/**
	 * Return the image associated to the locale
	 * 
	 * @return an swt image, could be null
	 */
	public Image getImage(){
		return image;
	}
	
	/**
	 * Return the locale
	 * 
	 * @return a locale
	 */
	public Locale getLocale(){
		return locale;
	}
	
}
