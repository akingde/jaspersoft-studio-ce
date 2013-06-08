package com.jaspersoft.studio.data.sql.action.select;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.dialogs.EditSelectColumnDialog;
import com.jaspersoft.studio.data.sql.model.query.MSelectColumn;
import com.jaspersoft.studio.model.ANode;

public class EditColumn extends AAction {

	public EditColumn(SQLQueryDesigner designer) {
		super("&Edit Column", designer);
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		return selection != null && selection.length == 1 && isColumn((ANode) selection[0]);
	}

	protected boolean isColumn(ANode element) {
		return element instanceof MSelectColumn;
	}

	@Override
	public void run() {
		MSelectColumn mcol = null;
		for (Object obj : selection) {
			if (obj instanceof MSelectColumn) {
				mcol = (MSelectColumn) obj;
				break;
			}
		}
		EditSelectColumnDialog dialog = new EditSelectColumnDialog(Display.getDefault().getActiveShell());
		dialog.setValue(mcol);
		if (dialog.open() == Dialog.OK) {
			mcol.setAlias(dialog.getAlias());
			mcol.setAliasKeyword(dialog.getAliasKeyword());
			selectInTree(mcol);
		}
	}
}