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

import org.eclipse.draw2d.Cursors;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RelativeLocator;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.handles.AbstractHandle;

/**
 * The Class BandResizeHandle.
 */
public class CellResizeHandle extends AbstractHandle {

	/** The tracker. */
	CellResizeTracker tracker = null;

	/**
	 * Constructor for SectionResizeHandle.
	 * 
	 * @param owner
	 *          the owner
	 */
	public CellResizeHandle(GraphicalEditPart owner, int position) {
		super(owner, new CellHandleLocator(owner.getFigure(), position));
		initialize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.handles.AbstractHandle#createDragTracker()
	 */
	protected DragTracker createDragTracker() {
		if (tracker == null) {
			tracker = new CellResizeTracker(getOwner());
		}
		return tracker;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.handles.AbstractHandle#getDragTracker()
	 */
	public DragTracker getDragTracker() {
		return createDragTracker();
	}

	/**
	 * Initializes the handle. Sets the {@link DragTracker} and DragCursor.
	 */
	protected void initialize() {
		setOpaque(false);
		setCursor(Cursors.SIZES);
	}

}
