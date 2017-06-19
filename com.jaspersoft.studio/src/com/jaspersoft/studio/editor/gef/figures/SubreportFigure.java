/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.figures;

import java.awt.Graphics2D;

import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRSubreport;

import com.jaspersoft.studio.jasper.JSSDrawVisitor;
import com.jaspersoft.studio.model.subreport.MSubreport;
/*
 * The Class SubreportFigure.
 * 
 * @author Chicu Veaceslav
 */
public class SubreportFigure extends FrameFigure {

	/**
	 * Instantiates a crosstab figure.
	 */
	public SubreportFigure(MSubreport model) {
		super(model);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jaspersoft.studio.editor.gef.figures.ComponentFigure#draw(net.sf.jasperreports.engine.export.draw.DrawVisitor,
	 * net.sf.jasperreports.engine.JRElement)
	 */
	@Override
	protected void draw(JSSDrawVisitor drawVisitor, JRElement jrElement) {
		if (model != null && allowsFigureDrawCache()) {
			Graphics2D oldGraphics = drawVisitor.getGraphics2d();
			if (needRefresh(oldGraphics)) {
				model.setChangedProperty(false);
				cachedGraphics = getCachedGraphics(oldGraphics);
				drawVisitor.setGraphics2D(cachedGraphics);
				drawVisitor.visitSubreport((JRSubreport) jrElement);
				drawVisitor.setGraphics2D(oldGraphics);
			}
			cachedGraphics.setGraphics(drawVisitor.getGraphics2d());
			cachedGraphics.paintCache();
		} else {
			drawVisitor.visitSubreport((JRSubreport) jrElement);
		}
	}
}
