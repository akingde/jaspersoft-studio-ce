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
package com.jaspersoft.studio.crosstab.part;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.requests.ChangeBoundsRequest;

import com.jaspersoft.studio.crosstab.part.editpolicy.CrosstabCellContainerEditPolicy;
import com.jaspersoft.studio.crosstab.part.editpolicy.CrosstabCellMoveEditPolicy;
import com.jaspersoft.studio.crosstab.part.editpolicy.CrosstabCellResizableEditPolicy;
import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.editor.gef.parts.IContainerPart;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.ElementEditPolicy;

/**
 * BandEditPart creates the figure for the band. The figure is actually just the bottom border of the band. This allows
 * to drag this border to resize the band. The PageEditPart sets a specific contraint for the BandEditPart elements in
 * order to make them move only vertically. The BandMoveEditPolicy is responsable for the feedback when the band is
 * dragged.
 * 
 * @author Chicu Veaceslav, Giulio Toffoli
 * 
 */
public class CrosstabCellEditPart extends FigureEditPart implements IContainerPart {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ElementEditPolicy());
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new CrosstabCellMoveEditPolicy());
		installEditPolicy(EditPolicy.CONTAINER_ROLE, new CrosstabCellContainerEditPolicy());
	}

	@Override
	public boolean isSelectable() {
		return true;
	}

	public EditPolicy getEditPolicy() {
		return new CrosstabCellResizableEditPolicy();
	}

	public Object getConstraintFor(ChangeBoundsRequest request, GraphicalEditPart child) {
		if (request.getResizeDirection() == PositionConstants.SOUTH
				|| request.getResizeDirection() == PositionConstants.NORTH
				|| request.getResizeDirection() == PositionConstants.EAST
				|| request.getResizeDirection() == PositionConstants.WEST)
			System.out.println(" Constraint request:  " + request.getSizeDelta() + "  " + request.getResizeDirection()); //$NON-NLS-1$ //$NON-NLS-2$
		return new Rectangle(0, 0, request.getSizeDelta().width, request.getSizeDelta().height);
	}

}
