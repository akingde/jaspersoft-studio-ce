/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.part;

import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.jface.viewers.ISelection;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.callout.CalloutEditPart;
import com.jaspersoft.studio.callout.command.CalloutSetConstraintCommand;
import com.jaspersoft.studio.callout.pin.PinEditPart;
import com.jaspersoft.studio.callout.pin.command.PinSetConstraintCommand;
import com.jaspersoft.studio.components.crosstab.CrosstabComponentFactory;
import com.jaspersoft.studio.components.crosstab.figure.CellFigure;
import com.jaspersoft.studio.components.crosstab.model.MCrosstab;
import com.jaspersoft.studio.components.crosstab.model.cell.MCell;
import com.jaspersoft.studio.components.crosstab.model.cell.command.CreateElementCommand;
import com.jaspersoft.studio.components.crosstab.model.cell.command.OrphanElementCommand;
import com.jaspersoft.studio.components.crosstab.part.editpolicy.CrosstabCellResizableEditPolicy;
import com.jaspersoft.studio.editor.gef.commands.SetPageConstraintCommand;
import com.jaspersoft.studio.editor.gef.figures.ReportPageFigure;
import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.PageLayoutEditPolicy;
import com.jaspersoft.studio.editor.outline.editpolicy.CloseSubeditorDeletePolicy;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IGraphicElement;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.MPage;

import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.engine.design.JRDesignElement;

/*
 * BandEditPart creates the figure for the band. The figure is actually just the bottom border of the band. This allows
 * to drag this border to resize the band. The PageEditPart sets a specific contraint for the BandEditPart elements in
 * order to make them move only vertically. The BandMoveEditPolicy is responsable for the feedback when the band is
 * dragged.
 * 
 * @author Chicu Veaceslav, Giulio Toffoli
 * 
 */
public class CrosstabCellEditPart extends ACrosstabCellEditPart {

	@Override
	public MCell getModel() {
		return (MCell) super.getModel();
	}

	@Override
	public void performRequest(Request req) {
		if (RequestConstants.REQ_OPEN.equals(req.getType())) {
			Command c = CrosstabComponentFactory.INST().getStretchToContent(getModel());
			if (c != null)
				getViewer().getEditDomain().getCommandStack().execute(c);
		}
		super.performRequest(req);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new CloseSubeditorDeletePolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new PageLayoutEditPolicy() {

			private RectangleFigure targetFeedback;

			protected void eraseLayoutTargetFeedback(Request request) {
				super.eraseLayoutTargetFeedback(request);
				if (targetFeedback != null) {
					removeFeedback(targetFeedback);
					targetFeedback = null;
				}
			}
			
			protected IFigure getLayoutTargetFeedback(Request request) {
				if (request instanceof ChangeBoundsRequest) {
					ChangeBoundsRequest cbr = (ChangeBoundsRequest) request;
					List<EditPart> lst = cbr.getEditParts();
					for (EditPart ep : lst) {
						if (((ANode) ep.getModel()).getParent() == getModel())
							return null;
						if (ep instanceof CrosstabCellEditPart)
							return null;
					}
				} else if ((request instanceof CreateRequest) && !(getModel() instanceof MCell)){
					return null;
				}
				if (targetFeedback == null) {
					targetFeedback = new RectangleFigure();
					targetFeedback.setFill(false);

					IFigure hostFigure = getHostFigure();
					Rectangle bounds = hostFigure.getBounds();
					if (hostFigure instanceof HandleBounds)
						bounds = ((HandleBounds) hostFigure).getHandleBounds();
					Rectangle rect = new PrecisionRectangle(bounds);
					getHostFigure().translateToAbsolute(rect);
					getFeedbackLayer().translateToRelative(rect);

					targetFeedback.setBounds(rect.shrink(2, 2));
					targetFeedback.setBorder(new LineBorder(ColorConstants.lightBlue, 3));
					addFeedback(targetFeedback);
				}
				return targetFeedback;
			}

			protected void showLayoutTargetFeedback(Request request) {
				super.showLayoutTargetFeedback(request);
				getLayoutTargetFeedback(request);
			}

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
			 * the restore of the selection so when an element is added inside a cell its
			 * selection will be preserved
			 */
			protected Command getAddCommand(Request generic) {
				ChangeBoundsRequest request = (ChangeBoundsRequest) generic;
				List<?> editParts = request.getEditParts();
				MCell mband = getModel();
				JSSCompoundCommand command = new JSSCompoundCommand(mband);
				command.enableSelectionRestore(true);
				command.setDebugLabel("Add in Table Crosstab Cell");//$NON-NLS-1$
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
			protected Command createAddCommand(EditPart child, Object constraint) {
				Rectangle rect = (Rectangle) constraint;
				if (child.getModel() instanceof MGraphicElement) {
					MGraphicElement cmodel = (MGraphicElement) child.getModel();
					MCell cparent = (MCell) cmodel.getParent();
					if (cparent == getModel()) {
						//case when an element is moved inside the same cell
						SetPageConstraintCommand cmd = new SetPageConstraintCommand();
						MGraphicElement model = (MGraphicElement) child.getModel();
						Rectangle r = model.getBounds();

						JRDesignElement jde = (JRDesignElement) model.getValue();
						int x = r.x + rect.x - jde.getX() + 1;
						int y = r.y + rect.y - jde.getY() + 1;
						rect.setLocation(x, y);
						cmd.setContext(getModel(), (ANode) child.getModel(), rect);

						return cmd;
					} else if (cparent != null){
						//case when an element is moved from a cell to another
						//Return a CompoundCommand, because the JSSCompoundCommand will be created by the getAddCommand method
						CompoundCommand c = new CompoundCommand();
						c.add(new OrphanElementCommand(cparent, cmodel));
						c.add(new CreateElementCommand(getModel(), cmodel, rect, -1));
						return c;
					}
				} else if (child instanceof CalloutEditPart) {
					return new CalloutSetConstraintCommand(((CalloutEditPart) child).getModel(), adaptConstraint(constraint));
				} else if (child instanceof PinEditPart) {
					return new PinSetConstraintCommand(((PinEditPart) child).getModel(), adaptConstraint(constraint));
				}
				return null;
			}

		});
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new CrosstabCellResizableEditPolicy() {
			@Override
			protected void showSelection() {
				if (getFigure().getBounds().height>0){
					super.showSelection();
					updateRulers();
				}
			}
		});
	}

	@Override
	protected void setupFigure(IFigure rect) {
		updateContainerSize();
		MCell model = getModel();
		rect.setToolTip(new Label(model.getToolTip()));
		if (model.getValue() != null) {
			Rectangle bounds = ((IGraphicElement) model).getBounds();
			if (bounds == null)
				bounds = new Rectangle(0, 0, 0, 0);
			int x = bounds.x + ReportPageFigure.PAGE_BORDER.left;
			int y = bounds.y + ReportPageFigure.PAGE_BORDER.top;

			rect.setLocation(new Point(x, y));

			if (model instanceof MCell) {
				CellFigure f = (CellFigure) rect;
				f.setJRElement((JRDesignCellContents) model.getValue(), getDrawVisitor());
			} else {
				rect.setSize(bounds.width, bounds.height);
			}
			updateRulers();
		} else {
			// System.out.println("EMPTy");
		}
		if (getSelected() == 1)
			updateRulers();
		else {
			List<?> selected = getViewer().getSelectedEditParts();
			if (selected.isEmpty())
				updateRulers();
			else
				for (Object obj : selected) {
					if (obj instanceof FigureEditPart) {
						FigureEditPart figEditPart = (FigureEditPart) obj;
						if (figEditPart.getModel().getParent() == getModel())
							figEditPart.updateRulers();
					}
				}
		}
	}

	@Override
	protected MCrosstab getCrosstab() {
		return getModel().getCrosstab();
	}

}
