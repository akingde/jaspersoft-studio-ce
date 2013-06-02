package com.jaspersoft.studio.data.sql.action.expression;

import java.util.Collection;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.dialogs.EditExpressionDialog;
import com.jaspersoft.studio.data.sql.model.metadata.MColumn;
import com.jaspersoft.studio.data.sql.model.query.MExpression;
import com.jaspersoft.studio.data.sql.model.query.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.MHaving;
import com.jaspersoft.studio.data.sql.model.query.MWhere;
import com.jaspersoft.studio.model.ANode;

public class CreateExpression extends AAction {

	public CreateExpression(IXtextDocument xtextDocument, SQLQueryDesigner designer) {
		super("&Add Expression", xtextDocument, designer);
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		return selection != null && selection.length == 1 && isInSelect(selection[0]);
	}

	public static boolean isInSelect(Object element) {
		return element instanceof MWhere || element instanceof MHaving || element instanceof MExpression || element instanceof MFromTable;
	}

	@Override
	public void run() {
		Object sel = selection[0];
		if (sel instanceof MExpression)
			sel = run(null, (MExpression) sel);
		else if (isInSelect(sel))
			sel = run(null, (ANode) sel, 0);
		showDialog((MExpression) sel);
	}

	public void run(Collection<MColumn> nodes) {
		Object sel = selection[0];
		for (MColumn t : nodes) {
			if (sel instanceof MExpression)
				sel = run(t, (MExpression) sel);
			else if (isInSelect(sel))
				sel = run(t, (ANode) sel, 0);
		}
		showDialog((MExpression) sel);
	}

	protected void showDialog(MExpression mexpr) {
		EditExpressionDialog dialog = new EditExpressionDialog(Display.getDefault().getActiveShell());
		dialog.setValue(mexpr);
		if (dialog.open() == Dialog.OK) {
			selectInTree(mexpr);
		} else {
			ANode p = mexpr.getParent();
			p.removeChild(mexpr);
			selectInTree(p);
		}
	}

	protected MExpression run(Object node, MExpression mtable) {
		ANode mfrom = mtable.getParent();
		return run(node, mfrom, mfrom.getChildren().indexOf(mtable) + 1);
	}

	public MExpression run(Object node, ANode select, int index) {
		return new MExpression(select, node, index);
	}

}