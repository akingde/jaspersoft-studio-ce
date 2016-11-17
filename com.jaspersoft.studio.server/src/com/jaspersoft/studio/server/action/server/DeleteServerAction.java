/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.action.server;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;

import com.jaspersoft.studio.server.Activator;
import com.jaspersoft.studio.server.ServerManager;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.server.MServerProfile;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

public class DeleteServerAction extends Action {

	public static final String ID = "com.jaspersoft.studio.server.action.resource.deleteServerAction"; //$NON-NLS-1$

	private TreeViewer treeViewer;

	public DeleteServerAction(TreeViewer treeViewer) {
		super();
		this.treeViewer = treeViewer;
		setId(ID);
		setText(com.jaspersoft.studio.messages.Messages.common_delete);
		setDescription(Messages.DeleteServerAction_desc);
		setToolTipText(Messages.DeleteServerAction_desc); // $NON-NLS-1$
		setImageDescriptor(Activator.getDefault().getImageDescriptor("icons/server--minus.png")); //$NON-NLS-1$
	}

	@Override
	public boolean isEnabled() {
		Object firstElement = ((TreeSelection) treeViewer.getSelection()).getFirstElement();
		return firstElement != null && (firstElement instanceof MServerProfile);
	}

	@Override
	public void run() {
		if (!UIUtils.showDeleteConfirmation())
			return;
		final boolean delDir = UIUtils.showConfirmation(com.jaspersoft.studio.messages.Messages.DeleteServerAction_1,
				com.jaspersoft.studio.messages.Messages.DeleteServerAction_2);
		final TreeSelection s = (TreeSelection) treeViewer.getSelection();
		final TreePath[] p = s.getPaths();
		Job job = new Job(com.jaspersoft.studio.messages.Messages.DeleteServerAction_1) {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask(com.jaspersoft.studio.messages.Messages.DeleteServerAction_4,
						IProgressMonitor.UNKNOWN);
				try {

					IContainer root = ResourcesPlugin.getWorkspace().getRoot();
					for (int i = 0; i < p.length; i++) {
						Object obj = p[i].getLastSegment();
						if (obj instanceof MServerProfile) {
							final MServerProfile msrv = (MServerProfile) obj;
							if (delDir && msrv.getValue().getProjectPath() != null) {
								IResource r = root.findMember(msrv.getValue().getProjectPath());
								if (r instanceof IFolder) {
									try {
										r.delete(true, monitor);
									} catch (CoreException e) {
										UIUtils.showError(e);
									}
								}
							}
							UIUtils.getDisplay().syncExec(new Runnable() {
								public void run() {
									ServerManager.removeServerProfile(msrv);
									treeViewer.refresh(true);
								}
							});
						}
					}
				} finally {
					monitor.done();
				}
				return Status.OK_STATUS;
			}
		};
		job.setPriority(Job.LONG);
		job.schedule();

	}
}
