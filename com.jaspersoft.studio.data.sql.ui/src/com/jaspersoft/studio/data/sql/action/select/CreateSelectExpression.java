package com.jaspersoft.studio.data.sql.action.select;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.dialogs.EditSelectExpressionDialog;
import com.jaspersoft.studio.data.sql.model.query.select.MSelect;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectColumn;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectExpression;

public class CreateSelectExpression extends AAction {

	public CreateSelectExpression(SQLQueryDesigner designer) {
		super("&Add Expression", designer);
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		return selection != null && selection.length == 1 && isInSelect(selection[0]);
	}

	public static boolean isInSelect(Object element) {
		return element instanceof MSelect || element instanceof MSelectColumn || element instanceof MSelectExpression;
	}

	@Override
	public void run() {
		EditSelectExpressionDialog dialog = new EditSelectExpressionDialog(Display.getDefault().getActiveShell());
		MSelectExpression mexpr = new MSelectExpression(null, " ");
		dialog.setValue(mexpr);
		if (dialog.open() == Window.OK) {
			Object sel = selection[0];
			MSelect mselect = null;
			int index = 0;
			if (sel instanceof MSelect)
				mselect = (MSelect) sel;
			else if (sel instanceof MSelectExpression) {
				mselect = (MSelect) ((MSelectExpression) sel).getParent();
				index = mselect.getChildren().indexOf((MSelectExpression) sel) + 1;
			}
			mexpr.setParent(mselect, index);
			mexpr.setValue(dialog.getExpression());
			mexpr.setAlias(dialog.getAlias());
			mexpr.setAliasKeyword(dialog.getAliasKeyword());
			selectInTree(mexpr);
		}
	}

}