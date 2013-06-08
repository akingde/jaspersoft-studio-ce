package com.jaspersoft.studio.data.sql.action.expression;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.dialogs.EditExpressionDialog;
import com.jaspersoft.studio.data.sql.model.enums.Operator;
import com.jaspersoft.studio.data.sql.model.query.MExpression;
import com.jaspersoft.studio.model.ANode;

public class EditExpression extends AAction {

	public EditExpression(SQLQueryDesigner designer) {
		super("&Edit Column", designer);
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		return selection != null && selection.length == 1 && isColumn((ANode) selection[0]);
	}

	protected boolean isColumn(ANode element) {
		return element instanceof MExpression;
	}

	@Override
	public void run() {
		MExpression mcol = null;
		for (Object obj : selection) {
			if (obj instanceof MExpression) {
				mcol = (MExpression) obj;
				break;
			}
		}
		EditExpressionDialog dialog = new EditExpressionDialog(Display.getDefault().getActiveShell());
		dialog.setValue(mcol);
		if (dialog.open() == Dialog.OK) {
			mcol.setOperator(Operator.getOperator((dialog.getOperator())));
			mcol.setPrevCond(dialog.getPrevcond());
			mcol.setOperands(dialog.getOperands());
			selectInTree(mcol);
		}
	}
}