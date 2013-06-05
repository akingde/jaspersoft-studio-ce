package com.jaspersoft.studio.data.sql.action.table;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.xtext.ui.editor.model.IXtextDocument;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.model.query.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.MFromTableJoin;
import com.jaspersoft.studio.model.ANode;

public class DeleteTableJoin extends AAction {

	public DeleteTableJoin(IXtextDocument xtextDocument, SQLQueryDesigner designer) {
		super("&Delete Table Join", xtextDocument, designer);
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
		if (UIUtils.showConfirmation("Delete Join Between Tables", "Are you sure you want to delete the join between tables?")) {
			MFromTable mtbl = new MFromTable(mcol.getParent().getParent(), mcol.getValue());
			mtbl.setAlias(mcol.getAlias());
			mtbl.setAliasKeyword(mcol.getAliasKeyword());

			mcol.setParent(null, -1);
			selectInTree(mtbl);
		}
	}
}