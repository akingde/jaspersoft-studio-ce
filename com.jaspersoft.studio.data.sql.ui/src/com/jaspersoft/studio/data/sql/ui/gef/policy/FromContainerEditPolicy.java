/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.ui.gef.policy;

import java.util.Collection;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;
import org.eclipse.gef.editpolicies.ContainerEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.GroupRequest;

import com.jaspersoft.studio.data.sql.model.metadata.MSqlTable;
import com.jaspersoft.studio.data.sql.model.query.from.MFrom;
import com.jaspersoft.studio.data.sql.ui.gef.command.AddTableCommand;
import com.jaspersoft.studio.data.sql.ui.gef.parts.TableEditPart;

public class FromContainerEditPolicy extends ContainerEditPolicy {

	/**
	 * @see org.eclipse.gef.editpolicies.ContainerEditPolicy#getAddCommand(org.eclipse.gef.requests.GroupRequest)
	 */
	protected Command getAddCommand(GroupRequest request) {
		return null;
	}

	/**
	 * @see ContainerEditPolicy#getCreateCommand(org.eclipse.gef.requests.CreateRequest)
	 */
	protected Command getCreateCommand(CreateRequest request) {
		Object newObject = request.getNewObject();
		if (getHost().getModel() instanceof MFrom) {
			if (newObject instanceof Collection<?>) {
				Collection<MSqlTable> col = (Collection<MSqlTable>) newObject;
				CompoundCommand cc = new CompoundCommand();
				boolean first = false;
				for (MSqlTable mtbl : col) {
					if (!first) {
						int x = 10;
						int y = 10;
						int w = 100;
						int h = 100;
						if (request.getLocation() != null) {
							x = request.getLocation().x;
							y = request.getLocation().y;
						}
						if (request.getSize() != null) {
							w = request.getSize().width;
							h = request.getSize().height;
						}
						cc.add(new AddTableCommand((MFrom) getHost().getModel(), mtbl, new Rectangle(x, y, w, h)));
						first = true;
					} else
						cc.add(new AddTableCommand((MFrom) getHost().getModel(), mtbl, null));
				}
				return cc;
			}
		}
		return null;
	}

	/**
	 * @see AbstractEditPolicy#getTargetEditPart(org.eclipse.gef.Request)
	 */
	public EditPart getTargetEditPart(Request request) {
		if (request instanceof ChangeBoundsRequest) {
			ChangeBoundsRequest cbr = (ChangeBoundsRequest) request;
			for (EditPart ep : (List<EditPart>) cbr.getEditParts()) {
				if (ep instanceof TableEditPart)
					return ep.getParent();
			}
		}
		if (REQ_CREATE.equals(request.getType()))
			return getHost();
		if (REQ_ADD.equals(request.getType()))
			return getHost();
		if (REQ_MOVE.equals(request.getType()))
			return getHost();
		return super.getTargetEditPart(request);
	}

}
