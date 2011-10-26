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

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.repository.IRepositoryViewProvider;
import com.jaspersoft.studio.repository.actions.CreateDataAdapterAction;
import com.jaspersoft.studio.repository.actions.DeleteDataAdapterAction;
import com.jaspersoft.studio.repository.actions.DuplicateDataAdapterAction;
import com.jaspersoft.studio.repository.actions.EditDataAdapterAction;

public class DataAdapterProvider implements IRepositoryViewProvider {
	private CreateDataAdapterAction createDataAdapterItemAction;
	private EditDataAdapterAction editDataAdapterItemAction;
	private DeleteDataAdapterAction deleteDataAdapterItemAction;
	private DuplicateDataAdapterAction duplicateDataAdapterItemAction;

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
		}
		return lst;
	}

	public void hookKeyEvent(KeyEvent event) {
		if (event.character == SWT.DEL && event.stateMask == 0 && deleteDataAdapterItemAction.isEnabled()) {
			deleteDataAdapterItemAction.run();
		}
	}

	public void doubleClick(TreeViewer treeViewer) {
		editDataAdapterItemAction.run();
	}

	public ANode getNode(ANode root) {
		return new MDataAdapters(root);
	}

	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		DataAdapterManager.getPropertyChangeSupport().addPropertyChangeListener(pcl);
	}

	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		DataAdapterManager.getPropertyChangeSupport().removePropertyChangeListener(pcl);
	}

	public void handleTreeEvent(TreeExpansionEvent event) {

	}

}
