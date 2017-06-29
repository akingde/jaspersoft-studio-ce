/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.util.TransferDragSourceListener;
import org.eclipse.jface.util.TransferDropTargetListener;
import org.eclipse.jface.util.Util;
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
import com.jaspersoft.studio.dnd.DataAdapterDragSourceListener;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
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
			lst.add(new Separator());
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

	/**
	 * Check if the key event has the push of the delete key on mac
	 * 
	 * @param event
	 *          a not null key event
	 * @return true if the current OS is mac and the pressed key is the delete one (BS)
	 */
	private boolean isMacDelete(KeyEvent event) {
		return Util.isMac() && event.character == SWT.BS;
	}

	public void hookKeyEvent(TreeViewer treeViewer, KeyEvent event) {
		// Triggered when delete is used or backspace on mac also
		if ((event.character == SWT.DEL || isMacDelete(event)) && event.stateMask == 0) {
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

	@Override
	public List<TransferDragSourceListener> getTransferDragSourceListeners(TreeViewer treeViewer) {
		ArrayList<TransferDragSourceListener> dragListeners = new ArrayList<TransferDragSourceListener>(1);
		dragListeners.add(new DataAdapterDragSourceListener());
		return dragListeners;
	}

	@Override
	public List<TransferDropTargetListener> getTransferDropTargetListeners(TreeViewer treeViewer) {
		return new ArrayList<TransferDropTargetListener>(0);
	}

	@Override
	public List<Command> dropResource(String key, INode root) throws InterruptedException {
		return null;
	}

}
