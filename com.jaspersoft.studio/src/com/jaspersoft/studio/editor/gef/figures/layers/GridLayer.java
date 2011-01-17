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
package com.jaspersoft.studio.editor.gef.figures.layers;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

import com.jaspersoft.studio.editor.java2d.J2DGraphics;
import com.jaspersoft.studio.editor.java2d.J2DUtils;

public class GridLayer extends org.eclipse.gef.editparts.GridLayer {
	public GridLayer() {
		super();
		setForegroundColor(ColorConstants.lightGray);
	}

	@Override
	protected void paintGrid(Graphics g) {
		Graphics2D graphics2d = ((J2DGraphics) g).getGraphics2D();
		Stroke oldStroke = graphics2d.getStroke();

		graphics2d.setStroke(new BasicStroke(0.1f));
		Rectangle clip = getBounds();// g.getClip(Rectangle.SINGLETON);
		if (gridX > 0) {
			if (origin.x >= clip.x)
				while (origin.x - gridX >= clip.x)
					origin.x -= gridX;
			else
				while (origin.x < clip.x)
					origin.x += gridX;
			int j = 0;
			for (int i = origin.x; i < clip.x + clip.width; i += gridX) {
				if (j == 10) {
					graphics2d.setStroke(new BasicStroke(0.5f));
					j = 0;
				} else
					graphics2d.setStroke(new BasicStroke(0.1f));
				
				graphics2d.setStroke(J2DUtils.getInvertedZoomedStroke(graphics2d.getStroke(), g.getAbsoluteScale()));
				graphics2d.drawLine(i, clip.y, i, clip.y + clip.height);
				j++;
			}
		}
		if (gridY > 0) {
			if (origin.y >= clip.y)
				while (origin.y - gridY >= clip.y)
					origin.y -= gridY;
			else
				while (origin.y < clip.y)
					origin.y += gridY;
			int j = 0;
			for (int i = origin.y; i < clip.y + clip.height; i += gridY) {
				if (j == 10) {
					graphics2d.setStroke(new BasicStroke(0.5f));
					j = 0;
				} else
					graphics2d.setStroke(new BasicStroke(0.1f));
				
				graphics2d.setStroke(J2DUtils.getInvertedZoomedStroke(graphics2d.getStroke(), g.getAbsoluteScale()));
				graphics2d.drawLine(clip.x, i, clip.x + clip.width, i);
				j++;
			}
		}
		graphics2d.setStroke(oldStroke);
	}

}
