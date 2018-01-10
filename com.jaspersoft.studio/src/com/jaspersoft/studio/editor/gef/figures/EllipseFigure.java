/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.figures;

import java.awt.Graphics2D;

import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JREllipse;

import com.jaspersoft.studio.jasper.JSSDrawVisitor;
import com.jaspersoft.studio.model.MEllipse;
/*
 * The Class EllipseFigure.
 */
public class EllipseFigure extends LineFigure {

	public EllipseFigure(MEllipse model) {
		super(model);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jaspersoft.studio.editor.gef.figures.GenericFigure#draw(net.sf.jasperreports.engine.export.draw.DrawVisitor,
	 * net.sf.jasperreports.engine.JRElement)
	 */
	@Override
	protected void draw(JSSDrawVisitor drawVisitor, JRElement jrElement) {
		Graphics2D oldGraphics = drawVisitor.getGraphics2d();
		if (needRefresh(oldGraphics)){
			model.setChangedProperty(false);
			cachedGraphics = getCachedGraphics(oldGraphics);
			drawVisitor.setGraphics2D(cachedGraphics);
			drawVisitor.visitEllipse((JREllipse) jrElement);
			drawVisitor.setGraphics2D(oldGraphics);
		}
		cachedGraphics.setGraphics(drawVisitor.getGraphics2d());
		cachedGraphics.paintCache();
	}

}
