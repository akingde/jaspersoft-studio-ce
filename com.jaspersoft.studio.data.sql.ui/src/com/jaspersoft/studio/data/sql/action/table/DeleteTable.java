/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.action.table;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.TreeViewer;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.action.DeleteAction;
import com.jaspersoft.studio.data.sql.messages.Messages;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.groupby.MGroupBy;
import com.jaspersoft.studio.data.sql.model.query.groupby.MGroupByColumn;
import com.jaspersoft.studio.data.sql.model.query.orderby.MOrderBy;
import com.jaspersoft.studio.data.sql.model.query.orderby.MOrderByColumn;
import com.jaspersoft.studio.data.sql.model.query.select.MSelect;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectColumn;
import com.jaspersoft.studio.data.sql.model.query.subquery.MQueryTable;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;

public class DeleteTable extends DeleteAction<MFromTable> {

	public DeleteTable(SQLQueryDesigner designer, TreeViewer treeViewer) {
		super(designer, treeViewer, Messages.DeleteTable_0, MFromTable.class);
		setId(getClass().getCanonicalName());
	}

	@Override
	protected boolean isGoodNode(ANode element) {
		if (element instanceof MFromTable
				&& ((MFromTable) element).getValue() instanceof MQueryTable)
			return false;
		return element instanceof MFromTable;
	}

	@Override
	protected List<ANode> doDeleteMore(ANode parent, MFromTable todel) {
		if (parent.getRoot() == null)
			return null;
		List<ANode> toRemove = new ArrayList<ANode>();
		for (INode n : parent.getRoot().getChildren()) {
			if (n instanceof MSelect)
				for (INode gb : n.getChildren()) {
					if (gb instanceof MSelectColumn) {
						MSelectColumn gbc = (MSelectColumn) gb;
						if (gbc.getMFromTable() != null
								&& gbc.getMFromTable().equals(todel))
							toRemove.add(gbc);
					}
				}
			else if (n instanceof MGroupBy)
				for (INode gb : n.getChildren()) {
					MGroupByColumn gbc = (MGroupByColumn) gb;
					if (gbc.getMFromTable() != null
							&& gbc.getMFromTable().equals(todel))
						toRemove.add(gbc);
				}
			else if (n instanceof MOrderBy)
				for (INode gb : n.getChildren()) {
					if (gb instanceof MOrderByColumn) {
						MOrderByColumn gbc = (MOrderByColumn) gb;
						if (gbc.getMFromTable() != null
								&& gbc.getMFromTable().equals(todel))
							toRemove.add(gbc);
					}
				}
		}
		return toRemove;
	}
}
