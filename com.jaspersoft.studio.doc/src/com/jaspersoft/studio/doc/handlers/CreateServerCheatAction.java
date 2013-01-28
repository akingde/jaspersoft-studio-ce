package com.jaspersoft.studio.doc.handlers;

import org.eclipse.jface.viewers.TreeViewer;

import com.jaspersoft.studio.server.action.server.CreateServerAction;

public class CreateServerCheatAction extends AsyncAction {
	
	@Override
	public void doAction() {
		TreeViewer treeViewer = HandlersUtil.getRepositoryView().getTreeViewer();
		CreateServerAction action = new CreateServerAction(treeViewer);
		action.run();
	}
}
