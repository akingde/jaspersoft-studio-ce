/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.gallery.controls.render;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.nebula.widgets.gallery.Gallery;
import org.eclipse.nebula.widgets.gallery.GalleryItem;
import org.eclipse.swt.dnd.DragSourceEffect;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.graphics.Image;

/**
 * Show an effect during the drag and drop, the effect consist of a half-transparent
 * snapshot of the dragged element under the mouse pointer
 * 
 * @author Orlandin Marco
 *
 */
public class PageDragSourceEffect extends DragSourceEffect {

	/**
	 * Gallery from where the selected element is taken
	 */
	private Gallery g = null;

	/**
	 * Suggested width of the snapshot
	 */
	private int width;

	/**
	 * Suggested height of the snapshot
	 */
	private int height;

	/**
	 * Reference to the last generated snapshot image
	 */
	private Image lastImage = null;

	/**
	 * Creates the drag source effect. The width and height of the snapshot
	 * are indicative because it try to keep the aspect ratio
	 * 
	 * @param gallery the gallery
	 * @param width Suggested width of the snapshot
	 * @param height Suggested height of the snapshot
	 */
	public PageDragSourceEffect(Gallery gallery, int width, int height) {
		super(gallery);
		g = gallery;
		this.width = width;
		this.height = height;
	}

	@Override
	public void dragStart(DragSourceEvent event) {
		GalleryItem[] selection = g.getSelection();
		if (selection != null && selection.length > 0) {
			Image img = selection[0].getImage();
			if (img != null) {
				event.image = scaleImage(img);
			}
		}
	}
	
	/**
	 * Generate a scaled snapshot image, but before dispose the old one
	 * 
	 * @param sourceImage image to scale
	 * @return scaled image
	 */
	private Image scaleImage(Image sourceImage) {
		disposeImage();
		int newWidth = 0;
		int newHeight = 0;
		int srcWidth = sourceImage.getImageData().width;
		int srcHeight = sourceImage.getImageData().height;
		//Keep proportion basing them on the longest edge
		if (srcWidth > srcHeight) {
			newWidth = width;
			newHeight = (newWidth * srcHeight) / srcWidth;
		} else {
			newHeight = height;
			newWidth = (newHeight * srcWidth) / srcHeight;
		}
		Image scaled = new Image(UIUtils.getDisplay(), sourceImage.getImageData().scaledTo(newWidth, newHeight));
		lastImage = scaled;
		return lastImage;
	}
	
	/**
	 * Dispose the currently cached image if necessary
	 */
	public void disposeImage() {
		if (lastImage != null) {
			lastImage.dispose();
			lastImage = null;
		}
	}
}
