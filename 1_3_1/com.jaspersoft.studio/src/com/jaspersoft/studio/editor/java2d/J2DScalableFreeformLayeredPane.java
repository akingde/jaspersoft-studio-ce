/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.java2d;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.ScalableFreeformLayeredPane;
/*
 * A J2DScalableFreeformLayeredPane behaves like a ScalableFreeformLayeredPane except that no intermediaite
 * ScaledGraphics is created: the scale factor is directly applied to the Graphics object because java2D will compute
 * and apply the correct transformation for subsequent calls.
 * 
 * @author Christophe Avare
 * @version $Revision: 1.5.4.1.2.1 $
 */
public class J2DScalableFreeformLayeredPane extends ScalableFreeformLayeredPane {

	/**
	 * Instantiates a new j2 d scalable freeform layered pane.
	 */
	public J2DScalableFreeformLayeredPane() {
		super();
	}

	/**
	 * No ScaledGraphics needed here, only setScale() on the passed graphics. Graphics state is preserved.
	 * 
	 * @param graphics
	 *          the graphics
	 * @see org.eclipse.draw2d.Figure#paintClientArea(org.eclipse.draw2d.Graphics)
	 */
	protected void paintClientArea(Graphics graphics) {
		if (getChildren().isEmpty())
			return;
		if (!(graphics instanceof J2DGraphics)) {
			super.paintClientArea(graphics);
		} else {
			double scale = getScale();
			if (Double.compare(scale, 1.0) == 0) {
				// Hopefully this will have the same effet
				// on the inherited code!
				super.paintClientArea(graphics);
			} else {
				boolean optimizeClip = getBorder() == null || getBorder().isOpaque();
				if (!optimizeClip)
					graphics.clipRect(getBounds().getCropped(getInsets()));
				graphics.pushState();
				graphics.scale(scale);
				paintChildren(graphics);
				graphics.popState();
			}
		}
	}
}
