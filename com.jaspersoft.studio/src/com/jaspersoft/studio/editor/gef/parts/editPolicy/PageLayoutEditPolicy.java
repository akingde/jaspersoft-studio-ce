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
package com.jaspersoft.studio.editor.gef.parts.editPolicy;

import net.sf.jasperreports.engine.design.JRDesignBand;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;

import com.jaspersoft.studio.editor.action.create.CreateElementAction;
import com.jaspersoft.studio.editor.gef.commands.SetConstraintCommand;
import com.jaspersoft.studio.editor.gef.parts.AJDEditPart;
import com.jaspersoft.studio.editor.gef.parts.band.BandEditPart;
import com.jaspersoft.studio.editor.outline.OutlineTreeEditPartFactory;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.property.SetValueCommand;

/**
 * The Class PageLayoutEditPolicy.
 */
public class PageLayoutEditPolicy extends XYLayoutEditPolicy {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editpolicies.XYLayoutEditPolicy#getConstraintFor(org.eclipse.gef.requests.ChangeBoundsRequest,
	 * org.eclipse.gef.GraphicalEditPart)
	 */
	@Override
	protected Object getConstraintFor(ChangeBoundsRequest request, GraphicalEditPart child) {
		System.out.println("Constraint request:  " + request.getMoveDelta());
		if (child instanceof BandEditPart) {
			if (request.getResizeDirection() == PositionConstants.SOUTH
					|| request.getResizeDirection() == PositionConstants.NORTH)
				System.out.println(" Constraint request:  " + request.getSizeDelta() + "  " + request.getResizeDirection());
			return new Rectangle(0, 0, 0, request.getSizeDelta().height);
		}
		// If we are dragging a band, we need to check the bounds and suggest a
		// proper
		// new location accordingly with the max and min band size.
		IFigure figure = child.getFigure();
		Rectangle bounds = figure.getBounds();
		if (figure instanceof HandleBounds)
			bounds = ((HandleBounds) figure).getHandleBounds();
		Rectangle rect = new PrecisionRectangle(bounds);
		Rectangle original = rect.getCopy();
		figure.translateToAbsolute(rect);
		rect = request.getTransformedRectangle(rect);
		figure.translateToRelative(rect);
		rect.translate(getLayoutOrigin().getNegated());

		if (request.getSizeDelta().width == 0 && request.getSizeDelta().height == 0) {
			Rectangle cons = getCurrentConstraintFor(child);
			if (cons != null) // Bug 86473 allows for unintended use of this method
				rect.setSize(cons.width, cons.height);
		} else { // resize
			Dimension minSize = getMinimumSizeFor(child);
			if (rect.width < minSize.width) {
				rect.width = minSize.width;
				if (rect.x > (original.right() - minSize.width))
					rect.x = original.right() - minSize.width;
			}
			if (rect.height < minSize.height) {
				rect.height = minSize.height;
				if (rect.y > (original.bottom() - minSize.height))
					rect.y = original.bottom() - minSize.height;
			}
		}
		return getConstraintFor(rect);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getCreateCommand(org.eclipse.gef.requests.CreateRequest)
	 */
	@Override
	protected Command getCreateCommand(CreateRequest request) {
		System.out.println("DESIGNER CREATE COMMAND " + request);
		if (request.getType() == REQ_MOVE) {
			System.out.println("getCreateCommand " + request);
		} else if (request.getType() == REQ_CREATE && getHost() instanceof AJDEditPart) {

			Rectangle constraint = (Rectangle) getConstraintFor(request);

			if (request.getNewObject() instanceof CreateElementAction) {
				CreateElementAction action = (CreateElementAction) request.getNewObject();
				action.dropInto(getHost().getModel(), new Point(constraint.x, constraint.y), -1);
				action.run();
				return action.getCommand();
			} else if (request.getNewObject() instanceof MGraphicElement) {
				return OutlineTreeEditPartFactory.getCreateCommand((ANode) getHost().getModel(), (MGraphicElement) request
						.getNewObject(), new Point(constraint.x, constraint.y), -1);
			}
		}
		return null;
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
		if (child instanceof BandEditPart) {
			MBand model = (MBand) child.getModel();
			JRDesignBand jrdesign = (JRDesignBand) model.getValue();
			int height = jrdesign.getHeight() + ((Rectangle) constraint).height;
			if (height < 0)
				height = 0;
			SetValueCommand setCommand = new SetValueCommand();
			setCommand.setTarget(model);
			setCommand.setPropertyId(JRDesignBand.PROPERTY_HEIGHT);
			setCommand.setPropertyValue(height);
			return setCommand;

		}
		SetConstraintCommand cmd = new SetConstraintCommand();
		cmd.setContext((ANode) getHost().getModel(), (ANode) child.getModel(), (Rectangle) constraint);
		return cmd;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#createChildEditPolicy(org.eclipse.gef.EditPart)
	 */
	@Override
	protected EditPolicy createChildEditPolicy(EditPart child) {
		if (child instanceof BandEditPart) {
			return new BandResizableEditPolicy();
		}
		return super.createChildEditPolicy(child);
	}

}
