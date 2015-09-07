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
