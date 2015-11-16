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
package com.jaspersoft.studio.data.sql.ui.gef.command;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTableJoin;
import com.jaspersoft.studio.data.sql.model.query.from.TableJoin;

public class MoveJoinCommand extends ACommand {
	private MFromTable destTbl, srcTbl;
	private TableJoin tjoin;

	public MoveJoinCommand(MFromTable destTbl, TableJoin tjoin,
			SQLQueryDesigner designer) {
		this.tjoin = tjoin;
		this.destTbl = destTbl;
		srcTbl = tjoin.getJoinTable();
	}

	public MoveJoinCommand(TableJoin tjoin, MFromTable destTbl,
			SQLQueryDesigner designer) {
		this.tjoin = tjoin;
		this.destTbl = tjoin.getFromTable();
		this.srcTbl = destTbl;
	}

	@Override
	public void execute() {
		super.execute();
		Command c = new DeleteTableJoinCommand(
				new Object[] { tjoin.getJoinTable() });
		undoCmd.add(c);
		c.execute();
		if (srcTbl instanceof MFromTableJoin)
			srcTbl = ((DeleteTableJoinCommand) c).getResultFromTable();

		MFromTable fromTbl = destTbl;
		if (destTbl instanceof MFromTableJoin)
			fromTbl = JoinCommand.getParentFromTable((MFromTableJoin) destTbl);
		c = new JoinTableCommand(null, srcTbl, null, destTbl, fromTbl);
		undoCmd.add(c);
		c.execute();
	}

	@Override
	protected void firePropertyChange() {
	}
}
