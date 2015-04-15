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
