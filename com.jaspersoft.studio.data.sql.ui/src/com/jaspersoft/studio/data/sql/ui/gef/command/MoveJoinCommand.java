/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.ui.gef.command;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.model.query.AMKeyword;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTableJoin;
import com.jaspersoft.studio.data.sql.model.query.from.TableJoin;

public class MoveJoinCommand extends ACommand {
	private MFromTable destTbl, srcTbl;
	private TableJoin tjoin;

	public MoveJoinCommand(MFromTable destTbl, TableJoin tjoin, SQLQueryDesigner designer) {
		this.tjoin = tjoin;
		this.destTbl = destTbl;
		srcTbl = tjoin.getJoinTable();
	}

	public MoveJoinCommand(TableJoin tjoin, MFromTable destTbl, SQLQueryDesigner designer) {
		this.tjoin = tjoin;
		this.destTbl = tjoin.getFromTable();
		this.srcTbl = destTbl;
	}

	@Override
	public void execute() {
		super.execute();
		Command c = new DeleteTableJoinCommand(new Object[] { tjoin.getJoinTable() });
		undoCmd.add(c);
		c.execute();
		if (srcTbl instanceof MFromTableJoin)
			srcTbl = ((DeleteTableJoinCommand) c).getResultFromTable();

		MFromTable fromTbl = destTbl;
		if (destTbl instanceof MFromTableJoin)
			fromTbl = JoinCommand.getParentFromTable((MFromTableJoin) destTbl);

		String join = AMKeyword.INNER_JOIN;
		if (srcTbl instanceof MFromTableJoin)
			join = ((MFromTableJoin) srcTbl).getJoin();
		else if (destTbl instanceof MFromTableJoin)
			join = ((MFromTableJoin) destTbl).getJoin();
		c = new JoinTableCommand(null, srcTbl, null, destTbl, fromTbl, join);
		undoCmd.add(c);
		c.execute();
	}

	@Override
	protected void firePropertyChange() {
	}
}
