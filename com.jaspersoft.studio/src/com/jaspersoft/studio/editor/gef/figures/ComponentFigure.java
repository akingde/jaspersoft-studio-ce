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

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRComponentElement;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.export.draw.DrawVisitor;

import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;

import com.jaspersoft.studio.editor.gef.decorator.IDecorator;
import com.jaspersoft.studio.editor.java2d.J2DGraphics;

/*
 * The Class GenericFigure.
 */
public class ComponentFigure extends RectangleFigure {

	/** The jr element. */
	protected JRElement jrElement;

	/** The draw visitor. */
	protected DrawVisitor drawVisitor;

	/**
	 * Instantiates a new generic figure.
	 */
	public ComponentFigure() {
		super();
		setLayoutManager(new FreeformLayout());
	}

	/**
	 * Sets the jr element.
	 * 
	 * @param jrElement
	 *          the jr element
	 * @param drawVisitor
	 *          the draw visitor
	 */
	public void setJRElement(JRElement jrElement, DrawVisitor drawVisitor) {
		this.drawVisitor = drawVisitor;
		this.jrElement = jrElement;
		if (jrElement != null)
			setSize(jrElement.getWidth(), jrElement.getHeight());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.Figure#paint(org.eclipse.draw2d.Graphics)
	 */
	@Override
	public void paint(Graphics graphics) {
		Graphics2D graphics2d = ((J2DGraphics) graphics).getGraphics2D();

		Rectangle b = (this instanceof HandleBounds) ? ((HandleBounds) this).getHandleBounds() : this.getBounds();
		try {
			graphics2d.translate(b.x, b.y);

			if (drawVisitor != null) {
				drawVisitor.setGraphics2D(graphics2d);

				draw(drawVisitor, jrElement);
			} else
				graphics2d.drawRect(b.x, b.y, b.width, b.height);
		} catch (Exception e) {
			// when a font is missing exception is thrown by DrawVisitor
			// FIXME: maybe draw something, else?
			e.printStackTrace();
		} finally {
			graphics2d.translate(-b.x, -b.y);
		}
		paintBorder(graphics);
		paintDecorators(graphics);
	}

	/**
	 * Draw.
	 * 
	 * @param drawVisitor
	 *          the draw visitor
	 * @param jrElement
	 *          the jr element
	 */
	protected void draw(DrawVisitor drawVisitor, JRElement jrElement) {
		if (jrElement instanceof JRComponentElement)
			drawVisitor.visitComponentElement((JRComponentElement) jrElement);
	}

	/**
	 * Gets the jr element.
	 * 
	 * @return the jr element
	 */
	public JRElement getJrElement() {
		return jrElement;
	}

	private void paintDecorators(Graphics graphics) {
		if (decorators == null)
			return;
		for (IDecorator d : decorators)
			d.paint(graphics, this);
	}

	private List<IDecorator> decorators;

	public void addDecorator(IDecorator decorator) {
		if (decorators == null)
			decorators = new ArrayList<IDecorator>();
		decorators.add(decorator);
	}

	public void removeDecorator(IDecorator decorator) {
		if (decorators != null)
			decorators.remove(decorator);
	}
}
