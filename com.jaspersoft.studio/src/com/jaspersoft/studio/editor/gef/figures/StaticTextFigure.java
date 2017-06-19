/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.figures;

import java.awt.Graphics2D;

import com.jaspersoft.studio.editor.java2d.StackGraphics2D;
import com.jaspersoft.studio.jasper.JSSDrawVisitor;
import com.jaspersoft.studio.model.text.MStaticText;

import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRStaticText;
/*
 * The Class StaticTextFigure.
 */
public class StaticTextFigure extends FrameFigure {
	/**
	 * Instantiates a new static text figure.
	 */
	public StaticTextFigure(MStaticText model) {
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
		if (model != null && allowsFigureDrawCache()) {
			Graphics2D oldGraphics = drawVisitor.getGraphics2d();
			if (needRefresh(oldGraphics)) {
				model.setChangedProperty(false);
				cachedGraphics = getCachedGraphics(oldGraphics);
				drawVisitor.setGraphics2D(cachedGraphics);
				drawVisitor.visitStaticText((JRStaticText) jrElement);
				drawVisitor.setGraphics2D(oldGraphics);
			}
			cachedGraphics.setGraphics(drawVisitor.getGraphics2d());
			cachedGraphics.paintCache();
		} else {
			drawVisitor.visitStaticText((JRStaticText) jrElement);
		}	
	}
	
	@Override
	protected ACachedGraphics getCachedGraphics(Graphics2D originalGraphics) {
		return new StackGraphics2D(originalGraphics);
	}
}
