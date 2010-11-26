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

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.SnapToGuides;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.rulers.RulerProvider;

import com.jaspersoft.studio.editor.action.create.CreateElementAction;
import com.jaspersoft.studio.editor.gef.commands.SetConstraintCommand;
import com.jaspersoft.studio.editor.gef.parts.AJDEditPart;
import com.jaspersoft.studio.editor.gef.parts.IContainerPart;
import com.jaspersoft.studio.editor.gef.rulers.ReportRulerGuide;
import com.jaspersoft.studio.editor.gef.rulers.command.ChangeGuideCommand;
import com.jaspersoft.studio.editor.outline.OutlineTreeEditPartFactory;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MGraphicElement;

/**
 * The Class PageLayoutEditPolicy.
 */
public class PageLayoutEditPolicy extends XYLayoutEditPolicy {
	protected Command chainGuideAttachmentCommand(Request request, MGraphicElement part, Command cmd, boolean horizontal) {
		Command result = cmd;

		// Attach to guide, if one is given
		Integer guidePos = (Integer) request.getExtendedData().get(
				horizontal ? SnapToGuides.KEY_HORIZONTAL_GUIDE : SnapToGuides.KEY_VERTICAL_GUIDE);
		if (guidePos != null) {
			int alignment = ((Integer) request.getExtendedData().get(
					horizontal ? SnapToGuides.KEY_HORIZONTAL_ANCHOR : SnapToGuides.KEY_VERTICAL_ANCHOR)).intValue();
			ChangeGuideCommand cgm = new ChangeGuideCommand(part, horizontal);
			cgm.setNewGuide(findGuideAt(guidePos.intValue(), horizontal), alignment);
			result = result.chain(cgm);
		}

		return result;
	}

	protected Command chainGuideDetachmentCommand(Request request, MGraphicElement part, Command cmd, boolean horizontal) {
		Command result = cmd;

		// Detach from guide, if none is given
		Integer guidePos = (Integer) request.getExtendedData().get(
				horizontal ? SnapToGuides.KEY_HORIZONTAL_GUIDE : SnapToGuides.KEY_VERTICAL_GUIDE);
		if (guidePos == null)
			result = result.chain(new ChangeGuideCommand(part, horizontal));

		return result;
	}

	protected ReportRulerGuide findGuideAt(int pos, boolean horizontal) {
		RulerProvider provider = ((RulerProvider) getHost().getViewer().getProperty(
				horizontal ? RulerProvider.PROPERTY_VERTICAL_RULER : RulerProvider.PROPERTY_HORIZONTAL_RULER));
		return (ReportRulerGuide) provider.getGuideAt(pos);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editpolicies.XYLayoutEditPolicy#getConstraintFor(org.eclipse.gef.requests.ChangeBoundsRequest,
	 * org.eclipse.gef.GraphicalEditPart)
	 */
	@Override
	protected Object getConstraintFor(ChangeBoundsRequest request, GraphicalEditPart child) {
		System.out.println("Constraint request:  " + request.getMoveDelta());
		if (child instanceof IContainerPart) {
			return ((IContainerPart) child).getConstraintFor(request, child);
		}
		// If we are dragging a band, we need to check the bounds and suggest a
		// proper
		// new location accordingly with the max and min band size.
		IFigure figure = child.getFigure();
		Rectangle bounds = figure.getBounds();
		if (figure instanceof HandleBounds) {
			bounds = ((HandleBounds) figure).getHandleBounds();
			bounds.width = bounds.width - 1;
			bounds.height = bounds.height - 1;
		}
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
		// Command create = null;
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
				return OutlineTreeEditPartFactory.getCreateCommand((ANode) getHost().getModel(),
						(MGraphicElement) request.getNewObject(), new Point(constraint.x, constraint.y), -1);
			}
		}

		// Command cmd = chainGuideAttachmentCommand(request, newPart, create, true);
		// return chainGuideAttachmentCommand(request, newPart, cmd, false);
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
		if (child instanceof IContainerPart) {
			return ((IContainerPart) child).createChangeConstraintCommand(child, constraint);
		}
		SetConstraintCommand cmd = new SetConstraintCommand();
		cmd.setContext((ANode) getHost().getModel(), (ANode) child.getModel(), (Rectangle) constraint);

		return cmd;
	}

	protected Command createChangeConstraintCommand(ChangeBoundsRequest request, EditPart child, Object constraint) {
		Command result = createChangeConstraintCommand(child, constraint);
		if (child instanceof IContainerPart)
			return result;
		if (child.getModel() instanceof MGraphicElement) {
			MGraphicElement part = (MGraphicElement) child.getModel();

			if ((request.getResizeDirection() & PositionConstants.NORTH_SOUTH) != 0) {
				Integer guidePos = (Integer) request.getExtendedData().get(SnapToGuides.KEY_HORIZONTAL_GUIDE);
				if (guidePos != null) {
					result = chainGuideAttachmentCommand(request, part, result, true);
				} else if (part.getHorizontalGuide() != null) {
					// SnapToGuides didn't provide a horizontal guide, but this part is attached
					// to a horizontal guide. Now we check to see if the part is attached to
					// the guide along the edge being resized. If that is the case, we need to
					// detach the part from the guide; otherwise, we leave it alone.
					int alignment = part.getHorizontalGuide().getAlignment(part);
					int edgeBeingResized = 0;
					if ((request.getResizeDirection() & PositionConstants.NORTH) != 0)
						edgeBeingResized = -1;
					else
						edgeBeingResized = 1;
					if (alignment == edgeBeingResized)
						result = result.chain(new ChangeGuideCommand(part, true));
				}
			}

			if ((request.getResizeDirection() & PositionConstants.EAST_WEST) != 0) {
				Integer guidePos = (Integer) request.getExtendedData().get(SnapToGuides.KEY_VERTICAL_GUIDE);
				if (guidePos != null) {
					result = chainGuideAttachmentCommand(request, part, result, false);
				} else if (part.getVerticalGuide() != null) {
					int alignment = part.getVerticalGuide().getAlignment(part);
					int edgeBeingResized = 0;
					if ((request.getResizeDirection() & PositionConstants.WEST) != 0)
						edgeBeingResized = -1;
					else
						edgeBeingResized = 1;
					if (alignment == edgeBeingResized)
						result = result.chain(new ChangeGuideCommand(part, false));
				}
			}

			if (request.getType().equals(REQ_MOVE_CHILDREN) || request.getType().equals(REQ_ALIGN_CHILDREN)) {
				result = chainGuideAttachmentCommand(request, part, result, true);
				result = chainGuideAttachmentCommand(request, part, result, false);
				result = chainGuideDetachmentCommand(request, part, result, true);
				result = chainGuideDetachmentCommand(request, part, result, false);
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#createChildEditPolicy(org.eclipse.gef.EditPart)
	 */
	@Override
	protected EditPolicy createChildEditPolicy(EditPart child) {
		if (child instanceof IContainerPart) {
			return ((IContainerPart) child).getEditPolicy();
		}
		return super.createChildEditPolicy(child);
	}

}
