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
package com.jaspersoft.studio.editor.gef.figures;

import java.awt.Graphics2D;
import java.awt.Stroke;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

import com.jaspersoft.studio.editor.java2d.J2DGraphics;
import com.jaspersoft.studio.editor.java2d.J2DUtils;
/*
 * The Class BandFigure.
 */
public class BandFigure extends RectangleFigure {
	private static Color marginsColor = new Color(null, 0, 0, 255);
	private int columnNumber = 1;
	private int columnSpacing = 0;
	private int columnWidth = 0;

	private int marginLeft = 0;
	private int marginRight = 0;

	private boolean drawColumn = false;

	public static Color getMarginsColor() {
		return marginsColor;
	}

	public static void setMarginsColor(Color marginsColor) {
		BandFigure.marginsColor = marginsColor;
	}

	public int getColumnNumber() {
		return columnNumber;
	}

	public void setColumnNumber(int columnNumber) {
		this.columnNumber = columnNumber;
	}

	public int getColumnSpacing() {
		return columnSpacing;
	}

	public void setColumnSpacing(int columnSpacing) {
		this.columnSpacing = columnSpacing;
	}

	public int getColumnWidth() {
		return columnWidth;
	}

	public void setColumnWidth(int columnWidth) {
		this.columnWidth = columnWidth;
	}

	public int getMarginLeft() {
		return marginLeft;
	}

	public void setMarginLeft(int marginLeft) {
		this.marginLeft = marginLeft;
	}

	public int getMarginRight() {
		return marginRight;
	}

	public void setMarginRight(int marginRight) {
		this.marginRight = marginRight;
	}

	/**
	 * Instantiates a new band figure.
	 */
	public BandFigure(boolean drawColumn) {
		super();
		setLayoutManager(new FreeformLayout());
		setOpaque(false);
		this.drawColumn = drawColumn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.Figure#paint(org.eclipse.draw2d.Graphics)
	 */
	public void paint(Graphics graphics) {
		Rectangle b = getBounds();
		if (b.height == 0) {
			graphics.setForegroundColor(ColorConstants.red);
			graphics.setBackgroundColor(ColorConstants.red);
		}
		graphics.setForegroundColor(marginsColor);
		graphics.setBackgroundColor(marginsColor);

		graphics.setAlpha(128);
		if (graphics instanceof J2DGraphics) {
			Graphics2D g = ((J2DGraphics) graphics).getGraphics2D();
			Stroke oldStroke = g.getStroke();
			g.setStroke(J2DUtils.getInvertedZoomedStroke(oldStroke, graphics.getAbsoluteScale()));

			g.drawLine(b.x, b.y, b.x + b.width, b.y);
			g.drawLine(b.x, b.y + b.height - 1, b.x + b.width, b.y + b.height - 1);

			if (drawColumn) {
				int x = marginLeft + ReportPageFigure.PAGE_BORDER.left;
				for (int i = 0; i < columnNumber; i++) {
					if (i > 0)
						g.drawLine(x, b.y, x, b.y + b.height + 1);
					x += columnWidth;
					if (i < columnNumber-1)
						g.drawLine(x, b.y, x, b.y + b.height + 1);
					x += columnSpacing;
				}
			}

			g.setStroke(oldStroke);
		} else
			graphics.drawLine(b.x, b.y + b.height - 1, b.x + b.width, b.y + b.height - 1);

	}

}
