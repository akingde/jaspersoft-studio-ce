package com.jaspersoft.studio.data.sql.action.select;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.model.query.MGroupBy;
import com.jaspersoft.studio.data.sql.model.query.MGroupByColumn;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectColumn;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;

public class CreateGroupByFromColumn extends AAction {

	public CreateGroupByFromColumn(SQLQueryDesigner designer) {
		super("&Add To Group By", designer);
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		if (selection == null)
			return false;
		else {
			for (Object s : selection) {
				if (!isColumn((ANode) s))
					return false;
			}
		}
		return true;
	}

	protected boolean isColumn(ANode element) {
		return element instanceof MSelectColumn;
	}

	@Override
	public void run() {
		MGroupByColumn gbc = null;
		MGroupBy mgroupby = null;
		for (Object obj : selection) {
			if (obj instanceof MSelectColumn) {
				MSelectColumn msc = (MSelectColumn) obj;
				if (mgroupby == null) {
					for (INode n : msc.getRoot().getChildren())
						if (n instanceof MGroupBy) {
							mgroupby = (MGroupBy) n;
							break;
						}
				}
				gbc = new MGroupByColumn(mgroupby, msc);
			}
		}
		selectInTree(gbc);
	}

}