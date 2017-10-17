/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.action.table;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.TreeViewer;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.dialogs.JoinFromTableDialog;
import com.jaspersoft.studio.data.sql.messages.Messages;
import com.jaspersoft.studio.data.sql.model.query.from.MFrom;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTableJoin;
import com.jaspersoft.studio.data.sql.model.query.subquery.MQueryTable;
import com.jaspersoft.studio.data.sql.ui.gef.command.JoinTableCommand;
import com.jaspersoft.studio.model.ANode;

public class JoinTable extends AAction {
	private SQLQueryDesigner designer;

	public JoinTable(SQLQueryDesigner designer, TreeViewer treeViewer) {
		super(Messages.JoinTable_0, treeViewer);
		this.designer = designer;
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		return selection != null && selection.length == 1 && selection[0] instanceof ANode
				&& isColumn((ANode) selection[0]);
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
			b = b && mfrom != null && mfrom.getChildren().size() > 1;
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
		JoinFromTableDialog dialog = new JoinFromTableDialog(treeViewer.getControl().getShell(), designer, true);
		dialog.setValue(mfromTable);
		if (dialog.open() == Dialog.OK) {
			MFromTable destTbl = getFromTable(mfromTable, dialog);

			if (mfromTable instanceof MFromTableJoin) {
				mfromTable = (MFromTable) mfromTable.getParent();

				MFromTable tmp = destTbl;
				destTbl = mfromTable;
				mfromTable = tmp;
			}

			JoinTableCommand c = new JoinTableCommand(null, mfromTable, null, destTbl, destTbl);
			designer.getDiagram().getViewer().getEditDomain().getCommandStack().execute(c);

			selectInTree(c.getMexpr());

		}
	}

	public static MFromTable getFromTable(MFromTable mcol, JoinFromTableDialog dialog) {
		String ft = dialog.getFromTable().replace(",", "").trim(); //$NON-NLS-1$ //$NON-NLS-2$
		MFromTable mFromTable = null;
		for (MFromTable mft : Util.getFromTables(Util.getKeyword(mcol, MFrom.class))) {
			if (mft == mcol)
				continue;
			String alias = ""; //$NON-NLS-1$
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
