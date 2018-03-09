/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.find;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.server.ServerProvider;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.model.server.MServerProfile;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

public class FindResourceJob {
	public static ResourceDescriptor doFindResource(MServerProfile msp, String[] in, String[] excl) {
		return doFindResource(msp, in, excl, false, null);
	}

	public static ResourceDescriptor doFindResource(MServerProfile msp, String[] in, String[] excl,
			boolean containedResource) {
		return doFindResource(msp, in, excl, containedResource, null);
	}

	public static ResourceDescriptor doFindResource(MServerProfile msp, String[] in, String[] excl,
			boolean containedResource, String defaultName) {
		return doFindResource(UIUtils.getShell(), msp, in, excl, containedResource, defaultName);
	}

	public static ResourceDescriptor doFindResource(Shell shell, MServerProfile msp, String[] in, String[] excl,
			boolean containedResource, String defaultName) {
		FindResourceWizard wizard = new FindResourceWizard(msp, containedResource);
		wizard.setFilterTypes(in, excl);
		wizard.setDefaultName(defaultName);
		WizardDialog dialog = new FindWizardDialog(shell, wizard);
		dialog.setHelpAvailable(false);
		dialog.create();
		if (dialog.open() == Dialog.OK)
			return wizard.getValue();
		return null;
	}

	public static void doFindResource(ServerProvider sp, TreeViewer treeViewer) {
		doFindResource(sp, treeViewer, false);
	}

	public static void doFindResource(ServerProvider sp, TreeViewer treeViewer, boolean containedResource) {
		TreeSelection ts = (TreeSelection) treeViewer.getSelection();
		Object el = ts.getFirstElement();
		MServerProfile msp = null;
		if (el instanceof MServerProfile)
			msp = (MServerProfile) el;
		else if (el instanceof AMResource) {
			INode n = ((AMResource) el).getRoot();
			if (n != null && n instanceof MServerProfile)
				msp = (MServerProfile) n;
		}
		if (msp != null) {
			FindResourceWizard wizard = new FindResourceWizard(msp, containedResource);
			WizardDialog dialog = new FindWizardDialog(UIUtils.getShell(), wizard);
			dialog.setHelpAvailable(false);
			dialog.create();
			if (dialog.open() == Dialog.OK) {
				ResourceDescriptor rd = wizard.getValue();
				if (rd != null)
					selectResource(sp, msp, rd, treeViewer);
			}
		}
	}

	public static void selectResource(final ServerProvider sp, final MServerProfile msp, final ResourceDescriptor rd,
			final TreeViewer treeViewer) {
		Job job = new Job(Messages.FindResourceJob_0) {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				IStatus status = Status.OK_STATUS;
				try {
					final AMResource mr = WSClientHelper.findSelected(monitor, rd, msp);
					if (mr != null) {
						UIUtils.getDisplay().asyncExec(() -> {
							try {
								sp.setSkipLazyLoad(true);
								treeViewer.refresh(true);
								treeViewer.setSelection(new StructuredSelection(mr));
							} finally {
								sp.setSkipLazyLoad(false);
							}
						});
					}
				} catch (Exception e) {
					UIUtils.showError(e);
				} finally {
					monitor.done();
				}
				return status;
			}
		};
		job.setPriority(Job.SHORT);
		job.setSystem(false);
		job.setUser(true);
		job.schedule();
	}

}
