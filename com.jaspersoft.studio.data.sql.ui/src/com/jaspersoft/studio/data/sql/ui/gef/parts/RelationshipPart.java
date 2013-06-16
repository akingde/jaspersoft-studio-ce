package com.jaspersoft.studio.data.sql.ui.gef.parts;

import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.action.table.EditTableJoin;
import com.jaspersoft.studio.data.sql.model.query.from.TableJoin;
import com.jaspersoft.studio.data.sql.ui.gef.SQLQueryDiagram;

public class RelationshipPart extends AbstractConnectionEditPart {

	/**
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, new ConnectionEndpointEditPolicy());
		// installEditPolicy(EditPolicy.COMPONENT_ROLE, new
		// RelationshipEditPolicy());
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

}