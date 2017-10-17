/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.action.select;

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
import com.jaspersoft.studio.data.sql.model.query.select.MSelect;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectColumn;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectSubQuery;
import com.jaspersoft.studio.data.sql.ui.gef.command.CreateColumnCommand;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;

public class CreateColumn extends AAction {

	private CreateTable ct;
	private SQLQueryDesigner designer;

	public CreateColumn(SQLQueryDesigner designer, TreeViewer treeViewer) {
		super(Messages.CreateColumn_0, treeViewer);
		this.designer = designer;
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		return selection != null && selection.length == 1
				&& isInSelect(selection[0]);
	}

	private boolean checkTables = true;

	public void setCheckTables(boolean checkTables) {
		this.checkTables = checkTables;
	}

	public boolean isInSelect(Object element) {
		if (element instanceof MSelectSubQuery)
			return false;
		boolean b = element instanceof MSelect
				|| (element instanceof ANode && ((ANode) element).getParent() instanceof MSelect);
		if (b && checkTables) {
			MFrom mfrom = Util.getKeyword((ANode) ((ANode) element).getRoot(),
					MFrom.class);
			if (mfrom != null)
				return !Misc.isNullOrEmpty(mfrom.getChildren());
			return false;
		}
		return b;
	}

	@Override
	public void run() {
		FromTableColumnsDialog dialog = new FromTableColumnsDialog(
				UIUtils.getShell());
		dialog.setSelection((ANode) selection[0]);
		if (dialog.open() == Window.OK)
			run(dialog.getColumns());
	}

	public void run(Map<MSQLColumn, MFromTable> cols) {
		Object sel = selection[0];
		for (MSQLColumn t : cols.keySet()) {
			MFromTable mftable = cols.get(t);
			if (sel instanceof MSelect)
				sel = run(t, mftable, (MSelect) sel, 0);
			else if (sel instanceof ANode
					&& ((ANode) sel).getParent() instanceof MSelect) {
				MSelect msel = (MSelect) ((ANode) sel).getParent();
				int index = msel.getChildren().indexOf(sel) + 1;
				sel = run(t, mftable, msel, index);
			}
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
						tbls = Util.getFromTables((ANode) sel);
						break;
					}
				}
			}

			if (sel instanceof MSelect)
				sel = run(t, mftable, (MSelect) sel, 0);
			else if (sel instanceof MSelectColumn)
				sel = run(t, mftable, (MSelectColumn) sel);
		}
		selectInTree(sel);
	}

	public MSelectColumn run(MSQLColumn sCol, MFromTable mfTable) {
		MSelect mSelect = Util.getKeyword(mfTable, MSelect.class);
		MSelectColumn msCol = run(sCol, mfTable, mSelect, -1);
		selectInTree(msCol);
		return msCol;
	}

	protected MSelectColumn run(MSQLColumn sCol, MFromTable mfTable,
			MSelectColumn mSelCol) {
		MSelect mSelect = Util.getKeyword(mfTable, MSelect.class);
		return run(sCol, mfTable, mSelect,
				mSelect.getChildren().indexOf(mSelCol) + 1);
	}

	public MSelectColumn run(MSQLColumn node, MFromTable mfTable,
			MSelect select, int index) {
		CreateColumnCommand c = new CreateColumnCommand(node, select, index,
				mfTable);
		designer.getDiagram().getViewer().getEditDomain().getCommandStack()
				.execute(c);
		return c.getResultColumn();
	}

}
