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

package com.jaspersoft.studio.editor.gef.figures.borders;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.swt.graphics.Color;

public class Line1Border extends LineBorder {
	@Override
	public void paint(IFigure figure, Graphics graphics, Insets insets) {
		org.eclipse.swt.graphics.Color c = getColor();
		setColor(ColorConstants.white);
		super.paint(figure, graphics, insets);
		setColor(c);
		setWidth(getWidth() - 1);
		super.paint(figure, graphics, insets);
		setWidth(getWidth() + 1);
	}

	public Line1Border() {
		super();
	}

	public Line1Border(Color color, int width, int style) {
		super(color, width, style);
	}

	public Line1Border(Color color, int width) {
		super(color, width);
	}

	public Line1Border(Color color) {
		super(color);
	}

	public Line1Border(int width) {
		super(width);
	}
}
