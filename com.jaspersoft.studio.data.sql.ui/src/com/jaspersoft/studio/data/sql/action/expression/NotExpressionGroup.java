/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.action.expression;

import org.eclipse.jface.viewers.TreeViewer;

import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.messages.Messages;
import com.jaspersoft.studio.data.sql.model.query.expression.MExpressionGroup;
import com.jaspersoft.studio.model.ANode;

public class NotExpressionGroup extends AAction {

	private static final String CHANGE_TO = Messages.NotExpressionGroup_0;

	public NotExpressionGroup(TreeViewer treeViewer) {
		super(CHANGE_TO, treeViewer);
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		return selection != null && selection.length == 1
				&& selection[0] instanceof ANode
				&& isColumn((ANode) selection[0]);
	}

	protected boolean isColumn(ANode element) {
		boolean b = element instanceof MExpressionGroup;
		if (b)
			setMenuText((MExpressionGroup) element);
		return b;
	}

	protected void setMenuText(MExpressionGroup msel) {
		if (msel.isNot())
			setText(Messages.NotExpressionGroup_1);
		else
			setText(Messages.NotExpressionGroup_2);
	}

	@Override
	public void run() {
		for (Object obj : selection) {
			if (obj instanceof MExpressionGroup) {
				MExpressionGroup msel = (MExpressionGroup) obj;
				msel.setNot(!msel.isNot());
				setMenuText(msel);
				selectInTree(obj);
				break;
			}
		}
	}
}
