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
package com.jaspersoft.studio.components.crosstab.figure;

import java.awt.Graphics2D;
import java.awt.TexturePaint;

import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.engine.export.draw.DrawVisitor;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;

import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.editor.gef.figures.FrameFigure;
import com.jaspersoft.studio.editor.gef.texture.EmptyTexture;

public class EmptyCellFigure extends FrameFigure {

	public EmptyCellFigure() {
		super();
		setOpaque(true);
		setAlpha(50);
		setBackgroundColor(ColorConstants.white);
		setBorder(null);
		createTexture();
	}

	private Dimension d;

	public void setJRElement(JRDesignCellContents column,
			DrawVisitor drawVisitor, Dimension d) {
		this.d = d;
		super.setJRElement(null, drawVisitor);
		setSize(getElementWidth() + 3, getElementHeight() + 3);
	}

	@Override
	protected int getElementHeight() {
		return d.height;
	}

	@Override
	protected int getElementWidth() {
		return d.width;
	}

	@Override
	public void paint(Graphics graphics) {
		Rectangle b = (this instanceof HandleBounds) ? ((HandleBounds) this)
				.getHandleBounds() : this.getBounds();

		Graphics2D g = ComponentFigure.getG2D(graphics);
		if (g != null) {
			g.setPaint(tp);
			g.fillRect(b.x, b.y, b.width - 1, b.height - 1);
		}
		paintBorder(graphics);
	}

	private TexturePaint tp;

	public TexturePaint createTexture() {
		if (tp == null)
			tp = EmptyTexture.createTexture(null, null);
		return tp;
	}
}
