/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.figures;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;

import org.eclipse.core.runtime.Assert;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.swt.graphics.Color;

import com.jaspersoft.studio.editor.java2d.J2DUtils;

/**
 * Class that contains all the method to paint a grid on a graphics, without
 * using figure or other GEF resources
 * 
 * @author Orlandin Marco
 *
 */
public class GridPainter {

	/**
	 * The rectangular area that this grid occupies.
	 */
	protected Rectangle bounds = new Rectangle(0, 0, 0, 0);
	
	/**
	 * Field indicating the horizontal grid spacing
	 */
	protected int gridX = SnapToGrid.DEFAULT_GRID_SIZE;
	
	/**
	 * Field for the vertical grid spacing
	 */
	protected int gridY = SnapToGrid.DEFAULT_GRID_SIZE;
	
	/**
	 * Field indicating what the grid origin is. This is used simply to
	 * determine the offset from 0,0.
	 */
	protected Point origin = new Point();
	
	/**
	 * The color of the grid
	 */
	private Color foregroundColor = ColorConstants.lightGray;

	/**
	 * Flag to control to set the grid visible or not
	 */
	private boolean isVisible = true;
	
	private boolean needFullUpdate = false;
	
	/**
	 * Paint the grid of the specified size on the passed graphics. The 
	 * painting is done only if the grid is visible
	 *  
	 * @param graphics the graphics where the grid is painted, must be not null
	 */
	public synchronized void paintGrid(Graphics graphics, IFigure parentFigure) {
		Graphics2D g = ComponentFigure.getG2D(graphics);
		if (isVisible && g != null) {
			Stroke oldStroke = g.getStroke();
			Color oldColor = graphics.getForegroundColor();
			graphics.setForegroundColor(foregroundColor);
			g.setStroke(new BasicStroke(0.1f));
			
			
			Rectangle clip = getBounds();
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
						g.setStroke(new BasicStroke(0.5f));
						j = 0;
					} else
						g.setStroke(new BasicStroke(0.1f));

					g.setStroke(J2DUtils.getInvertedZoomedStroke(g.getStroke(), graphics.getAbsoluteScale()));
					graphics.drawLine(i, clip.y, i, clip.y + clip.height);
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
						g.setStroke(new BasicStroke(0.5f));
						j = 0;
					} else
						g.setStroke(new BasicStroke(0.1f));

					g.setStroke(J2DUtils.getInvertedZoomedStroke(g.getStroke(), graphics.getAbsoluteScale()));
					graphics.drawLine(clip.x, i, clip.x + clip.width, i);
					j++;
				}
			}
			g.setStroke(oldStroke);
			graphics.setForegroundColor(oldColor);

			if (needFullUpdate){
				parentFigure.getUpdateManager().addDirtyRegion(parentFigure, clip);
				needFullUpdate = false;
			}
		}
	}
	
	/**
	 * Sets the origin of the grid. The origin is used only to determine the
	 * offset from 0,0.
	 * 
	 * @param p
	 *            the origin
	 */
	public synchronized void setOrigin(Point p) {
		if (p == null)
			p = new Point();
		if (!origin.equals(p)) {
			origin = p;
			needFullUpdate = true;
		}
	}
	
	/**
	 * Sets the horizontal and vertical spacing of the grid. A grid spacing of 0
	 * will be replaced with the {@link SnapToGrid#DEFAULT_GRID_SIZE default}
	 * spacing. A negative spacing will cause no grid lines to be drawn for that
	 * dimension.
	 * 
	 * @param spacing
	 *            A Dimension representing the horizontal (width) and vertical
	 *            (height) gaps
	 */
	public synchronized void setSpacing(Dimension spacing) {
		if (spacing == null){
			spacing = new Dimension(SnapToGrid.DEFAULT_GRID_SIZE, SnapToGrid.DEFAULT_GRID_SIZE);
		}
		if (!spacing.equals(gridX, gridY)) {
			gridX = spacing.width != 0 ? spacing.width : gridX;
			gridY = spacing.height != 0 ? spacing.height : gridY;
			needFullUpdate = true;
		}
	}
	
	/**
	 * Returns the bounds of the grid
	 * 
	 * @return The bounds of the grid, it is not null
	 */
	public Rectangle getBounds() {
		return bounds;
	}
	
	public void setBounds(Rectangle bounds){
		Assert.isNotNull(bounds);
		this.bounds = bounds;
	}
	
	public boolean isVisible(){
		return isVisible;
	}
	
	public void setVisible(boolean value){
		isVisible = value;
	}
	
	public void setForegroundColor(Color color){
		Assert.isNotNull(color);
		this.foregroundColor = color;
	}
}
