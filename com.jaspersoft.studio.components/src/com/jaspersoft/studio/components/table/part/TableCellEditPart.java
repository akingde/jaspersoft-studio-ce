/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.components.table.part;

import java.util.List;

import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.gef.requests.ChangeBoundsRequest;

import com.jaspersoft.studio.components.table.figure.CellFigure;
import com.jaspersoft.studio.components.table.figure.EmptyCellFigure;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.model.cell.command.CreateElementCommand;
import com.jaspersoft.studio.components.table.model.cell.command.OrphanElementCommand;
import com.jaspersoft.studio.components.table.model.column.MCell;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.components.table.part.editpolicy.TableCellMoveEditPolicy;
import com.jaspersoft.studio.components.table.part.editpolicy.TableCellResizableEditPolicy;
import com.jaspersoft.studio.editor.gef.commands.SetPageConstraintCommand;
import com.jaspersoft.studio.editor.gef.figures.ReportPageFigure;
import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.editor.gef.parts.IContainerPart;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.ElementEditPolicy;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.PageLayoutEditPolicy;
import com.jaspersoft.studio.editor.gef.rulers.ReportRuler;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IContainer;
import com.jaspersoft.studio.model.IGraphicElement;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.MPage;

/*
 * BandEditPart creates the figure for the band. The figure is actually just the bottom border of the band. This allows
 * to drag this border to resize the band. The PageEditPart sets a specific contraint for the BandEditPart elements in
 * order to make them move only vertically. The BandMoveEditPolicy is responsable for the feedback when the band is
 * dragged.
 * 
 * @author Chicu Veaceslav, Giulio Toffoli
 * 
 */
public class TableCellEditPart extends FigureEditPart implements
		IContainerPart, IContainer {
	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class key) {
		return getParent().getAdapter(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ElementEditPolicy());
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
				if (request.getType().equals(RequestConstants.REQ_ADD)
						&& request instanceof ChangeBoundsRequest) {
					ChangeBoundsRequest cbr = (ChangeBoundsRequest) request;
					List<EditPart> lst = cbr.getEditParts();
					for (EditPart ep : lst) {
						if (((ANode) ep.getModel()).getParent() == getModel())
							return null;
						if (ep instanceof TableCellEditPart)
							return null;
					}
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

					targetFeedback.setBounds(rect.shrink(4, 4));
					targetFeedback.setBorder(new LineBorder(
							ColorConstants.lightBlue, 3));
					addFeedback(targetFeedback);
				}
				return targetFeedback;
			}

			protected void showLayoutTargetFeedback(Request request) {
				super.showLayoutTargetFeedback(request);
				getLayoutTargetFeedback(request);
			}

			protected Command getCreateCommand(ANode parent, Object obj,
					Rectangle constraint) {
				if (parent instanceof MPage)
					parent = getModel();
				Rectangle b = getModel().getBounds();
				int x = constraint.x - b.x - ReportPageFigure.PAGE_BORDER.left;
				int y = constraint.y - b.y - ReportPageFigure.PAGE_BORDER.top;
				constraint = new Rectangle(x, y, constraint.width,
						constraint.height);

				return super.getCreateCommand(parent, obj, constraint);
			}

			@Override
			protected Command createAddCommand(EditPart child, Object constraint) {
				Rectangle rect = (Rectangle) constraint;
				if (child.getModel() instanceof MGraphicElement) {
					MGraphicElement cmodel = (MGraphicElement) child.getModel();
					if (cmodel.getParent() instanceof MCell) {
						MCell cparent = (MCell) cmodel.getParent();
						if (cparent == getModel()) {
							SetPageConstraintCommand cmd = new SetPageConstraintCommand();
							MGraphicElement model = (MGraphicElement) child
									.getModel();
							Rectangle r = model.getBounds();

							JRDesignElement jde = (JRDesignElement) model
									.getValue();
							int x = r.x + rect.x - jde.getX() + 1;
							int y = r.y + rect.y - jde.getY() + 1;
							rect.setLocation(x, y);
							cmd.setContext(getModel(),
									(ANode) child.getModel(), rect);

							return cmd;
						} else {
							CompoundCommand c = new CompoundCommand();

							c.add(new OrphanElementCommand(cparent, cmodel));
							c.add(new CreateElementCommand((MCell) getModel(),
									cmodel, rect, -1));
							return c;
						}
					}
				}
				return null;
			}

		});
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE,
				new TableCellResizableEditPolicy() {
					@Override
					protected void showSelection() {
						super.showSelection();
						updateRulers();
					}

				});
	}

	public EditPolicy getEditPolicy() {
		return new TableCellMoveEditPolicy();
	}

	@Override
	public Command getCommand(Request request) {
		if (request.getType().equals(REQ_MOVE)
				&& ((ChangeBoundsRequest) request).getMoveDelta().x == 0)
			return null;
		return super.getCommand(request);
	}

	@Override
	public boolean isSelectable() {
		return true;
	}

	@Override
	public MColumn getModel() {
		return (MColumn) super.getModel();
	}

	@Override
	protected void setupFigure(IFigure rect) {
		updateContainerSize();
		ANode model = getModel();
		rect.setToolTip(new Label(model.getToolTip()));
		if (model.getValue() != null) {
			Rectangle bounds = ((IGraphicElement) model).getBounds();
			int x = bounds.x + ReportPageFigure.PAGE_BORDER.left;
			int y = bounds.y + ReportPageFigure.PAGE_BORDER.top;
			rect.setLocation(new Point(x, y));
			if (model instanceof MCell) {
				CellFigure f = (CellFigure) rect;
				// rect.setBounds(rect.getBounds().resize(-5, -3));

				f.setJRElement(((MCell) model).getCell(),
						(StandardBaseColumn) model.getValue(), getDrawVisitor());
			} else {
				rect.setSize(bounds.width, bounds.height);
				((EmptyCellFigure) rect).setJRElement(
						(StandardBaseColumn) model.getValue(),
						getDrawVisitor(), bounds.height);
			}
			updateRulers();
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

	public Object getConstraintFor(ChangeBoundsRequest request,
			GraphicalEditPart child) {
		return new Rectangle(0, 0, request.getSizeDelta().width,
				request.getSizeDelta().height);
	}

	public static final int X_OFFSET = 10;
	public static final int Y_OFFSET = 10;

	@Override
	public void updateRulers() {
		Dimension d = getContaierSize();
		EditPartViewer v = getViewer();
		if (d != null) {
			v.setProperty(ReportRuler.PROPERTY_HEND, d.width);
			v.setProperty(ReportRuler.PROPERTY_VEND, d.height);
		}
		v.setProperty(ReportRuler.PROPERTY_HOFFSET, X_OFFSET);
		v.setProperty(ReportRuler.PROPERTY_VOFFSET, Y_OFFSET);

		v.setProperty(SnapToGrid.PROPERTY_GRID_ORIGIN, new Point(X_OFFSET,
				Y_OFFSET));
	}

	private Dimension containerSize;

	public Dimension getContaierSize() {
		return containerSize;
	}

	private void updateContainerSize() {
		MTable table = getModel().getMTable();
		if (table != null) {
			Dimension d = table.getTableManager().getSize();
			d.height = Math.max(d.height, (Integer) table
					.getPropertyValue(JRDesignElement.PROPERTY_HEIGHT));
			d.width = Math.max(d.width, (Integer) table
					.getPropertyValue(JRDesignElement.PROPERTY_WIDTH));
			containerSize = d;
		} else
			containerSize = null;
	}

}
