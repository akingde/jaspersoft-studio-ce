package com.jaspersoft.studio.data.sql.action.expression;

import java.util.Collection;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.dialogs.EditExpressionDialog;
import com.jaspersoft.studio.data.sql.model.enums.Operator;
import com.jaspersoft.studio.data.sql.model.metadata.MColumn;
import com.jaspersoft.studio.data.sql.model.query.MExpression;
import com.jaspersoft.studio.data.sql.model.query.MExpressionGroup;
import com.jaspersoft.studio.data.sql.model.query.MFromTableJoin;
import com.jaspersoft.studio.data.sql.model.query.MHaving;
import com.jaspersoft.studio.data.sql.model.query.MWhere;
import com.jaspersoft.studio.data.sql.model.query.operand.FieldOperand;
import com.jaspersoft.studio.data.sql.widgets.Factory;
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
		return element instanceof MWhere || element instanceof MHaving || element instanceof MExpression || element instanceof MFromTableJoin || element instanceof MExpressionGroup;
	}

	@Override
	public void run() {
		Object sel = selection[0];
		MExpression mexpr = null;
		if (sel instanceof MExpression)
			mexpr = run(null, (MExpression) sel);
		else if (isInSelect(sel))
			mexpr = run(null, (ANode) sel, -1);
		mexpr.getOperands().add(Factory.getDefaultOperand(mexpr));
		mexpr.getOperands().add(Factory.getDefaultOperand(mexpr));
		showDialog(mexpr);
	}

	public void run(Collection<MColumn> nodes) {
		Object sel = selection[0];
		MExpression mexpr = null;
		for (MColumn t : nodes) {
			if (sel instanceof MExpression)
				mexpr = run(t, (MExpression) sel);
			else if (isInSelect(sel))
				mexpr = run(t, (ANode) sel, -1);
			sel = mexpr;
			mexpr.getOperands().add(new FieldOperand(t, mexpr));
			mexpr.getOperands().add(Factory.getDefaultOperand(mexpr));
		}
		showDialog(mexpr);
	}

	protected void showDialog(MExpression mexpr) {
		EditExpressionDialog dialog = new EditExpressionDialog(Display.getDefault().getActiveShell());
		dialog.setValue(mexpr);
		if (dialog.open() == Dialog.OK) {
			mexpr.setOperator(Operator.getOperator((dialog.getOperator())));
			mexpr.setPrevCond(dialog.getPrevcond());
			mexpr.setOperands(dialog.getOperands());
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