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
package com.jaspersoft.studio.editor.gef.parts;

import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;

import com.jaspersoft.studio.editor.gef.commands.SetPageConstraintCommand;
import com.jaspersoft.studio.editor.gef.figures.ReportPageFigure;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.PageLayoutEditPolicy;
import com.jaspersoft.studio.editor.outline.OutlineTreeEditPartFactory;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IContainer;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.MPage;
import com.jaspersoft.studio.model.command.CreateElementCommand;
import com.jaspersoft.studio.model.frame.MFrame;

public class FrameFigureEditPart extends FigureEditPart implements IContainer {
	@Override
	public MFrame getModel() {
		return (MFrame) super.getModel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new PageLayoutEditPolicy() {

			@Override
			protected Command getCreateCommand(ANode parent, Object obj, Rectangle constraint) {
				if (parent instanceof MPage)
					parent = getModel();
				Rectangle b = getModel().getBounds();
				int x = constraint.x - b.x - ReportPageFigure.PAGE_BORDER.left;
				int y = constraint.y - b.y - ReportPageFigure.PAGE_BORDER.top;
				constraint = new Rectangle(x, y, constraint.width, constraint.height);
				return super.getCreateCommand(parent, obj, constraint);
			}

			@Override
			protected Command createAddCommand(EditPart child, Object constraint) {
				Rectangle rect = (Rectangle) constraint;
				if (child.getModel() instanceof MGraphicElement) {
					MGraphicElement cmodel = (MGraphicElement) child.getModel();
					if (cmodel.getParent() instanceof MFrame) {
						MFrame cparent = (MFrame) cmodel.getParent();
						if (cparent == getModel()) {
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
					} else {
						CompoundCommand c = new CompoundCommand();

						c.add(OutlineTreeEditPartFactory.getOrphanCommand(cmodel.getParent(), cmodel));
						c.add(new CreateElementCommand((MFrame) getModel(), cmodel, rect, -1));
						return c;
					}
				} else {
					return super.createChangeConstraintCommand(child, constraint);
				}
				return null;
			}

		});
	}
}
