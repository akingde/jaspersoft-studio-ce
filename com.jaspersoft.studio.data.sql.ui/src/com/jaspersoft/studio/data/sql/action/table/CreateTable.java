/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.action.table;

import java.util.Collection;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.dialogs.TablesDialog;
import com.jaspersoft.studio.data.sql.messages.Messages;
import com.jaspersoft.studio.data.sql.model.metadata.MSqlTable;
import com.jaspersoft.studio.data.sql.model.query.from.MFrom;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.subquery.MQueryTable;
import com.jaspersoft.studio.data.sql.ui.gef.command.CreateTableCommand;

public class CreateTable extends AAction {
	private SQLQueryDesigner designer;

	public CreateTable(SQLQueryDesigner designer, TreeViewer treeViewer) {
		super(Messages.CreateTable_0, treeViewer);
		this.designer = designer;
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		return selection == null
				|| (selection != null && selection.length == 1 && isInFrom(selection[0]));
	}

	public static boolean isInFrom(Object element) {
		if (element instanceof MFromTable
				&& ((MFromTable) element).getValue() instanceof MQueryTable)
			return false;
		return element instanceof MFrom || element instanceof MFromTable;
	}

	@Override
	public void run() {
		TablesDialog dialog = new TablesDialog(treeViewer.getControl().getShell());
		dialog.setRoot(designer.getDbMetadata().getRoot());
		if (dialog.open() == Window.OK)
			run(dialog.getTable());
	}

	public void run(Collection<MSqlTable> nodes) {
		Object sel = selection[0];
		for (MSqlTable t : nodes) {
			if (sel instanceof MFrom)
				sel = run(t, (MFrom) sel, 0);
			else if (sel instanceof MFromTable)
				sel = run(t, (MFromTable) sel);
		}
		selectInTree(sel);
	}

	protected MFromTable run(MSqlTable node, MFromTable mtable) {
		while (mtable.getParent() instanceof MFromTable)
			mtable = (MFromTable) mtable.getParent();

		MFrom mfrom = (MFrom) mtable.getParent();
		return run(node, mfrom, mfrom.getChildren().indexOf(mtable) + 1);
	}

	public MFromTable run(final MSqlTable node, MFrom mfrom, int index) {
		UIUtils.getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				designer.getDbMetadata().loadTable(
						Util.getTable(designer.getDbMetadata().getRoot(), node,
								designer));
			}
		});
		CreateTableCommand c = new CreateTableCommand(node, mfrom, index);
		designer.getDiagram().getViewer().getEditDomain().getCommandStack()
				.execute(c);
		return c.getResultTable();
	}
}
