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
package com.jaspersoft.studio.components.list.part;

import java.util.Collection;

import net.sf.jasperreports.components.list.DesignListContents;
import net.sf.jasperreports.components.list.StandardListComponent;
import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.components.list.model.MList;
import com.jaspersoft.studio.editor.action.create.CreateElementAction;
import com.jaspersoft.studio.editor.gef.commands.SetPageConstraintCommand;
import com.jaspersoft.studio.editor.gef.parts.EditableFigureEditPart;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.ElementEditPolicy;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.FigureSelectionEditPolicy;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.PageLayoutEditPolicy;
import com.jaspersoft.studio.editor.outline.OutlineTreeEditPartFactory;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.MPage;
import com.jaspersoft.studio.model.command.CreateElementCommand;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.utils.ModelUtils;

public class ListEditPart extends EditableFigureEditPart {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ElementEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new PageLayoutEditPolicy() {

			@Override
			protected Command getCreateCommand(CreateRequest request) {
				Rectangle constraint = (Rectangle) getConstraintFor(request);

				if (request.getNewObject() instanceof CreateElementAction) {
					CreateElementAction action = (CreateElementAction) request
							.getNewObject();
					action.dropInto(getHost().getModel(), constraint.getCopy(),
							-1);
					action.run();
					return action.getCommand();
				} else if (request.getNewObject() instanceof MGraphicElement) {
					return OutlineTreeEditPartFactory.getCreateCommand(
							(ANode) getHost().getModel(),
							(ANode) request.getNewObject(),
							constraint.getCopy(), -1);
				} else if (request.getNewObject() instanceof Collection<?>) {
					CompoundCommand cmd = new CompoundCommand();
					Collection<?> c = (Collection<?>) request.getNewObject();
					for (Object obj : c) {
						if (obj instanceof ANode)
							cmd.add(OutlineTreeEditPartFactory
									.getCreateCommand((ANode) getHost()
											.getModel(), (ANode) obj,
											constraint.getCopy(), -1));
					}
					return cmd;
				}
				return null;
			}

			@Override
			protected Command createAddCommand(EditPart child, Object constraint) {
				Rectangle rect = (Rectangle) constraint;
				if (child.getModel() instanceof MGraphicElement) {
					MGraphicElement cmodel = (MGraphicElement) child.getModel();
					if (cmodel.getParent() instanceof MList) {
						MList cparent = (MList) cmodel.getParent();
						if (cparent == getModel()) {
							Rectangle r = cmodel.getBounds();
							SetPageConstraintCommand cmd = new SetPageConstraintCommand();
							JRDesignElement jde = (JRDesignElement) cmodel
									.getValue();
							int x = r.x + rect.x - jde.getX() + 2;
							int y = r.y + rect.y - jde.getY() + 2;
							rect.setLocation(x, y);
							cmd.setContext((ANode) getHost().getModel(),
									(ANode) child.getModel(), rect);

							return cmd;
						}
					} else {
						CompoundCommand c = new CompoundCommand();

						c.add(OutlineTreeEditPartFactory.getOrphanCommand(
								cmodel.getParent(), cmodel));
						c.add(new CreateElementCommand((MList) getModel(),
								cmodel, rect, -1));
						return c;
					}
				} else {
					return super.createChangeConstraintCommand(child,
							constraint);
				}
				return null;
			}

		});
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE,
				new FigureSelectionEditPolicy());
	}

	@Override
	public void performRequest(Request req) {
		if (RequestConstants.REQ_OPEN.equals(req.getType())) {
			MList model = (MList) getModel();
			if (model.getParent() instanceof MPage) {
				StandardListComponent jrList = (StandardListComponent) model
						.getValue().getComponent();
				Dimension d = ModelUtils.getContainerSize(jrList.getContents()
						.getChildren(), new Dimension(0, 0));
				if (d.height > 0 && d.width > 0) {
					CompoundCommand c = new CompoundCommand(
							"Resize to container");

					SetValueCommand cmd = new SetValueCommand();
					cmd.setTarget((IPropertySource) model);
					cmd.setPropertyId(JRDesignElement.PROPERTY_HEIGHT);
					cmd.setPropertyValue(d.height);
					c.add(cmd);

					cmd = new SetValueCommand();
					cmd.setTarget((IPropertySource) model);
					cmd.setPropertyId(MList.PREFIX
							+ DesignListContents.PROPERTY_HEIGHT);
					cmd.setPropertyValue(d.height);
					c.add(cmd);

					cmd = new SetValueCommand();
					cmd.setTarget((IPropertySource) model);
					cmd.setPropertyId(JRDesignElement.PROPERTY_WIDTH);
					cmd.setPropertyValue(d.width);
					c.add(cmd);

					cmd = new SetValueCommand();
					cmd.setTarget((IPropertySource) model);
					cmd.setPropertyId(MList.PREFIX
							+ DesignListContents.PROPERTY_WIDTH);
					cmd.setPropertyValue(d.width);
					c.add(cmd);

					getViewer().getEditDomain().getCommandStack().execute(c);
				}
			}
		}
		super.performRequest(req);
	}
}
