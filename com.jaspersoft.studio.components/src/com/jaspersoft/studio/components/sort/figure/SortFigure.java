/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.components.sort.figure;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import net.sf.jasperreports.components.sort.SortComponent;
import net.sf.jasperreports.engine.JRComponentElement;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.export.draw.DrawVisitor;
import net.sf.jasperreports.engine.type.HorizontalAlignEnum;
import net.sf.jasperreports.engine.type.ModeEnum;
import net.sf.jasperreports.engine.type.VerticalAlignEnum;
import net.sf.jasperreports.engine.util.JRColorUtil;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;

import com.jaspersoft.studio.editor.gef.figures.FrameFigure;
import com.jaspersoft.studio.editor.java2d.J2DGraphics;

/**
 * 
 * @author veaceslav chicu
 * 
 */
public class SortFigure extends FrameFigure {

	/**
	 * Instantiates a new map figure.
	 */
	public SortFigure() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jaspersoft.studio.editor.gef.figures.ComponentFigure#draw(net.sf.
	 * jasperreports.engine.export.draw.DrawVisitor,
	 * net.sf.jasperreports.engine.JRElement)
	 */
	@Override
	protected void draw(DrawVisitor drawVisitor, JRElement jrElement) {
		drawVisitor.visitComponentElement((JRComponentElement) jrElement);
	}

	@Override
	public void paint(Graphics graphics) {
		Graphics2D gr = ((J2DGraphics) graphics).getGraphics2D();
		Color oldColor = gr.getColor();
		Font oldFont = gr.getFont();
		Shape oldClip = gr.getClip();

		AffineTransform af = gr.getTransform();

		try {
			Rectangle r = (this instanceof HandleBounds) ? ((HandleBounds) this)
					.getHandleBounds() : this.getBounds();
			// Graphics2D newGraphics = (Graphics2D) graphics2d.create(b.x, b.y,
			// b.width, b.height);

			gr.translate(r.x, r.y);

			AffineTransform new_af = (AffineTransform) af.clone();
			AffineTransform translate = AffineTransform.getTranslateInstance(
					getInsets().left + r.x, getInsets().top + r.y);
			new_af.concatenate(translate);
			gr.setTransform(new_af);

			JRElement e = getJrElement();

			// Composite oldComposite = gr.getComposite();

			Shape rect = new Rectangle2D.Float(0, 0, e.getWidth(),
					e.getHeight());
			gr.clip(rect);

			if (gr.getBackground() != null
					&& e.getModeValue() == ModeEnum.OPAQUE) {
				gr.setColor(e.getBackcolor());
				gr.fillRect(0, 0, e.getWidth(), e.getHeight());
			}

			// Draw the small arrow in the center...

			SortComponent c = (SortComponent) ((JRDesignComponentElement) e)
					.getComponent();

			int size = 10;
			try {
				size = Integer.parseInt(c.getHandlerFontSize());
			} catch (Exception ex) {
				size = 10;
			}

			Font f = new java.awt.Font("Dialog", Font.PLAIN, size);

			gr.setFont(f);
			Rectangle2D stringBounds = gr.getFontMetrics().getStringBounds(
					"\u25B2", gr);

			Color col = Color.white;
			if (c.getHandlerColor() != null)
				col = JRColorUtil.getColor(c.getHandlerColor(), null);

			gr.setColor(col);

			int x = 0;
			if (c.getHandlerHorizontalAlign() == null)
				x = 0;
			else if (c.getHandlerHorizontalAlign() == HorizontalAlignEnum.CENTER)
				x = (int) ((e.getWidth() - stringBounds.getWidth()) / 2);
			else if (c.getHandlerHorizontalAlign() == HorizontalAlignEnum.RIGHT)
				x = (int) (e.getWidth() - stringBounds.getWidth());

			int y = 0;
			if (c.getHandlerVerticalAlign() == null)
				y = (int) (stringBounds.getHeight());
			else if (c.getHandlerVerticalAlign() == VerticalAlignEnum.TOP)
				y = (int) (stringBounds.getHeight());
			else if (c.getHandlerVerticalAlign() == VerticalAlignEnum.MIDDLE)
				y = (int) ((e.getHeight() + stringBounds.getHeight()) / 2);
			else if (c.getHandlerVerticalAlign() == VerticalAlignEnum.BOTTOM)
				y = (int) (e.getHeight());

			gr.drawString("\u25B2", x, y);

		} catch (Exception ex) {
		} finally {
			gr.setTransform(af);
			gr.setColor(oldColor);
			gr.setFont(oldFont);
			gr.setClip(oldClip);
		}
		// super.paintWidgetImplementation();
	}

}
