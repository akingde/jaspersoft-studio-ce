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

import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.action.table.EditTableJoin;
import com.jaspersoft.studio.data.sql.model.query.IQueryString;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTableJoin;
import com.jaspersoft.studio.data.sql.model.query.from.TableJoin;
import com.jaspersoft.studio.data.sql.ui.gef.SQLQueryDiagram;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.util.ModelVisitor;

public class RelationshipPart extends AbstractConnectionEditPart {

	/**
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, new ConnectionEndpointEditPolicy());
	}

	@Override
	public TableJoin getModel() {
		return (TableJoin) super.getModel();
	};

	@Override
	public void performRequest(Request req) {
		if (RequestConstants.REQ_OPEN.equals(req.getType())) {
			SQLQueryDesigner designer = (SQLQueryDesigner) getViewer().getProperty(SQLQueryDiagram.SQLQUERYDIAGRAM);
			EditTableJoin ct = designer.getOutline().getAfactory().getAction(EditTableJoin.class);
			if (ct.calculateEnabled(new Object[] { getModel().getJoinTable() }))
				ct.run();
		}
	}

	/**
	 * @see org.eclipse.gef.editparts.AbstractConnectionEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		PolylineConnection conn = (PolylineConnection) super.createFigure();
		conn.setConnectionRouter(new BendpointConnectionRouter());
		conn.setTargetDecoration(new PolygonDecoration());

		return conn;
	}

	@Override
	protected void refreshVisuals() {
		super.refreshVisuals();
		MFromTableJoin joinTable = getModel().getJoinTable();
		final StringBuffer tt = new StringBuffer();
		tt.append(getModel().getFromTable().getToolTip()).append(" ").append(joinTable.getToolTip());
		new ModelVisitor<Object>(joinTable) {

			@Override
			public boolean visit(INode n) {
				if (n instanceof IQueryString)
					tt.append(((IQueryString) n).toSQLString());
				return true;
			}
		};
		getFigure().setToolTip(new Label(tt.toString()));
	}
}
