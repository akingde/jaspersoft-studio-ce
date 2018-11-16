/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.action.select;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;

import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.dialogs.EditSelectExpressionDialog;
import com.jaspersoft.studio.data.sql.messages.Messages;
import com.jaspersoft.studio.data.sql.model.query.select.MSelect;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectExpression;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectSubQuery;
import com.jaspersoft.studio.model.ANode;

public class CreateSelectExpression extends AAction {

	public CreateSelectExpression(TreeViewer treeViewer) {
		super(Messages.CreateSelectExpression_0, treeViewer);
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		return selection != null && selection.length == 1 && isInSelect(selection[0]);
	}

	public static boolean isInSelect(Object element) {
		if (element instanceof MSelectSubQuery)
			return false;
		return element instanceof MSelect
				|| (element instanceof ANode && ((ANode) element).getParent() instanceof MSelect);
	}

	@Override
	public void run() {
		EditSelectExpressionDialog dialog = new EditSelectExpressionDialog(treeViewer.getControl().getShell());
		MSelectExpression mexpr = new MSelectExpression(null, Messages.CreateSelectExpression_1);
		dialog.setValue(mexpr);
		if (dialog.open() == Window.OK) {
			Object sel = selection[0];
			MSelect mselect = null;
			int index = 0;
			if (sel instanceof MSelect)
				mselect = (MSelect) sel;
			else if (sel instanceof ANode && ((ANode) sel).getParent() instanceof MSelect) {
				mselect = (MSelect) ((ANode) sel).getParent();
				index = mselect.getChildren().indexOf(sel) + 1;
			}
			mexpr.setParent(mselect, index);
			mexpr.setValue(dialog.getExpression());
			mexpr.setAlias(dialog.getAlias());
			mexpr.setAliasKeyword(dialog.getAliasKeyword());
			selectInTree(mexpr);
		}
	}

}
