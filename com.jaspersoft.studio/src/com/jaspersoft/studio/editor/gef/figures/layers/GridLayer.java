package com.jaspersoft.studio.editor.gef.figures.layers;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

import com.jaspersoft.studio.editor.java2d.J2DGraphics;

public class GridLayer extends org.eclipse.gef.editparts.GridLayer {
	public GridLayer() {
		super();
		// setSpacing(new Dimension(10, 10));
		setForegroundColor(ColorConstants.lightGray);
	}

	@Override
	protected void paintGrid(Graphics g) {
		Graphics2D graphics2d = ((J2DGraphics) g).getGraphics2D();
		Stroke oldStroke = graphics2d.getStroke();

		graphics2d.setStroke(new BasicStroke(0.1f));
		Rectangle clip = getBounds();// g.getClip(Rectangle.SINGLETON);
		// origin = new Point(clip.x, clip.y);
		if (gridX > 0) {
			if (origin.x >= clip.x)
				while (origin.x - gridX >= clip.x)
					origin.x -= gridX;
			else
				while (origin.x < clip.x)
					origin.x += gridX;
			int j = 0;
			for (int i = origin.x; i < clip.x + clip.width; i += gridX) {
				if (j > 10) {
					graphics2d.setStroke(new BasicStroke(0.5f));
					// g.setLineWidthFloat(1.0f);
					j = 0;
				} else
					graphics2d.setStroke(new BasicStroke(0.1f));
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
				if (j > 10) {
					graphics2d.setStroke(new BasicStroke(0.5f));
					// g.setLineWidthFloat(1.0f);
					j = 0;
				} else
					graphics2d.setStroke(new BasicStroke(0.1f));
				// g.setLineWidthFloat(0.1f);

				graphics2d.drawLine(clip.x, i, clip.x + clip.width, i);
				j++;
			}
		}
		graphics2d.setStroke(oldStroke);
	}

}
