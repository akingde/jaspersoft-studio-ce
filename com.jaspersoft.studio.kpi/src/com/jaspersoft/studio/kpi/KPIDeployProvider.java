package com.jaspersoft.studio.kpi;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.util.TransferDragSourceListener;
import org.eclipse.jface.util.TransferDropTargetListener;
import org.eclipse.jface.viewers.TreeExpansionEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.events.KeyEvent;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.repository.IRepositoryViewProvider;
import com.jaspersoft.studio.repository.actions.Separator;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.model.server.MServerProfile;

public class KPIDeployProvider implements IRepositoryViewProvider {
	
	private KPIDeployAction kpiDeployAction;
	private KPIUpdateCacheAction kpiUpdateCacheAction;

	@Override
	public Action[] getActions(TreeViewer treeViewer) {
		createActions(treeViewer);
		return new Action[] {  };
	}

	private void createActions(TreeViewer treeViewer) {
		if (kpiDeployAction == null)
			kpiDeployAction = new KPIDeployAction(treeViewer);
		
		if (kpiUpdateCacheAction == null)
			kpiUpdateCacheAction = new KPIUpdateCacheAction(treeViewer);
	}

	@Override
	public ANode getNode(ANode root) {
		return null;
	}

	@Override
	public List<IAction> fillContextMenu(TreeViewer treeViewer, ANode node) {
		
		
		createActions(treeViewer);
		
		List<IAction> lst = new ArrayList<IAction>();

		if (node instanceof MReportUnit) {
			if (kpiDeployAction.isEnabled()) {
				lst.add(new Separator());
				lst.add(kpiDeployAction);
				lst.add(new Separator());
			}
		}
		else if (node instanceof MServerProfile)
		{
			if (kpiUpdateCacheAction.isEnabled()) {
				lst.add(new Separator());
				lst.add(kpiUpdateCacheAction);
			}
		}
		return lst;
	}

	@Override
	public void hookKeyEvent(TreeViewer treeViewer, KeyEvent event) {

	}

	@Override
	public void doubleClick(TreeViewer treeViewer) {
		if (kpiDeployAction.isEnabled())
			kpiDeployAction.run();
	}

	@Override
	public List<TransferDragSourceListener> getTransferDragSourceListeners(TreeViewer treeViewer) {
		return null;
	}

	@Override
	public List<TransferDropTargetListener> getTransferDropTargetListeners(TreeViewer treeViewer) {
		return null;
	}

	@Override
	public void handleTreeEvent(TreeExpansionEvent event) {

	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener pcl) {

	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener pcl) {

	}

}
