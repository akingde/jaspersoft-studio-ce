package com.jaspersoft.studio.repository.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.internal.WorkbenchMessages;

import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.datasource.AMDatasource;
import com.jaspersoft.studio.model.datasource.IDatasource;
import com.jaspersoft.studio.model.datasource.MDatasources;
import com.jaspersoft.studio.model.datasource.jdbc.MJDBCDataSource;

public class DeleteDataSourceAction extends Action {
	private TreeViewer treeViewer;

	public DeleteDataSourceAction(TreeViewer treeViewer) {
		super();
		this.treeViewer = treeViewer;
		setText(WorkbenchMessages.Workbench_delete);
		setDescription(WorkbenchMessages.Workbench_deleteToolTip);
		setToolTipText(WorkbenchMessages.Workbench_deleteToolTip);
	}

	@Override
	public boolean isEnabled() {
		Object firstElement = ((TreeSelection) treeViewer.getSelection()).getFirstElement();
		return firstElement != null && (firstElement instanceof AMDatasource);
	}

	@Override
	public void run() {

		TreeSelection s = (TreeSelection) treeViewer.getSelection();
		TreePath[] p = s.getPaths();
		for (int i = 0; i < p.length; i++) {
			Object obj = p[i].getLastSegment();
			if (obj instanceof AMDatasource) {
				AMDatasource m = (AMDatasource) obj;
				INode root = m.getRoot();
				m.setParent(null, -1);

				treeViewer.refresh(true);
			}

		}
	}
}
