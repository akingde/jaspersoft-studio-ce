/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.gef.figures.borders;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.gef.handles.HandleBounds;

import com.jaspersoft.studio.editor.gef.figures.ReportPageFigure;
import com.jaspersoft.studio.editor.java2d.J2DGraphics;
import com.jaspersoft.studio.editor.java2d.J2DUtils;

/**
 * The Class ShadowBorder.
 */
public class SimpleShadowBorder extends AbstractBorder {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.Border#getInsets(org.eclipse.draw2d.IFigure)
	 */
	public Insets getInsets(IFigure figure) {
		return new Insets(ReportPageFigure.PAGE_BORDER.top);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.Border#paint(org.eclipse.draw2d.IFigure, org.eclipse.draw2d.Graphics,
	 * org.eclipse.draw2d.geometry.Insets)
	 */
	public void paint(IFigure figure, Graphics graphics, Insets insets) {

		if (graphics instanceof J2DGraphics) {
			org.eclipse.draw2d.geometry.Rectangle bounds = figure.getBounds();
			if (figure instanceof HandleBounds)
				bounds = ((HandleBounds) figure).getHandleBounds();

			paintShadowBorder(graphics, bounds.x - insets.left, bounds.y - insets.top, bounds.width + insets.right
					+ insets.left, bounds.height + insets.top + insets.bottom);
		}

	}

	/**
	 * Paint shadow border.
	 * 
	 * @param g
	 *          the g
	 * @param x
	 *          the x
	 * @param y
	 *          the y
	 * @param width
	 *          the width
	 * @param height
	 *          the height
	 */
	private void paintShadowBorder(Graphics graphics, int x, int y, int width, int height) {
		Graphics2D g = ((J2DGraphics) graphics).getGraphics2D();
		Stroke oldStroke = g.getStroke();
		g.setStroke(J2DUtils.getInvertedZoomedStroke(oldStroke, graphics.getAbsoluteScale()));

		// shadow
		g.setBackground(Color.lightGray);
		g.setColor(Color.lightGray);

		int sOffset = 5;
		g.fillRect(x + ReportPageFigure.PAGE_BORDER.left + sOffset, y + height - ReportPageFigure.PAGE_BORDER.bottom, width
				- (ReportPageFigure.PAGE_BORDER.left + ReportPageFigure.PAGE_BORDER.left), sOffset);
		g.fillRect(x + width - ReportPageFigure.PAGE_BORDER.right, y + ReportPageFigure.PAGE_BORDER.top + sOffset, sOffset, height
				- ReportPageFigure.PAGE_BORDER.bottom - ReportPageFigure.PAGE_BORDER.top);

		g.setBackground(Color.black);
		g.setColor(Color.black);
		// TOP
		g.drawLine(x + ReportPageFigure.PAGE_BORDER.left, y + ReportPageFigure.PAGE_BORDER.top, x + width
				- ReportPageFigure.PAGE_BORDER.right, y + ReportPageFigure.PAGE_BORDER.top);

		// BOTTOM
		g.drawLine(x + ReportPageFigure.PAGE_BORDER.left, y + height - ReportPageFigure.PAGE_BORDER.bottom, x + width
				- ReportPageFigure.PAGE_BORDER.right, y + height - ReportPageFigure.PAGE_BORDER.top);

		// LEFT
		g.drawLine(x + ReportPageFigure.PAGE_BORDER.left, y + ReportPageFigure.PAGE_BORDER.top, x + ReportPageFigure.PAGE_BORDER.left, y
				+ height - ReportPageFigure.PAGE_BORDER.top);

		// RIGHT
		g.drawLine(x + width - ReportPageFigure.PAGE_BORDER.right, y + ReportPageFigure.PAGE_BORDER.top, x + width
				- ReportPageFigure.PAGE_BORDER.right, y + height - ReportPageFigure.PAGE_BORDER.top);

		g.setStroke(oldStroke);
	}
}
