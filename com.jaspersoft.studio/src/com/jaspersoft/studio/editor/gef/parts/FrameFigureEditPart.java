/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.parts;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.CompoundSnapToHelper;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.SnapToGuides;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.jface.viewers.ISelection;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.editor.gef.commands.SetPageConstraintCommand;
import com.jaspersoft.studio.editor.gef.figures.ReportPageFigure;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.ColoredLayoutPositionRectangle;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.FigureSelectionEditPolicy;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.PageLayoutEditPolicy;
import com.jaspersoft.studio.editor.outline.OutlineTreeEditPartFactory;
import com.jaspersoft.studio.editor.outline.editpolicy.CloseSubeditorDeletePolicy;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IContainer;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.MPage;
import com.jaspersoft.studio.model.command.CreateElementCommand;
import com.jaspersoft.studio.model.field.MField;
import com.jaspersoft.studio.model.frame.MFrame;
import com.jaspersoft.studio.preferences.RulersGridPreferencePage;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.engine.design.JRDesignElement;

public class FrameFigureEditPart extends FigureEditPart implements IContainer {

	/**
	 * Color of the feedback when an element is dragged into the frame
	 */
	public static Color addElementColor = new Color(Color.blue.getRed(), Color.blue.getGreen(), Color.blue.getBlue(), 128);

	/**
	 * figure of the target feedback
	 */
	private ColoredLayoutPositionRectangle targetFeedback;

	@Override
	public MFrame getModel() {
		return (MFrame) super.getModel();
	}

	/**
	 * True if the frame figure has a target figure feedback set, otherwise false
	 * 
	 * @return
	 */
	public boolean hasTargetFeedBack() {
		return targetFeedback != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	@Override
	protected void createEditPolicies() {
		// super.createEditPolicies();
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new CloseSubeditorDeletePolicy());
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new FigureSelectionEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new PageLayoutEditPolicy() {

			@Override
			protected Command getCreateCommand(ANode parent, Object obj, Rectangle constraint, int index, Request request) {
				if (parent instanceof MPage)
					parent = getModel();
				Rectangle b = getModel().getBounds();
				int x = constraint.x - b.x - ReportPageFigure.PAGE_BORDER.left;
				int y = constraint.y - b.y - ReportPageFigure.PAGE_BORDER.top;
				constraint = new Rectangle(x, y, constraint.width, constraint.height);
				return super.getCreateCommand(parent, obj, constraint, index, request);
			}
			
			/**
			 * Overrides <code>getAddCommand()</code> to generate the proper constraint
			 * for each child being added. Once the constraint is calculated,
			 * {@link #createAddCommand(EditPart,Object)} is called. It will also enable
			 * the restore of the selection so when an element is added inside a frame its
			 * selection will be preserved
			 */
			protected Command getAddCommand(Request generic) {
				ChangeBoundsRequest request = (ChangeBoundsRequest) generic;
				List<?> editParts = request.getEditParts();
				MFrame mband = (MFrame)getModel();
				JSSCompoundCommand command = new JSSCompoundCommand(mband);
				command.enableSelectionRestore(true);
				command.setDebugLabel("Add in Frame");//$NON-NLS-1$
				GraphicalEditPart child;
				ISelection currentSelection = null;
				for (int i = 0; i < editParts.size(); i++) {
					child = (GraphicalEditPart) editParts.get(i);
					if (currentSelection == null){
						currentSelection = child.getViewer().getSelection();
					}
					command.add(createAddCommand(request, child, translateToModelConstraint(getConstraintFor(request, child))));
				}
				return command;
			}

			@Override
			protected Command createAddCommand(ChangeBoundsRequest request, EditPart child, Object constraint) {
				Rectangle rect = (Rectangle) constraint;
				if (child.getModel() instanceof MGraphicElement) {
					MGraphicElement cmodel = (MGraphicElement) child.getModel();
					//if the parent of the child its a frame, then its a SetPageConstraintCommand because
					//i'm moving the element inside the frame
					if (cmodel.getParent() instanceof MFrame && cmodel.getParent() == getModel()) {
						SetPageConstraintCommand cmd = new SetPageConstraintCommand();
						MGraphicElement model = (MGraphicElement) child.getModel();
						Rectangle r = model.getBounds();

						JRDesignElement jde = (JRDesignElement) model.getValue();
						int x = r.x + rect.x - jde.getX() + 1;
						int y = r.y + rect.y - jde.getY() + 1;
						rect.setLocation(x, y);
						cmd.setContext(getModel(), (ANode) child.getModel(), rect);

						return cmd;
					} else {
						//Return a CompoundCommand, because the JSSCompoundCommand will be created by the getAddCommand method
						CompoundCommand c = new CompoundCommand();
						// Without this an element it's one point up when placed into a frame
						rect.y++;
						c.add(OutlineTreeEditPartFactory.getOrphanCommand(cmodel.getParent(), cmodel));
						CreateElementCommand createCommand = new CreateElementCommand(getModel(), cmodel, rect, -1);
						createCommand.setApplyDefault(false);
						c.add(createCommand);
						return c;
					}
				} else {
					return super.createChangeConstraintCommand(child, constraint);
				}
			}
			
			/**
			 * Move an element inside the frame, in this case the parent never change
			 */
			@Override
			protected Command createChangeConstraintCommand(ChangeBoundsRequest request, EditPart child, Object constraint) {
				Rectangle rect = ((Rectangle) constraint).getCopy();
				if (child.getModel() instanceof MGraphicElement) {
					SetPageConstraintCommand cmd = new SetPageConstraintCommand();
					MGraphicElement model = (MGraphicElement) child.getModel();
					Rectangle r = model.getBounds();

					JRDesignElement jde = (JRDesignElement) model.getValue();
					int x = r.x + rect.x - jde.getX() + 1;
					int y = r.y + rect.y - jde.getY() + 1;
					rect.setLocation(x, y);
					cmd.setContext(getModel(), (ANode) child.getModel(), rect);

					return cmd;
				}
				return null;
			}
			
			/**
			 * Show the feedback during drag and drop
			 */
			@Override
			protected void showLayoutTargetFeedback(Request request) {
				super.showLayoutTargetFeedback(request);
				getLayoutTargetFeedback(request);
			}

			/**
			 * Erase the feedback from a ban when no element is dragged into it
			 */
			@Override
			protected void eraseLayoutTargetFeedback(Request request) {
				super.eraseLayoutTargetFeedback(request);
				if (targetFeedback != null) {
					removeFeedback(targetFeedback);
					targetFeedback = null;
				}
			}

			/**
			 * Paint the figure to give the feedback, a blue border overlapping the band border
			 * 
			 * @param request
			 * @return feedback figure
			 */
			protected IFigure getLayoutTargetFeedback(Request request) {
				if (targetFeedback == null) {
					Point mouseLocation = null;
					List<Object> nodes = new ArrayList<Object>();
					if (request.getType().equals(RequestConstants.REQ_CREATE) && request instanceof CreateRequest) {
						CreateRequest cbr = (CreateRequest) request;
						mouseLocation = cbr.getLocation();
						if (cbr.getNewObject() instanceof Collection<?>) {
							Collection<?> c = (Collection<?>) cbr.getNewObject();
							if (!c.isEmpty()) {
								for(Object obj : c){
									if (obj instanceof MField){
										nodes.add((MField)obj);
									}
								}
							}
						} else {
							nodes.add(cbr.getNewObject());
						}
					} else if (request instanceof ChangeBoundsRequest) {
						ChangeBoundsRequest cbr = (ChangeBoundsRequest) request;
						mouseLocation = cbr.getLocation();
						@SuppressWarnings("unchecked")
						List<EditPart> lst = cbr.getEditParts();
						for (EditPart ep : lst) {
							nodes.add(ep.getModel());
						}
					}
					//get the drop position to generate the correct feedback
					int index = -1;
					if (mouseLocation != null){
						IFigure bandFigure = getFigure();
				        Point location = mouseLocation.getCopy();
				        bandFigure.translateToRelative(location);
				        Dimension newLocation = location.getDifference(bandFigure.getBounds().getTopLeft());
				        index = ModelUtils.getBetweenIndex(getModel(), new Point(newLocation.width, newLocation.height));
					}
					targetFeedback = new ColoredLayoutPositionRectangle(FrameFigureEditPart.this, FrameFigureEditPart.addElementColor, 2.0f, getModel(), nodes, index);
					targetFeedback.setFill(false);

					IFigure hostFigure = getHostFigure();
					Rectangle bounds = hostFigure.getBounds();
					if (hostFigure instanceof HandleBounds)
						bounds = ((HandleBounds) hostFigure).getHandleBounds();
					Rectangle rect = new PrecisionRectangle(bounds);
					double zoom = 1.0d;
					ZoomManager zoomMgr = (ZoomManager) getViewer().getProperty(ZoomManager.class.toString());
					if (zoomMgr != null) {
						zoom = zoomMgr.getZoom();
					}
					rect.scale(zoom);
					targetFeedback.setBounds(rect);
					addFeedback(targetFeedback);
				}
				return targetFeedback;
			}
		});
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class key) {
		if (key == SnapToHelper.class) {
			List<SnapToHelper> snapStrategies = new ArrayList<SnapToHelper>();
			Boolean val = jConfig.getPropertyBoolean(RulersGridPreferencePage.P_PAGE_RULERGRID_SHOWRULER, Boolean.TRUE);
			Boolean stg = jConfig.getPropertyBoolean(RulersGridPreferencePage.P_PAGE_RULERGRID_SNAPTOGUIDES, Boolean.TRUE);
			if (val.booleanValue() && stg != null && stg.booleanValue())
				snapStrategies.add(new SnapToGuides(this));
			val = jConfig.getPropertyBoolean(RulersGridPreferencePage.P_PAGE_RULERGRID_SNAPTOGEOMETRY, Boolean.TRUE);
			if (val.booleanValue()) {

				SnapToGeometryThreshold snapper = new SnapToGeometryThreshold(this);
				snapper.setThreshold(2.0);
				snapStrategies.add(snapper);
			}
			val = jConfig.getPropertyBoolean(RulersGridPreferencePage.P_PAGE_RULERGRID_SNAPTOGRID, Boolean.TRUE);
			if (val.booleanValue())
				snapStrategies.add(new SnapToGrid(this));

			if (snapStrategies.size() == 0)
				return null;
			if (snapStrategies.size() == 1)
				return snapStrategies.get(0);

			SnapToHelper ss[] = new SnapToHelper[snapStrategies.size()];
			for (int i = 0; i < snapStrategies.size(); i++)
				ss[i] = snapStrategies.get(i);
			return new CompoundSnapToHelper(ss);
		}
		return super.getAdapter(key);
	}
}
