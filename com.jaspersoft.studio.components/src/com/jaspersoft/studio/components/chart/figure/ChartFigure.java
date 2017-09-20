/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.figure;

import java.awt.Graphics2D;

import com.jaspersoft.studio.components.chart.model.MChart;
import com.jaspersoft.studio.editor.gef.figures.ACachedGraphics;
import com.jaspersoft.studio.editor.gef.figures.FrameFigure;
import com.jaspersoft.studio.editor.java2d.ImageGraphics2D;
import com.jaspersoft.studio.jasper.JSSDrawVisitor;

import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRElement;
/*
 * The Class ChartFigure.
 */
public class ChartFigure extends FrameFigure {
	
	/**
	 * Instantiates a new static text figure.
	 */
	public ChartFigure(MChart model) {
		super(model);
	}
	
	protected void fallbackDraw(JSSDrawVisitor drawVisitor, JRElement jrElement) {
		drawVisitor.visitChart((JRChart) jrElement);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.editor.gef.figures.GenericFigure#draw(net.sf.
	 * jasperreports.engine.export.draw.DrawVisitor,
	 * net.sf.jasperreports.engine.JRElement)
	 */
	@Override
	protected void draw(JSSDrawVisitor drawVisitor, JRElement jrElement) {
		if (model != null && allowsFigureDrawCache()) {
			Graphics2D oldGraphics = drawVisitor.getGraphics2d();
			if (needRefresh(oldGraphics)) {
				cachedGraphics = getCachedGraphics(oldGraphics);
				drawVisitor.setGraphics2D(cachedGraphics);
				drawVisitor.visitChart((JRChart) jrElement);
				drawVisitor.setGraphics2D(oldGraphics);
				model.setChangedProperty(false);
			}
			cachedGraphics.setGraphics(drawVisitor.getGraphics2d());
			cachedGraphics.paintCache();
		} else {
			fallbackDraw(drawVisitor, jrElement);
		}
	}
	
	@Override
	protected ACachedGraphics getCachedGraphics(Graphics2D originalGraphics) {
		return new ImageGraphics2D(originalGraphics);
	}
}
