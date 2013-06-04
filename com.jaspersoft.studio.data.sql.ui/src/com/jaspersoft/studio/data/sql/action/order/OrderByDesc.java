package com.jaspersoft.studio.data.sql.action.order;

import org.eclipse.xtext.ui.editor.model.IXtextDocument;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.model.query.MOrderByColumn;
import com.jaspersoft.studio.model.ANode;

public class OrderByDesc extends AAction {

	public OrderByDesc(IXtextDocument xtextDocument, SQLQueryDesigner designer) {
		super("Set Ascending", xtextDocument, designer);
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		return selection != null && selection.length == 1 && isColumn((ANode) selection[0]);
	}

	protected boolean isColumn(ANode element) {
		boolean b = element instanceof MOrderByColumn;
		if (b)
			setMenuText((MOrderByColumn) element);
		return b;
	}

	protected void setMenuText(MOrderByColumn msel) {
		if (msel.isDesc())
			setText("Set Ascending");
		else
			setText("Set Descending");
	}

	@Override
	public void run() {
		for (Object obj : selection) {
			if (obj instanceof MOrderByColumn) {
				MOrderByColumn msel = (MOrderByColumn) obj;
				msel.setDesc(!msel.isDesc());
				setMenuText(msel);
				selectInTree(obj);
				break;
			}
		}
	}
}