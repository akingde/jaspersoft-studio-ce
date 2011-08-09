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
package com.jaspersoft.studio.list.part;

import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import com.jaspersoft.studio.editor.action.create.CreateElementAction;
import com.jaspersoft.studio.editor.gef.commands.SetPageConstraintCommand;
import com.jaspersoft.studio.editor.gef.parts.EditableFigureEditPart;
import com.jaspersoft.studio.editor.gef.parts.ReportPageEditPart;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.ElementEditPolicy;
import com.jaspersoft.studio.editor.outline.OutlineTreeEditPartFactory;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MGraphicElement;

public class ListEditPart extends EditableFigureEditPart {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	@Override
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
	}

	@Override
	public boolean isSelectable() {
		return getParent() instanceof ReportPageEditPart;
	}

}
