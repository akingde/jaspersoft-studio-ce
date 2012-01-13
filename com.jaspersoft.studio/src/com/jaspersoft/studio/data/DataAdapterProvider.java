/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.data;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.TreeExpansionEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;

import com.jaspersoft.studio.data.actions.CreateDataAdapterAction;
import com.jaspersoft.studio.data.actions.DeleteDataAdapterAction;
import com.jaspersoft.studio.data.actions.DuplicateDataAdapterAction;
import com.jaspersoft.studio.data.actions.EditDataAdapterAction;
import com.jaspersoft.studio.data.actions.ExportDataAdapterAction;
import com.jaspersoft.studio.data.actions.ImportDataAdapterAction;
import com.jaspersoft.studio.data.storage.ADataAdapterStorage;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.repository.IRepositoryViewProvider;
import com.jaspersoft.studio.repository.actions.Separator;

public class DataAdapterProvider implements IRepositoryViewProvider {
	private CreateDataAdapterAction createDataAdapterItemAction;
	private EditDataAdapterAction editDataAdapterItemAction;
	private DeleteDataAdapterAction deleteDataAdapterItemAction;
	private DuplicateDataAdapterAction duplicateDataAdapterItemAction;
	private ExportDataAdapterAction exportDataAdapterItemAction;
	private ImportDataAdapterAction importDataAdapterItemAction;

	public Action[] getActions(TreeViewer treeViewer) {
		createActions(treeViewer);
		return new Action[] { createDataAdapterItemAction };
	}

	private void createActions(TreeViewer treeViewer) {
		if (createDataAdapterItemAction == null)
			createDataAdapterItemAction = new CreateDataAdapterAction(treeViewer);
		if (editDataAdapterItemAction == null)
			editDataAdapterItemAction = new EditDataAdapterAction(treeViewer);
		if (deleteDataAdapterItemAction == null)
			deleteDataAdapterItemAction = new DeleteDataAdapterAction(treeViewer);
		if (duplicateDataAdapterItemAction == null)
			duplicateDataAdapterItemAction = new DuplicateDataAdapterAction(treeViewer);

		if (exportDataAdapterItemAction == null)
			exportDataAdapterItemAction = new ExportDataAdapterAction(treeViewer);
		if (importDataAdapterItemAction == null)
			importDataAdapterItemAction = new ImportDataAdapterAction(treeViewer);
	}

	public List<IAction> fillContextMenu(TreeViewer treeViewer, ANode node) {
		createActions(treeViewer);
		List<IAction> lst = new ArrayList<IAction>();
		if (node instanceof MDataAdapters) {
			// data adapters actions
			if (createDataAdapterItemAction.isEnabled())
				lst.add(createDataAdapterItemAction);
		} else if (node instanceof MDataAdapter) {
			if (editDataAdapterItemAction.isEnabled())
				lst.add(editDataAdapterItemAction);
			if (duplicateDataAdapterItemAction.isEnabled())
				lst.add(duplicateDataAdapterItemAction);

			if (deleteDataAdapterItemAction.isEnabled())
				lst.add(deleteDataAdapterItemAction);

			lst.add(new Separator());

			if (exportDataAdapterItemAction.isEnabled())
				lst.add(exportDataAdapterItemAction);
		}
		if (importDataAdapterItemAction.isEnabled())
			lst.add(importDataAdapterItemAction);
		return lst;
	}

	public void hookKeyEvent(TreeViewer treeViewer, KeyEvent event) {
		if (event.character == SWT.DEL && event.stateMask == 0) {
			if (deleteDataAdapterItemAction.isEnabled())
				deleteDataAdapterItemAction.run();
		}
	}

	public void doubleClick(TreeViewer treeViewer) {
		editDataAdapterItemAction.run();
	}

	private List<ADataAdapterStorage> storages = new ArrayList<ADataAdapterStorage>();

	public ANode getNode(ANode root) {
		ADataAdapterStorage s = DataAdapterManager.getPreferencesStorage();
		storages.add(s);
		return new MDataAdapters(root, s);
	}

	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		for (ADataAdapterStorage ds : storages)
			ds.addPropertyChangeListener(pcl);
	}

	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		for (ADataAdapterStorage ds : storages)
			ds.removePropertyChangeListener(pcl);
	}

	public void handleTreeEvent(TreeExpansionEvent event) {

	}

}
