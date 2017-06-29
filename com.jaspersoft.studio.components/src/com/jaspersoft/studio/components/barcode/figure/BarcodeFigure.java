/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.barcode.figure;

import java.awt.Graphics2D;

import com.jaspersoft.studio.editor.gef.figures.ACachedGraphics;
import com.jaspersoft.studio.editor.gef.figures.JRComponentFigure;
import com.jaspersoft.studio.editor.java2d.ImageGraphics2D;
import com.jaspersoft.studio.model.MGraphicElement;

/*
 * @author Chicu Veaceslav
 * @version $Id$
 */
public class BarcodeFigure extends JRComponentFigure {

	/**
	 * Instantiates a new text field figure.
	 */
	public BarcodeFigure(MGraphicElement model) {
		super(model);
	}
	
	@Override
	protected boolean allowsFigureDrawCache() {
		return true;
	}

	/**
	 * The bar code figure is not compatible with the StackGraphics2D, since the 
	 * bar code are painted trough the batik library and the paint process is pretty
	 * complicate. In this case the ImageGraphics2D is more reliable since it act
	 * like a standard Graphics2D
	 */
	@Override
	protected ACachedGraphics getCachedGraphics(Graphics2D originalGraphics) {
		return new ImageGraphics2D(originalGraphics);
	}
}
