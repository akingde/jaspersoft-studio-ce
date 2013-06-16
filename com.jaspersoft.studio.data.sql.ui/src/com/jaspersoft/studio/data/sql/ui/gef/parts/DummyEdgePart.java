package com.jaspersoft.studio.data.sql.ui.gef.parts;

import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;

public class DummyEdgePart {
	/**
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
	}

	/**
	 * @see org.eclipse.gef.editparts.AbstractConnectionEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		PolylineConnection conn = new PolylineConnection();
		conn.setConnectionRouter(new BendpointConnectionRouter());
		conn.setVisible(false);
		return conn;
	}

}
