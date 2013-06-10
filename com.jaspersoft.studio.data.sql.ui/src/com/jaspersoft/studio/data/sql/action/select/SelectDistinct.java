package com.jaspersoft.studio.data.sql.action.select;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.model.query.AMKeyword;
import com.jaspersoft.studio.data.sql.model.query.select.MSelect;
import com.jaspersoft.studio.model.ANode;

public class SelectDistinct extends AAction {

	public SelectDistinct(SQLQueryDesigner designer) {
		super("Change to " + AMKeyword.SELECT_DISTINCT_KEYWORD, designer);
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		return selection != null && selection.length == 1 && isColumn((ANode) selection[0]);
	}

	protected boolean isColumn(ANode element) {
		boolean b = element instanceof MSelect;
		if (b)
			setMenuText((MSelect) element);
		return b;
	}

	protected void setMenuText(MSelect msel) {
		if (msel.getValue().equals(AMKeyword.SELECT_KEYWORD))
			setText("Change to" + AMKeyword.SELECT_DISTINCT_KEYWORD);
		else
			setText("Change to" + AMKeyword.SELECT_KEYWORD);
	}

	@Override
	public void run() {
		for (Object obj : selection) {
			if (obj instanceof MSelect) {
				MSelect msel = (MSelect) obj;
				if (msel.getValue().equals(AMKeyword.SELECT_KEYWORD))
					msel.setValue(AMKeyword.SELECT_DISTINCT_KEYWORD);
				else
					msel.setValue(AMKeyword.SELECT_KEYWORD);
				setMenuText(msel);
				selectInTree(obj);
				break;
			}
		}
	}
}