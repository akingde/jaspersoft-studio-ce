package com.jaspersoft.studio.data.sql.action.table;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.dialogs.JoinFromTableDialog;
import com.jaspersoft.studio.data.sql.model.query.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.MFromTableJoin;
import com.jaspersoft.studio.model.ANode;

public class EditTableJoin extends AAction {

	public EditTableJoin(IXtextDocument xtextDocument, SQLQueryDesigner designer) {
		super("&Edit Table Join", xtextDocument, designer);
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		return selection != null && selection.length == 1 && isColumn((ANode) selection[0]);
	}

	protected boolean isColumn(ANode element) {
		return element instanceof MFromTableJoin;
	}

	@Override
	public void run() {
		MFromTableJoin mcol = null;
		for (Object obj : selection) {
			if (obj instanceof MFromTableJoin) {
				mcol = (MFromTableJoin) obj;
				break;
			}
		}
		JoinFromTableDialog dialog = new JoinFromTableDialog(Display.getDefault().getActiveShell());
		dialog.setValue(mcol);
		if (dialog.open() == Dialog.OK) {
			MFromTable mtab = JoinTable.getFromTable(mcol, dialog);
			mcol.setParent(mtab, -1);
			mcol.setJoin(dialog.getJoin());
			selectInTree(mcol);
		}
	}
}