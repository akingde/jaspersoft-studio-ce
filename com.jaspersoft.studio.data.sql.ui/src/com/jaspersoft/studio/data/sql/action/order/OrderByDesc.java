package com.jaspersoft.studio.data.sql.action.order;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.model.query.orderby.AMOrderByMember;
import com.jaspersoft.studio.model.ANode;

public class OrderByDesc extends AAction {

	public OrderByDesc(SQLQueryDesigner designer) {
		super("Set Ascending", designer);
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		return selection != null && selection.length == 1 && selection[0] instanceof ANode && isColumn((ANode) selection[0]);
	}

	protected boolean isColumn(ANode element) {
		boolean b = element instanceof AMOrderByMember;
		if (b)
			setMenuText((AMOrderByMember<?>) element);
		return b;
	}

	protected void setMenuText(AMOrderByMember<?> msel) {
		if (msel.isDesc())
			setText("Set Ascending");
		else
			setText("Set Descending");
	}

	@Override
	public void run() {
		for (Object obj : selection) {
			if (obj instanceof AMOrderByMember) {
				AMOrderByMember<?> msel = (AMOrderByMember<?>) obj;
				msel.setDesc(!msel.isDesc());
				setMenuText(msel);
				selectInTree(obj);
				break;
			}
		}
	}
}