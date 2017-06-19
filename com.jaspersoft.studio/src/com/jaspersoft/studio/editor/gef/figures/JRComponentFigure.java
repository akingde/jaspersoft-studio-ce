/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.figures;

import java.awt.Graphics2D;

import net.sf.jasperreports.engine.JRComponentElement;
import net.sf.jasperreports.engine.JRElement;

import com.jaspersoft.studio.jasper.JSSDrawVisitor;
import com.jaspersoft.studio.model.MGraphicElement;

public class JRComponentFigure extends FrameFigure {
	
	
	public JRComponentFigure(MGraphicElement model) {
		super(model);
	}
	
	public JRComponentFigure() {
		super(null);
	}

	@Override
	protected void draw(JSSDrawVisitor drawVisitor, JRElement jrElement) {
		if (model == null || !allowsFigureDrawCache()){
			//fallback, used the default draw method
			drawVisitor.visitComponentElement((JRComponentElement) jrElement);
			return;
		} else {
			Graphics2D oldGraphics = drawVisitor.getGraphics2d();
			if (needRefresh(oldGraphics)){
				model.setChangedProperty(false);
				cachedGraphics = getCachedGraphics(oldGraphics);
				drawVisitor.setGraphics2D(cachedGraphics);
				drawVisitor.visitComponentElement((JRComponentElement) jrElement);
				drawVisitor.setGraphics2D(oldGraphics);
			}
		}
		cachedGraphics.setGraphics(drawVisitor.getGraphics2d());
		cachedGraphics.paintCache();
	}
	
	@Override
	protected boolean allowsFigureDrawCache() {
		return false;
	}

}
