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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.TreeExpansionEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.repository.IRepositoryViewProvider;
import com.jaspersoft.studio.repository.actions.Separator;
import com.jaspersoft.studio.server.action.resource.AddResourceAction;
import com.jaspersoft.studio.server.action.resource.CopyResourceAction;
import com.jaspersoft.studio.server.action.resource.CutResourceAction;
import com.jaspersoft.studio.server.action.resource.DeleteResourceAction;
import com.jaspersoft.studio.server.action.resource.OpenInEditorAction;
import com.jaspersoft.studio.server.action.resource.PasteResourceAction;
import com.jaspersoft.studio.server.action.resource.PropertiesAction;
import com.jaspersoft.studio.server.action.resource.RefreshResourcesAction;
import com.jaspersoft.studio.server.action.resource.RunReportUnitAction;
import com.jaspersoft.studio.server.action.server.CreateServerAction;
import com.jaspersoft.studio.server.action.server.DeleteServerAction;
import com.jaspersoft.studio.server.action.server.DuplicateServerAction;
import com.jaspersoft.studio.server.action.server.EditServerAction;
import com.jaspersoft.studio.server.model.MFolder;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.model.server.MServers;
import com.jaspersoft.studio.utils.UIUtils;

public class ServerProvider implements IRepositoryViewProvider {
	private CreateServerAction createServerAction;
	private EditServerAction editServerAction;
	private DeleteServerAction deleteServerAction;
	private DuplicateServerAction duplicateServerAction;
	private DeleteResourceAction deleteAction;
	private RefreshResourcesAction refreshAction;

	private PropertiesAction editAction;

	private CutResourceAction cutAction;
	private CopyResourceAction copyAction;
	private PasteResourceAction pasteAction;

	private AddResourceAction addAction;

	private RunReportUnitAction runReportUnitAction;
	private OpenInEditorAction openInEditorAction;

	public Action[] getActions(TreeViewer treeViewer) {
		createActions(treeViewer);
		return new Action[] { createServerAction };
	}

	private void createActions(TreeViewer treeViewer) {
		if (createServerAction == null)
			createServerAction = new CreateServerAction(treeViewer);
		if (editServerAction == null)
			editServerAction = new EditServerAction(treeViewer);
		if (deleteServerAction == null)
			deleteServerAction = new DeleteServerAction(treeViewer);
		if (duplicateServerAction == null)
			duplicateServerAction = new DuplicateServerAction(treeViewer);
		if (deleteAction == null)
			deleteAction = new DeleteResourceAction(treeViewer);
		if (refreshAction == null)
			refreshAction = new RefreshResourcesAction(treeViewer);

		if (cutAction == null)
			cutAction = new CutResourceAction(treeViewer);
		if (copyAction == null)
			copyAction = new CopyResourceAction(treeViewer);
		if (pasteAction == null)
			pasteAction = new PasteResourceAction(treeViewer);

		if (addAction == null)
			addAction = new AddResourceAction(treeViewer);

		if (runReportUnitAction == null)
			runReportUnitAction = new RunReportUnitAction(treeViewer);
		if (editAction == null)
			editAction = new PropertiesAction(treeViewer);

		if (openInEditorAction == null)
			openInEditorAction = new OpenInEditorAction(treeViewer);
	}

	public List<IAction> fillContextMenu(TreeViewer treeViewer, ANode node) {
		createActions(treeViewer);
		List<IAction> lst = new ArrayList<IAction>();
		if (node instanceof MServers) {
			if (createServerAction.isEnabled())
				lst.add(createServerAction);
		} else if (node instanceof MServerProfile) {
			if (addAction.isEnabled())
				lst.add(addAction);
			lst.add(new Separator());

			if (pasteAction.isEnabled())
				lst.add(pasteAction);
			lst.add(new Separator());

			if (editServerAction.isEnabled())
				lst.add(editServerAction);
			if (duplicateServerAction.isEnabled())
				lst.add(duplicateServerAction);
			lst.add(new Separator());
			if (deleteServerAction.isEnabled())
				lst.add(deleteServerAction);
		} else if (node instanceof MResource) {
			if (addAction.isEnabled()
					&& (node instanceof MFolder || node instanceof MReportUnit))
				lst.add(addAction);
			lst.add(new Separator());

			if (((MResource) node).isInsideReportUnit()
					&& runReportUnitAction.isEnabled())
				lst.add(runReportUnitAction);
			lst.add(new Separator());

			if (openInEditorAction.isEnabled())
				lst.add(openInEditorAction);

			lst.add(new Separator());

			if (cutAction.isEnabled())
				lst.add(cutAction);
			if (copyAction.isEnabled())
				lst.add(copyAction);
			if (pasteAction.isEnabled())
				lst.add(pasteAction);

			if (deleteAction.isEnabled())
				lst.add(deleteAction);
			lst.add(new Separator());

			if (refreshAction.isEnabled())
				lst.add(refreshAction);
			lst.add(new Separator());

			if (editAction.isEnabled())
				lst.add(editAction);

		}
		return lst;
	}

	public void hookKeyEvent(TreeViewer treeViewer, KeyEvent event) {
		if (event.character == SWT.DEL && event.stateMask == 0) {
			if (deleteServerAction.isEnabled())
				deleteServerAction.run();
			if (deleteAction.isEnabled())
				deleteAction.run();
		}
	}

	public void doubleClick(TreeViewer treeViewer) {
		if (editServerAction.isEnabled())
			editServerAction.run();
		// if (runReportUnitAction.isEnabled())
		// runReportUnitAction.run();
		if (openInEditorAction.isEnabled())
			openInEditorAction.run();
	}

	public ANode getNode(ANode root) {
		MServers servers = new MServers(root);

		ServerManager.loadServerProfiles(servers);

		return servers;
	}

	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		ServerManager.getPropertyChangeSupport().addPropertyChangeListener(pcl);
	}

	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		ServerManager.getPropertyChangeSupport().removePropertyChangeListener(
				pcl);
	}

	public void handleTreeEvent(TreeExpansionEvent event) {
		if (event.getElement() instanceof MServerProfile) {
			listServer(event);
		} else if (event.getElement() instanceof MResource) {
			lazyLoadResource(event);
		}
	}

	private void listServer(final TreeExpansionEvent event) {
		Job job = new Job("Refreshing tree") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				MServerProfile r = (MServerProfile) event.getElement();
				try {
					WSClientHelper.connectGetData(r, monitor);
					Display.getDefault().asyncExec(new Runnable() {

						public void run() {
							event.getTreeViewer().refresh(true);
						}
					});

					return Status.OK_STATUS;
				} catch (final Throwable e) {
					Display.getDefault().syncExec(new Runnable() {

						public void run() {
							event.getTreeViewer().collapseToLevel(
									(MServerProfile) event.getElement(), 1);
							UIUtils.showErrorDialog(e.getMessage(), e);
						}
					});
				}
				return Status.CANCEL_STATUS;
			}
		};
		job.setPriority(Job.SHORT);
		job.setSystem(false);
		job.setUser(true);
		job.schedule();
	}

	private void lazyLoadResource(final TreeExpansionEvent event) {
		Job job = new Job("Refreshing tree") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				MResource r = (MResource) event.getElement();
				try {
					WSClientHelper.refreshResource(r, monitor);
					Display.getDefault().asyncExec(new Runnable() {

						public void run() {
							event.getTreeViewer().refresh(true);
						}
					});

					return Status.OK_STATUS;
				} catch (final Throwable e) {
					Display.getDefault().syncExec(new Runnable() {

						public void run() {
							event.getTreeViewer().collapseToLevel(
									event.getElement(), 1);
							UIUtils.showErrorDialog(e.getMessage(), e);
						}
					});

				}
				return Status.CANCEL_STATUS;
			}
		};
		job.setPriority(Job.SHORT);
		job.setSystem(false);
		job.setUser(true);
		job.schedule();
	}
}
