/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.decorator.chainable;

import java.awt.Graphics2D;
import java.awt.Point;

/**
 * Interface to implement to define a decorator that paint something on a figure
 * 
 * @author Orlandin Marco
 *
 */
public interface IDecoratorPainter {
	
	/**
	 * The implementation of the method will paint the decorator to the figure
	 * 
	 * @param g the graphics of the figure
	 * @param x the position x where the decorator should be painted
	 * @param y the position y where the decorator should be painted
	 */
	public void paint(Graphics2D g, int x, int y);
	
	/**
	 * Return a not null point with the size of the decorator that the implementation
	 * will paint
	 * 
	 * @param g the graphics of the figure
	 * @return a not null point with the width and height of the decorator
	 */
	public Point getElementSize(Graphics2D g);
}
