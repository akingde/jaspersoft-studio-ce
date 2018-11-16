/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.action.table;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.TreeViewer;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.dialogs.EditFromTableDialog;
import com.jaspersoft.studio.data.sql.messages.Messages;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.subquery.MQueryTable;
import com.jaspersoft.studio.data.sql.ui.gef.command.EditTableCommand;
import com.jaspersoft.studio.model.ANode;

public class EditTable extends AAction {
	private SQLQueryDesigner designer;

	public EditTable(SQLQueryDesigner designer, TreeViewer treeViewer) {
		super(Messages.EditTable_0, treeViewer);
		this.designer = designer;
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		return selection != null && selection.length == 1 && selection[0] instanceof ANode
				&& isColumn((ANode) selection[0]);
	}

	protected boolean isColumn(ANode element) {
		if (element instanceof MFromTable && element.getValue() instanceof MQueryTable)
			setText(Messages.EditTable_1);
		else
			setText(Messages.EditTable_0);
		return element instanceof MFromTable;
	}

	@Override
	public void run() {
		MFromTable mcol = null;
		for (Object obj : selection) {
			if (obj instanceof MFromTable) {
				mcol = (MFromTable) obj;
				break;
			}
		}
		EditFromTableDialog d = new EditFromTableDialog(treeViewer.getControl().getShell());
		d.setValue(mcol);
		if (d.open() == Dialog.OK) {
			EditTableCommand c = new EditTableCommand(mcol, d.getAlias(), d.getAliasKeyword());
			designer.getDiagram().getViewer().getEditDomain().getCommandStack().execute(c);
			selectInTree(mcol);
		}
	}
}
