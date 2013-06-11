package com.jaspersoft.studio.data.sql.action.select;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.model.query.orderby.MOrderBy;
import com.jaspersoft.studio.data.sql.model.query.orderby.MOrderByColumn;
import com.jaspersoft.studio.data.sql.model.query.orderby.MOrderByExpression;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectColumn;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectExpression;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;

public class CreateOrderByFromColumn extends AAction {

	public CreateOrderByFromColumn(SQLQueryDesigner designer) {
		super("&Add To Order By", designer);
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
		return element instanceof MSelectColumn || element instanceof MSelectExpression;
	}

	@Override
	public void run() {
		ANode gbc = null;
		MOrderBy mgroupby = null;
		for (Object obj : selection) {
			if (obj instanceof MSelectColumn) {
				MSelectColumn msc = (MSelectColumn) obj;
				if (mgroupby == null)
					mgroupby = getOrderBy(mgroupby, msc);
				gbc = new MOrderByColumn(mgroupby, msc);
			} else if (obj instanceof MSelectExpression) {
				MSelectExpression msc = (MSelectExpression) obj;
				if (mgroupby == null)
					mgroupby = getOrderBy(mgroupby, msc);
				gbc = new MOrderByExpression(mgroupby, msc);
			}
		}
		selectInTree(gbc);
	}

	protected MOrderBy getOrderBy(MOrderBy mgroupby, ANode msc) {
		for (INode n : msc.getRoot().getChildren())
			if (n instanceof MOrderBy) {
				mgroupby = (MOrderBy) n;
				break;
			}
		return mgroupby;
	}
}