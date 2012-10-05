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
package com.jaspersoft.studio.components.table.figure;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;

import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.engine.export.draw.DrawVisitor;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;

import com.jaspersoft.studio.editor.gef.figures.FrameFigure;
import com.jaspersoft.studio.editor.java2d.J2DGraphics;

public class EmptyCellFigure extends FrameFigure {
	private static final Color COLOR1 = new Color(224, 224, 224);
	private static final Color COLOR2 = new Color(255, 255, 255);
	private StandardBaseColumn column;

	public EmptyCellFigure() {
		super();
		setOpaque(true);
		setAlpha(50);
		setBackgroundColor(ColorConstants.white);
		setBorder(null);
		createTexture();
	}

	public void setJRElement(StandardBaseColumn column,
			DrawVisitor drawVisitor, int height) {
		this.column = column;
		this.height = height;
		super.setJRElement(null, drawVisitor);
		setSize(getElementWidth() + 3, getElementHeight() + 3);
	}

	private int height;

	@Override
	protected int getElementHeight() {
		return height;
	}

	@Override
	protected int getElementWidth() {
		return column.getWidth();
	}

	@Override
	public void paint(Graphics graphics) {
		Rectangle b = (this instanceof HandleBounds) ? ((HandleBounds) this)
				.getHandleBounds() : this.getBounds();

		Graphics2D g = ((J2DGraphics) graphics).getGraphics2D();
		g.setPaint(tp);
		g.fillRect(b.x, b.y, b.width - 1, b.height - 1);

		paintBorder(graphics);
	}

	private TexturePaint tp;

	public TexturePaint createTexture() {
		if (tp == null) {
			int gridSize = 10;
			BufferedImage img = new BufferedImage(gridSize * 2, gridSize * 2,
					BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = img.createGraphics();

			AlphaComposite ac = AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, 0.2f);
			g2.setComposite(ac);

			g2.setColor(COLOR1);
			g2.fillRect(0, 0, 10, 10);
			g2.fillRect(10, 10, 10, 10);

			g2.setColor(COLOR2);
			g2.fillRect(10, 0, 10, 10);
			g2.fillRect(0, 10, 10, 10);

			tp = new TexturePaint(img, new java.awt.Rectangle(0, 0, gridSize,
					gridSize));
		}
		return tp;
	}
}
