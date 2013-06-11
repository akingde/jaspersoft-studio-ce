package com.jaspersoft.studio.data.sql.action.select;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.action.AMultiSelectionAction;
import com.jaspersoft.studio.data.sql.action.expression.CreateExpression;
import com.jaspersoft.studio.data.sql.model.query.MWhere;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectColumn;
import com.jaspersoft.studio.model.ANode;

public class CreateWhereFromColumn extends AMultiSelectionAction {
	private CreateExpression ce;

	public CreateWhereFromColumn(SQLQueryDesigner designer) {
		super("Create &Where Condition", designer);
	}

	protected boolean isGoodNode(ANode element) {
		return element instanceof MSelectColumn;
	}

	@Override
	public void run() {
		MWhere mwhere = null;
		for (Object obj : selection) {
			if (obj instanceof MSelectColumn) {
				MSelectColumn msc = (MSelectColumn) obj;
				if (mwhere == null)
					mwhere = Util.getKeyword(msc, MWhere.class);

				if (ce == null)
					ce = new CreateExpression(designer);
				ce.run(mwhere, msc);
				break;
			}
		}
	}

}