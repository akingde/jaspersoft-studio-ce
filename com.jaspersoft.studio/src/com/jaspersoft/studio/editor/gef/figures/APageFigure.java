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

import org.eclipse.draw2d.FreeformLayeredPane;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;

import com.jaspersoft.studio.editor.gef.figures.layers.GridLayer;

/*
 * The Class PageFigure.
 * 
 * @author Chicu Veaceslav
 */
public abstract class APageFigure extends FreeformLayeredPane implements HandleBounds {

	/** The view margins. */
	protected boolean viewMargins = true;

	/** The Constant PAGE_BORDER. */
	public static final Insets PAGE_BORDER = new Insets(10, 10, 10, 10);

	/** The jr design. */

	private GridLayer grid = new GridLayer();

	/**
	 * Instantiates a new page figure.
	 * 
	 * @param jd
	 *          the jd
	 * @param viewMargins
	 *          the view margins
	 */
	public APageFigure(boolean viewMargins) {
		this.viewMargins = viewMargins;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.Shape#paintFigure(org.eclipse.draw2d.Graphics)
	 */
	@Override
	public void paintFigure(Graphics g) {
		if (viewMargins) {
			Rectangle clientArea = getClientArea();
			clientArea.x -= dx;
			clientArea.y -= dy;

			int pageWidth = getSize().width;
			int pageHeight = getSize().height;// + jrDesign.getTopMargin() + jrDesign.getBottomMargin();

			// int leftMargin = PAGE_BORDER.left;
			// int rightMargin = PAGE_BORDER.right;
			// int topMargin = PAGE_BORDER.top;
			// int bottomMargin = PAGE_BORDER.bottom;

			Rectangle rectangle = new Rectangle(clientArea.x, clientArea.y, pageWidth, pageHeight);
			g.fillRectangle(rectangle);

			// Point topLeft = new Point(clientArea.x + leftMargin, clientArea.y);
			// Point topRight = new Point(clientArea.x + pageWidth - rightMargin, clientArea.y);

			// Point bottomLeft = new Point(topLeft.x, clientArea.y + pageHeight);
			// Point bottomRight = new Point(topRight.x, clientArea.y + pageHeight);

			// Graphics2D graphics2d = ((J2DGraphics) g).getGraphics2D();
			// Stroke oldStroke = graphics2d.getStroke();
			// graphics2d.setStroke(J2DUtils.getInvertedZoomedStroke(oldStroke, g.getAbsoluteScale()));

			paintGrid(g, rectangle);
		}
		if (getBorder() != null)
			getBorder().paint(this, g, NO_INSETS);
	}

	protected int dx = 0;
	protected int dy = 0;

	@Override
	protected void primTranslate(int dx, int dy) {
		this.dx += dx;
		this.dy += dy;
		super.primTranslate(dx, dy);
	}

	Point origin = new Point();

	protected void paintGrid(Graphics g, Rectangle clip) {
		if (grid.isVisible()) {
			grid.setBounds(clip);
			grid.paint(g);
		}
	}

	@Override
	protected void paintBorder(Graphics graphics) {
		// super.paintBorder(graphics);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.handles.HandleBounds#getHandleBounds()
	 */
	public Rectangle getHandleBounds() {
		Rectangle clientArea = getClientArea();
		clientArea.x -= dx;
		clientArea.y -= dy;

		int pageWidth = getSize().width;
		int pageHeight = getSize().height;// + jrDesign.getTopMargin() + jrDesign.getBottomMargin();
		Insets insets = getInsets();
		return new Rectangle(clientArea.x - insets.right, clientArea.y - insets.top,
				pageWidth + insets.left + insets.right, pageHeight + insets.top + insets.bottom);
	}

	@Override
	public Rectangle getFreeformExtent() {
		Rectangle freeformExtent = super.getFreeformExtent();
		freeformExtent.height += PAGE_BORDER.bottom + 80;
		return freeformExtent;
	}

	public GridLayer getGrid() {
		return grid;
	}
}
