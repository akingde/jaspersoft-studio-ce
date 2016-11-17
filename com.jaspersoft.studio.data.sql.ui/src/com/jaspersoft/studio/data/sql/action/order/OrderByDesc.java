/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.action.order;

import org.eclipse.jface.viewers.TreeViewer;

import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.messages.Messages;
import com.jaspersoft.studio.data.sql.model.query.orderby.AMOrderByMember;
import com.jaspersoft.studio.model.ANode;

public class OrderByDesc extends AAction {

	public OrderByDesc(TreeViewer treeViewer) {
		super(Messages.OrderByDesc_0, treeViewer);
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		return selection != null && selection.length == 1 && selection[0] instanceof ANode && isColumn((ANode) selection[0]);
	}

	protected boolean isColumn(ANode element) {
		boolean b = element instanceof AMOrderByMember;
		if (b)
			setMenuText((AMOrderByMember<?>) element);
		return b;
	}

	protected void setMenuText(AMOrderByMember<?> msel) {
		if (msel.isDesc())
			setText(Messages.OrderByDesc_1);
		else
			setText(Messages.OrderByDesc_2);
	}

	@Override
	public void run() {
		for (Object obj : selection) {
			if (obj instanceof AMOrderByMember) {
				AMOrderByMember<?> msel = (AMOrderByMember<?>) obj;
				msel.setDesc(!msel.isDesc());
				setMenuText(msel);
				selectInTree(obj);
				break;
			}
		}
	}
}
