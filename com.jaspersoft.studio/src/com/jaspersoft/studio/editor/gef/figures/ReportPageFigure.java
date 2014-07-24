/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.figures;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import net.sf.jasperreports.engine.base.JRBaseReport;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

import com.jaspersoft.studio.editor.java2d.J2DUtils;

/*
 * The Class PageFigure.
 * 
 * @author Chicu Veaceslav
 */
public class ReportPageFigure extends APageFigure {

	/** The bands height. */
	private int bandsHeight = 0;
	protected JRBaseReport jrDesign = null;
	/** The bands vertical lines color */
	protected Color printMarginColor = new Color(170, 168, 255);

	public void setPrintMarginColor(Color printMarginColor) {
		this.printMarginColor = printMarginColor;
	}

	/**
	 * Instantiates a new page figure.
	 * 
	 * @param jd
	 *          the jd
	 * @param viewMargins
	 *          the view margins
	 */
	public ReportPageFigure(JRBaseReport jd, boolean viewMargins) {
		super(viewMargins);
		this.jrDesign = jd;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.Shape#paintFigure(org.eclipse.draw2d.Graphics)
	 */
	@Override
	public void paintFigure(Graphics g) {
		if (jrDesign != null && viewMargins) {
			Rectangle clientArea = getClientArea();
			clientArea.x -= dx;
			clientArea.y -= dy;

			int pageWidth = jrDesign.getPageWidth();
			int pageHeight = bandsHeight;// + jrDesign.getTopMargin() + jrDesign.getBottomMargin();

			int leftMargin = jrDesign.getLeftMargin();
			int rightMargin = jrDesign.getRightMargin();
			// int topMargin = jrDesign.getTopMargin();
			// int bottomMargin = jrDesign.getBottomMargin();

			Rectangle rectangle = new Rectangle(clientArea.x, clientArea.y, pageWidth, pageHeight);
			g.setBackgroundColor(pageBackground);
			g.fillRectangle(rectangle);

			Point topLeft = new Point(clientArea.x + leftMargin, clientArea.y);
			Point topRight = new Point(clientArea.x + pageWidth - rightMargin, clientArea.y);

			Point bottomLeft = new Point(topLeft.x, clientArea.y + pageHeight);
			Point bottomRight = new Point(topRight.x, clientArea.y + pageHeight);
			Graphics2D graphics2d = ComponentFigure.getG2D(g);
			if (graphics2d != null) {
				Stroke oldStroke = graphics2d.getStroke();

				paintGrid(g, rectangle);

				graphics2d.setColor(printMarginColor);

				graphics2d.setStroke(new BasicStroke(0.5f));
				graphics2d.setStroke(J2DUtils.getInvertedZoomedStroke(graphics2d.getStroke(), g.getAbsoluteScale()));

				// g.drawLine(clientArea.x, clientArea.y + topMargin, clientArea.x + pageWidth, clientArea.y + topMargin);
				g.drawLine(topLeft.x, topLeft.y, bottomLeft.x, bottomLeft.y);
				g.drawLine(topRight.x, topRight.y, bottomRight.x, bottomRight.y);
				graphics2d.setStroke(oldStroke);
			}
		}
		if (getBorder() != null)
			getBorder().paint(this, g, NO_INSETS);
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

		int pageWidth = jrDesign.getPageWidth();
		int pageHeight = bandsHeight;// + jrDesign.getTopMargin() + jrDesign.getBottomMargin();
		Insets insets = getInsets();
		return new Rectangle(clientArea.x - insets.right, clientArea.y - insets.top,
				pageWidth + insets.left + insets.right, pageHeight + insets.top + insets.bottom);
	}

}
