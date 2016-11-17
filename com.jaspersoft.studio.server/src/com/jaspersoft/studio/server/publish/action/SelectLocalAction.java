/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.publish.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TableViewer;

import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.publish.ResourcePublishMethod;

public class SelectLocalAction extends Action {
	private AMResource mres;
	private TableViewer tableViewer;

	public SelectLocalAction(TableViewer tableViewer) {
		super();
		setText(Messages.SelectLocalAction_0);
		this.tableViewer = tableViewer;
	}

	public boolean calculateEnabled(AMResource mres) {
		this.mres = mres;
		return mres.getPublishOptions().getPublishMethod() != ResourcePublishMethod.LOCAL;
	}

	@Override
	public void run() {
		mres.getPublishOptions().setPublishMethod(ResourcePublishMethod.LOCAL);
		tableViewer.refresh();
	}
}
