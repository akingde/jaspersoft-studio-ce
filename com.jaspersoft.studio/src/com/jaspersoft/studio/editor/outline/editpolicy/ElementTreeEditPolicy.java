/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.outline.editpolicy;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
/*
 * The Class ElementTreeEditPolicy.
 */
public class ElementTreeEditPolicy extends AbstractEditPolicy {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#getCommand(org.eclipse.gef.Request)
	 */
	@Override
	public Command getCommand(Request req) {
		if (REQ_MOVE.equals(req.getType()))
			return getMoveCommand((ChangeBoundsRequest) req);
		return null;
	}

	/**
	 * Gets the move command.
	 * 
	 * @param req
	 *          the req
	 * @return the move command
	 */
	protected Command getMoveCommand(ChangeBoundsRequest req) {
		EditPart parent = getHost().getParent();
		if (parent != null) {
			ChangeBoundsRequest request = new ChangeBoundsRequest(REQ_MOVE_CHILDREN);
			request.setEditParts(getHost());
			request.setLocation(req.getLocation());
			return parent.getCommand(request);
		}
		return UnexecutableCommand.INSTANCE;
	}
}
