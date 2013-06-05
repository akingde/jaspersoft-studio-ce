package com.jaspersoft.studio.data.sql.action.table;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.dialogs.EditFromTableDialog;
import com.jaspersoft.studio.data.sql.model.query.MFromTable;
import com.jaspersoft.studio.model.ANode;

public class EditTable extends AAction {

	public EditTable(IXtextDocument xtextDocument, SQLQueryDesigner designer) {
		super("&Edit Table", xtextDocument, designer);
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		return selection == null || (selection != null && selection.length == 1 && isColumn((ANode) selection[0]));
	}

	protected boolean isColumn(ANode element) {
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
		EditFromTableDialog dialog = new EditFromTableDialog(Display.getDefault().getActiveShell());
		dialog.setValue(mcol);
		if (dialog.open() == Dialog.OK) {
			mcol.setAlias(dialog.getAlias());
			mcol.setAliasKeyword(dialog.getAliasKeyword());
			selectInTree(mcol);
		}
	}
}