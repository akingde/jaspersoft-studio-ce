/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.ui.gef.parts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.wb.swt.SWTResourceManager;

import com.jaspersoft.studio.data.sql.model.MSQLRoot;
import com.jaspersoft.studio.data.sql.model.query.MUnion;
import com.jaspersoft.studio.data.sql.model.query.from.MFrom;
import com.jaspersoft.studio.data.sql.ui.gef.policy.FromContainerEditPolicy;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.NoSelectionEditPolicy;
import com.jaspersoft.studio.model.INode;

public class QueryEditPart extends AbstractGraphicalEditPart {

	@Override
	protected IFigure createFigure() {
		Figure fig = new FreeformLayer();
		fig.setLayoutManager(new XYLayout());
		fig.setOpaque(true);
		fig.setBackgroundColor(SWTResourceManager.getColor(255, 255, 255));

		return fig;
	}

	@Override
	public MSQLRoot getModel() {
		return (MSQLRoot) super.getModel();
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.CONTAINER_ROLE,
				new FromContainerEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new XYLayoutEditPolicy() {

			@Override
			protected Command getCreateCommand(CreateRequest request) {
				return null;
			}

			@Override
			protected Command createChangeConstraintCommand(
					ChangeBoundsRequest request, EditPart child,
					Object constraint) {
				Rectangle b = getHostFigure().getBounds();
				Rectangle r = (Rectangle) constraint;
				if (b.x + r.x + FromEditPart.INSETS.left < FromEditPart.INSETS.left)
					return null;

				if (b.y + r.y + FromEditPart.INSETS.top < FromEditPart.INSETS.top)
					return null;

				if (child instanceof FromEditPart) {
					FromEditPart fep = (FromEditPart) child;
					Point delta = request.getMoveDelta();

					CompoundCommand c = new CompoundCommand("Moving Tables");
					List<EditPart> children = child.getChildren();
					FromEditPart.moveTables(c, children, fep, delta);
					return c;
				}
				return null;
			}

			@Override
			protected EditPolicy createChildEditPolicy(EditPart child) {
				if (child instanceof FromEditPart)
					return new NoSelectionEditPolicy() {
						@Override
						protected IFigure createDragSourceFeedbackFigure() {
							// Use a ghost rectangle for feedback
							Label r = new Label();
							// FigureUtilities.makeGhostShape(r);
							// r.setLineStyle(Graphics.LINE_SOLID);
							r.setForegroundColor(ColorConstants.black);
							r.setOpaque(true);
							r.setBorder(new LineBorder(ColorConstants.red));
							r.setBackgroundColor(ColorConstants.white);
							r.setBounds(getInitialFeedbackBounds());

							r.validate();
							addFeedback(r);
							return r;
						}

						@Override
						protected void showChangeBoundsFeedback(
								ChangeBoundsRequest request) {
							super.showChangeBoundsFeedback(request);
							Label r = (Label) getDragSourceFeedbackFigure();
							Rectangle b = r.getBounds();
							r.setText(b.x + "," + b.y);
						}
					};
				return super.createChildEditPolicy(child);
			}
		});
	}

	@Override
	protected List<?> getModelChildren() {
		MSQLRoot root = getModel();
		List<MFrom> list = new ArrayList<MFrom>();
		for (INode n : root.getChildren()) {
			if (n instanceof MFrom && !n.getChildren().isEmpty())
				list.add((MFrom) n);
			else if (n instanceof MUnion)
				for (INode sn : n.getChildren())
					if (sn instanceof MFrom && !n.getChildren().isEmpty())
						;// list.add((MFrom) sn);
		}
		root.rebuildJoins();
		return list;
	}

}
