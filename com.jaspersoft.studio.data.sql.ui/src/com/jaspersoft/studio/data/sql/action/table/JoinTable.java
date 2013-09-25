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
package com.jaspersoft.studio.data.sql.action.table;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.dialogs.JoinFromTableDialog;
import com.jaspersoft.studio.data.sql.model.metadata.MSQLColumn;
import com.jaspersoft.studio.data.sql.model.metadata.MSqlTable;
import com.jaspersoft.studio.data.sql.model.query.expression.MExpression;
import com.jaspersoft.studio.data.sql.model.query.from.MFrom;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTableJoin;
import com.jaspersoft.studio.data.sql.model.query.operand.FieldOperand;
import com.jaspersoft.studio.data.sql.model.query.subquery.MQueryTable;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;

public class JoinTable extends AAction {
	private SQLQueryDesigner designer;

	public JoinTable(SQLQueryDesigner designer, TreeViewer treeViewer) {
		super("&Join Table", treeViewer);
		this.designer = designer;
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		return selection != null && selection.length == 1 && selection[0] instanceof ANode && isColumn((ANode) selection[0]);
	}

	protected boolean isColumn(ANode element) {
		boolean b = element instanceof MFromTable;// && !(element instanceof
																							// MFromTableJoin);
		if (b) {
			MFrom mfrom = null;
			if (element instanceof MFromTable && element.getValue() instanceof MQueryTable)
				mfrom = Util.getKeyword(element.getParent(), MFrom.class);
			else
				mfrom = Util.getKeyword(element, MFrom.class);
			b = b && mfrom.getChildren().size() > 1;
		}
		return b;
	}

	@Override
	public void run() {
		MFromTable mfromTable = null;
		for (Object obj : selection) {
			if (obj instanceof MFromTable) {
				mfromTable = (MFromTable) obj;
				break;
			}
		}
		JoinFromTableDialog dialog = new JoinFromTableDialog(Display.getDefault().getActiveShell(), designer, true);
		dialog.setValue(mfromTable);
		if (dialog.open() == Dialog.OK) {
			MFromTable srcTbl = mfromTable;
			if (mfromTable instanceof MFromTableJoin)
				srcTbl = (MFromTable) mfromTable.getParent();
			MFromTable destTbl = getFromTable(mfromTable, dialog);
			doRun(null, srcTbl, null, destTbl);
		}
	}

	public void doRun(MSQLColumn src, MFromTable srcTbl, MSQLColumn dest, MFromTable destTbl) {
		if (src == null)
			src = getColumn(srcTbl.getValue());
		if (dest == null)
			dest = getColumn(destTbl.getValue());
		srcTbl.setParent(null, -1);

		MFromTableJoin mtbljoin = new MFromTableJoin(destTbl, srcTbl.getValue());
		mtbljoin.setAlias(srcTbl.getAlias());
		mtbljoin.setAliasKeyword(srcTbl.getAliasKeyword());

		Util.copySubQuery(srcTbl, mtbljoin);

		MExpression mexpr = new MExpression(mtbljoin, src, -1);
		mexpr.getOperands().add(new FieldOperand(src, mtbljoin, mexpr));
		mexpr.getOperands().add(new FieldOperand(dest, destTbl, mexpr));
		selectInTree(mexpr);

		Util.cleanTableVersions(mtbljoin, srcTbl);
	}

	private MSQLColumn getColumn(MSqlTable tbl) {
		if (!tbl.getChildren().isEmpty()) {
			for (INode n : tbl.getChildren())
				if (((MSQLColumn) n).getPrimaryKey() != null)
					return (MSQLColumn) n;
			return (MSQLColumn) tbl.getChildren().get(0);
		}
		return null;
	}

	public static MFromTable getFromTable(MFromTable mcol, JoinFromTableDialog dialog) {
		String ft = dialog.getFromTable().replace(",", "").trim();
		MFromTable mFromTable = null;
		for (MFromTable mft : Util.getFromTables(Util.getKeyword(mcol, MFrom.class))) {
			if (mft == mcol)
				continue;
			String alias = "";
			if (mft.getAlias() != null)
				alias = mft.getAliasKeyString() + mft.getAlias();
			if ((mft.getValue().getDisplayText() + alias).trim().equals(ft)) {
				mFromTable = mft;
				break;
			}
		}
		if (mFromTable instanceof MFromTableJoin)
			mFromTable = (MFromTable) mFromTable.getParent();
		return mFromTable;
	}
}
