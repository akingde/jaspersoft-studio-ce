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
