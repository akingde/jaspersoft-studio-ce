/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.action.expression;

import org.eclipse.jface.viewers.TreeViewer;

import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.messages.Messages;
import com.jaspersoft.studio.data.sql.model.query.AMKeyword;
import com.jaspersoft.studio.data.sql.model.query.expression.MExpressionGroup;
import com.jaspersoft.studio.model.ANode;

public class ChangeOperator extends AAction {

	public ChangeOperator(TreeViewer treeViewer) {
		super(Messages.ChangeOperator_0 + AMKeyword.OR_OPERATOR, treeViewer);
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		return selection != null && selection.length == 1 && selection[0] instanceof ANode && isColumn((ANode) selection[0]);
	}

	protected boolean isColumn(ANode element) {
		boolean b = element instanceof MExpressionGroup && !element.isFirst();
		if (b)
			setMenuText((MExpressionGroup) element);
		return b;
	}

	protected void setMenuText(MExpressionGroup msel) {
		if (msel.getValue().equals(AMKeyword.AND_OPERATOR))
			setText(Messages.ChangeOperator_1 + AMKeyword.OR_OPERATOR);
		else
			setText(Messages.ChangeOperator_1 + AMKeyword.AND_OPERATOR);
	}

	@Override
	public void run() {
		for (Object obj : selection) {
			if (obj instanceof MExpressionGroup) {
				MExpressionGroup msel = (MExpressionGroup) obj;
				if (msel.getValue().equals(AMKeyword.AND_OPERATOR))
					msel.setValue(AMKeyword.OR_OPERATOR);
				else
					msel.setValue(AMKeyword.AND_OPERATOR);
				setMenuText(msel);
				selectInTree(obj);
				break;
			}
		}
	}
}
