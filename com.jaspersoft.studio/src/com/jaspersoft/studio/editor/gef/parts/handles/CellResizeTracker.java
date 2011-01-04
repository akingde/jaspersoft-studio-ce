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
package com.jaspersoft.studio.editor.gef.parts.handles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.AutoexposeHelper;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editparts.ViewportAutoexposeHelper;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.tools.AbstractTool;
import org.eclipse.gef.tools.SimpleDragTracker;
import org.eclipse.gef.tools.ToolUtilities;
import org.eclipse.swt.widgets.Display;

/**
 * The Class BandResizeTracker.
 */
public class CellResizeTracker extends SimpleDragTracker {

	/** The editpart. */
	private EditPart editpart;

	/** The expose helper. */
	private AutoexposeHelper exposeHelper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.tools.SimpleDragTracker#deactivate()
	 */
	public void deactivate() {
		super.deactivate();
		setAutoexposeHelper(null);
	}

	/**
	 * Called to perform an iteration of the autoexpose process. If the expose helper is set, it will be asked to step at
	 * the current mouse location. If it returns true, another expose iteration will be queued. There is no delay between
	 * autoexpose events, other than the time required to perform the step().
	 */
	protected void doAutoexpose() {
		if (exposeHelper == null)
			return;
		if (exposeHelper.step(getLocation())) {
			handleAutoexpose();
			Display.getCurrent().asyncExec(new QueuedAutoexpose());
		} else
			setAutoexposeHelper(null);
	}

	/**
	 * The Class QueuedAutoexpose.
	 */
	class QueuedAutoexpose implements Runnable {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Runnable#run()
		 */
		public void run() {
			doAutoexpose();
		}
	}

	/**
	 * Sets the active autoexpose helper to the given helper, or <code>null</code>. If the helper is not <code>null</code>
	 * , a runnable is queued on the event thread that will trigger a subsequent {@link #doAutoexpose()}. The helper is
	 * typically updated only on a hover event.
	 * 
	 * @param helper
	 *          the new autoexpose helper or <code>null</code>
	 */
	protected void setAutoexposeHelper(AutoexposeHelper helper) {
		exposeHelper = helper;
		if (exposeHelper == null)
			return;
		Display.getCurrent().asyncExec(new QueuedAutoexpose());
	}

	/**
	 * Updates the active {@link AutoexposeHelper}. Does nothing if there is still an active helper. Otherwise, obtains a
	 * new helper (possible <code>null</code>) at the current mouse location and calls
	 * {@link #setAutoexposeHelper(AutoexposeHelper)}.
	 */
	protected void updateAutoexposeHelper() {
		if (exposeHelper != null)
			return;
		AutoexposeHelper.Search search = new AutoexposeHelper.Search(getLocation());

		getCurrentViewer().findObjectAtExcluding(getLocation(), Collections.EMPTY_LIST, search);

		setAutoexposeHelper(search.result);
	}

	/**
	 * This method is called whenever an autoexpose occurs. When an autoexpose occurs, it is possible that everything in
	 * the viewer has moved a little. Therefore, by default, {@link AbstractTool#handleMove() handleMove()} is called to
	 * simulate the mouse moving even though it didn't.
	 */
	protected void handleAutoexpose() {
		handleMove();
		handleDragInProgress();
	}

	/**
	 * Constructor for SectionResizeTracker.
	 * 
	 * @param sourceEditPart
	 *          the source edit part
	 */
	public CellResizeTracker(EditPart sourceEditPart) {
		setSourceEditPart(sourceEditPart);
		setAutoexposeHelper(new ViewportAutoexposeHelper((GraphicalEditPart) sourceEditPart.getViewer().getRootEditPart()));
		updateAutoexposeHelper();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.tools.AbstractTool#getCommandName()
	 */
	protected String getCommandName() {
		return REQ_MOVE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.tools.AbstractTool#getDebugName()
	 */
	protected String getDebugName() {
		return "Cell Resize Handle Tracker"; //$NON-NLS-1$
	}

	/**
	 * Gets the source edit part.
	 * 
	 * @return the source edit part
	 */
	protected EditPart getSourceEditPart() {
		return editpart;
	}

	/**
	 * Sets the source edit part.
	 * 
	 * @param part
	 *          the new source edit part
	 */
	protected void setSourceEditPart(EditPart part) {
		this.editpart = part;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.tools.AbstractTool#createOperationSet()
	 */
	protected List createOperationSet() {
		List<EditPart> editparts = null;
		if (editpart == null) {
			editparts = Collections.emptyList();
		} else {
			editparts = new ArrayList<EditPart>();
			editparts.add(editpart);
			ToolUtilities.filterEditPartsUnderstanding(editparts, getSourceRequest());
		}
		return editparts;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.tools.SimpleDragTracker#createSourceRequest()
	 */
	protected Request createSourceRequest() {
		ChangeBoundsRequest request = new ChangeBoundsRequest(REQ_RESIZE);
		// request.setResizeDirection(PositionConstants.NORTH_EAST);
		return request;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.tools.SimpleDragTracker#updateSourceRequest()
	 */
	protected void updateSourceRequest() {
		ChangeBoundsRequest request = (ChangeBoundsRequest) getSourceRequest();

		Dimension d = getDragMoveDelta();

		Point location = new Point(getLocation());
		Point p = new Point(0, 0);
		p.y = d.height;
		p.x = d.width;

		request.setSizeDelta(new Dimension(d.width, d.height));
		request.setLocation(location);
		request.setEditParts(getOperationSet());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.tools.AbstractTool#getCommand()
	 */
	protected Command getCommand() {
		List<?> editparts = getOperationSet();
		EditPart part = null;
		CompoundCommand command = new CompoundCommand();
		command.setDebugLabel("Move Section Handle Tracker"); //$NON-NLS-1$
		for (int i = 0; i < editparts.size(); i++) {
			part = (EditPart) editparts.get(i);
			command.add(part.getCommand(getSourceRequest()));
		}
		return command.unwrap();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.tools.SimpleDragTracker#handleDragInProgress()
	 */
	public boolean handleDragInProgress() {
		boolean b = super.handleDragInProgress();
		updateAutoexposeHelper();
		return b;
	}

}
