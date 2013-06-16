package com.jaspersoft.studio.data.sql.ui.gef.policy;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.FlowLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.model.metadata.MColumn;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.ui.gef.command.JoinCommand;
import com.jaspersoft.studio.data.sql.ui.gef.parts.ColumnEditPart;
import com.jaspersoft.studio.data.sql.ui.gef.parts.TableEditPart;

public class TableLayoutEditPolicy extends FlowLayoutEditPolicy {

	/**
	 * Creates command to transfer child column to after column (in another table)
	 */
	protected Command createAddCommand(EditPart child, EditPart after) {
		if (!(child instanceof ColumnEditPart))
			return null;
		if (!(after instanceof ColumnEditPart))
			return null;

		MColumn toMove = ((ColumnEditPart) child).getModel();
		MFromTable srcTbl = ((ColumnEditPart) child).getParent().getModel();
		MColumn afterModel = ((ColumnEditPart) after).getModel();
		MFromTable destTbl = ((ColumnEditPart) after).getParent().getModel();

		SQLQueryDesigner designer = ((TableEditPart) getHost()).getDesigner();

		return new JoinCommand(toMove, srcTbl, afterModel, destTbl, designer);
	}

	/**
	 * Creates command to transfer child column to after specified column (within
	 * table)
	 */
	protected Command createMoveChildCommand(EditPart child, EditPart after) {
		return null;
	}

	/**
	 * @param request
	 * @return
	 */
	protected Command getCreateCommand(CreateRequest request) {
		return null;
	}

	/**
	 * @param request
	 * @return
	 */
	protected Command getDeleteDependantCommand(Request request) {
		return null;
	}

}