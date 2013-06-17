/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.data.sql.ui.gef.parts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.ui.gef.figures.SqlTableFigure;
import com.jaspersoft.studio.data.sql.ui.gef.layout.GraphLayoutManager;
import com.jaspersoft.studio.data.sql.ui.gef.policy.FromContainerEditPolicy;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.util.ModelVisitor;

public class FromEditPart extends AbstractGraphicalEditPart {

	@Override
	protected IFigure createFigure() {
		// FreeformLayeredPane fig = new FreeformLayeredPane();
		// FreeformLayer fig = new FreeformLayer();
		RectangleFigure fig = new RectangleFigure();
		fig.setSize(1000, 1000);
		fig.setLayoutManager(new GraphLayoutManager(this));
		// fig.setLayoutManager(new FreeformLayout());
		fig.setOpaque(true);
		return fig;
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.CONTAINER_ROLE, new FromContainerEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, null);
	}

	@Override
	protected List getModelChildren() {
		final List<ANode> list = new ArrayList<ANode>();
		new ModelVisitor<ANode>((INode) getModel()) {

			@Override
			public boolean visit(INode n) {
				if (n instanceof MFromTable)
					list.add((ANode) n);
				return true;
			}
		};
		return list;
	}

	protected void refreshVisuals() {
		getParent();
	}

	public boolean setTableModelBounds() {
		List<TableEditPart> tableParts = getChildren();
		for (TableEditPart tablePart : tableParts) {
			SqlTableFigure tableFigure = tablePart.getFigure();
			tablePart.getModel().setBounds(tableFigure.getBounds().getCopy());
		}
		return true;

	}
}
