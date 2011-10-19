package com.jaspersoft.studio.data;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreeViewer;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.repository.IRepositoryViewProvider;
import com.jaspersoft.studio.repository.actions.CreateDataAdapterAction;

public class DataAdapterProvider implements IRepositoryViewProvider {

	public Action[] getActions(TreeViewer treeViewer) {
		return new Action[] { new CreateDataAdapterAction(treeViewer) };
	}

	public ANode getNode(ANode root) {
		return new MDataAdapters(root);
	}

}
