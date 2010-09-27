/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.java2d;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.ScalableLayeredPane;

// TODO: Auto-generated Javadoc
/**
 * The Class J2DScalableLayeredPane.
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
			g2.clipRect(getBounds().getCropped(getInsets()));
		}

		g2.scale(d);
		g2.pushState();
		paintChildren(g2);
		g2.popState();
		g2.scale(1.0D / d);
	}
}