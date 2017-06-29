/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.util;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.ZoomManager;

public class GEFUtil {
	public static double getZoom(Rectangle hbounds, IFigure figure) {
		double zoom = hbounds.x;
		Rectangle copy = hbounds.getCopy();
		figure.translateToAbsolute(copy);
		zoom = copy.x / zoom;
		return zoom;
	}

	public static double getZoom(GraphicalEditPart editPart) {
		ZoomManager zm = (ZoomManager) editPart.getViewer().getProperty(ZoomManager.class.toString());
		return zm.getZoom();
	}
}
