/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.gef.figures.borders;

import java.awt.Graphics2D;
import java.awt.Stroke;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.swt.graphics.Color;

import com.jaspersoft.studio.editor.java2d.J2DGraphics;
import com.jaspersoft.studio.editor.java2d.J2DUtils;

public class ElementLineBorder extends LineBorder {
	public ElementLineBorder(Color color) {
		super(color);
	}

	@Override
	public void paint(IFigure figure, Graphics graphics, Insets insets) {
		tempRect.setBounds(getPaintRectangle(figure, insets));
		if (getWidth() % 2 == 1) {
			tempRect.width--;
			tempRect.height--;
		}
		tempRect.shrink(getWidth() / 2, getWidth() / 2);
		if (graphics instanceof J2DGraphics) {
			Graphics2D g = ((J2DGraphics) graphics).getGraphics2D();
			Stroke oldStroke = g.getStroke();
			g.setStroke(J2DUtils.getInvertedZoomedStroke(oldStroke, graphics.getAbsoluteScale()));

			if (getColor() != null)
				g.setColor((J2DGraphics.toAWTColor(getColor())));
			g.drawRect(tempRect.x, tempRect.y, tempRect.width, tempRect.height);

			g.setStroke(oldStroke);
		}
	}
}
