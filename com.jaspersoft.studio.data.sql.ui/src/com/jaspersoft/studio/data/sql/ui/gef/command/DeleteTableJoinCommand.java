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

import java.util.ArrayList;
import java.util.List;

import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.model.query.MHaving;
import com.jaspersoft.studio.data.sql.model.query.MUnion;
import com.jaspersoft.studio.data.sql.model.query.MWhere;
import com.jaspersoft.studio.data.sql.model.query.from.MFrom;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTableJoin;
import com.jaspersoft.studio.data.sql.model.query.groupby.MGroupBy;
import com.jaspersoft.studio.data.sql.model.query.select.MSelect;
import com.jaspersoft.studio.data.sql.model.query.subquery.MQueryTable;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;

public class DeleteTableJoinCommand extends ACommand {
	private Object[] selection;

	public DeleteTableJoinCommand(Object[] selection) {
		this.selection = selection;
	}

	@Override
	public void execute() {
		super.execute();
		mtbl = null;
		MFromTableJoin mftj = doGetJoinedTable();
		if (mftj != null && mftj.getParent() != null
				&& mftj.getParent().getParent() != null) {
			MFromTable mtbl = new MFromTable(mftj.getParent().getParent(),
					mftj.getValue());
			undoRemove.add(mtbl);
			mtbl.setAlias(mftj.getAlias());
			mtbl.setAliasKeyword(mftj.getAliasKeyword());
			mftj.copyProperties(mtbl);
			reparent(mftj, null);

			copySubQuery(mftj, mtbl);

			Util.cleanTableVersions(mtbl, mftj);
		}
	}

	protected void copySubQuery(MFromTable mftj, MFromTable mtbl) {
		if (mftj.getValue() instanceof MQueryTable) {
			List<INode> children = new ArrayList<INode>(mftj.getChildren());
			for (INode n : children) {
				if (n instanceof MUnion || n instanceof MSelect
						|| n instanceof MFrom || n instanceof MWhere
						|| n instanceof MGroupBy || n instanceof MHaving)
					reparent((ANode) n, mtbl);
			}
		}
	}

	private MFromTable mtbl;

	public MFromTable getResultFromTable() {
		return mtbl;
	}

	protected MFromTableJoin doGetJoinedTable() {
		for (Object obj : selection)
			if (obj instanceof MFromTableJoin)
				return (MFromTableJoin) obj;
		return null;
	}

}
