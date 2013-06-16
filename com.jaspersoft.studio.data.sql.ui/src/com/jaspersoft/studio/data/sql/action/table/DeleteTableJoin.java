package com.jaspersoft.studio.data.sql.action.table;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTableJoin;
import com.jaspersoft.studio.model.ANode;

public class DeleteTableJoin extends AAction {

	public DeleteTableJoin(SQLQueryDesigner designer) {
		super("&Delete Table Join", designer);
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		return selection != null && selection.length == 1 && selection[0] instanceof ANode && isColumn((ANode) selection[0]);
	}

	protected boolean isColumn(ANode element) {
		return element instanceof MFromTableJoin;
	}

	@Override
	public void run() {
		MFromTableJoin mcol = doGetJoinedTable();
		if (UIUtils.showConfirmation("Delete Join Between Tables", "Are you sure you want to delete the join between tables?"))
			doDelete(mcol);
	}

	public MFromTable runSilent() {
		return doDelete(doGetJoinedTable());
	}

	protected MFromTableJoin doGetJoinedTable() {
		MFromTableJoin mcol = null;
		for (Object obj : selection) {
			if (obj instanceof MFromTableJoin) {
				mcol = (MFromTableJoin) obj;
				break;
			}
		}
		return mcol;
	}

	protected MFromTable doDelete(MFromTableJoin mcol) {
		MFromTable mtbl = new MFromTable(mcol.getParent().getParent(), mcol.getValue());
		mtbl.setAlias(mcol.getAlias());
		mtbl.setAliasKeyword(mcol.getAliasKeyword());

		mcol.setParent(null, -1);
		Util.cleanTableVersions(mtbl, mcol);

		selectInTree(mtbl);
		return mtbl;
	}

}