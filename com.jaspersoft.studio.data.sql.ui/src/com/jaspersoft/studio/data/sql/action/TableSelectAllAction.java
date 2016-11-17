/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.action;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.gef.commands.CompoundCommand;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.model.metadata.MSQLColumn;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.select.MSelect;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectColumn;
import com.jaspersoft.studio.data.sql.model.query.subquery.MQueryTable;
import com.jaspersoft.studio.data.sql.ui.gef.command.CreateColumnCommand;
import com.jaspersoft.studio.model.INode;

public class TableSelectAllAction extends AAction {
	private SQLQueryDesigner designer;

	public TableSelectAllAction(SQLQueryDesigner designer, String title) {
		super(title, designer.getOutline().getTreeViewer());
		this.designer = designer;
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		if (selection == null)
			return false;
		return selection.length == 1 && isInFrom(selection[0]);
	}

	public static boolean isInFrom(Object element) {
		if (element instanceof MFromTable && ((MFromTable) element).getValue() instanceof MQueryTable)
			return false;
		return element instanceof MFromTable;
	}

	@Override
	public void run() {
		CompoundCommand c = new CompoundCommand();
		MFromTable tbl = (MFromTable) selection[0];

		MSelect sel = Util.getKeyword(tbl, MSelect.class);
		Set<MSelectColumn> existing = new HashSet<MSelectColumn>();
		for (INode n : sel.getChildren()) {
			if (n instanceof MSelectColumn && ((MSelectColumn) n).getMFromTable() == tbl)
				existing.add((MSelectColumn) n);
		}
		for (INode n : tbl.getValue().getChildren()) {
			if (n instanceof MSQLColumn) {
				boolean exists = false;
				for (MSelectColumn sc : existing) {
					if (sc.getValue().equals(n)) {
						exists = true;
						break;
					}
				}
				if (!exists)
					c.add(new CreateColumnCommand((MSQLColumn) n, sel, -1, tbl));
			}
		}
		if (!c.isEmpty()) {
			designer.getDiagram().getViewer().getEditDomain().getCommandStack().execute(c);
			tbl.firePropertyChange("wrongvalue", true, false);
		}
	}

}
