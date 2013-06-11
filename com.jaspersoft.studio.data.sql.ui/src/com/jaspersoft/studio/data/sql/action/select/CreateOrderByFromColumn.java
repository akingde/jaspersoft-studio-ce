package com.jaspersoft.studio.data.sql.action.select;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.action.AMultiSelectionAction;
import com.jaspersoft.studio.data.sql.model.query.orderby.MOrderBy;
import com.jaspersoft.studio.data.sql.model.query.orderby.MOrderByColumn;
import com.jaspersoft.studio.data.sql.model.query.orderby.MOrderByExpression;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectColumn;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectExpression;
import com.jaspersoft.studio.model.ANode;

public class CreateOrderByFromColumn extends AMultiSelectionAction {

	public CreateOrderByFromColumn(SQLQueryDesigner designer) {
		super("&Add To Order By", designer);
	}

	protected boolean isGoodNode(ANode element) {
		return element instanceof MSelectColumn || element instanceof MSelectExpression;
	}

	@Override
	public void run() {
		ANode gbc = null;
		MOrderBy morderby = null;
		for (Object obj : selection) {
			if (obj instanceof MSelectColumn) {
				MSelectColumn msc = (MSelectColumn) obj;
				if (morderby == null)
					morderby = Util.getKeyword(msc, MOrderBy.class);
				gbc = new MOrderByColumn(morderby, msc);
			} else if (obj instanceof MSelectExpression) {
				MSelectExpression msc = (MSelectExpression) obj;
				if (morderby == null)
					morderby = Util.getKeyword(msc, MOrderBy.class);
				gbc = new MOrderByExpression(morderby, msc);
			}
		}
		selectInTree(gbc);
	}
}