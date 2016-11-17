/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chartspider.figure;

import java.awt.Graphics2D;

import com.jaspersoft.studio.editor.gef.figures.ACachedGraphics;
import com.jaspersoft.studio.editor.gef.figures.JRComponentFigure;
import com.jaspersoft.studio.editor.java2d.ImageGraphics2D;
import com.jaspersoft.studio.model.MGraphicElement;

/**
 * 
 * @author veaceslav chicu
 * 
 */
public class SpiderChartFigure extends JRComponentFigure {

	/**
	 * Instantiates a new map figure.
	 */
	public SpiderChartFigure(MGraphicElement node) {
		super(node);
	}

	@Override
	protected ACachedGraphics getCachedGraphics(Graphics2D originalGraphics) {
		return new ImageGraphics2D(originalGraphics);
	}
}
