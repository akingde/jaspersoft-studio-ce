package com.jaspersoft.studio.data.sql.action.select;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.dialogs.EditSelectColumnDialog;
import com.jaspersoft.studio.data.sql.dialogs.EditSelectExpressionDialog;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectColumn;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectExpression;
import com.jaspersoft.studio.model.ANode;

public class EditColumn extends AAction {

	public EditColumn(SQLQueryDesigner designer) {
		super("&Edit Column", designer);
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		return selection != null && selection.length == 1 && selection[0] instanceof ANode && isColumn((ANode) selection[0]);
	}

	protected boolean isColumn(ANode element) {
		return element instanceof MSelectColumn || element instanceof MSelectExpression;
	}

	@Override
	public void run() {
		for (Object obj : selection) {
			if (obj instanceof MSelectColumn) {
				doRunColumn((MSelectColumn) obj);
				break;
			} else if (obj instanceof MSelectExpression) {
				doRunExpression((MSelectExpression) obj);
				break;
			}
		}

	}

	protected void doRunExpression(MSelectExpression mcol) {
		EditSelectExpressionDialog dialog = new EditSelectExpressionDialog(Display.getDefault().getActiveShell());
		dialog.setValue(mcol);
		if (dialog.open() == Window.OK) {
			mcol.setValue(dialog.getExpression());
			mcol.setAlias(dialog.getAlias());
			mcol.setAliasKeyword(dialog.getAliasKeyword());
			selectInTree(mcol);
		}
	}

	protected void doRunColumn(MSelectColumn mcol) {
		EditSelectColumnDialog dialog = new EditSelectColumnDialog(Display.getDefault().getActiveShell());
		dialog.setValue(mcol);
		if (dialog.open() == Dialog.OK) {
			mcol.setAlias(dialog.getAlias());
			mcol.setAliasKeyword(dialog.getAliasKeyword());
			selectInTree(mcol);
		}
	}
}