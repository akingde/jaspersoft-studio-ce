package com.jaspersoft.studio.data.sql.action.select;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.action.AMultiSelectionAction;
import com.jaspersoft.studio.data.sql.action.expression.CreateExpression;
import com.jaspersoft.studio.data.sql.model.query.MHaving;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectColumn;
import com.jaspersoft.studio.model.ANode;

public class CreateHavingFromColumn extends AMultiSelectionAction {
	private CreateExpression ce;

	public CreateHavingFromColumn(SQLQueryDesigner designer) {
		super("Create &Having Condition", designer);
	}

	protected boolean isGoodNode(ANode element) {
		return element instanceof MSelectColumn;
	}

	@Override
	public void run() {
		MHaving mhaving = null;
		for (Object obj : selection) {
			if (obj instanceof MSelectColumn) {
				MSelectColumn msc = (MSelectColumn) obj;
				if (mhaving == null)
					mhaving = Util.getKeyword(msc, MHaving.class);

				if (ce == null)
					ce = new CreateExpression(designer);
				ce.run(mhaving, msc);
				break;
			}
		}
	}

}