/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
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

import java.util.Collection;

import net.sf.jasperreports.engine.design.JRDesignGraphicElement;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.SnapToGuides;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.rulers.RulerProvider;

import com.jaspersoft.studio.editor.action.create.CreateElementAction;
import com.jaspersoft.studio.editor.gef.commands.SetConstraintCommand;
import com.jaspersoft.studio.editor.gef.parts.AJDEditPart;
import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.editor.gef.parts.IContainerPart;
import com.jaspersoft.studio.editor.gef.rulers.ReportRulerGuide;
import com.jaspersoft.studio.editor.gef.rulers.command.ChangeGuideCommand;
import com.jaspersoft.studio.editor.outline.OutlineTreeEditPartFactory;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IGuidebleElement;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.band.MBand;

/*
 * The Class PageLayoutEditPolicy.
 */
public class PageLayoutEditPolicy extends XYLayoutEditPolicy {

	@Override
	protected Dimension getMinimumSizeFor(GraphicalEditPart child) {
		return new Dimension(1, 1);
	}

	protected Command chainGuideAttachmentCommand(Request request, IGuidebleElement part, Command cmd, boolean horizontal) {
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

	protected Command chainGuideDetachmentCommand(Request request, IGuidebleElement part, Command cmd, boolean horizontal) {
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
		// if (child instanceof IContainerPart) {
		// return ((IContainerPart) child).getConstraintFor(request, child);
		// }
		// If we are dragging a band, we need to check the bounds and suggest a
		// proper
		// new location accordingly with the max and min band size.
		IFigure figure = child.getFigure();
		Rectangle bounds = figure.getBounds();
		if (figure instanceof HandleBounds) {
			bounds = ((HandleBounds) figure).getHandleBounds();
			// if (request.getResizeDirection() == PositionConstants.NORTH
			// || request.getResizeDirection() == PositionConstants.SOUTH)
			bounds.width--;
			// if (request.getResizeDirection() == PositionConstants.EAST
			// || request.getResizeDirection() == PositionConstants.WEST)
			bounds.height--;
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
		if (request.getType() == REQ_CREATE && getHost() instanceof AJDEditPart) {
			Rectangle constraint = adaptConstraint(getConstraintFor(request));

			ANode parent = (ANode) getHost().getModel();
			Rectangle copyconstraint = constraint.getCopy();
			if (request.getNewObject() instanceof Collection<?>) {
				CompoundCommand ccmd = new CompoundCommand();
				for (Object it : (Collection<?>) request.getNewObject()) {
					Command cmd = getCreateCommand(parent, it, copyconstraint.getCopy());
					if (cmd != null) {
						ccmd.add(cmd);
						copyconstraint.translate(70, 0);
					}
				}
				if (!ccmd.isEmpty())
					return ccmd;
			} else if (request.getNewObject() instanceof CreateElementAction) {
				CreateElementAction action = (CreateElementAction) request.getNewObject();
				action.dropInto(getHost().getModel(), copyconstraint, -1);
				action.run();
				return action.getCommand();
			} else
				return getCreateCommand(parent, request.getNewObject(), copyconstraint);
		}

		// Command cmd = chainGuideAttachmentCommand(request, newPart, create, true);
		// return chainGuideAttachmentCommand(request, newPart, cmd, false);
		return null;
	}

	protected Command getCreateCommand(ANode parent, Object obj, Rectangle constraint) {
		if (obj instanceof ANode) {
			ANode aNode = (ANode) obj;
			if (aNode instanceof MGraphicElement) {
				Object value = aNode.getValue();
				if (value != null && value instanceof JRDesignGraphicElement) {
					JRDesignGraphicElement jrDesignGraphicElement = (JRDesignGraphicElement) value;
					try {
						aNode = aNode.getClass().newInstance();
					} catch (InstantiationException e) {
						e.printStackTrace();
						return null;
					} catch (IllegalAccessException e) {
						e.printStackTrace();
						return null;
					}
					aNode.setValue(jrDesignGraphicElement.clone());

					constraint.width = jrDesignGraphicElement.getWidth();
					constraint.height = jrDesignGraphicElement.getHeight();
				}
				if (parent instanceof MBand) {
					MBand band = (MBand) parent;
					int x = constraint.x - band.getBounds().x;
					int y = constraint.y - band.getBounds().y;
					constraint.setLocation(x, y);
				}
			}
			return OutlineTreeEditPartFactory.getCreateCommand(parent, aNode, constraint, -1);
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
		// if (child instanceof IContainerPart) {
		// return ((IContainerPart) child).createChangeConstraintCommand(child, constraint);
		// }

		SetConstraintCommand cmd = new SetConstraintCommand();
		cmd.setContext((ANode) getHost().getModel(), (ANode) child.getModel(), adaptConstraint(constraint));

		return cmd;
	}

	protected Rectangle adaptConstraint(Object constraint) {
		Rectangle r = (Rectangle) constraint;
		Insets insets = getHostFigure().getInsets();
		r.translate(getLayoutOrigin().translate(-insets.left, -insets.top));
		return r;
	}

	@Override
	protected Command createChangeConstraintCommand(ChangeBoundsRequest request, EditPart child, Object constraint) {
		Command result = createChangeConstraintCommand(child, constraint);
		if (child instanceof IContainerPart)
			return result;
		if (child.getModel() instanceof IGuidebleElement) {
			IGuidebleElement part = (IGuidebleElement) child.getModel();

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
		if (child instanceof FigureEditPart) {
			return new ElementResizableEditPolicy();
		}
		return super.createChildEditPolicy(child);
	}

}
