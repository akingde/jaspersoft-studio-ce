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
package com.jaspersoft.studio.book.gallery.controls;

import org.eclipse.nebula.widgets.gallery.GalleryItem;

/**
 * 
 * Define the location of a drop operation feedback inside a gallery component
 * 
 * @author Orlandin Marco
 *
 */
public class DropEffectLocation {

	/**
	 * Element after or before which the feedback must be placed
	 */
	private GalleryItem item = null;
	
	/**
	 * True if the feedback must be pain after the item otherwise before
	 */
	private boolean placeAfter = true;
	
	/**
	 * Return the item where the feedback must be painted
	 * 
	 * @return a GalleryItem could be null
	 */
	public GalleryItem getItem() {
		return item;
	}

	/**
	 * Set the item where the feedback must be painted
	 * 
	 * @param item a GalleryItem can be null if no feedback
	 * must be painted
	 */
	public void setItem(GalleryItem item) {
		this.item = item;
	}

	/**
	 * Return if the feedback must be painted after or
	 * before the item
	 * 
	 * @return true if the feedback must be painted after, false
	 * otherwise
	 */
	public boolean isPlacedAfter() {
		return placeAfter;
	}

	/**
	 * Set if the feedback must be painted after or
	 * before the item
	 * 
	 * @param placeAfter true if the feedback must be painted after, false
	 * otherwise
	 */
	public void setPlaceAfer(boolean placeAfter) {
		this.placeAfter = placeAfter;
	}

	/**
	 * Check if there is a valid element where the feedback must be painted
	 * 
	 * @return true if the item is not equals to null, false otherwise
	 */
	public boolean isValid(){
		return item != null;
	}
	
	/**
	 * Check if two drop location are equals. They are equals if they reference
	 * the same item and paint the feedback in the same direction
	 * 
	 * @return true if the two object had the feedback painted in the same location
	 * false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		DropEffectLocation d2 = (DropEffectLocation)obj;
		return item == d2.getItem() && placeAfter == d2.isPlacedAfter();
	}
}
