/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.action.order;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.action.table.CreateTable;
import com.jaspersoft.studio.data.sql.dialogs.FromTableColumnsDialog;
import com.jaspersoft.studio.data.sql.messages.Messages;
import com.jaspersoft.studio.data.sql.model.metadata.MSQLColumn;
import com.jaspersoft.studio.data.sql.model.metadata.MSqlTable;
import com.jaspersoft.studio.data.sql.model.query.from.MFrom;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.orderby.MOrderBy;
import com.jaspersoft.studio.data.sql.model.query.orderby.MOrderByColumn;
import com.jaspersoft.studio.data.sql.model.query.orderby.MOrderByExpression;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;

import net.sf.jasperreports.eclipse.util.Misc;

public class CreateOrderByColumn extends AAction {
	private CreateTable ct;
	private SQLQueryDesigner designer;

	public CreateOrderByColumn(SQLQueryDesigner designer, TreeViewer treeViewer) {
		super(Messages.CreateOrderByColumn_0, treeViewer);
		this.designer = designer;
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		return selection != null && selection.length == 1 && isInSelect(selection[0]);
	}

	public static boolean isInSelect(Object element) {
		boolean b = element instanceof MOrderBy || element instanceof MOrderByColumn
				|| element instanceof MOrderByExpression;
		if (b) {
			MFrom mfrom = Util.getKeyword((ANode) ((ANode) element).getRoot(), MFrom.class);
			if (mfrom != null)
				return !Misc.isNullOrEmpty(mfrom.getChildren());
			return false;
		}
		return b;
	}

	@Override
	public void run() {
		FromTableColumnsDialog dialog = new FromTableColumnsDialog(treeViewer.getControl().getShell());
		dialog.setSelection((ANode) selection[0]);
		if (dialog.open() == Window.OK)
			run(dialog.getColumns());
	}

	public void run(Map<MSQLColumn, MFromTable> cols) {
		Object sel = selection[0];
		for (MSQLColumn t : cols.keySet()) {
			MFromTable mftable = cols.get(t);
			if (sel instanceof MOrderBy)
				sel = run(t, mftable, (MOrderBy) sel, 0);
			else if (sel instanceof MOrderByColumn)
				sel = run(t, mftable, (MOrderByColumn) sel);
			else if (sel instanceof MOrderByExpression)
				sel = run(t, mftable, (MOrderByExpression) sel);
		}
		selectInTree(sel);
	}

	public void run(Collection<MSQLColumn> nodes) {
		Object sel = selection[0];
		List<MFromTable> tbls = Util.getFromTables((ANode) sel);
		for (MSQLColumn t : nodes) {
			MSqlTable tbl = (MSqlTable) t.getParent();
			MFromTable mftable = null;
			for (MFromTable ft : tbls) {
				if (ft.getValue().equals(tbl)) {
					mftable = ft;
					break;
				}
			}
			if (mftable == null) {
				if (ct == null)
					ct = new CreateTable(designer, treeViewer);
				ANode r = Util.getQueryRoot((ANode) sel);
				for (INode n : r.getChildren()) {
					if (n instanceof MFrom) {
						mftable = ct.run(tbl, (MFrom) n, -1);
						break;
					}
				}
			}
			if (sel instanceof MOrderBy)
				sel = run(t, mftable, (MOrderBy) sel, 0);
			else if (sel instanceof MOrderByColumn) {
				sel = run(t, mftable, (MOrderByColumn) sel);
			}
		}
		selectInTree(sel);
	}

	protected MOrderByColumn run(MSQLColumn node, MFromTable mfTable, MOrderByColumn mtable) {
		MOrderBy mfrom = (MOrderBy) mtable.getParent();
		return run(node, mfTable, mfrom, mfrom.getChildren().indexOf(mtable) + 1);
	}

	protected MOrderByColumn run(MSQLColumn node, MFromTable mfTable, MOrderByExpression mtable) {
		MOrderBy mfrom = (MOrderBy) mtable.getParent();
		return run(node, mfTable, mfrom, mfrom.getChildren().indexOf(mtable) + 1);
	}

	public MOrderByColumn run(MSQLColumn node, MFromTable mfTable, MOrderBy select, int index) {
		return new MOrderByColumn(select, node, mfTable, index);
	}

}
