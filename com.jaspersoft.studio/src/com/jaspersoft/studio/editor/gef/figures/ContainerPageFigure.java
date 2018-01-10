/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;

import com.jaspersoft.studio.editor.gef.parts.PageEditPart;

public class ContainerPageFigure extends APageFigure {
	private Dimension containerSize;

	public ContainerPageFigure(boolean viewMargins, PageEditPart page) {
		super(viewMargins, page);
	}

	public void setContainerSize(Dimension d) {
		this.containerSize = d;
		setSize(d);
	}

	@Override
	public void paintFigure(Graphics g) {
		if (viewMargins) {
			Rectangle clientArea = getClientArea();
			clientArea.x -= dx;
			clientArea.y -= dy;
			Rectangle rectangle = new Rectangle(clientArea.x, clientArea.y, containerSize.width, containerSize.height);
			g.setBackgroundColor(pageBackground);
			g.fillRectangle(rectangle);

			setGridSize(rectangle, g);
		}
		if (getBorder() != null)
			getBorder().paint(this, g, NO_INSETS);
	}

	@Override
	public Rectangle getHandleBounds() {
		Rectangle clientArea = getClientArea();
		clientArea.x -= dx;
		clientArea.y -= dy;
		Insets insets = getInsets();
		return new Rectangle(clientArea.x - insets.right, clientArea.y - insets.top, containerSize.width + insets.left
				+ insets.right, containerSize.height + insets.top + insets.bottom);
	}

	
	/**
	 * Override the original paintChildren to avoid to pain elements 
	 * that are marked as not visible inside the model. The grid is always painted
	 */
	protected void paintChildren(Graphics graphics) {
		for (int i = 0; i < getChildren().size(); i++) {
			IFigure child = (IFigure) getChildren().get(i);
			if ((child == grid) ||child.isVisible() && isFigureVisible(child)) {
				// determine clipping areas for child
				Rectangle[] clipping = null;
				if (getClippingStrategy() != null) {
					clipping = getClippingStrategy().getClip(child);
				} else {
					// default clipping behavior is to clip at bounds
					clipping = new Rectangle[] { child.getBounds() };
				}
				// child may now paint inside the clipping areas
				for (int j = 0; j < clipping.length; j++) {
					if (clipping[j].intersects(graphics.getClip(Rectangle.SINGLETON))) {
						graphics.clipRect(clipping[j]);
						child.paint(graphics);
						graphics.restoreState();
					}
				}
			}
		}
	}
}
