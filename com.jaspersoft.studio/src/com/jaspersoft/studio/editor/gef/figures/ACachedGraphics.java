/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.figures;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 * Abstract class for a graphics2d that supports the caching of the
 * operation. Can be used when something need a many calculations to
 * be drawn. Until something that modify the calculations happen
 * it's useless to repeat them only because of a refresh. The idea 
 * is to cache in some way the operations executed the first time
 * and be able to repeat them
 * 
 * 
 * @author Orlandin Marco
 *
 */
public abstract class ACachedGraphics extends Graphics2D {
	
	protected double scaleX = 1.0;
	
	protected double scaleY = 1.0;
	
	
	/**
	 * Execute the operation in the cache to paint the content
	 * on the set graphics
	 */
	public abstract void paintCache();
	
	/**
	 * The graphics where the operation in the cache will paint
	 * 
	 * @param graphics a not null graphics 2d
	 */
	public abstract void setGraphics(Graphics2D graphics);
	
	/**
	 * Check if the current cached graphics ask for a repaint. This is done
	 * to separate the repaint requested by the model and the ones from
	 * the graphic layer.
	 * 
	 * This implementation ask for a repaint when the zoom changes
	 * 
	 * @param originalGraphics the original graphics of the element
	 * @return true if the zoom level changed, false otherwise
	 */
	public boolean needRepaint(Graphics2D originalGraphics){
		if (originalGraphics != null){
			AffineTransform transform = originalGraphics.getTransform();
			double scaleX = transform.getScaleX();
			double scaleY = transform.getScaleY();
			if (this.scaleX != scaleX || this.scaleY != scaleY){
				this.scaleX = scaleX;
				this.scaleY = scaleY;
				return true;
			}
		}
		return false;
	}
	
}
