/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.action.select;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;

import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.dialogs.EditSelectColumnDialog;
import com.jaspersoft.studio.data.sql.dialogs.EditSelectExpressionDialog;
import com.jaspersoft.studio.data.sql.dialogs.EditSelectSubQueryDialog;
import com.jaspersoft.studio.data.sql.messages.Messages;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectColumn;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectExpression;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectSubQuery;
import com.jaspersoft.studio.model.ANode;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

public class EditColumn extends AAction {

	public EditColumn(TreeViewer treeViewer) {
		super(Messages.EditColumn_0, treeViewer);
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		return selection != null && selection.length == 1 && selection[0] instanceof ANode
				&& isColumn((ANode) selection[0]);
	}

	protected boolean isColumn(ANode element) {
		if (element instanceof MSelectSubQuery)
			setText(Messages.EditColumn_1);
		else if (element instanceof MSelectSubQuery)
			setText(Messages.EditColumn_0);
		else if (element instanceof MSelectExpression)
			setText(Messages.EditColumn_2);
		else if (element instanceof MSelectColumn)
			setText(Messages.EditColumn_0);
		return element instanceof MSelectColumn || element instanceof MSelectExpression
				|| element instanceof MSelectSubQuery;
	}

	@Override
	public void run() {
		for (Object obj : selection) {
			if (obj instanceof MSelectColumn) {
				doRunColumn((MSelectColumn) obj);
				break;
			} else if (obj instanceof MSelectExpression) {
				doRunExpression((MSelectExpression) obj);
				break;
			} else if (obj instanceof MSelectSubQuery) {
				doRunSubQuery((MSelectSubQuery) obj);
				break;
			}
		}

	}

	protected void doRunSubQuery(MSelectSubQuery mcol) {
		EditSelectSubQueryDialog dialog = new EditSelectSubQueryDialog(treeViewer.getControl().getShell());
		dialog.setValue(mcol);
		if (dialog.open() == Window.OK) {
			mcol.setAlias(dialog.getAlias());
			mcol.setAliasKeyword(dialog.getAliasKeyword());
			selectInTree(mcol);
		}
	}

	protected void doRunExpression(MSelectExpression mcol) {
		EditSelectExpressionDialog dialog = new EditSelectExpressionDialog(treeViewer.getControl().getShell());
		dialog.setValue(mcol);
		if (dialog.open() == Window.OK) {
			mcol.setValue(dialog.getExpression());
			mcol.setAlias(dialog.getAlias());
			mcol.setAliasKeyword(dialog.getAliasKeyword());
			selectInTree(mcol);
		}
	}

	protected void doRunColumn(MSelectColumn mcol) {
		EditSelectColumnDialog dialog = new EditSelectColumnDialog(UIUtils.getShell());
		dialog.setValue(mcol);
		if (dialog.open() == Dialog.OK) {
			mcol.setAlias(dialog.getAlias());
			mcol.setAliasKeyword(dialog.getAliasKeyword());
			selectInTree(mcol);
		}
	}
}
