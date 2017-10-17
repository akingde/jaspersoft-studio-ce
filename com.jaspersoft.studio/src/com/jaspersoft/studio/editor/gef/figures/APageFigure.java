/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
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
	protected GridPainter grid = new GridPainter();
	
	/**
	 * The current page
	 */
	protected PageEditPart page;
	
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
			int pageHeight = getSize().height;

			Rectangle rectangle = new Rectangle(clientArea.x, clientArea.y, pageWidth, pageHeight);
			g.setBackgroundColor(pageBackground);
			g.fillRectangle(rectangle);
			
			setGridSize(rectangle, g);
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
	protected void setGridSize(Rectangle size, Graphics graphics) {
		if (grid.isVisible()) {
			grid.setBounds(size);
			grid.paintGrid(graphics, this);
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

	public GridPainter getGrid() {
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
