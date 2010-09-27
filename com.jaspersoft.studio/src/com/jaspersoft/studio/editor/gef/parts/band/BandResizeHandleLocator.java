/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.gef.parts.band;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RelativeLocator;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;

public class BandResizeHandleLocator extends RelativeLocator {
	private double relativeY;

	/**
	 * Constructs a RelativeLocator with the given reference figure and relative location. The location is a constant from
	 * {@link PositionConstants} used as a convenient and readable way to set both the relativeX and relativeY values.
	 * 
	 * @param reference
	 *          the reference figure
	 * @param location
	 *          one of NORTH, NORTH_EAST, etc.
	 * @since 2.0
	 */
	public BandResizeHandleLocator(IFigure reference, int location) {
		setReferenceFigure(reference);
		switch (location & PositionConstants.NORTH_SOUTH) {
		case PositionConstants.NORTH:
			relativeY = 0;
			break;
		case PositionConstants.SOUTH:
			relativeY = 1.0;
			break;
		default:
			relativeY = 0.5;
		}
	}

	@Override
	public void relocate(IFigure target) {
		IFigure reference = getReferenceFigure();
		Rectangle targetBounds = new PrecisionRectangle(getReferenceBox().getResized(-1, -1));
		reference.translateToAbsolute(targetBounds);
		target.translateToRelative(targetBounds);
		targetBounds.resize(1, 1);

		Dimension targetSize = target.getPreferredSize();

		targetBounds.x += 7;

		targetBounds.y += (int) (targetBounds.height * relativeY - ((targetSize.height + 1) / 2));
		targetBounds.setSize(targetBounds.width - 14, 7);
		target.setBounds(targetBounds);
	}
}
