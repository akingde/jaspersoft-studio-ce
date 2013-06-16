package com.jaspersoft.studio.data.sql.action.table;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.dialogs.JoinFromTableDialog;
import com.jaspersoft.studio.data.sql.model.metadata.MColumn;
import com.jaspersoft.studio.data.sql.model.metadata.MSqlTable;
import com.jaspersoft.studio.data.sql.model.query.MExpression;
import com.jaspersoft.studio.data.sql.model.query.from.MFrom;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTableJoin;
import com.jaspersoft.studio.data.sql.model.query.operand.FieldOperand;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;

public class JoinTable extends AAction {

	public JoinTable(SQLQueryDesigner designer) {
		super("&Join Table", designer);
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		return selection != null && selection.length == 1 && selection[0] instanceof ANode && isColumn((ANode) selection[0]);
	}

	protected boolean isColumn(ANode element) {
		return element instanceof MFromTable && !(element instanceof MFromTableJoin) && Util.getKeyword(element, MFrom.class).getChildren().size() > 1;
	}

	@Override
	public void run() {
		MFromTable mfromTable = null;
		for (Object obj : selection) {
			if (obj instanceof MFromTable) {
				mfromTable = (MFromTable) obj;
				break;
			}
		}
		JoinFromTableDialog dialog = new JoinFromTableDialog(Display.getDefault().getActiveShell());
		dialog.setValue(mfromTable);
		if (dialog.open() == Dialog.OK) {
			MFromTable mtab = getFromTable(mfromTable, dialog);

			doRun(null, mfromTable, null, mtab);
		}
	}

	public void doRun(MColumn src, MFromTable srcTbl, MColumn dest, MFromTable destTbl) {
		if (src == null)
			src = getColumn(srcTbl.getValue());
		if (dest == null)
			dest = getColumn(destTbl.getValue());
		srcTbl.setParent(null, -1);

		MFromTableJoin mtbljoin = new MFromTableJoin(destTbl, srcTbl.getValue());
		mtbljoin.setAlias(srcTbl.getAlias());
		mtbljoin.setAliasKeyword(srcTbl.getAliasKeyword());

		MExpression mexpr = new MExpression(mtbljoin, src, -1);
		mexpr.getOperands().add(new FieldOperand(src, mtbljoin, mexpr));
		mexpr.getOperands().add(new FieldOperand(dest, destTbl, mexpr));
		selectInTree(mexpr);

		Util.cleanTableVersions(mtbljoin, srcTbl);
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