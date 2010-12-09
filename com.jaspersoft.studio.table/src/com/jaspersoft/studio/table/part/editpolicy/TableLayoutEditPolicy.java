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
package com.jaspersoft.studio.table.part.editpolicy;

import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardBaseColumn;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;

import com.jaspersoft.studio.editor.gef.parts.editPolicy.PageLayoutEditPolicy;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.table.model.MCell;
import com.jaspersoft.studio.table.model.MColumn;
import com.jaspersoft.studio.table.part.TableCellEditPart;

/**
 * The Class PageLayoutEditPolicy.
 */
public class TableLayoutEditPolicy extends PageLayoutEditPolicy {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editpolicies.XYLayoutEditPolicy#getConstraintFor(org.eclipse.gef.requests.ChangeBoundsRequest,
	 * org.eclipse.gef.GraphicalEditPart)
	 */
	@Override
	protected Object getConstraintFor(ChangeBoundsRequest request, GraphicalEditPart child) {
		System.out.println("Constraint request:  " + request.getMoveDelta()); //$NON-NLS-1$
		if (child instanceof TableCellEditPart) {
			if (request.getResizeDirection() == PositionConstants.SOUTH
					|| request.getResizeDirection() == PositionConstants.NORTH
					|| request.getResizeDirection() == PositionConstants.EAST
					|| request.getResizeDirection() == PositionConstants.WEST)
				System.out.println(" Constraint request:  " + request.getSizeDelta() + "  " + request.getResizeDirection()); //$NON-NLS-1$ //$NON-NLS-2$
			return new Rectangle(0, 0, request.getSizeDelta().width, request.getSizeDelta().height);
		}
		return super.getConstraintFor(request, child);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#createChangeConstraintCommand(org.eclipse.gef.EditPart,
	 * java.lang.Object)
	 */
	@Override
	protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
		if (child instanceof TableCellEditPart) {
			MColumn model = (MColumn) child.getModel();
			StandardBaseColumn jrdesign = (StandardBaseColumn) model.getValue();
			Rectangle constraintR = (Rectangle) constraint;

			CompoundCommand c = new CompoundCommand("Change Cell Size"); //$NON-NLS-1$
			if (constraintR.height > 0 && model instanceof MCell) {
				MCell mc = (MCell) model;

				int height = (Integer) mc.getPropertyValue(DesignCell.PROPERTY_HEIGHT) + constraintR.height;
				if (height < 0)
					height = 0;

				SetValueCommand setCommand = new SetValueCommand();
				setCommand.setTarget(model);
				setCommand.setPropertyId(DesignCell.PROPERTY_HEIGHT);
				setCommand.setPropertyValue(height);
				c.add(setCommand);
			}
			if (constraintR.width > 0) {
				int width = jrdesign.getWidth() + constraintR.width;
				if (width < 0)
					width = 0;

				SetValueCommand setCommand = new SetValueCommand();
				setCommand.setTarget(model);
				setCommand.setPropertyId(StandardBaseColumn.PROPERTY_WIDTH);
				setCommand.setPropertyValue(width);
				c.add(setCommand);
			}
			return c;
		}
		return super.createChangeConstraintCommand(child, constraint);
	}

	protected Command createChangeConstraintCommand(ChangeBoundsRequest request, EditPart child, Object constraint) {
		Command result = createChangeConstraintCommand(child, constraint);
		if (child instanceof TableCellEditPart)
			return result;
		return super.createChangeConstraintCommand(request, child, constraint);
	}

	@Override
	protected EditPolicy createChildEditPolicy(EditPart child) {
		if (child instanceof TableCellEditPart) {
			return new TableCellResizableEditPolicy();
		}
		return super.createChildEditPolicy(child);
	}

}
