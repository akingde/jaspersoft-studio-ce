/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.action.server;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;

import com.jaspersoft.studio.model.MDummy;
import com.jaspersoft.studio.server.Activator;
import com.jaspersoft.studio.server.ServerManager;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.wizard.ServerProfileWizard;
import com.jaspersoft.studio.server.wizard.ServerProfileWizardDialog;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

public class EditServerAction extends Action {
	public static final String ID = "editServerAction"; //$NON-NLS-1$
	private TreeViewer treeViewer;

	public EditServerAction(TreeViewer treeViewer) {
		super();
		this.treeViewer = treeViewer;
		setId(ID);
		setText(Messages.EditServerAction_title);
		setDescription(Messages.EditServerAction_desc);
		setToolTipText(Messages.EditServerAction_desc);
		setImageDescriptor(Activator.getDefault().getImageDescriptor("icons/server--pencil.png")); //$NON-NLS-1$
	}

	@Override
	public boolean isEnabled() {
		Object firstElement = ((TreeSelection) treeViewer.getSelection()).getFirstElement();
		return firstElement != null && (firstElement instanceof MServerProfile);
	}

	@Override
	public void run() {
		Object obj = ((TreeSelection) treeViewer.getSelection()).getFirstElement();
		if (obj instanceof MServerProfile) {
			MServerProfile mspold = (MServerProfile) obj;

			MServerProfile mspCopy = ServerManager.getMServerProfileCopy(mspold);
			try {
				mspCopy.setValue(mspCopy.getValue().clone());
			} catch (CloneNotSupportedException e) {
				UIUtils.showError(e);
			}

			ServerProfileWizard wizard = new ServerProfileWizard(mspCopy);
			ServerProfileWizardDialog dialog = new ServerProfileWizardDialog(UIUtils.getShell(), wizard);
			wizard.bindTestButton(dialog);
			dialog.create();
			if (dialog.open() == Dialog.OK) {
				MServerProfile msprof = wizard.getServerProfile();
				mspold.setValue(msprof.getValue());
				mspold.setWsClient(null);

				ServerManager.saveServerProfile(mspold);
				mspold.setWsClient(msprof.getWsClient());
				fillServerProfile(mspold, treeViewer);
			}

		}
	}

	public static void fillServerProfile(final MServerProfile mspold, final TreeViewer treeViewer) {
		Job job = new Job(Messages.EditServerAction_jobname) {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				try {
					monitor.beginTask(Messages.EditServerAction_taskname, IProgressMonitor.UNKNOWN);
					mspold.removeChildren();
					new MDummy(mspold);
					showSelection();
					WSClientHelper.connectGetData(mspold, monitor, false);
					showSelection();
				} catch (InvocationTargetException e) {
					UIUtils.showError(e);
					mspold.setWsClient(null);
					return Status.CANCEL_STATUS;
				} catch (Exception e) {
					UIUtils.showError(e);
					mspold.setWsClient(null);
					return Status.CANCEL_STATUS;
				}
				return Status.OK_STATUS;
			}

			private void showSelection() {
				UIUtils.getDisplay().syncExec(new Runnable() {

					@Override
					public void run() {
						treeViewer.refresh(true);
						TreeSelection s = (TreeSelection) treeViewer.getSelection();
						if (!s.isEmpty()) {
							TreePath[] p = s.getPaths();
							treeViewer.expandToLevel(p[0], 1);
						}
					}
				});
			}
		};
		job.setSystem(false);
		job.schedule();
	}
}
