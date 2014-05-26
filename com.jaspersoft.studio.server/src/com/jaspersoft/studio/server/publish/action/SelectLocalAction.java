package com.jaspersoft.studio.server.publish.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TableViewer;

import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.publish.ResourcePublishMethod;

public class SelectLocalAction extends Action {
	private MResource mres;
	private TableViewer tableViewer;

	public SelectLocalAction(TableViewer tableViewer) {
		super();
		setText("Use Local Resource");
		this.tableViewer = tableViewer;
	}

	public boolean calculateEnabled(MResource mres) {
		this.mres = mres;
		return mres.getPublishOptions().getPublishMethod() != ResourcePublishMethod.LOCAL;
	}

	@Override
	public void run() {
		mres.getPublishOptions().setPublishMethod(ResourcePublishMethod.LOCAL);
		tableViewer.refresh();
	}
}
