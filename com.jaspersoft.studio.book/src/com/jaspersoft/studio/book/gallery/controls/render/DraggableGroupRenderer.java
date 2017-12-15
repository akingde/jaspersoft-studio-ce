/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.gallery.controls.render;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.nebula.widgets.gallery.GalleryItem;
import org.eclipse.nebula.widgets.gallery.NoGroupRenderer;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

import com.jaspersoft.studio.book.gallery.controls.DropEffectLocation;

/**
 * Group render for the gallery control to add a feedback during the 
 * drag and drop operation
 * 
 * @author Orlandin Marco
 *
 */
public class DraggableGroupRenderer extends NoGroupRenderer {
	
	/**
	 * Specify where the drag and drop feedback must be placed
	 */
	private DropEffectLocation dropTargetEffect = null;

	/**
	 * Pain all the elements and the if it is available also the drag feedback
	 */
	@Override
	public void draw(GC gc, GalleryItem group, int x, int y, int clipX, int clipY, int clipWidth, int clipHeight) {
		super.draw(gc, group, x, y, clipX, clipY, clipWidth, clipHeight);
		paintDropTargetEffect(gc);
	}
	
	/**
	 * Pain the feedback on the gallery item if it is position is provided trought the dropTargetEffect
	 * field
	 * 
	 * @param graphicContext graphic context to pain the feedback on the gallery item
	 */
	protected void paintDropTargetEffect(GC graphicContext) {
		if (dropTargetEffect != null && dropTargetEffect.isValid()) {
			int oldStroke = graphicContext.getLineWidth();
			graphicContext.setLineWidth(2);
			GalleryItem elementGroups = dropTargetEffect.getItem().getParentItem();
			int index = ArrayUtils.indexOf(elementGroups.getItems(), dropTargetEffect.getItem());
			Rectangle itemBounds = dropTargetEffect.getItem().getBounds();
			if (dropTargetEffect.isPlacedAfter()) {
				if (index == (elementGroups.getItemCount() - 1)) {
					// it's the last element, draw the line after it
					int xAxis = itemBounds.x + itemBounds.width;
					graphicContext.drawLine(xAxis, itemBounds.y, xAxis, itemBounds.y + itemBounds.height);
				} else {
					// place the line in the middle between the element and the next one
					GalleryItem nextItem = elementGroups.getItem(index + 1);
					Rectangle nextItemBounds = nextItem.getBounds();
					if (nextItemBounds.y != itemBounds.y) {
						// the element are on two different levels
						int xAxis = itemBounds.x + itemBounds.width;
						graphicContext.drawLine(xAxis, itemBounds.y, xAxis, itemBounds.y + itemBounds.height);
					} else {				
						DraggableGalleryItemRenderer itemRender = (DraggableGalleryItemRenderer)gallery.getItemRenderer();
						Rectangle image1Bounds = itemRender.imagesBounds.get(dropTargetEffect.getItem());
						Rectangle image2Bounds = itemRender.imagesBounds.get(nextItem);
						int mX = image1Bounds.x + image1Bounds.width;
						int mWidth = image2Bounds.x - mX;
						int drawLineX = mX + mWidth / 2 - graphicContext.getLineWidth() / 2;
						graphicContext.drawLine(drawLineX, itemBounds.y, drawLineX, itemBounds.y + itemBounds.height);
					}
				}
			} else {
				if (index == 0) {
					// it's the first element, draw the line before it
					graphicContext.drawLine(itemBounds.x, itemBounds.y, itemBounds.x, itemBounds.y + itemBounds.height);
				} else {
					// place the line in the middle between the element and the previous one
					GalleryItem previousItem = elementGroups.getItem(index - 1);
					Rectangle previousItemBounds = previousItem.getBounds();
					if (previousItemBounds.y != itemBounds.y) {
						// the element are on two different levels
						graphicContext.drawLine(itemBounds.x, itemBounds.y, itemBounds.x, itemBounds.y + itemBounds.height);
					} else {
						DraggableGalleryItemRenderer itemRender = (DraggableGalleryItemRenderer)gallery.getItemRenderer();
						Rectangle image1Bounds = itemRender.imagesBounds.get(previousItem);
						Rectangle image2Bounds = itemRender.imagesBounds.get(dropTargetEffect.getItem());
						int mX = image1Bounds.x + image1Bounds.width;
						int mWidth = image2Bounds.x - mX;

						int drawLineX = mX + mWidth / 2 - graphicContext.getLineWidth() / 2;
						graphicContext.drawLine(drawLineX, itemBounds.y, drawLineX, itemBounds.y + itemBounds.height);
					}
				}
			}
			graphicContext.setLineWidth(oldStroke);
		}
	}
	
	/**
	 * Set the drop feedback location
	 * 
	 * @param location a location of the drop feedback. If null no feedback is painted
	 */
	public void setDropTargetLocation(DropEffectLocation location){
		this.dropTargetEffect = location;
	}
}
