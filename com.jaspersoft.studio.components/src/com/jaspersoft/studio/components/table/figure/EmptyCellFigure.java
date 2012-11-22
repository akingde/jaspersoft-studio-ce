/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.components.table.figure;

import java.awt.Graphics2D;
import java.awt.TexturePaint;

import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.engine.export.draw.DrawVisitor;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;

import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.editor.gef.figures.FrameFigure;
import com.jaspersoft.studio.editor.gef.texture.EmptyTexture;

public class EmptyCellFigure extends FrameFigure {
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
