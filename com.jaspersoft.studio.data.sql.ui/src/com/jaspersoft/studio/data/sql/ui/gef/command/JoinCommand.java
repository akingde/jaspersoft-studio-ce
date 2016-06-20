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

import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.action.ActionFactory;
import com.jaspersoft.studio.data.sql.model.metadata.MSQLColumn;
import com.jaspersoft.studio.data.sql.model.query.expression.MExpression;
import com.jaspersoft.studio.data.sql.model.query.from.MFrom;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTableJoin;
import com.jaspersoft.studio.data.sql.model.query.operand.FieldOperand;
import com.jaspersoft.studio.data.sql.model.query.subquery.MQueryTable;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;

public class JoinCommand extends ACommand {
	private SQLQueryDesigner designer;
	private MSQLColumn src, dest;
	private MFromTable srcTbl, destTbl;

	public JoinCommand(MSQLColumn src, MFromTable srcTbl, MSQLColumn dest, MFromTable destTbl,
			SQLQueryDesigner designer) {
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
		super.execute();
		ActionFactory afactory = designer.getOutline().getAfactory();
		MFromTable mfSrcTbl = srcTbl;
		if (srcTbl.getParent() instanceof MFrom && srcTbl.getParent() != null
				&& srcTbl.getParent().getParent() instanceof MFromTable
				&& srcTbl.getParent().getParent().getValue() != null
				&& srcTbl.getParent().getParent().getValue() instanceof MQueryTable)
			mfSrcTbl = (MFromTable) srcTbl.getParent().getParent();
		MFromTable mfDesttbl = destTbl;
		if (destTbl.getParent() instanceof MFrom && destTbl.getParent() != null
				&& destTbl.getParent().getParent() instanceof MFromTable
				&& destTbl.getParent().getParent().getValue() != null
				&& destTbl.getParent().getParent().getValue() instanceof MQueryTable)
			mfDesttbl = (MFromTable) destTbl.getParent().getParent();
		if (mfDesttbl != mfSrcTbl) {
			srcTbl = mfSrcTbl;
			destTbl = mfDesttbl;
		}
		if (srcTbl instanceof MFromTableJoin && !(destTbl instanceof MFromTableJoin)) {
			MFromTable tmp = srcTbl;
			MSQLColumn tmpColumn = src;
			srcTbl = destTbl;
			src = dest;
			destTbl = tmp;
			dest = tmpColumn;
		}
		if (srcTbl instanceof MFromTableJoin) {
			DeleteTableJoinCommand cmd = new DeleteTableJoinCommand(new Object[] { srcTbl });
			undoCmd.add(cmd);
			cmd.execute();
			srcTbl = cmd.getResultFromTable();
		}

		MFromTable fromTbl = destTbl;
		if (destTbl instanceof MFromTableJoin)
			fromTbl = getParentFromTable((MFromTableJoin) destTbl);
		if (srcTbl == destTbl)
			return;
		for (INode n : fromTbl.getChildren()) {
			if (n == destTbl) {
				MExpression mexpr = new MExpression(srcTbl, src, -1);
				undoRemove.add(mexpr);
				mexpr.getOperands().add(new FieldOperand(src, srcTbl, mexpr));
				mexpr.getOperands().add(new FieldOperand(dest, destTbl, mexpr));

				refresh();
				return;
			}
		}

		JoinTableCommand c = new JoinTableCommand(src, srcTbl, dest, destTbl, fromTbl);
		undoCmd.add(c);
		c.execute();

		if (srcTbl instanceof MFromTable && !srcTbl.getChildren().isEmpty()) {
			List<MFromTableJoin> lst = new ArrayList<MFromTableJoin>();
			for (INode n : srcTbl.getChildren()) {
				if (n == destTbl)
					return;
				if (n instanceof MFromTableJoin)
					lst.add((MFromTableJoin) n);
			}
			for (MFromTable mft : lst)
				reparent(mft, destTbl);
		}
		refresh();
	}

	private void refresh() {
		TreeViewer tv = designer.getOutline().getTreeViewer();
		tv.refresh(true);
		tv.setSelection(new TreeSelection(new TreePath(new Object[] { destTbl })));
		tv.reveal(destTbl);
	}

	public static MFromTable getParentFromTable(MFromTableJoin dest) {
		ANode res = dest.getParent();
		while (res != null) {
			if (res instanceof MFromTable && !(res instanceof MFromTableJoin))
				return (MFromTable) res;
			res = res.getParent();
		}
		return dest;
	}

	@Override
	protected void firePropertyChange() {
	}
}
