/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.ui.gef.policy;

import java.util.Collection;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;
import org.eclipse.gef.editpolicies.ContainerEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.GroupRequest;

import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.model.MSQLRoot;
import com.jaspersoft.studio.data.sql.model.metadata.MSqlTable;
import com.jaspersoft.studio.data.sql.model.query.from.MFrom;
import com.jaspersoft.studio.data.sql.ui.gef.command.AddTableCommand;
import com.jaspersoft.studio.data.sql.ui.gef.parts.FromEditPart;
import com.jaspersoft.studio.data.sql.ui.gef.parts.QueryEditPart;
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
		MFrom parent = null;
		EditPart host = getHost();
		if (host instanceof QueryEditPart) {
			MSQLRoot mroot = ((QueryEditPart) host).getModel();
			parent = Util.getKeyword(mroot, MFrom.class);
		}
		if (host.getModel() instanceof MFrom)
			parent = (MFrom) host.getModel();
		if (parent != null)
			if (newObject instanceof Collection<?>) {
				Rectangle r = new Rectangle(10, 10, 100, 100);
				if (request.getLocation() != null) {
					r.setLocation(request.getLocation().getCopy());
					GraphicalEditPart ep = (GraphicalEditPart) host;
					if (host instanceof QueryEditPart) {
						r.translate(-20, -20);
					} else {
						Rectangle ca = ep.getFigure().getClientArea();
						r.translate(-ca.x, -ca.y);
						if (host instanceof FromEditPart) {
							FromEditPart fep = (FromEditPart) host;
							Rectangle b = fep.getFigure().getBounds();
							r.translate(b.x, b.y);
						}
					}
				}
				if (request.getSize() != null) {
					r.width = request.getSize().width;
					r.height = request.getSize().height;
				}
				System.out.println(r);

				return new AddTableCommand(parent,
						(Collection<MSqlTable>) newObject, r);
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
