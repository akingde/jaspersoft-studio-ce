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

import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRImage;
import net.sf.jasperreports.engine.JRLineBox;
import net.sf.jasperreports.engine.JRPen;
import net.sf.jasperreports.engine.export.draw.DrawVisitor;

import org.eclipse.draw2d.geometry.Rectangle;
/*
 * The Class ChartFigure.
 */
public class ImageFigure extends FrameFigure {

	/**
	 * Instantiates a new ImageFigure.
	 */
	public ImageFigure() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jaspersoft.studio.editor.gef.figures.GenericFigure#draw(net.sf.jasperreports.engine.export.draw.DrawVisitor,
	 * net.sf.jasperreports.engine.JRElement)
	 */
	@Override
	protected void draw(DrawVisitor drawVisitor, JRElement jrElement) {
		drawVisitor.visitImage((JRImage) jrElement);
	}

	protected JRPen getLinePen() {
		return ((JRImage) getJrElement()).getLinePen();
	}

	protected Rectangle calcBorder(JRLineBox jrLineBox) {
		int x = (int) Math.ceil(jrLineBox.getLeftPen().getLineWidth() / 2);
		int y = (int) Math.ceil(jrLineBox.getTopPen().getLineWidth() / 2);
		int w = (int) Math.ceil(jrLineBox.getRightPen().getLineWidth() / 2);
		int h = (int) Math.ceil(jrLineBox.getBottomPen().getLineWidth() / 2);

		if (x == 0 && y == 0 && w == 0 && h == 0) {
			JRPen jrPen = getLinePen();
			x = (int) Math.ceil(jrPen.getLineWidth() / 2);
			y = x;
			w = x;
			h = x;
		}
		return new Rectangle(x, y, w, h);
	}

}
