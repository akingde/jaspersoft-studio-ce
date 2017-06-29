/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.figures.borders;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.swt.graphics.Color;

public class Line1Border extends LineBorder {
	@Override
	public void paint(IFigure figure, Graphics graphics, Insets insets) {
		org.eclipse.swt.graphics.Color c = getColor();
		setColor(ColorConstants.white);
		super.paint(figure, graphics, insets);
		setColor(c);
		setWidth(getWidth() - 1);
		super.paint(figure, graphics, insets);
		setWidth(getWidth() + 1);
	}

	public Line1Border() {
		super();
	}

	public Line1Border(Color color, int width, int style) {
		super(color, width, style);
	}

	public Line1Border(Color color, int width) {
		super(color, width);
	}

	public Line1Border(Color color) {
		super(color);
	}

	public Line1Border(int width) {
		super(width);
	}
}
