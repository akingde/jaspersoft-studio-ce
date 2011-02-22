/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of iReport.
 *
 * iReport is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * iReport is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with iReport. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.crosstab.part.editpolicy;

import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabCell;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;

import com.jaspersoft.studio.crosstab.model.cell.MCell;
import com.jaspersoft.studio.editor.gef.parts.handles.CellResizeHandle;
import com.jaspersoft.studio.property.SetValueCommand;

/**
 * The Class BandMoveEditPolicy.
 * 
 * @author Chicu Veaceslav
 */
public class CrosstabCellMoveEditPolicy extends GraphicalEditPolicy {

	/** The feedback. */
	private IFigure feedback;

	/** The handle. */
	private IFigure handle;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.EditPolicy#activate()
	 */
	public void activate() {
		super.activate();
		// setHandle(new CellResizeHandle((GraphicalEditPart) getHost()));
		setHandle(new CellResizeHandle((GraphicalEditPart) getHost(), PositionConstants.SOUTH));
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
		if (feedback != null) {
			removeFeedback(feedback);
			feedback = null;
		}
		super.deactivate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.EditPolicy#understandsRequest(Request)
	 */
	public boolean understandsRequest(Request request) {
		if (REQ_RESIZE.equals(request.getType()))
			return true;
		return false;
	}

	/**
	 * Erase change bounds feedback.
	 * 
	 * @param request
	 *          the request
	 */
	protected void eraseChangeBoundsFeedback(ChangeBoundsRequest request) {
		if (feedback != null) {
			removeFeedback(feedback);
		}
		feedback = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.EditPolicy#eraseSourceFeedback(Request)
	 */
	public void eraseSourceFeedback(Request request) {
		if (REQ_RESIZE.equals(request.getType()))
			eraseChangeBoundsFeedback((ChangeBoundsRequest) request);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.EditPolicy#getCommand(Request)
	 */
	public Command getCommand(Request request) {
		if (REQ_RESIZE.equals(request.getType()))
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
	protected Command getResizeCommand(ChangeBoundsRequest request) {
		if (request.getResizeDirection() == PositionConstants.SOUTH
				|| request.getResizeDirection() == PositionConstants.SOUTH_EAST
				|| request.getResizeDirection() == PositionConstants.EAST) {
			MCell mBand = (MCell) getHost().getModel();

			Dimension sizeDelta = request.getSizeDelta();
			PrecisionRectangle deltaRect = new PrecisionRectangle(new Rectangle(0, 0, sizeDelta.width,
					sizeDelta.height));
			getHostFigure().translateToRelative(deltaRect);

			JRDesignCellContents jrdesign = (JRDesignCellContents) mBand.getValue();
			int height = jrdesign.getHeight() + deltaRect.height;
			if (height < 0)
				height = 0;

			int width = jrdesign.getWidth() + deltaRect.width;
			if (width < 0)
				width = 0;

			CompoundCommand c = new CompoundCommand("Change Cell Size"); //$NON-NLS-1$

			if (sizeDelta.width != 0) {
				SetValueCommand setCommand = new SetValueCommand();
				setCommand.setTarget(mBand);
				setCommand.setPropertyId(JRDesignCrosstabCell.PROPERTY_WIDTH);
				setCommand.setPropertyValue(width);
				c.add(setCommand);
			}
			if (sizeDelta.height != 0) {
				SetValueCommand setCommand = new SetValueCommand();
				setCommand.setTarget(mBand);
				setCommand.setPropertyId(JRDesignCrosstabCell.PROPERTY_HEIGHT);
				setCommand.setPropertyValue(height);
				c.add(setCommand);
			}
			return c;
		}
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

}
