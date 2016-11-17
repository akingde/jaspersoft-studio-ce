/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.callout.pin;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.Request;

import com.jaspersoft.studio.editor.gef.parts.editPolicy.ElementResizableEditPolicy;

public class PinMoveEditPolicy extends ElementResizableEditPolicy {
	public PinMoveEditPolicy() {
		super();
	}

	@Override
	protected List<?> createSelectionHandles() {
		if (getResizeDirections() == PositionConstants.NONE) {
			// non resizable, so delegate to super implementation
			return super.createSelectionHandles();
		}

		// resizable in at least one direction
		List<?> list = new ArrayList<Object>();
		createMoveHandle(list);
		return list;
	}

	@Override
	public boolean understandsRequest(Request request) {
		if (REQ_MOVE.equals(request.getType()))
			return isDragAllowed();
		return false;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected void createResizeHandle(List handles, int direction) {
	}
}
