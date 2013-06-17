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
package com.jaspersoft.studio.data.sql.ui.gef.command;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.action.ActionFactory;
import com.jaspersoft.studio.data.sql.action.table.DeleteTableJoin;
import com.jaspersoft.studio.data.sql.action.table.JoinTable;
import com.jaspersoft.studio.data.sql.model.metadata.MColumn;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTableJoin;

public class JoinCommand extends Command {
	private SQLQueryDesigner designer;
	private MColumn src, dest;
	private MFromTable srcTbl, destTbl;

	public JoinCommand(MColumn src, MFromTable srcTbl, MColumn dest, MFromTable destTbl, SQLQueryDesigner designer) {
		this.designer = designer;
		this.src = src;
		this.srcTbl = srcTbl;
		this.dest = dest;
		this.destTbl = destTbl;
	}

	public JoinCommand(MFromTable srcTbl, MFromTable destTbl, SQLQueryDesigner designer) {
		this.designer = designer;
		this.srcTbl = srcTbl;
		this.destTbl = destTbl;
	}

	@Override
	public void execute() {
		ActionFactory afactory = designer.getOutline().getAfactory();
		if (srcTbl instanceof MFromTableJoin) {
			DeleteTableJoin dtj = afactory.getAction(DeleteTableJoin.class);
			dtj.calculateEnabled(new Object[] { srcTbl });
			srcTbl = dtj.runSilent();
		}
		if (destTbl instanceof MFromTableJoin) {
			DeleteTableJoin dtj = afactory.getAction(DeleteTableJoin.class);
			dtj.calculateEnabled(new Object[] { destTbl });
			destTbl = dtj.runSilent();
		}

		JoinTable jt = afactory.getAction(JoinTable.class);
		if (!srcTbl.getChildren().contains(destTbl))
			jt.doRun(dest, destTbl, src, srcTbl);
		else
			jt.doRun(src, srcTbl, dest, destTbl);

	}
}
