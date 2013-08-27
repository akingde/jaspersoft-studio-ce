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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.action.ActionFactory;
import com.jaspersoft.studio.data.sql.action.table.DeleteTableJoin;
import com.jaspersoft.studio.data.sql.action.table.JoinTable;
import com.jaspersoft.studio.data.sql.model.metadata.MSQLColumn;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTableJoin;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;

public class JoinCommand extends Command {
	private SQLQueryDesigner designer;
	private MSQLColumn src, dest;
	private MFromTable srcTbl, destTbl;

	public JoinCommand(MSQLColumn src, MFromTable srcTbl, MSQLColumn dest, MFromTable destTbl, SQLQueryDesigner designer) {
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
		if (destTbl instanceof MFromTableJoin)
			destTbl = getParentFromTable((MFromTableJoin) destTbl);
		if (srcTbl == destTbl)
			return;

		JoinTable jt = afactory.getAction(JoinTable.class);
		jt.doRun(src, srcTbl, dest, destTbl);

		if (srcTbl instanceof MFromTable && !srcTbl.getChildren().isEmpty()) {
			List<MFromTableJoin> lst = new ArrayList<MFromTableJoin>();
			for (INode n : srcTbl.getChildren())
				if (n instanceof MFromTableJoin)
					lst.add((MFromTableJoin) n);
			for (MFromTable mft : lst)
				mft.setParent(destTbl, -1);
		}
	}

	private MFromTable getParentFromTable(MFromTableJoin dest) {
		ANode res = dest.getParent();
		while (res != null) {
			if (res instanceof MFromTable && !(res instanceof MFromTableJoin))
				return (MFromTable) res;
			res = res.getParent();
		}
		return dest;
	}
}
