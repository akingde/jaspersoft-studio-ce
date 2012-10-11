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

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;

import com.jaspersoft.studio.editor.gef.figures.APageFigure;
import com.jaspersoft.studio.editor.gef.figures.layers.GridLayer;

public class WhenNoDataCellFigure extends CellFigure {

	public WhenNoDataCellFigure() {
		super();
	}

	// @Override
	// public void setBorder(Border border) {
	// super.setBorder(new ShadowBorder());
	// }
	//
	// protected void paintBorder(Graphics graphics) {
	// if (getBorder() != null)
	// getBorder().paint(this, graphics, getBorder().getInsets(this));
	// }

	@Override
	public void paint(Graphics graphics) {
		Rectangle b = (this instanceof HandleBounds) ? ((HandleBounds) this)
				.getHandleBounds() : this.getBounds();
		// graphics.translate(b.x, b.y);
		graphics.setBackgroundColor(ColorConstants.white);
		graphics.fillRectangle(b.x, b.y, b.width, b.height);

		// graphics.setForegroundColor(ColorConstants.blue);
		// graphics.setBackgroundColor(ColorConstants.lightGray);
		// graphics.setLineWidthFloat(0.1f);
		// graphics.drawRectangle(b.x, b.y, b.width, b.height);
		super.paint(graphics);
		if (getParent() instanceof APageFigure) {
			GridLayer grid = ((APageFigure) getParent()).getGrid();
			if (grid.isVisible()) {
				grid.setBounds(b);
				grid.paint(graphics);
			}
		}

	}

}
