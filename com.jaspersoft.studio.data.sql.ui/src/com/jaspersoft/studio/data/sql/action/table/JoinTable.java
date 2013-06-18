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
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.dialogs.JoinFromTableDialog;
import com.jaspersoft.studio.data.sql.model.metadata.MSQLColumn;
import com.jaspersoft.studio.data.sql.model.metadata.MSqlTable;
import com.jaspersoft.studio.data.sql.model.query.MExpression;
import com.jaspersoft.studio.data.sql.model.query.from.MFrom;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTableJoin;
import com.jaspersoft.studio.data.sql.model.query.operand.FieldOperand;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;

public class JoinTable extends AAction {

	public JoinTable(SQLQueryDesigner designer) {
		super("&Join Table", designer);
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		return selection != null && selection.length == 1 && selection[0] instanceof ANode && isColumn((ANode) selection[0]);
	}

	protected boolean isColumn(ANode element) {
		return element instanceof MFromTable && !(element instanceof MFromTableJoin) && Util.getKeyword(element, MFrom.class).getChildren().size() > 1;
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
		JoinFromTableDialog dialog = new JoinFromTableDialog(Display.getDefault().getActiveShell());
		dialog.setValue(mfromTable);
		if (dialog.open() == Dialog.OK) {
			MFromTable mtab = getFromTable(mfromTable, dialog);

			doRun(null, mfromTable, null, mtab);
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
		ANode p = mcol.getParent();
		if (p instanceof MFromTableJoin)
			p = p.getParent();
		String ft = dialog.getFromTable();
		MFromTable mtab = null;
		for (INode n : p.getChildren()) {
			if (n instanceof MFromTable && ((MFromTable) n).getValue().toSQLString().equals(ft)) {
				mtab = (MFromTable) n;
				break;
			}
		}
		return mtab;
	}
}
