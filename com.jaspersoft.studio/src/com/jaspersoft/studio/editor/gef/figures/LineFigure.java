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
package com.jaspersoft.studio.editor.gef.figures;

import net.sf.jasperreports.engine.JRCommonGraphicElement;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRLine;
import net.sf.jasperreports.engine.JRPen;
import net.sf.jasperreports.engine.export.draw.DrawVisitor;

import org.eclipse.draw2d.geometry.Rectangle;

/**
 * The Class LineFigure.
 */
public class LineFigure extends AHandleBoundsFigure {
	/**
	 * Instantiates a new line figure.
	 */
	public LineFigure() {
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
		drawVisitor.visitLine((JRLine) jrElement);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.editor.gef.figures.GenericFigure#setJRElement(net.sf.jasperreports.engine.JRElement,
	 * net.sf.jasperreports.engine.export.draw.DrawVisitor)
	 */
	@Override
	public void setJRElement(JRElement jrElement, DrawVisitor drawVisitor) {
		super.setJRElement(jrElement, drawVisitor);
		// set bounds + 1/2 border
		int offset = calcBorder(getLinePen());
		Rectangle b = getBounds();
		setBounds(new Rectangle(b.x - offset, b.y - offset, b.width + offset * 2 + 1, b.height + offset * 2 + 1));
	}

	/**
	 * Gets the line pen.
	 * 
	 * @return the line pen
	 */
	protected JRPen getLinePen() {
		return ((JRCommonGraphicElement) getJrElement()).getLinePen();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.editor.gef.figures.GenericFigure#getHandleBounds()
	 */
	@Override
	public Rectangle getHandleBounds() {
		Rectangle b = getBounds();
		int offset = calcBorder(getLinePen());
		return new Rectangle(b.x + offset, b.y + offset, getJrElement().getWidth() + 1, getJrElement().getHeight() + 1);
	}

	/**
	 * Calc border.
	 * 
	 * @param jrPen
	 *          the jr pen
	 * @return the int
	 */
	protected int calcBorder(JRPen jrPen) {
		int offset = (int) Math.ceil(((double) jrPen.getLineWidth()) / 2);
		return offset;
	}
}
