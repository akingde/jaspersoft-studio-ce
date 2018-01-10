/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.action.groupby;

import org.eclipse.jface.viewers.TreeViewer;

import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.action.AMultiSelectionAction;
import com.jaspersoft.studio.data.sql.messages.Messages;
import com.jaspersoft.studio.data.sql.model.query.groupby.MGroupBy;
import com.jaspersoft.studio.data.sql.model.query.groupby.MGroupByColumn;
import com.jaspersoft.studio.data.sql.model.query.groupby.MGroupByExpression;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectColumn;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectExpression;
import com.jaspersoft.studio.data.sql.ui.gef.parts.ColumnEditPart;
import com.jaspersoft.studio.model.ANode;

public class CreateGroupByFromColumn extends AMultiSelectionAction {

	public CreateGroupByFromColumn(TreeViewer treeViewer) {
		super(Messages.CreateGroupByFromColumn_0, treeViewer);
	}

	protected boolean isGoodNode(ANode element) {
		return element instanceof MSelectColumn;
//				|| element instanceof MSelectExpression;
	}

	@Override
	public void run() {
		ANode gbc = null;
		MGroupBy mgroupby = null;
		for (Object obj : selection) {
			obj = convertObject(obj);
			if (obj instanceof MSelectColumn) {
				MSelectColumn msc = (MSelectColumn) obj;
				if (mgroupby == null)
					mgroupby = Util.getKeyword(msc, MGroupBy.class);
				gbc = new MGroupByColumn(mgroupby, msc);
			} else if (obj instanceof MSelectExpression) {
				MSelectExpression msc = (MSelectExpression) obj;
				if (mgroupby == null)
					mgroupby = Util.getKeyword(msc, MGroupBy.class);
				gbc = new MGroupByExpression(mgroupby, msc);
			}
		}
		selectInTree(gbc);
	}

	protected ANode convertObject(Object obj) {
		if (obj instanceof ColumnEditPart)
			return ((ColumnEditPart) obj).getmSelectColumn();
		return super.convertObject(obj);
	}
}
