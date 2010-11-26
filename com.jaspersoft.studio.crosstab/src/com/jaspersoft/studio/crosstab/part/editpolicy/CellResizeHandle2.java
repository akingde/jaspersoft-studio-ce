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
package com.jaspersoft.studio.crosstab.part.editpolicy;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.handles.ResizeHandle;
import org.eclipse.swt.graphics.Color;

import com.jaspersoft.studio.editor.gef.parts.band.BandResizeTracker;

/**
 * The Class BandResizeHandle2.
 */
public class CellResizeHandle2 extends ResizeHandle {

	/** The tracker. */
	BandResizeTracker tracker = null;

	/**
	 * Constructor for SectionResizeHandle.
	 * 
	 * @param owner
	 *          the owner
	 * @param direction
	 *          the direction
	 */
	public CellResizeHandle2(GraphicalEditPart owner, int direction) {
		super(owner, direction);
		setLocator(new CellResizeHandleLocator(owner.getFigure(), direction));
		setPreferredSize(7, 7);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.handles.SquareHandle#getFillColor()
	 */
	@Override
	protected Color getFillColor() {
		if (getOwner().getFigure().getBounds().height == 0)
			return ColorConstants.red;
		return super.getFillColor();
	}

}
