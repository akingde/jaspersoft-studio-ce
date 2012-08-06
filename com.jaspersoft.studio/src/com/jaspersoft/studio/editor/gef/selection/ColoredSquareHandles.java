package com.jaspersoft.studio.editor.gef.selection;

import java.security.acl.Owner;

import net.sf.jasperreports.engine.design.JRVerifier;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.handles.ResizeHandle;
import org.eclipse.gef.handles.SquareHandle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;

public class ColoredSquareHandles extends ResizeHandle {

	/**
	 * Creates a new ResizeHandle for the given GraphicalEditPart.
	 * 
	 * @see SquareHandle#SquareHandle(GraphicalEditPart, Locator, Cursor)
	 */
	public ColoredSquareHandles(GraphicalEditPart owner, Locator loc, Cursor c) {
		super(owner, loc, c);
	}
	
	/*public void paintFigure(Graphics g) {
		Rectangle r = getBounds();
		r.shrink(1, 1);
		try {
			g.setBackgroundColor(getFillColor());
			g.fillRectangle(r.x, r.y, r.width, r.height);
			g.setForegroundColor(getBorderColor());
			g.drawRectangle(r.x, r.y, r.width, r.height);
			g.fillRectangle(r.x-2, r.y-2, 4, 4);
		} finally {
			// We don't really own rect 'r', so fix it.
			r.expand(1, 1);
		}
	}*/
	
	/**
	 * Returns the color for the inside of the handle.
	 * 
	 * @return the color of the handle
	 */
	protected Color getFillColor() {
		//JRVerifier.verifyDesign(jasperDesign);
		return (isPrimary()) ? ColorConstants.blue : ColorConstants.gray;
	}
	
	/**
	 * Creates a new ResizeHandle for the given GraphicalEditPart.
	 * <code>direction</code> is the relative direction from the center of the
	 * owner figure. For example, <code>SOUTH_EAST</code> would place the handle
	 * in the lower-right corner of its owner figure. These direction constants
	 * can be found in {@link org.eclipse.draw2d.PositionConstants}.
	 * 
	 * @param owner
	 *            owner of the ResizeHandle
	 * @param direction
	 *            relative direction from the center of the owner figure
	 */
	public ColoredSquareHandles(GraphicalEditPart owner, int direction) {
		super(owner, direction);
	}


}
