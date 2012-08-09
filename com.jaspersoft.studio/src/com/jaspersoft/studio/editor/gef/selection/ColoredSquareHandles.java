/*******************************************************************************
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License  as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero  General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.selection;

import java.awt.Graphics2D;
import java.lang.invoke.ConstantCallSite;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.handles.ResizeHandle;
import org.eclipse.gef.handles.SquareHandle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;

import com.jaspersoft.studio.editor.java2d.J2DGraphics;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MGraphicElement;

/**
 * Handle to color an design the figures on the border of a selection
 * @author Marco Orlandin
 *
 */
public class ColoredSquareHandles extends ResizeHandle {
	/**
	 * The default size for square handles.
	 */
	protected static int JSS_HANDLE_SIZE = 8;
	
	/**
	 * The color of an element that cover entirely another element
	 */
	protected static Color JSS_OVERLAP_COLOR = ColorConstants.green;
	
	/**
	 * The color of an element that overlap partially another element
	 */
	protected static Color JSS_COVER_COLOR = ColorConstants.red;
	
	/**
	 * The color of a focused element
	 */
	protected static Color JSS_FOCUSED_COLOR = ColorConstants.blue;
	
	/**
	 * The color of a not focused element
	 */
	protected static Color JSS_NOT_FOCUSED_COLOR = ColorConstants.gray;
	
	/**
	 * Creates a new ResizeHandle for the given GraphicalEditPart.
	 * 
	 * @see SquareHandle#SquareHandle(GraphicalEditPart, Locator, Cursor)
	 */
	public ColoredSquareHandles(GraphicalEditPart owner, Locator loc, Cursor c) {
		super(owner, loc, c);
	}
	
	/**
	 * Initializes the handle.
	 */
	protected void init() {
		super.init();
		setPreferredSize(new Dimension(JSS_HANDLE_SIZE, JSS_HANDLE_SIZE));
	}
	
	public void paintFigure(Graphics g) {
		Rectangle r = getBounds();
		r.shrink(1, 1);
		try {
			g.setBackgroundColor(getFillColor());
			g.fillRectangle(r.x, r.y, r.width, r.height);
			g.setForegroundColor(ColorConstants.black);
			g.drawRectangle(r.x-1, r.y-1, r.width+1, r.height+1);
			//Graphics2D gr = ((J2DGraphics)g).getGraphics2D();
			//gr.setColor(ColorConstants.black);
			//g.drawRectangle(r.x-1, r.y-1, r.width+1, r.height+1);
		} finally {
			// We don't really own rect 'r', so fix it.
			r.expand(1, 1);
		}
	}
	
	/**
	 * Returns the color for the inside of the handle.
	 * 
	 * @return the color of the handle
	 */
	protected Color getFillColor() {
		MGraphicElement element = (MGraphicElement)getOwner().getModel();
		Rectangle bound1 = element.getBounds();
		List<INode> brothers = element.getParent().getChildren();
		Iterator<INode> it = brothers.iterator();
		boolean overlap = false;
		boolean cover = false;
		while(it.hasNext() && !cover){
			INode actualElement = it.next();
			if (actualElement != element && actualElement instanceof MGraphicElement){
				MGraphicElement element2 = (MGraphicElement)actualElement;
				Rectangle bound2 = element2.getBounds();
				if (bound1.intersects(bound2)){
					overlap = true;
				}
				if (bound1.contains(bound2)){
					cover = true;
				}
			}
		}
		if (cover) return JSS_COVER_COLOR;
		if (overlap) return JSS_OVERLAP_COLOR;
		return (isPrimary()) ? JSS_FOCUSED_COLOR : JSS_NOT_FOCUSED_COLOR;
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
	
	/**
	 * Set the color of the selection resizing images when 2 or more elements are overlapped
	 * @param newColor the new color
	 */
	public void setOverlapColor(Color newColor){
		JSS_OVERLAP_COLOR.dispose();
		JSS_OVERLAP_COLOR = newColor;
	}
	
	/**
	 * Set the color of the selection resizing images when one element cover one or more elements
	 * @param newColor the new color
	 */
	public void setCoverColor(Color newColor){
		JSS_COVER_COLOR.dispose();
		JSS_COVER_COLOR = newColor;
	}
	
	/**
	 * Set the color of the selection resizing images of a focused element
	 * @param newColor the new color
	 */
	public void setFocusedColor(Color newColor){
		JSS_FOCUSED_COLOR.dispose();
		JSS_FOCUSED_COLOR = newColor;
	}
	
	/**
	 * Set the color of the selection resizing images of a not focused element
	 * @param newColor the new color
	 */
	public void setNotFocusedColor(Color newColor){
		JSS_NOT_FOCUSED_COLOR.dispose();
		JSS_NOT_FOCUSED_COLOR = newColor;
	}
	
	/**
	 * Size of the designed resizing images
	 * @param newSize the new size in pixel
	 */
	public void setSize(int newSize){
		JSS_HANDLE_SIZE = newSize;
	}


}
