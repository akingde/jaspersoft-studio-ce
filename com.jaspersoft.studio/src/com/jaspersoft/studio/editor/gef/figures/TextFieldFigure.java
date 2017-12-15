/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.figures;

import java.awt.Graphics2D;

import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRTextField;

import com.jaspersoft.studio.jasper.JSSDrawVisitor;
import com.jaspersoft.studio.model.text.MTextField;
/*
 * The Class TextFieldFigure.
 * 
 * @author Chicu Veaceslav
 */
public class TextFieldFigure extends FrameFigure {

	/**
	 * Instantiates a new text field figure.
	 */
	public TextFieldFigure(MTextField model) {
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
		if (model != null && allowsFigureDrawCache()){
			Graphics2D oldGraphics = drawVisitor.getGraphics2d();
			if (needRefresh(oldGraphics)){
				cachedGraphics = getCachedGraphics(oldGraphics);
				drawVisitor.setGraphics2D(cachedGraphics);
				drawVisitor.visitTextField((JRTextField) jrElement);
				drawVisitor.setGraphics2D(oldGraphics);
				model.setChangedProperty(false);
			}
			cachedGraphics.setGraphics(drawVisitor.getGraphics2d());
			cachedGraphics.paintCache();
		} else {
			drawVisitor.visitTextField((JRTextField) jrElement);
		}
	}
}
