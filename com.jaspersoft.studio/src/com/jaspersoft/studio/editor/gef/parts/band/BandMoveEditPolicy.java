/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.parts.band;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;

/**
 * The Class BandMoveEditPolicy. This class is used only to show the resize handle and accept resize operation,
 * because the command for the resize is handled by {@link BandResizableEditPolicy}
 * 
 * @author Chicu Veaceslav
 */
public class BandMoveEditPolicy extends SelectionEditPolicy {

	/** The handle. */
	private IFigure handle;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.EditPolicy#activate()
	 */
	public void activate() {
		super.activate();
		setHandle(new BandResizeHandle((GraphicalEditPart) getHost()));
		getLayer(LayerConstants.HANDLE_LAYER).add(getHandle());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.EditPolicy#deactivate()
	 */
	public void deactivate() {
		if (getHandle() != null) {
			getLayer(LayerConstants.HANDLE_LAYER).remove(getHandle());
			setHandle(null);
		}
		super.deactivate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.EditPolicy#understandsRequest(Request)
	 */
	public boolean understandsRequest(Request request) {
		if (REQ_RESIZE.equals(request.getType())) {
			ChangeBoundsRequest r = (ChangeBoundsRequest) request;
			if (r.getResizeDirection() == PositionConstants.SOUTH)
				return true;
		}
		return false;
	}

	/**
	 * Actually this doesn't provide commands since a band can not be 
	 * moved and the resize is handled by {@link BandResizableEditPolicy} 
	 */
	public Command getCommand(Request request) {
		return null;
	}

	/**
	 * Sets the handle.
	 * 
	 * @param handle
	 *          the new handle
	 */
	private void setHandle(IFigure handle) {
		this.handle = handle;
	}

	/**
	 * Gets the handle.
	 * 
	 * @return the handle
	 */
	private IFigure getHandle() {
		return handle;
	}

	@Override
	protected void hideSelection() {

	}

	@Override
	protected void showSelection() {

	}

}
