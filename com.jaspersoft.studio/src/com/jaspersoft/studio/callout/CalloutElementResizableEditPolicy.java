/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.callout;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Cursors;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.GraphicalEditPart;

import com.jaspersoft.studio.editor.gef.parts.editPolicy.ElementResizableEditPolicy;

public class CalloutElementResizableEditPolicy extends ElementResizableEditPolicy {
	@Override
	protected List<?> createSelectionHandles() {
		if (getResizeDirections() == PositionConstants.NONE) {
			// non resizable, so delegate to super implementation
			return super.createSelectionHandles();
		}

		// resizable in at least one direction
		List<?> list = new ArrayList<Object>();
		createMoveHandle(list);
		createResizeHandle(list, PositionConstants.SOUTH_EAST);
		return list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected void createResizeHandle(List handles, int direction) {
		if ((getResizeDirections() & direction) == direction) {
			SothEastRectangleHandles handle = new SothEastRectangleHandles((GraphicalEditPart) getHost(), direction);
			handle.setDragTracker(getResizeTracker(direction));
			handle.setCursor(Cursors.getDirectionalCursor(direction, getHostFigure().isMirrored()));
			handles.add(handle);
		} else {
			// display 'resize' handle to allow dragging or indicate selection
			// only
			createDragHandle(handles, direction);
		}
	}
}
