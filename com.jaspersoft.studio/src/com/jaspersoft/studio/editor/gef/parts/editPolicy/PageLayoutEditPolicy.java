/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.parts.editPolicy;

import java.util.Collection;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
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

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.background.BackgroundImageEditPart;
import com.jaspersoft.studio.callout.CalloutEditPart;
import com.jaspersoft.studio.callout.CalloutElementResizableEditPolicy;
import com.jaspersoft.studio.callout.command.CalloutSetConstraintCommand;
import com.jaspersoft.studio.callout.pin.PinEditPart;
import com.jaspersoft.studio.callout.pin.PinMoveEditPolicy;
import com.jaspersoft.studio.callout.pin.command.PinSetConstraintCommand;
import com.jaspersoft.studio.editor.action.create.CreateElementAction;
import com.jaspersoft.studio.editor.gef.commands.SetConstraintCommand;
import com.jaspersoft.studio.editor.gef.figures.ReportPageFigure;
import com.jaspersoft.studio.editor.gef.parts.AJDEditPart;
import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.editor.gef.parts.IContainerPart;
import com.jaspersoft.studio.editor.gef.rulers.ReportRulerGuide;
import com.jaspersoft.studio.editor.gef.rulers.command.ChangeGuideCommand;
import com.jaspersoft.studio.editor.gef.util.CreateRequestUtil;
import com.jaspersoft.studio.editor.outline.OutlineTreeEditPartFactory;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IContainerLayout;
import com.jaspersoft.studio.model.IGraphicElement;
import com.jaspersoft.studio.model.IGuidebleElement;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.field.FieldUtils;
import com.jaspersoft.studio.model.field.MField;
import com.jaspersoft.studio.model.field.MFields;
import com.jaspersoft.studio.model.parameter.MParameter;
import com.jaspersoft.studio.model.variable.MVariable;

import net.sf.jasperreports.engine.design.JRDesignGraphicElement;

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
			}
			if (rect.height < minSize.height) {
				rect.height = minSize.height;
			}
		}
		return getConstraintFor(rect);
	}

	@Override
	protected Point getLayoutOrigin() {
		IFigure container = getLayoutContainer();
		if (!(container.getLayoutManager() instanceof XYLayout))
			return container.getParent().getClientArea().getLocation();
		return super.getLayoutOrigin();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getCreateCommand(org.eclipse.gef.requests.CreateRequest)
	 */
	@Override
	protected Command getCreateCommand(CreateRequest request) {
		int index = CreateRequestUtil.getNewIndex(request);
		if (request.getType() == REQ_CREATE && getHost() instanceof AJDEditPart) {
			Rectangle constraint = adaptConstraint(getConstraintFor(request));
			ANode parent = (ANode) getHost().getModel();
			Rectangle copyconstraint = constraint.getCopy();
			if (request.getNewObject() instanceof Collection<?>) {
				JSSCompoundCommand ccmd = new JSSCompoundCommand(parent);
				Collection<?> objs = (Collection<?>) request.getNewObject(); 
				boolean positionModifiedCreation = false;
				if (parent instanceof IGraphicElement && !isGraphicObjects(objs)) {
					if (objs.size() > 1) {
						positionModifiedCreation = true;
						Rectangle rparent = ((IGraphicElement) parent).getBounds();
						int w = rparent.width / objs.size();
						int rest = rparent.width - w * objs.size();
						copyconstraint.setLocation(rparent.x + ReportPageFigure.PAGE_BORDER.left, copyconstraint.getLocation().y);
						// Commented for back-compatibility in 3.6.
						// Replaced with the following line.
						// copyconstraint.setWidth(w + rest);
						copyconstraint.width = w + rest;
						for (Object it : objs) {
							Command cmd = getCreateCommand(parent, it, copyconstraint.getCopy(), index, request);
							if (cmd != null) {
								ccmd.add(cmd);
								copyconstraint.translate(w + rest, 0);
								// Commented for back-compatibility in 3.6.
								// Replaced with the following line.
								// copyconstraint.setWidth(w);
								copyconstraint.width = w;
								rest = 0;
							}
						}
					} 	//THE CASE WHERE A SINGLE ITEM CREATED INTO AN EMPTY CONTAINER IS MOVED TO 0,0 WAS REMOVED DUE TO THE BUG JSS-662
						//ALSO THE ADJUST CONSTRINT CALL WAS MODIFIED TO AVOID THE Y SET TO 0
						/*else if (objs.size() == 1 && parent.getChildren().isEmpty()) {	
						//here if the element is moved to the top left if the parent is empty
						positionModifiedCreation = true;
						Rectangle rparent = ((IGraphicElement) parent).getBounds();
						copyconstraint.setLocation(rparent.x + ReportPageFigure.PAGE_BORDER.left, copyconstraint.getLocation().y);
						Command cmd = getCreateCommand(parent, objs.iterator().next(), copyconstraint.getCopy(), index, request);
						if (cmd != null) {
							ccmd.add(cmd);
						}	
					}*/
				} 
				if (!positionModifiedCreation) {
					for (Object it : objs) {
						Command cmd = getCreateCommand(parent, it, copyconstraint.getCopy(), index, request);
						if (cmd != null) {
							ccmd.add(cmd);
							copyconstraint.translate(70, 0);
						}
					}
				}
				if (!ccmd.isEmpty())
					return ccmd;
			} else if (request.getNewObject() instanceof CreateElementAction) {
				CreateElementAction action = (CreateElementAction) request.getNewObject();
				action.dropInto(getHost().getModel(), copyconstraint, -1);
				action.run();
				return action.getCommand();
			} else {
				return getCreateCommand(parent, request.getNewObject(), copyconstraint, index, request);
			}
		}
		return null;
	}

	private boolean isGraphicObjects(Collection<?> objs) {
		for (Object obj : objs)
			if (!(obj instanceof MGraphicElement))
				return false;
		return true;
	}

	/**
	 * Take and edit part and search it's container
	 * 
	 * @param child
	 * @return the container of the child, could be null
	 */
	private IGraphicElement searchParent(ANode child) {
		if (child != null) {
			// This use the model for the search because every EditPart in the report has the same father.
			Object parentModel = child.getParent();
			for (Object actualChild : getHost().getParent().getChildren()) {
				EditPart actualChildPart = (EditPart) actualChild;
				if (parentModel == actualChildPart.getModel()) {
					return (IGraphicElement) actualChildPart.getModel();
				}
			}
		}
		return null;
	}

	/**
	 * Added the search of the container element in case the the parent node is not a container.
	 * Doing this is necessary to permit to user to placing and element up another even if the second is not a container.
	 * 
	 * @param parent
	 * @param obj
	 * @param constraint
	 * @param index
	 * @return
	 */
	protected Command getCreateCommand(ANode parent, Object obj, Rectangle constraint, int index, Request request) {
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
				} else if (parent instanceof MGraphicElement) {
					// Parent is a graphical element, check if it's a container
					if (!(parent instanceof IContainerLayout)) {
						IGraphicElement container = searchParent(parent);
						int x = constraint.x - container.getBounds().x;
						int y = constraint.y - container.getBounds().y;
						constraint.setLocation(x, y);
						parent = (ANode) container;
					}
				}
			} else if (aNode instanceof MField || aNode instanceof MParameter || aNode instanceof MVariable) {
				//AVOID TO SET THE Y TO 0 IF ONLY AN ELEMENT IS CREATED
				boolean adjustConstraints = true;
				if (request != null && request instanceof CreateRequest) {
					CreateRequest createReq = (CreateRequest)request;
					if (createReq.getNewObject() instanceof Collection<?>) {
						Collection<?> objs = (Collection<?>) createReq.getNewObject(); 
						if (objs.size() <= 1) {
							adjustConstraints = false;
						}
					} else {
						adjustConstraints = false;
					}
				}
				if (adjustConstraints) adjustConstraints(parent, constraint);
			} else if (aNode instanceof MFields) {
				adjustConstraints(parent, constraint);
				CompoundCommand c = new CompoundCommand();
				List<MField> fields = FieldUtils.getFields((MFields) aNode);

				Rectangle rparent = ((IGraphicElement) parent).getBounds();
				int w = rparent.width / fields.size();
				int rest = rparent.width - w * fields.size() - constraint.x;
				constraint.width = w + rest;
				for (MField f : fields) {
					Command cmd = OutlineTreeEditPartFactory.getCreateCommand(parent, f, constraint.getCopy(), index, request, false);
					if (cmd != null && cmd.canExecute()) {
						c.add(cmd);
						constraint.translate(w + rest, 0);
						// Commented for back-compatibility in 3.6.
						// Replaced with the following line.
						// copyconstraint.setWidth(w);
						constraint.width = w;
						rest = 0;
					}
				}
				if (!c.isEmpty())
					return c;
				return null;
			}
			return OutlineTreeEditPartFactory.getCreateCommand(parent, aNode, constraint, index, request, false);
		}
		return null;
	}

	private void adjustConstraints(ANode parent, Rectangle constraint) {
		if (parent.getChildren().isEmpty())
			if (parent instanceof MGraphicElement)
				constraint.y = ((MGraphicElement) parent).getBounds().y;
			else if (parent instanceof MBand)
				constraint.y = ((MBand) parent).getBounds().y;
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
		if (child instanceof CalloutEditPart)
			return new CalloutSetConstraintCommand(((CalloutEditPart) child).getModel(), adaptConstraint(constraint));
		if (child instanceof PinEditPart)
			return new PinSetConstraintCommand(((PinEditPart) child).getModel(), adaptConstraint(constraint));

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
		if (child instanceof IContainerPart)
			return ((IContainerPart) child).getEditPolicy();
		if (child instanceof FigureEditPart)
			return new ElementResizableEditPolicy();
		if (child instanceof CalloutEditPart)
			return new CalloutElementResizableEditPolicy();
		if (child instanceof PinEditPart)
			return new PinMoveEditPolicy();
		return super.createChildEditPolicy(child);
	}
	
	/**
	 * Override of the method to avoid to add the standard edit policy on the 
	 * background elements, since it need its own policies to be handled correctly,
	 * for example it has a drag behavior really different from the other elements.
	 * 
	 * Also add the policy only to the elements that doesn't already have a specific EditPolicy.PRIMARY_DRAG_ROLE
	 */
	@Override
	protected void decorateChild(EditPart child) {
		if (!(child instanceof BackgroundImageEditPart) && child.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE) == null){
			EditPolicy policy = createChildEditPolicy(child);
			child.installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, policy);
		}
	}

}
