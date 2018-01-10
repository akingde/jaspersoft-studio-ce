/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.figures.borders;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.swt.graphics.Color;

import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.editor.java2d.J2DGraphics;

public class ElementLineBorder extends LineBorder {
	public ElementLineBorder(Color color) {
		super(color);
	}

	@Override
	public void paint(IFigure figure, Graphics graphics, Insets insets) {
		Rectangle tempRect = figure.getBounds();
		if (figure instanceof HandleBounds)
			tempRect = ((HandleBounds) figure).getHandleBounds();
		Graphics2D g = ComponentFigure.getG2D(graphics);
		if (g != null) {
			Stroke oldStroke = g.getStroke();
			g.setStroke(new BasicStroke(0.1f));

			if (getColor() != null)
				g.setColor((J2DGraphics.toAWTColor(getColor())));
			g.drawRect(tempRect.x, tempRect.y, tempRect.width - 1, tempRect.height - 1);

			g.setStroke(oldStroke);
		}
	}
}
