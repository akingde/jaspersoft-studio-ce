package com.jaspersoft.studio.data.sql.ui.gef.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.model.query.from.TableJoin;
import com.jaspersoft.studio.data.sql.ui.gef.command.MoveJoinCommand;
import com.jaspersoft.studio.data.sql.ui.gef.parts.RelationshipPart;
import com.jaspersoft.studio.data.sql.ui.gef.parts.TableEditPart;

public class TableNodeEditPolicy extends GraphicalNodeEditPolicy {

	/**
	 * @see GraphicalNodeEditPolicy#getConnectionCreateCommand(CreateConnectionRequest)
	 */
	protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
		return null;
	}

	/**
	 * @see GraphicalNodeEditPolicy#getConnectionCompleteCommand(CreateConnectionRequest)
	 */
	protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
		return null;
	}

	/**
	 * @see GraphicalNodeEditPolicy#getReconnectSourceCommand(ReconnectRequest)
	 */
	protected Command getReconnectSourceCommand(ReconnectRequest request) {
		TableJoin tj = ((RelationshipPart) request.getConnectionEditPart()).getModel();
		TableEditPart tep = (TableEditPart) getHost();
		SQLQueryDesigner designer = ((TableEditPart) getHost()).getDesigner();

		System.out.println(tep.getModel().toSQLString());
		return new MoveJoinCommand(tep.getModel(), tj, designer);
	}

	/**
	 * @see GraphicalNodeEditPolicy#getReconnectTargetCommand(ReconnectRequest)
	 */
	protected Command getReconnectTargetCommand(ReconnectRequest request) {
		TableJoin tj = ((RelationshipPart) request.getConnectionEditPart()).getModel();
		TableEditPart tep = (TableEditPart) getHost();
		SQLQueryDesigner designer = ((TableEditPart) getHost()).getDesigner();
		return new MoveJoinCommand(tj, tep.getModel(), designer);
	}

}