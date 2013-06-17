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
package com.jaspersoft.studio.data.sql.ui.gef.policy;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.FlowLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.model.metadata.MSQLColumn;
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

		MSQLColumn toMove = ((ColumnEditPart) child).getModel();
		MFromTable srcTbl = ((ColumnEditPart) child).getParent().getModel();
		MSQLColumn afterModel = ((ColumnEditPart) after).getModel();
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
