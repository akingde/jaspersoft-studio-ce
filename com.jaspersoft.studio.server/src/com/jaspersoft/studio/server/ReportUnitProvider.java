/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.server;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.TreeExpansionEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.KeyEvent;

import com.jaspersoft.studio.editor.report.UnitTransfer;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.repository.IRepositoryViewProvider;
import com.jaspersoft.studio.server.drag.UnitDragSourceListener;

/**
 * Extension for the RepositoryView, it only add to it a drag support for 
 * the report unit elements
 * 
 * 
 * @author Orlandin Marco
 */
public class ReportUnitProvider implements IRepositoryViewProvider {

	@Override
	public Action[] getActions(TreeViewer treeViewer) {

		return new Action[0];
	}

	@Override
	public ANode getNode(ANode root) {
		return null;
	}

	@Override
	public List<IAction> fillContextMenu(TreeViewer treeViewer, ANode node) {
		return new ArrayList<IAction>();
	}

	@Override
	public void hookKeyEvent(TreeViewer treeViewer, KeyEvent event) {}

	@Override
	public void doubleClick(TreeViewer treeViewer) {}

	@Override
	public void handleTreeEvent(TreeExpansionEvent event) {}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener pcl) {}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener pcl) {}

	/**
	 * Add a drag listener for the report unit elements
	 */
	@Override
	public void addDragListener(TreeViewer treeViewer) {
		Transfer[] types = new Transfer[] { UnitTransfer.getInstance()};
		treeViewer.addDragSupport(DND.DROP_MOVE, types, new UnitDragSourceListener(treeViewer));
	}

	@Override
	public void addDropListener(TreeViewer treeViewer) {}

}
