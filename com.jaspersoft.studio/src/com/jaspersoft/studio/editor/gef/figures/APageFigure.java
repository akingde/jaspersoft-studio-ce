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

import org.eclipse.draw2d.FreeformLayeredPane;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.wb.swt.SWTResourceManager;

import com.jaspersoft.studio.editor.gef.figures.layers.GridLayer;
import com.jaspersoft.studio.editor.gef.parts.JSSScalableFreeformRootEditPart;
import com.jaspersoft.studio.editor.gef.parts.PageEditPart;
import com.jaspersoft.studio.editor.java2d.JSSScrollingGraphicalViewer;

/*
 * The Class PageFigure.
 * 
 * @author Chicu Veaceslav
 */
public abstract class APageFigure extends FreeformLayeredPane implements HandleBounds {
	protected org.eclipse.swt.graphics.Color pageBackground = SWTResourceManager.getColor(255, 255, 255);
	/** The view margins. */
	protected boolean viewMargins = true;

	/** The Constant PAGE_BORDER. */
	public static final Insets PAGE_BORDER = new Insets(10, 10, 10, 10);
	
	protected Point origin = new Point();

	/**
	 * The grid figure, it is the first figure added to the page
	 */
	protected GridLayer grid = new GridLayer();
	
	/**
	 * The current page
	 */
	private PageEditPart page;
	
	/**
	 * The zoom manager for the current page
	 */
	private ZoomManager zoomManager = null;
	
	/**
	 * The viewport for the current page
	 */
	private Viewport viewPort = null;

	/**
	 * Instantiates a new page figure.
	 * 
	 * @param jd
	 *          the jd
	 * @param viewMargins
	 *          the view margins
	 */
	public APageFigure(boolean viewMargins, PageEditPart page) {
		this.viewMargins = viewMargins;
		this.page = page;
		//add the grid figure as first child of the page
		add(grid);
	}

	public void setPageBackground(org.eclipse.swt.graphics.Color pageBackground) {
		this.pageBackground = pageBackground;
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
			g.setBackgroundColor(pageBackground);
			g.fillRectangle(rectangle);

			// Point topLeft = new Point(clientArea.x + leftMargin, clientArea.y);
			// Point topRight = new Point(clientArea.x + pageWidth - rightMargin, clientArea.y);

			// Point bottomLeft = new Point(topLeft.x, clientArea.y + pageHeight);
			// Point bottomRight = new Point(topRight.x, clientArea.y + pageHeight);

			// Graphics2D graphics2d = ((J2DGraphics) g).getGraphics2D();
			// Stroke oldStroke = graphics2d.getStroke();
			// graphics2d.setStroke(J2DUtils.getInvertedZoomedStroke(oldStroke, g.getAbsoluteScale()));

			setGridSize(rectangle);
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


	/**
	 * Set the size of the grid figure. Also assure that the grid is always 
	 * the first figure, to be under any other. This check is necessary since
	 * this is a special figure that has not an edit part or a model and in
	 * the children refresh operation could be moved in some cases
	 * 
	 * @param size a not null rectangle with the size of the grid figure
	 */
	protected void setGridSize(Rectangle size) {
		if (grid.isVisible()) {
			//There is for sure a child, since the grid figure is added on the creation
			if (getChildren().get(0) != grid){
				remove(grid);
				add(grid, 0);
			}
			grid.setBounds(size);
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
	
	
	/**
	 * Return the current viewport. The first time it is returned it's also cached
	 * 
	 * @return the editor viewport or null if it can't be found
	 */
	protected Viewport getViewPort(){
		if (viewPort == null){
			IFigure figure = getParent();
			while (figure != null && !(figure instanceof Viewport)){
				figure = figure.getParent();
			}
			if (figure != null) viewPort = (Viewport)figure;
		}
		return viewPort;
	}
	
	/**
	 * Return the current zoom level
	 * 
	 * @return the current zoom level or 0d if it can't be found
	 */
	protected double getZoom(){
		if (zoomManager == null){
			EditPartViewer viewer = page.getViewer();
			zoomManager = (ZoomManager)viewer.getProperty(ZoomManager.class.toString());
			if (zoomManager == null){
				//fallback
				zoomManager = ((JSSScalableFreeformRootEditPart) viewer.getRootEditPart()).getZoomManager();
			}
		}
		return zoomManager != null ? zoomManager.getZoom() : 1d;
	}
	
	
	/**
	 * Check if a figure intersect the current visible area
	 * 
	 * @param figure a figure
	 * @return true if the figure intersect the visible area, false otherwise
	 */
	public boolean isFigureVisible(IFigure figure){
		boolean paintOnlyVisible = ((JSSScrollingGraphicalViewer)page.getViewer()).isPaintOnlyVisibleElements();
		if (!paintOnlyVisible) return true;
		if (figure == null) return false;
		double zoom = getZoom();
		Rectangle visibleArea = getViewPort().getClientArea();
		Rectangle bounds = figure.getBounds();
		int figureStartX = (int)Math.round(bounds.x*zoom);
		int figureStartY = (int)Math.round(bounds.y*zoom);
		int figureEndX = (int)Math.round(bounds.width*zoom);
		int fiugreEndY =  (int)Math.round(bounds.height*zoom);
		Rectangle figureArea = new Rectangle(figureStartX, figureStartY, figureEndX, fiugreEndY);
		boolean result = figureArea.intersects(visibleArea);
		                          
		return result;
	}
}
