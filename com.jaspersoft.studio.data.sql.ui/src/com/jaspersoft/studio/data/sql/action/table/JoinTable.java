package com.jaspersoft.studio.data.sql.action.table;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.dialogs.JoinFromTableDialog;
import com.jaspersoft.studio.data.sql.model.metadata.MColumn;
import com.jaspersoft.studio.data.sql.model.metadata.MSqlTable;
import com.jaspersoft.studio.data.sql.model.query.MExpression;
import com.jaspersoft.studio.data.sql.model.query.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.MFromTableJoin;
import com.jaspersoft.studio.data.sql.model.query.operand.FieldOperand;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;

public class JoinTable extends AAction {

	public JoinTable(IXtextDocument xtextDocument, SQLQueryDesigner designer) {
		super("&Join Table", xtextDocument, designer);
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		return selection != null && selection.length == 1 && isColumn((ANode) selection[0]);
	}

	protected boolean isColumn(ANode element) {
		return element instanceof MFromTable && !element.isFirst() && !(element instanceof MFromTableJoin);
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
		JoinFromTableDialog dialog = new JoinFromTableDialog(Display.getDefault().getActiveShell());
		dialog.setValue(mcol);
		if (dialog.open() == Dialog.OK) {
			MFromTable mtab = getFromTable(mcol, dialog);

			mcol.setParent(null, -1);

			MFromTableJoin mtbljoin = new MFromTableJoin(mtab, mcol.getValue());
			mtbljoin.setAlias(mcol.getAlias());
			mtbljoin.setAliasKeyword(mcol.getAliasKeyword());

			MColumn mSrc = getColumn(mtbljoin.getValue());
			MColumn mFrom = getColumn(mtab.getValue());

			MExpression mexpr = new MExpression(mtbljoin, mSrc, -1);
			mexpr.getOperands().add(new FieldOperand(mSrc));
			mexpr.getOperands().add(new FieldOperand(mFrom));
			selectInTree(mexpr);
		}
	}

	private MColumn getColumn(MSqlTable tbl) {
		if (!tbl.getChildren().isEmpty())
			return (MColumn) tbl.getChildren().get(0);
		return null;
	}

	public static MFromTable getFromTable(MFromTable mcol, JoinFromTableDialog dialog) {
		ANode p = mcol.getParent();
		if (p instanceof MFromTableJoin)
			p = p.getParent();
		MFromTable mtab = null;
		for (INode n : p.getChildren()) {
			if (n.getDisplayText().equals(dialog.getFromTable())) {
				mtab = (MFromTable) n;
				break;
			}
		}
		return mtab;
	}
}