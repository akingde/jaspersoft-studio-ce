/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components;

import java.util.List;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;

import com.jaspersoft.studio.editor.gef.parts.editPolicy.JSSCompoundResizeTracker;

/**
 * Resize tracker for the element inside a subeditor. It dosen't allow to the element
 * to be moved, but allow the resize.
 * 
 * 
 * @author Orlandin Marco
 */
public class SubEditorResizeTracker extends JSSCompoundResizeTracker {

	public SubEditorResizeTracker(GraphicalEditPart owner, int direction) {
		super(owner, direction);
	}
	
	/**
	 * Return a fixed version of the request where the move delta is always 0. Also
	 * fix the resize direction if it is the north or west corner
	 * 
	 * @return
	 */
	protected Request getSubeditorRequest(){
		ChangeBoundsRequest request = (ChangeBoundsRequest)getSourceRequest();
		request.setMoveDelta(new Point(0, 0));
		if (request.getResizeDirection() == PositionConstants.NORTH){
			request.setResizeDirection(PositionConstants.SOUTH);
		} else  if (request.getResizeDirection() == PositionConstants.NORTH_EAST){
			request.setResizeDirection(PositionConstants.SOUTH_EAST);
		} else if (request.getResizeDirection() == PositionConstants.NORTH_WEST){
			request.setResizeDirection(PositionConstants.SOUTH_EAST);
		} else if (request.getResizeDirection() == PositionConstants.WEST){
			request.setResizeDirection(PositionConstants.EAST);
		} else if (request.getResizeDirection() == PositionConstants.SOUTH_WEST){
			request.setResizeDirection(PositionConstants.SOUTH_EAST);
		}
		return request;
	}

	/**
	 * Generate the command with a fixed request to keep the element in position
	 */
	@Override
	protected Command getCommand() {
		List<?> editparts = getOperationSet();
		EditPart part;
		CompoundCommand command = new CompoundCommand();
		command.setDebugLabel("Resize Handle Tracker");//$NON-NLS-1$
		for (int i = 0; i < editparts.size(); i++) {
			part = (EditPart) editparts.get(i);
			command.add(part.getCommand(getSubeditorRequest()));
		}
		return command.unwrap();
	}
	
	
}
