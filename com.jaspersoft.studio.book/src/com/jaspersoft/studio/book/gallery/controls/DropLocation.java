/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.gallery.controls;

import org.eclipse.nebula.widgets.gallery.GalleryItem;

/**
 * 
 * Define the location of a drop operation inside a gallery component
 * 
 * @author Orlandin Marco
 *
 */
public class DropLocation {
	
	/**
	 * The source gallery of the drag operation
	 */
	private GalleryComposite sourceGallery = null;
	
	/**
	 * The target gallery of the drop operation
	 */
	private GalleryComposite targetGallery = null;
	
	
	/**
	 * Element after or before which the new element must be placed
	 */
	private GalleryItem item = null;
	
	/**
	 * True if the new element must be pain after the item otherwise before
	 */
	private boolean placeAfter = true;
	
	/**
	 * Return the item where the dragged element will be placed, if after
	 * or before is defined from the place after flag
	 * 
	 * @return a GalleryItem could be null
	 */
	public GalleryItem getItem() {
		return item;
	}

	/**
	 * Set the item where the dragged element will be placed, if after
	 * or before is defined from the place after flag
	 * 
	 * @param item a GalleryItem, if it is null the position it considered
	 * not valid
	 */
	public void setItem(GalleryItem item) {
		this.item = item;
	}

	/**
	 * Return if the dragged element must be painted after or
	 * before the item
	 * 
	 * @return true if the element must be moved after, false
	 * otherwise
	 */
	public boolean isPlacedAfter() {
		return placeAfter;
	}

	/**
	 * Set if the dragged element must be moved after or
	 * before the item
	 * 
	 * @param placeAfter true if the feedback must be dragged after, false
	 * otherwise
	 */
	public void setPlaceAfer(boolean placeAfter) {
		this.placeAfter = placeAfter;
	}
	
	/**
	 * Return the source gallery of the drag operation
	 * 
	 * @return the source gallery of the operation. Could be null if the element
	 * is not dragged from another gallery
	 */
	public GalleryComposite getSourceGallery() {
		return sourceGallery;
	}

	/**
	 * Set the source gallery of the drag operation
	 * 
	 * @param sourceGallery the source gallery of the operation. Could be null if the element
	 * is not dragged from another gallery
	 */
	public void setSourceGallery(GalleryComposite sourceGallery) {
		this.sourceGallery = sourceGallery;
	}

	/**
	 * Return the target gallery of the drop operation
	 * 
	 * @return the target gallery of the operation. Shouldn't be null
	 */
	public GalleryComposite getTargetGallery() {
		return targetGallery;
	}

	/**
	 * Set the target gallery of the drop operation
	 * 
	 * @param targetGallery the target gallery of the operation. Shouldn't be null
	 */
	public void setTargetGallery(GalleryComposite targetGallery) {
		this.targetGallery = targetGallery;
	}

	/**
	 * Check if there is a valid element where the feedback must be painted
	 * 
	 * @return true if the item is not equals to null, false otherwise
	 */
	public boolean isValid(){
		return item != null;
	}
}
