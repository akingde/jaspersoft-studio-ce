/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.action.expression;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.dialogs.EditExpressionDialog;
import com.jaspersoft.studio.data.sql.dialogs.EditExpressionXDialog;
import com.jaspersoft.studio.data.sql.model.enums.Operator;
import com.jaspersoft.studio.data.sql.model.query.expression.AMExpression;
import com.jaspersoft.studio.data.sql.model.query.expression.MExpression;
import com.jaspersoft.studio.data.sql.model.query.expression.MExpressionX;
import com.jaspersoft.studio.model.ANode;

public class EditExpression extends AAction {

	public EditExpression(TreeViewer treeViewer) {
		super("&Edit Expression", treeViewer);
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		return selection != null && selection.length == 1 && selection[0] instanceof ANode && isColumn((ANode) selection[0]);
	}

	protected boolean isColumn(ANode element) {
		return element instanceof AMExpression;
	}

	@Override
	public void run() {
		for (Object obj : selection) {
			if (obj instanceof MExpression) {
				MExpression mcol = (MExpression) obj;
				EditExpressionDialog dialog = new EditExpressionDialog(Display.getDefault().getActiveShell());
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
				EditExpressionXDialog dialog = new EditExpressionXDialog(Display.getDefault().getActiveShell());
				dialog.setValue(mcol);
				if (dialog.open() == Dialog.OK) {
					mcol.setFunction(dialog.getFunction());
					mcol.setPrevCond(dialog.getPrevcond());
					mcol.setOperands(dialog.getOperands());
					selectInTree(mcol);
				}
				break;
			}
		}

	}
}
