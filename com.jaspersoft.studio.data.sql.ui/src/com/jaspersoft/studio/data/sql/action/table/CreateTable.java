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

import java.util.Collection;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.dialogs.TablesDialog;
import com.jaspersoft.studio.data.sql.model.metadata.MSqlTable;
import com.jaspersoft.studio.data.sql.model.query.from.MFrom;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;

public class CreateTable extends AAction {
	private SQLQueryDesigner designer;

	public CreateTable(SQLQueryDesigner designer, TreeViewer treeViewer) {
		super("&Add Table", treeViewer);
		this.designer = designer;
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		return selection == null || (selection != null && selection.length == 1 && isInFrom(selection[0]));
	}

	public static boolean isInFrom(Object element) {
		return element instanceof MFrom || element instanceof MFromTable;
	}

	@Override
	public void run() {
		TablesDialog dialog = new TablesDialog(Display.getDefault().getActiveShell());
		dialog.setRoot(designer.getDbMetadata().getRoot());
		if (dialog.open() == Window.OK)
			run(dialog.getTable());
	}

	public void run(Collection<MSqlTable> nodes) {
		Object sel = selection[0];
		for (MSqlTable t : nodes) {
			if (sel instanceof MFrom)
				sel = run(t, (MFrom) sel, 0);
			else if (sel instanceof MFromTable) {
				sel = run(t, (MFromTable) sel);
			}
		}
		selectInTree(sel);
	}

	protected MFromTable run(MSqlTable node, MFromTable mtable) {
		while (mtable.getParent() instanceof MFromTable)
			mtable = (MFromTable) mtable.getParent();

		MFrom mfrom = (MFrom) mtable.getParent();
		return run(node, mfrom, mfrom.getChildren().indexOf(mtable) + 1);
	}

	public MFromTable run(MSqlTable node, MFrom mfrom, int index) {
		return new MFromTable(mfrom, node, index);
	}
}
