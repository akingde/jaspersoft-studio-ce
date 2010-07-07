/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.gef.figures;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;

import com.jaspersoft.studio.editor.java2d.J2DGraphics;
import com.jaspersoft.studio.editor.java2d.J2DUtils;

/**
 * The Class PageFigure.
 * 
 * @author Chicu Veaceslav
 */
public class PageFigure extends RectangleFigure implements HandleBounds {
	
	/** The view margins. */
	private boolean viewMargins = true;
	
	/** The Constant PAGE_BORDER. */
	public static final Insets PAGE_BORDER = new Insets(10, 10, 10, 10);

	/** The jr design. */
	private JasperDesign jrDesign = null;
	
	/** The bands. */
	private int bands = 0;
	
	/** The bands height. */
	private int bandsHeight = 0;
	
	/** The margins color. */
	private Color marginsColor = new Color(0, 0, 255, 128);

	/**
	 * Instantiates a new page figure.
	 * 
	 * @param jd
	 *          the jd
	 * @param viewMargins
	 *          the view margins
	 */
	public PageFigure(JasperDesign jd, boolean viewMargins) {
		this.jrDesign = jd;
		this.viewMargins = viewMargins;
		/*
		 * GridLayout manager = new GridLayout(1, true); manager.marginHeight =
		 * jd.getBottomMargin(); manager.marginWidth = 0; manager.horizontalSpacing
		 * = 0; manager.verticalSpacing = 0; setLayoutManager(manager);
		 */
	}

	/**
	 * Sets the view margins.
	 * 
	 * @param viewMargins
	 *          the new view margins
	 */
	public void setViewMargins(boolean viewMargins) {
		this.viewMargins = viewMargins;
	}

	/**
	 * Sets the band number.
	 * 
	 * @param bands
	 *          the new band number
	 */
	public void setBandNumber(int bands) {
		this.bands = bands;
	}

	/**
	 * Sets the bands height.
	 * 
	 * @param bandsHeight
	 *          the new bands height
	 */
	public void setBandsHeight(int bandsHeight) {
		this.bandsHeight = bandsHeight;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.draw2d.Shape#paintFigure(org.eclipse.draw2d.Graphics)
	 */
	@Override
	public void paintFigure(Graphics g) {
		if (jrDesign != null && viewMargins) {
			Rectangle clientArea = getClientArea();

			int pageWidth = jrDesign.getPageWidth();
			int pageHeight = bandsHeight + jrDesign.getTopMargin() + jrDesign.getBottomMargin();

			int leftMargin = jrDesign.getLeftMargin();
			int rightMargin = jrDesign.getRightMargin();
			int topMargin = jrDesign.getTopMargin();
			// int bottomMargin = jrDesign.getBottomMargin();

			g.fillRectangle(new Rectangle(clientArea.x, clientArea.y, pageWidth, pageHeight));

			Point topLeft = new Point(clientArea.x + leftMargin, clientArea.y);
			Point topRight = new Point(clientArea.x + pageWidth - rightMargin, clientArea.y);

			Point bottomLeft = new Point(topLeft.x, clientArea.y + pageHeight);
			Point bottomRight = new Point(topRight.x, clientArea.y + pageHeight);

			
			// Using Graphics2D we can draw unscaled lines...

			// Stroke oldStroke = g.getStroke();
			// ((J2DGraphics)
			// g).getGraphics2D().setStroke(J2DUtils.getInvertedZoomedStroke(oldStroke,
			// g.getAbsoluteScale()));
			// ((J2DGraphics) g).getGraphics2D().setColor(marginsColor);
			// g.drawLine(topLeft.x, topLeft.y, bottomLeft.x, bottomLeft.y);
			// g.drawLine(topRight.x, topRight.y, bottomRight.x, bottomRight.y);
			// ((J2DGraphics) g).getGraphics2D().setStroke(oldStroke);

			// ((J2DGraphics) g).getGraphics2D().drawLine(r.x, r.y + topMargin, r.x +
			// r.width, r.y + topMargin);
			// Using Graphics2D we can draw unscaled lines...

			Graphics2D graphics2d = ((J2DGraphics) g).getGraphics2D();
			Stroke oldStroke = graphics2d.getStroke();
			graphics2d.setStroke(J2DUtils.getInvertedZoomedStroke(oldStroke, g.getAbsoluteScale()));
			graphics2d.setColor(marginsColor);
			
			g.drawLine(clientArea.x, clientArea.y + topMargin, clientArea.x + pageWidth, clientArea.y + topMargin);
			
			g.drawLine(topLeft.x, topLeft.y, bottomLeft.x, bottomLeft.y);
			g.drawLine(topRight.x, topRight.y, bottomRight.x, bottomRight.y);
			graphics2d.setStroke(oldStroke);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.handles.HandleBounds#getHandleBounds()
	 */
	@Override
	public Rectangle getHandleBounds() {
		Rectangle clientArea = getClientArea();
		int pageWidth = jrDesign.getPageWidth();
		int pageHeight = bandsHeight + jrDesign.getTopMargin() + jrDesign.getBottomMargin();
		Insets insets = getInsets();
		return new Rectangle(clientArea.x - insets.right, clientArea.y - insets.top,
				pageWidth + insets.left + insets.right, pageHeight + insets.top + insets.bottom);
	}
}
