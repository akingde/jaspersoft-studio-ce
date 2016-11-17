/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.java2d;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.ScalableLayeredPane;

/*
 * /* The Class J2DScalableLayeredPane.
 */
public class J2DScalableLayeredPane extends ScalableLayeredPane {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.ScalableLayeredPane#paintClientArea(org.eclipse.draw2d.Graphics)
	 */
	protected final void paintClientArea(Graphics paramGraphics) {
		if (getChildren().isEmpty())
			return;
		double d = getScale();
		if (Double.compare(d, 1.0) == 0) {
			super.paintClientArea(paramGraphics);
			return;
		}
		J2DGraphics g2 = (J2DGraphics) paramGraphics;

		int i = ((getBorder() == null) || (getBorder().isOpaque())) ? 1 : 0;
		if (i == 0) {
			g2.clipRect(getBounds().getShrinked(getInsets()));
		}

		g2.scale(d);
		g2.pushState();
		paintChildren(g2);
		g2.popState();
		g2.scale(1.0D / d);
	}
}
