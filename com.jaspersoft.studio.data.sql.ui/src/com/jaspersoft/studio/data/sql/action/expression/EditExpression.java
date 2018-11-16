/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.action.expression;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.TreeViewer;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.dialogs.EditExpressionDialog;
import com.jaspersoft.studio.data.sql.dialogs.EditExpressionXDialog;
import com.jaspersoft.studio.data.sql.dialogs.EditPNotExpressionDialog;
import com.jaspersoft.studio.data.sql.messages.Messages;
import com.jaspersoft.studio.data.sql.model.enums.Operator;
import com.jaspersoft.studio.data.sql.model.query.expression.AMExpression;
import com.jaspersoft.studio.data.sql.model.query.expression.MExpression;
import com.jaspersoft.studio.data.sql.model.query.expression.MExpressionPNot;
import com.jaspersoft.studio.data.sql.model.query.expression.MExpressionX;
import com.jaspersoft.studio.model.ANode;

public class EditExpression extends AAction {
	private SQLQueryDesigner designer;

	public EditExpression(SQLQueryDesigner designer, TreeViewer treeViewer) {
		super(Messages.EditExpression_0, treeViewer);
		this.designer = designer;
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		return selection != null && selection.length == 1 && selection[0] instanceof ANode
				&& isColumn((ANode) selection[0]);
	}

	protected boolean isColumn(ANode element) {
		return element instanceof AMExpression;
	}

	@Override
	public void run() {
		for (Object obj : selection) {
			if (obj instanceof MExpression) {
				MExpression mcol = (MExpression) obj;
				EditExpressionDialog dialog = new EditExpressionDialog(treeViewer.getControl().getShell(), designer);
				dialog.setValue(mcol);
				if (dialog.open() == Dialog.OK) {
					mcol.setOperator(Operator.getOperator((dialog.getOperator())));
					mcol.setPrevCond(dialog.getPrevcond());
					mcol.setOperands(dialog.getOperands());
					selectInTree(mcol);
				}
				break;
			} else if (obj instanceof MExpressionX) {
				MExpressionX mcol = (MExpressionX) obj;
				EditExpressionXDialog dialog = new EditExpressionXDialog(treeViewer.getControl().getShell(), designer);
				dialog.setValue(mcol);
				if (dialog.open() == Dialog.OK) {
					mcol.setFunction(dialog.getFunction());
					mcol.setPrevCond(dialog.getPrevcond());
					mcol.setOperands(dialog.getOperands());
					selectInTree(mcol);
				}
				break;
			} else if (obj instanceof MExpressionPNot) {
				MExpressionPNot mcol = (MExpressionPNot) obj;
				EditPNotExpressionDialog dialog = new EditPNotExpressionDialog(treeViewer.getControl().getShell());
				dialog.setValue(mcol);
				if (dialog.open() == Dialog.OK) {
					mcol.setValue(dialog.getValue());
					selectInTree(mcol);
				}
				break;
			}
		}

	}
}
