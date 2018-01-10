/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.background;

import java.awt.Color;

import org.eclipse.gef.GraphicalEditPart;

import com.jaspersoft.studio.editor.gef.selection.ColoredSquareHandles;

/**
 * Custom handles for the resize of the background using the drag and drop
 * 
 * @author Orlandin Marco
 *
 */
public class BackgroundResizeHandles extends ColoredSquareHandles {

	/**
	 * The color of the handles, it goes from white to black
	 */
	protected static Color[] JSS_BACKGROUND_COLOR = {Color.getHSBColor(0.0f, 0.0f, 0.8f), Color.getHSBColor(0.0f, 0.0f, 0.6f), Color.getHSBColor(0.0f, 0.0f, 0.3f)};
	
	public BackgroundResizeHandles(GraphicalEditPart owner, int direction) {
		super(owner, direction);
	}
	
	/**
	 * despite the other element background dosen't consider its position and 
	 * show always the handle of the same colorS
	 */
	protected Color[] getFillColorAwt() {
		return JSS_BACKGROUND_COLOR;
	}

}
