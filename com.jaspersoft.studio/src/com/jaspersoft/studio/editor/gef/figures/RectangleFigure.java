/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.figures;

import java.awt.Graphics2D;

import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRRectangle;

import com.jaspersoft.studio.jasper.JSSDrawVisitor;
import com.jaspersoft.studio.model.MRectangle;
/*
 * The Class RectangleFigure.
 */
public class RectangleFigure extends LineFigure {
	
	public RectangleFigure(MRectangle rectangle) {
		super(rectangle);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.editor.gef.figures.LineFigure#draw(net.sf.jasperreports.engine.export.draw.DrawVisitor,
	 * net.sf.jasperreports.engine.JRElement)
	 */
	@Override
	protected void draw(JSSDrawVisitor drawVisitor, JRElement jrElement) {
		Graphics2D oldGraphics = drawVisitor.getGraphics2d();
		if (needRefresh(oldGraphics)){
			model.setChangedProperty(false);
			cachedGraphics = getCachedGraphics(oldGraphics);
			drawVisitor.setGraphics2D(cachedGraphics);
			drawVisitor.visitRectangle((JRRectangle) jrElement);
			drawVisitor.setGraphics2D(oldGraphics);
		}
		cachedGraphics.setGraphics(drawVisitor.getGraphics2d());
		cachedGraphics.paintCache();
	}

}
