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
package com.jaspersoft.studio.editor.gef.figures;

import net.sf.jasperreports.engine.JRBoxContainer;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRFrame;
import net.sf.jasperreports.engine.JRLineBox;
import net.sf.jasperreports.engine.export.draw.DrawVisitor;

import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/*
 * The Class FrameFigure.
 * 
 * @author Chicu Veaceslav
 */
public class FrameFigure extends AHandleBoundsFigure {

	/**
	 * Instantiates a new text field figure.
	 */
	public FrameFigure() {
		super();
		setLayoutManager(new XYLayout());
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
	public void setJRElement(JRElement jrElement, DrawVisitor drawVisitor, JasperReportsConfiguration fileResolver) {
		super.setJRElement(jrElement, drawVisitor, fileResolver);
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
		JRElement jrElement = getJrElement();
		JRLineBox box = null;
		if (jrElement != null) {
			if (jrElement instanceof JRBoxContainer)
				box = ((JRBoxContainer) jrElement).getLineBox();
			if (box == null && jrElement.getStyle() != null)
				box = jrElement.getStyle().getLineBox();
		} else {
			box = null;
		}
		return box;
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
		return new Rectangle(b.x + o.x, b.y + o.y, getElementWidth() + 1, getElementHeight() + 1);
		// return new Rectangle(b.x + o.x, b.y + o.y, getJrElement().getWidth() + o.width - 1, getJrElement().getHeight()
		// + o.height - 1);
	}

	protected int getElementWidth() {
		return getJrElement() != null ? getJrElement().getWidth() : 0;
	}

	protected int getElementHeight() {
		return getJrElement() != null ? getJrElement().getHeight() : 0;
	}

	/**
	 * Calc border.
	 * 
	 * @param jrLineBox
	 *          the jr line box
	 * @return the rectangle
	 */
	protected Rectangle calcBorder(JRLineBox jrLineBox) {
		if (jrLineBox == null)
			return new Rectangle(0, 0, 0, 0);
		int x = (int) Math.ceil(jrLineBox.getLeftPen().getLineWidth() / 2);
		int y = (int) Math.ceil(jrLineBox.getTopPen().getLineWidth() / 2);
		int w = (int) Math.ceil(jrLineBox.getRightPen().getLineWidth() / 2) + 1;
		int h = (int) Math.ceil(jrLineBox.getBottomPen().getLineWidth() / 2) + 1;
		return new Rectangle(x, y, w, h);
	}
}
