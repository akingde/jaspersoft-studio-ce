/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.action.select;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.TreeViewer;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.action.DeleteAction;
import com.jaspersoft.studio.data.sql.messages.Messages;
import com.jaspersoft.studio.data.sql.model.query.groupby.MGroupBy;
import com.jaspersoft.studio.data.sql.model.query.groupby.MGroupByColumn;
import com.jaspersoft.studio.data.sql.model.query.orderby.MOrderBy;
import com.jaspersoft.studio.data.sql.model.query.orderby.MOrderByColumn;
import com.jaspersoft.studio.data.sql.model.query.orderby.MOrderByExpression;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectColumn;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectExpression;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;

public class DeleteColumn extends DeleteAction<ANode> {

	public DeleteColumn(SQLQueryDesigner designer, TreeViewer treeViewer) {
		super(designer, treeViewer, Messages.DeleteColumn_2, ANode.class);
	}

	@Override
	protected boolean isGoodNode(ANode element) {
		if(element instanceof MSelectColumn)
			setText(Messages.DeleteColumn_0);
		else if(element instanceof MSelectExpression)
			setText(Messages.DeleteColumn_1);
		return element instanceof MSelectColumn || element instanceof MSelectExpression;
	}

	@Override
	protected List<ANode> doDeleteMore(ANode parent, ANode todel) {
		List<ANode> torm = new ArrayList<ANode>();
		for (INode n : parent.getRoot().getChildren()) {
			if (n instanceof MGroupBy) {
				for (INode gb : n.getChildren()) {
					MGroupByColumn gbc = (MGroupByColumn) gb;
					if (gbc.getMSelectColumn() != null && gbc.getMSelectColumn().equals(todel))
						gbc.setMSelectColumn(null);
				}
			} else if (n instanceof MOrderBy) {
				for (INode gb : n.getChildren()) {
					if (gb instanceof MOrderByColumn) {
						MOrderByColumn gbc = (MOrderByColumn) gb;
						if (gbc.getMSelectColumn() != null && gbc.getMSelectColumn().equals(todel))
							gbc.setMSelectColumn(null);
					} else if (gb instanceof MOrderByExpression) {
						MOrderByExpression gbc = (MOrderByExpression) gb;
						if (gbc.getMSelectionExpression() != null && gbc.getMSelectionExpression().equals(todel))
							gbc.setMSelectionExpression(null);
					}
				}
			}
		}
		return torm;
	}
}
