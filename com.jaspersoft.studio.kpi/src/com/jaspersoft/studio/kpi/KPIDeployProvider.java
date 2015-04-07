/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
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

/**
 * Provider for the RepositoryExplorer, contributed trough extension point.
 * It add some menus on the report units to allow the managing of the KPIs
 *
 */
public class KPIDeployProvider implements IRepositoryViewProvider {
	
	/**
	 * Actions on the report unit to handle a KPI
	 */
	private KPIDeployAction kpiDeployAction;
	
	/**
	 * Action on the server to update the KPI cache
	 */
	private KPIUpdateCacheAction kpiUpdateCacheAction;

	/**
	 * If necessary create the KPI and server actions
	 * 
	 * @param treeViewer the treeviewer of the repository explorer
	 */
	private void createActions(TreeViewer treeViewer) {
		if (kpiDeployAction == null)
			kpiDeployAction = new KPIDeployAction(treeViewer);
		
		if (kpiUpdateCacheAction == null)
			kpiUpdateCacheAction = new KPIUpdateCacheAction(treeViewer);
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
	public void doubleClick(TreeViewer treeViewer) {
		if (kpiDeployAction.isEnabled())
			kpiDeployAction.run();
	}

	@Override
	public Action[] getActions(TreeViewer treeViewer) {
		createActions(treeViewer);
		return new Action[] {  };
	}
	
	@Override
	public ANode getNode(ANode root) {
		return null;
	}
	
	@Override
	public void hookKeyEvent(TreeViewer treeViewer, KeyEvent event) {

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
