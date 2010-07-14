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

import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRFrame;
import net.sf.jasperreports.engine.JRLineBox;
import net.sf.jasperreports.engine.export.draw.DrawVisitor;

import org.eclipse.draw2d.geometry.Rectangle;

/**
 * The Class FrameFigure.
 * 
 * @author Chicu Veaceslav
 */
public class FrameFigure extends GenericFigure {

	/**
	 * Instantiates a new text field figure.
	 */
	public FrameFigure() {
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
		drawVisitor.visitFrame((JRFrame) jrElement);
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
		Rectangle o = calcBorder(getLineBox());
		Rectangle b = getBounds();
		setBounds(new Rectangle(b.x - o.x, b.y - o.y, b.width + o.x + o.width + 1, b.height + o.y + o.height + 1));
	}

	/**
	 * Gets the line box.
	 * 
	 * @return the line box
	 */
	protected JRLineBox getLineBox() {
		return ((JRFrame) getJrElement()).getLineBox();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.editor.gef.figures.GenericFigure#getHandleBounds()
	 */
	@Override
	public Rectangle getHandleBounds() {
		Rectangle b = getBounds();
		Rectangle o = calcBorder(getLineBox());
		return new Rectangle(b.x + o.x, b.y + o.y, getJrElement().getWidth() + o.width, getJrElement().getHeight()
				+ o.height);
	}

	/**
	 * Calc border.
	 * 
	 * @param jrLineBox
	 *          the jr line box
	 * @return the rectangle
	 */
	protected Rectangle calcBorder(JRLineBox jrLineBox) {
		int x = (int) Math.ceil(jrLineBox.getLeftPen().getLineWidth() / 2);
		int y = (int) Math.ceil(jrLineBox.getTopPen().getLineWidth() / 2);
		int w = (int) Math.ceil(jrLineBox.getRightPen().getLineWidth() / 2) + 1;
		int h = (int) Math.ceil(jrLineBox.getBottomPen().getLineWidth() / 2) + 1;
		return new Rectangle(x, y, w, h);
	}
}
