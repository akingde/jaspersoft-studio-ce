/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.ui.gef.command;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.model.metadata.MSQLColumn;
import com.jaspersoft.studio.data.sql.model.query.AMKeyword;
import com.jaspersoft.studio.data.sql.model.query.expression.MExpression;
import com.jaspersoft.studio.data.sql.model.query.from.MFrom;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTableJoin;
import com.jaspersoft.studio.data.sql.model.query.operand.FieldOperand;
import com.jaspersoft.studio.data.sql.model.query.subquery.MQueryTable;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;

import net.sf.jasperreports.eclipse.util.Misc;

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
		if (srcTbl instanceof MFromTable)
			srcTbl = convertToSubTable(srcTbl, fromTbl);
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
		String join = AMKeyword.INNER_JOIN;
		if (srcTbl instanceof MFromTableJoin)
			join = ((MFromTableJoin) srcTbl).getJoin();
		else if (destTbl instanceof MFromTableJoin)
			join = ((MFromTableJoin) destTbl).getJoin();
		JoinTableCommand c = new JoinTableCommand(src, srcTbl, dest, destTbl, fromTbl, join);
		undoCmd.add(c);
		c.execute();

		if (srcTbl instanceof MFromTable && !srcTbl.getChildren().isEmpty()) {
			List<MFromTableJoin> lst = new ArrayList<>();
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

	private MFromTableJoin convertToSubTable(MFromTable nsrc, MFromTable parent) {
		if (nsrc != parent)
			reparent(nsrc, null);
		MFromTableJoin join = new MFromTableJoin(parent, nsrc.getValue());
		undoRemove.add(join);
		undoProperties.put(nsrc, nsrc.copyPropertiesUndo(join));
		if (!Misc.isNullOrEmpty(nsrc.getChildren()))
			for (INode n : new ArrayList<INode>(nsrc.getChildren()))
				reparent((ANode) n, parent);
		Util.cleanTableVersions(join, nsrc);

		return join;
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
		// do nothing here
	}
}
