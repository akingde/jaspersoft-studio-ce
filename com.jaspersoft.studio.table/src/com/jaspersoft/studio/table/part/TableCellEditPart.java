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
/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of iReport.
 * 
 * iReport is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * iReport is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with iReport. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.table.part;

import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;

import com.jaspersoft.studio.editor.action.create.CreateElementAction;
import com.jaspersoft.studio.editor.gef.commands.SetPageConstraintCommand;
import com.jaspersoft.studio.editor.gef.figures.ReportPageFigure;
import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.editor.gef.parts.IContainerPart;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.ElementEditPolicy;
import com.jaspersoft.studio.editor.outline.OutlineTreeEditPartFactory;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IGraphicElement;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.table.figure.CellFigure;
import com.jaspersoft.studio.table.model.column.MCell;
import com.jaspersoft.studio.table.part.editpolicy.TableCellMoveEditPolicy;
import com.jaspersoft.studio.table.part.editpolicy.TableCellResizableEditPolicy;
/*
 * BandEditPart creates the figure for the band. The figure is actually just the bottom border of the band. This allows
 * to drag this border to resize the band. The PageEditPart sets a specific contraint for the BandEditPart elements in
 * order to make them move only vertically. The BandMoveEditPolicy is responsable for the feedback when the band is
 * dragged.
 * 
 * @author Chicu Veaceslav, Giulio Toffoli
 * 
 */
public class TableCellEditPart extends FigureEditPart implements IContainerPart {
	@Override
	public Object getAdapter(Class key) {
		return getParent().getAdapter(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ElementEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new XYLayoutEditPolicy() {
			@Override
			protected Dimension getMinimumSizeFor(GraphicalEditPart child) {
				return new Dimension(1, 1);
			}
			@Override
			protected Command getOrphanChildrenCommand(Request request) {
				// TODO Auto-generated method stub
				return super.getOrphanChildrenCommand(request);
			}

			@Override
			protected Command getCreateCommand(CreateRequest request) {
				Rectangle constraint = (Rectangle) getConstraintFor(request);

				if (request.getNewObject() instanceof CreateElementAction) {
					CreateElementAction action = (CreateElementAction) request.getNewObject();
					action.dropInto(getHost().getModel(), constraint.getCopy(), -1);
					action.run();
					return action.getCommand();
				} else if (request.getNewObject() instanceof MGraphicElement) {
					return OutlineTreeEditPartFactory.getCreateCommand((ANode) getHost().getModel(),
							(MGraphicElement) request.getNewObject(), constraint.getCopy(), -1);
				}
				return null;
			}

			@Override
			protected Command createAddCommand(EditPart child, Object constraint) {
				SetPageConstraintCommand cmd = new SetPageConstraintCommand();
				MGraphicElement model = (MGraphicElement) child.getModel();
				Rectangle r = model.getBounds();
				Rectangle rect = (Rectangle) constraint;

				JRDesignElement jde = (JRDesignElement) model.getValue();
				rect.setLocation(r.x + rect.x - jde.getX() + 2, r.y + rect.y - jde.getY() + 2);
				cmd.setContext((ANode) getHost().getModel(), (ANode) child.getModel(), rect);

				return cmd;
			}

			@Override
			protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
				// TODO Auto-generated method stub
				return null;
			}

		});
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new TableCellMoveEditPolicy());
	}

	@Override
	public Command getCommand(Request request) {
		if (request.getType().equals(REQ_ORPHAN))
			request.getType();
		if (request.getType().equals(REQ_ORPHAN_CHILDREN))
			request.getType();
		return super.getCommand(request);
	}

	@Override
	public boolean isSelectable() {
		return true;
	}

	protected void setupFigure(IFigure rect) {
		MCell model = (MCell) getModel();
		if (model instanceof IGraphicElement && model.getValue() != null) {
			Rectangle bounds = ((IGraphicElement) model).getBounds();
			int x = bounds.x + ReportPageFigure.PAGE_BORDER.left;
			int y = bounds.y + ReportPageFigure.PAGE_BORDER.top;

			CellFigure f = (CellFigure) rect;
			f.setLocation(new Point(x, y));
			f.setJRElement(model.getCell(), (StandardBaseColumn) model.getValue(), getDrawVisitor(), fileResolver);
		}
	}

	public EditPolicy getEditPolicy() {
		return new TableCellResizableEditPolicy();
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
