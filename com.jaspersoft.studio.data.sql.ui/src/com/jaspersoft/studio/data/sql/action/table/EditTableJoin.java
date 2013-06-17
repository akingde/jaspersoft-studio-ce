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
package com.jaspersoft.studio.data.sql.action.table;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.dialogs.JoinFromTableDialog;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTableJoin;
import com.jaspersoft.studio.model.ANode;

public class EditTableJoin extends AAction {

	public EditTableJoin(SQLQueryDesigner designer) {
		super("&Edit Table Join", designer);
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		return selection != null && selection.length == 1 && selection[0] instanceof ANode && isColumn((ANode) selection[0]);
	}

	protected boolean isColumn(ANode element) {
		return element instanceof MFromTableJoin;
	}

	@Override
	public void run() {
		MFromTableJoin mcol = null;
		for (Object obj : selection) {
			if (obj instanceof MFromTableJoin) {
				mcol = (MFromTableJoin) obj;
				break;
			}
		}
		JoinFromTableDialog dialog = new JoinFromTableDialog(Display.getDefault().getActiveShell());
		dialog.setValue(mcol);
		if (dialog.open() == Dialog.OK) {
			MFromTable mtab = JoinTable.getFromTable(mcol, dialog);
			mcol.setParent(mtab, -1);
			mcol.setJoin(dialog.getJoin());
			selectInTree(mcol);
		}
	}
}
