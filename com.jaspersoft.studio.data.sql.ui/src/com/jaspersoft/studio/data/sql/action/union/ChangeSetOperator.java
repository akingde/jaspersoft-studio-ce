/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.action.union;

import org.eclipse.jface.viewers.TreeViewer;

import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.messages.Messages;
import com.jaspersoft.studio.data.sql.model.query.MUnion;

public class ChangeSetOperator extends AAction {

	private static final String CHANGE_TO = Messages.ChangeSetOperator_0;
	private String operator;

	public ChangeSetOperator(String operator, TreeViewer treeViewer) {
		super(CHANGE_TO + operator, treeViewer);
		this.operator = operator;
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		return selection != null && selection.length == 1 && selection[0] instanceof MUnion && !((MUnion) selection[0]).getValue().equals(operator);
	}

	@Override
	public void run() {
		for (Object obj : selection) {
			if (obj instanceof MUnion) {
				((MUnion) obj).setValue(operator);
				break;
			}
		}
		treeViewer.refresh(true);
	}
}
