/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.properties.layout;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;

/**
 * This Layout stacks all the controls one on top of the other and resizes all controls
 * to have the same size and location.
 * The control specified in topControl is visible and all other controls are not visible.
 * Users must set the topControl value to flip between the visible items. It is a customized
 * version of a {@link org.eclipse.swt.custom.StackLayout} reworked to be faster
 */
public class StackLayout extends Layout {
	
 	/**
	 * marginWidth specifies the number of pixels of horizontal margin
	 * that will be placed along the left and right edges of the layout.
	 *
	 * The default value is 0.
	 */
 	public int marginWidth = 0;
	/**
	 * marginHeight specifies the number of pixels of vertical margin
	 * that will be placed along the top and bottom edges of the layout.
	 *
	 * The default value is 0.
	 */
	public int marginHeight = 0;

 	/**
 	 * topControl the Control that is displayed at the top of the stack.
 	 * All other controls that are children of the parent composite will not be visible.
 	 */
 	protected Control topControl;
 	
	@Override
	protected Point computeSize(Composite composite, int wHint, int hHint, boolean flushCache) {
		Control children[] = composite.getChildren();
		int maxWidth = 0;
		int maxHeight = 0;
		if (topControl == null){
			for (int i = 0; i < children.length; i++) {
				Point size = children[i].computeSize(wHint, hHint, flushCache);
				maxWidth = Math.max(size.x, maxWidth);
				maxHeight = Math.max(size.y, maxHeight);
			}
		} else {
			Point size = topControl.computeSize(wHint, hHint, flushCache);
			maxWidth = Math.max(size.x, maxWidth);
			maxHeight = Math.max(size.y, maxHeight);
		}
		int width = maxWidth + 2 * marginWidth;
		int height = maxHeight + 2 * marginHeight;
		if (wHint != SWT.DEFAULT) width = wHint;
		if (hHint != SWT.DEFAULT) height = hHint;
		return new Point(width, height);
	}
	
	@Override
	protected boolean flushCache(Control control) {
		return true;
	}
	
	@Override
	protected void layout(Composite composite, boolean flushCache) {
		Control children[] = composite.getChildren();
		Rectangle rect = composite.getClientArea();
		rect.x += marginWidth;
		rect.y += marginHeight;
		rect.width -= 2 * marginWidth;
		rect.height -= 2 * marginHeight;
		for (int i = 0; i < children.length; i++) {
			Control currentControl = children[i];
			if (currentControl == topControl){
				currentControl.setBounds(rect);
				currentControl.setVisible(true);
			} else if (currentControl.isVisible()){
				currentControl.setVisible(false);
			}
		}
	}
	
	protected String getName () {
		String string = getClass ().getName ();
		int index = string.lastIndexOf ('.');
		if (index == -1) return string;
		return string.substring (index + 1, string.length ());
	}
	
	/**
	 * Returns a string containing a concise, human-readable
	 * description of the receiver.
	 *
	 * @return a string representation of the layout
	 */
	@Override
	public String toString () {
	 	String string = getName ()+" {";
	 	if (marginWidth != 0) string += "marginWidth="+marginWidth+" ";
	 	if (marginHeight != 0) string += "marginHeight="+marginHeight+" ";
	 	if (topControl != null) string += "topControl="+topControl+" ";
	 	string = string.trim();
	 	string += "}";
	 	return string;
	}
	
	/**
	 * Set the top control of the layout
	 * 
	 * @param newControl the new top control, can be null, but probably it shouldn't
	 * @return true if the new control is now visible, false if it was already visible
	 */
	public boolean setTopControl(Control newControl){
		if (newControl != topControl){
			if (topControl != null){
				topControl.setVisible(false);
			}
			if (newControl != null){
				newControl.setVisible(true);
			}
			topControl = newControl;
			topControl.getParent().layout(true, true);
			return true;
		}
		return false;
	}
	
	public Control getTopControl(){
		return topControl;
	}
}
