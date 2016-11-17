/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.action.select;

import org.eclipse.jface.viewers.TreeViewer;

import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.messages.Messages;
import com.jaspersoft.studio.data.sql.model.query.AMKeyword;
import com.jaspersoft.studio.data.sql.model.query.select.MSelect;
import com.jaspersoft.studio.model.ANode;

public class SelectDistinct extends AAction {

	private static final String CHANGE_TO = Messages.SelectDistinct_0;

	public SelectDistinct(TreeViewer treeViewer) {
		super(CHANGE_TO + AMKeyword.SELECT_DISTINCT_KEYWORD, treeViewer);
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		return selection != null && selection.length == 1 && selection[0] instanceof ANode && isColumn((ANode) selection[0]);
	}

	protected boolean isColumn(ANode element) {
		boolean b = element instanceof MSelect;
		if (b)
			setMenuText((MSelect) element);
		return b;
	}

	protected void setMenuText(MSelect msel) {
		if (msel.getValue().equals(AMKeyword.SELECT_KEYWORD))
			setText(CHANGE_TO + AMKeyword.SELECT_DISTINCT_KEYWORD);
		else
			setText(CHANGE_TO + AMKeyword.SELECT_KEYWORD);
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
