/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
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

import com.jaspersoft.studio.editor.java2d.J2DGraphics;
/*
 * The Class ShadowBorder.
 */
public class CornerBorder extends LineBorder {
	private int o = 5;

	public CornerBorder(Color color, int lenght) {
		super(color);
		this.o = lenght;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.Border#paint(org.eclipse.draw2d.IFigure, org.eclipse.draw2d.Graphics,
	 * org.eclipse.draw2d.geometry.Insets)
	 */
	@Override
	public void paint(IFigure figure, Graphics graphics, Insets insets) {
		Rectangle bounds = figure.getBounds();
		if (figure instanceof HandleBounds)
			bounds = ((HandleBounds) figure).getHandleBounds();

		if (graphics instanceof J2DGraphics) {
			Graphics2D g = ((J2DGraphics) graphics).getGraphics2D();
			Stroke oldStroke = g.getStroke();

			g.setStroke(new BasicStroke(0.1f));

			if (getColor() != null)
				g.setColor((J2DGraphics.toAWTColor(getColor())));

			int bottom = bounds.y + bounds.height - 1;
			int right = bounds.x + bounds.width - 1;

			// top left
			g.drawLine(bounds.x, bounds.y, bounds.x + o, bounds.y);
			g.drawLine(bounds.x, bounds.y, bounds.x, bounds.y + o);
			// top right
			g.drawLine(right - o, bounds.y, right, bounds.y);
			g.drawLine(right, bounds.y, right, bounds.y + o);
			// bottom left
			g.drawLine(bounds.x, bottom, bounds.x + o, bottom);
			g.drawLine(bounds.x, bottom, bounds.x, bottom - o);
			// bottom right
			g.drawLine(right - o, bottom, right, bottom);
			g.drawLine(right, bottom, right, bottom - o);
			if (bounds.width > 50) {
				g.drawLine(bounds.x + bounds.width / 2 - o / 2, bounds.y, bounds.x + bounds.width / 2 + o / 2, bounds.y);
				g.drawLine(bounds.x + bounds.width / 2 - o / 2, bottom, bounds.x + bounds.width / 2 + o / 2, bottom);
			}
			g.setStroke(oldStroke);
		}
	}
}
