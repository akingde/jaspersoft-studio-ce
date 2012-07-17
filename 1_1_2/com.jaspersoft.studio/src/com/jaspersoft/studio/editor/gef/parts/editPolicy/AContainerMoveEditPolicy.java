/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.gef.parts.editPolicy;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Handle;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.SelectionHandlesEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;

import com.jaspersoft.studio.editor.gef.parts.handles.CellResizeHandle;

/*
 * The Class BandMoveEditPolicy.
 * 
 * @author Chicu Veaceslav
 */
public abstract class AContainerMoveEditPolicy extends SelectionHandlesEditPolicy {

	@Override
	public GraphicalEditPart getHost() {
		return (GraphicalEditPart) super.getHost();
	}

	@Override
	protected List<Handle> createSelectionHandles() {
		List<Handle> list = new ArrayList<Handle>();
		GraphicalEditPart geditPart = getHost();
		list.add(new CellResizeHandle(geditPart, PositionConstants.SOUTH));
		list.add(new CellResizeHandle(geditPart, PositionConstants.EAST));
		return list;
	}

	@Override
	protected void showSelection() {
	}

	@Override
	protected void hideSelection() {
	}

	@Override
	public void showSourceFeedback(Request request) {
	}

	@Override
	public void showTargetFeedback(Request request) {
		super.showTargetFeedback(request);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.EditPolicy#activate()
	 */
	@Override
	public void activate() {
		super.activate();
		addSelectionHandles();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.EditPolicy#deactivate()
	 */
	@Override
	public void deactivate() {
		removeSelectionHandles();
		super.deactivate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.EditPolicy#understandsRequest(Request)
	 */
	@Override
	public boolean understandsRequest(Request request) {
		if (REQ_RESIZE.equals(request.getType()))
			return true;
		if (REQ_MOVE.equals(request.getType()))
			return true;
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.EditPolicy#eraseSourceFeedback(Request)
	 */
	@Override
	public void eraseSourceFeedback(Request request) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.EditPolicy#getCommand(Request)
	 */
	@Override
	public Command getCommand(Request request) {
		if (REQ_RESIZE.equals(request.getType()))
			return getResizeCommand((ChangeBoundsRequest) request);
		if (REQ_MOVE.equals(request.getType()))
			return getResizeCommand((ChangeBoundsRequest) request);
		return null;
	}

	/**
	 * Gets the resize command.
	 * 
	 * @param request
	 *          the request
	 * @return the resize command
	 */
	protected abstract Command getResizeCommand(ChangeBoundsRequest request);

}
