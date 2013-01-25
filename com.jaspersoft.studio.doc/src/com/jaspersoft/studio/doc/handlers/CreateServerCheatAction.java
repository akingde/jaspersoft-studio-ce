package com.jaspersoft.studio.doc.handlers;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreeViewer;

import com.jaspersoft.studio.server.action.server.CreateServerAction;

public class CreateServerCheatAction extends Action {
	
	@Override
	public void run() {
		TreeViewer treeViewer = HandlersUtil.getRepositoryView().getTreeViewer();
		CreateServerAction action = new CreateServerAction(treeViewer);
		action.run();
	}
}
