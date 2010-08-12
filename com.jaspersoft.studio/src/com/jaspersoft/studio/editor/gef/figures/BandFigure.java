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

import java.awt.Graphics2D;
import java.awt.Stroke;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

import com.jaspersoft.studio.editor.java2d.J2DGraphics;
import com.jaspersoft.studio.editor.java2d.J2DUtils;

/**
 * The Class BandFigure.
 */
public class BandFigure extends RectangleFigure {
	private static Color marginsColor = new Color(null, 0, 0, 255);

	/**
	 * Instantiates a new band figure.
	 */
	public BandFigure() {
		super();
		setLayoutManager(new FreeformLayout());
		setOpaque(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.Figure#paint(org.eclipse.draw2d.Graphics)
	 */
	public void paint(Graphics graphics) {
		Rectangle b = getBounds();
		if (b.height == 0) {
			graphics.setForegroundColor(ColorConstants.red);
			graphics.setBackgroundColor(ColorConstants.red);
		}
		graphics.setForegroundColor(marginsColor);
		graphics.setBackgroundColor(marginsColor);

		graphics.setAlpha(128);
		if (graphics instanceof J2DGraphics) {
			Graphics2D g = ((J2DGraphics) graphics).getGraphics2D();
			Stroke oldStroke = g.getStroke();
			g.setStroke(J2DUtils.getInvertedZoomedStroke(oldStroke, graphics.getAbsoluteScale()));

			g.drawLine(b.x, b.y, b.x + b.width, b.y);
			g.drawLine(b.x, b.y + b.height - 1, b.x + b.width, b.y + b.height - 1);

			//g.fillRect(b.x, b.y, 5, b.height - 1);

			g.setStroke(oldStroke);
		} else
			graphics.drawLine(b.x, b.y + b.height - 1, b.x + b.width, b.y + b.height - 1);

	}

}
