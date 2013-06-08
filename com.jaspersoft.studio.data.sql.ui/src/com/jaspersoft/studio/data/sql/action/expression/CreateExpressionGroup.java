package com.jaspersoft.studio.data.sql.action.expression;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.model.query.MExpression;
import com.jaspersoft.studio.data.sql.model.query.MExpressionGroup;
import com.jaspersoft.studio.data.sql.model.query.MFromTableJoin;
import com.jaspersoft.studio.data.sql.model.query.MHaving;
import com.jaspersoft.studio.data.sql.model.query.MWhere;
import com.jaspersoft.studio.model.ANode;

public class CreateExpressionGroup extends AAction {

	public CreateExpressionGroup(SQLQueryDesigner designer) {
		super("&Add Expression Group", designer);
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
		if (isInSelect(sel))
			selectInTree(new MExpressionGroup((ANode) sel));
	}

}