/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.figure;

import java.awt.Graphics2D;

import net.sf.jasperreports.crosstabs.JRCrosstab;
import net.sf.jasperreports.engine.JRElement;

import org.eclipse.draw2d.XYLayout;

import com.jaspersoft.studio.components.crosstab.model.MCrosstab;
import com.jaspersoft.studio.editor.gef.figures.FrameFigure;
import com.jaspersoft.studio.jasper.JSSDrawVisitor;

public class CrosstabFigure extends FrameFigure {
	
	/**
	 * Instantiates a new text field figure.
	 */
	public CrosstabFigure(MCrosstab crosstabModel) {
		super(crosstabModel);
		setLayoutManager(new XYLayout());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jaspersoft.studio.editor.gef.figures.ComponentFigure#draw(net.sf.
	 * jasperreports.engine.export.draw.DrawVisitor,
	 * net.sf.jasperreports.engine.JRElement)
	 */
	@Override
	protected void draw(JSSDrawVisitor drawVisitor, JRElement jrElement) {
		if (model != null && allowsFigureDrawCache()){
			Graphics2D oldGraphics = drawVisitor.getGraphics2d();
			if (needRefresh(oldGraphics)){
				cachedGraphics = getCachedGraphics(oldGraphics);
				drawVisitor.setGraphics2D(cachedGraphics);
				drawVisitor.visitCrosstab((JRCrosstab) jrElement);
				drawVisitor.setGraphics2D(oldGraphics);
				model.setChangedProperty(false);
			}
			cachedGraphics.setGraphics(drawVisitor.getGraphics2d());
			cachedGraphics.paintCache();
		} else {
			drawVisitor.visitCrosstab((JRCrosstab) jrElement);
		}
	}

}
