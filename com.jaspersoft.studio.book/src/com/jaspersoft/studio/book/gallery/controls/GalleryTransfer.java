/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.gallery.controls;

import org.eclipse.gef.dnd.SimpleObjectTransfer;

/**
 * Transfer object used to transfer IGalleryElements from a gallery to another or to move
 * elements inside the same gallery.
 * 
 * @author Orlandin Marco
 */
public final class GalleryTransfer extends SimpleObjectTransfer {

	/**
	 * Shared instance of the transfer type
	 */
	private static final GalleryTransfer INSTANCE = new GalleryTransfer();
	
	private static final String TYPE_NAME = "Gallery transfer" + System.currentTimeMillis() + ":" + INSTANCE.hashCode();
	
	private static final int TYPEID = registerType(TYPE_NAME);
	
	/**
	 * Gallery source of the drag and drop
	 */
	private GalleryComposite sourceGallery = null;
	
	private GalleryTransfer() {
	}

	/**
	 * Returns the singleton instance
	 * 
	 * @return the singleton
	 */
	public static GalleryTransfer getInstance() {
		return INSTANCE;
	}
	
	/**
	 * @see org.eclipse.swt.dnd.Transfer#getTypeIds()
	 */
	protected int[] getTypeIds() {
		return new int[] { TYPEID };
	}

	/**
	 * @see org.eclipse.swt.dnd.Transfer#getTypeNames()
	 */
	protected String[] getTypeNames() {
		return new String[] { TYPE_NAME };
	}

	/**
	 * Sets the gallery Object source of the drag and drop.
	 * 
	 * @param gallery the gallery Object source of the drag and drop
	 */
	public void setSourceGallery(GalleryComposite gallery) {
		this.sourceGallery = gallery;
	}
	

	/**
	 * Returns the gallery Object source of the drag and drop
	 * 
	 * @return the gallery Object source of the drag and drop
	 */
	public GalleryComposite getSourceGallery() {
		return sourceGallery;
	}
}
