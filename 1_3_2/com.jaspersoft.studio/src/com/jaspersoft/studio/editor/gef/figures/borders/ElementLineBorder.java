/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
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
