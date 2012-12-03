/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.components.list.part;

import java.util.Collection;

import net.sf.jasperreports.components.list.DesignListContents;
import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.CreateRequest;

import com.jaspersoft.studio.components.list.ListComponentFactory;
import com.jaspersoft.studio.components.list.figure.ListFigure;
import com.jaspersoft.studio.components.list.model.MList;
import com.jaspersoft.studio.editor.action.create.CreateElementAction;
import com.jaspersoft.studio.editor.gef.commands.SetPageConstraintCommand;
import com.jaspersoft.studio.editor.gef.parts.EditableFigureEditPart;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.ElementEditPolicy;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.FigurePageLayoutEditPolicy;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.FigureSelectionEditPolicy;
import com.jaspersoft.studio.editor.outline.OutlineTreeEditPartFactory;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.command.CreateElementCommand;

/**
 * 
 * @author Chicu Veaceslav & Orlandin Marco
 *
 */
public class ListEditPart extends EditableFigureEditPart {

	/**
	 * Create a ListFigure and initialize it. The listfigure is the type of 
	 * the figure of this edit part
	 */
	protected IFigure createFigure() {
		ListFigure rect = new ListFigure();
		setupListFigure(rect);
		setPrefsBorder(rect);
		setupFigure(rect);
		figure = rect;
		return rect;
	}
	
	/**
	 * Set in the list figure the size of the cell
	 * @param rect a list figure
	 */
	protected void setupListFigure(IFigure rect) {
		MList model = (MList)getModel();
		int listHeight = (Integer)(model.getPropertyValue(MList.PREFIX + DesignListContents.PROPERTY_HEIGHT));
		int listWidth = (Integer)(model.getPropertyValue(MList.PREFIX + DesignListContents.PROPERTY_WIDTH));
		ListFigure lfig = (ListFigure) rect;
		lfig.setCellHeight(listHeight);
		lfig.setCellWidth(listWidth);
	}
	
	/**
	 * Reset the size of the cell in the list figure and refresh the element
	 */
	@Override
	public void refreshVisuals() {
		setupListFigure(getFigure());
		super.refreshVisuals();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ElementEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE,
				new FigurePageLayoutEditPolicy() {

					@Override
					protected Command getCreateCommand(CreateRequest request) {
						Rectangle constraint = (Rectangle) getConstraintFor(request);

						if (request.getNewObject() instanceof CreateElementAction) {
							CreateElementAction action = (CreateElementAction) request
									.getNewObject();
							action.dropInto(getHost().getModel(),
									constraint.getCopy(), -1);
							action.run();
							return action.getCommand();
						} else if (request.getNewObject() instanceof MGraphicElement) {
							return OutlineTreeEditPartFactory.getCreateCommand(
									(ANode) getHost().getModel(),
									(ANode) request.getNewObject(),
									constraint.getCopy(), -1);
						} else if (request.getNewObject() instanceof Collection<?>) {
							CompoundCommand cmd = new CompoundCommand();
							Collection<?> c = (Collection<?>) request
									.getNewObject();
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
					protected Command createAddCommand(EditPart child,
							Object constraint) {
						Rectangle rect = (Rectangle) constraint;
						if (child.getModel() instanceof MGraphicElement) {
							MGraphicElement cmodel = (MGraphicElement) child
									.getModel();
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
									cmd.setContext(
											(ANode) getHost().getModel(),
											(ANode) child.getModel(), rect);

									return cmd;
								}
							} else {
								CompoundCommand c = new CompoundCommand();

								c.add(OutlineTreeEditPartFactory
										.getOrphanCommand(cmodel.getParent(),
												cmodel));
								c.add(new CreateElementCommand(
										(MList) getModel(), cmodel, rect, -1));
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
			Command c = ListComponentFactory.INST().getStretchToContent(model);
			if (c != null)
				getViewer().getEditDomain().getCommandStack().execute(c);
		}
		super.performRequest(req);
	}
}
