/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.actions;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.jaspersoft.studio.data.MDataAdapter;
import com.jaspersoft.studio.data.MDataAdapters;
import com.jaspersoft.studio.data.wizard.imp.ImportDAWizard;
import com.jaspersoft.studio.messages.Messages;

public class ImportDataAdapterAction extends Action {
	public static final String ID = "importDataAdapteraction"; //$NON-NLS-1$
	private TreeViewer treeViewer;

	public ImportDataAdapterAction(TreeViewer treeViewer) {
		super();
		setId(ID);
		this.treeViewer = treeViewer;
		setText(Messages.ImportDataAdapterAction_name);
		setDescription(Messages.ImportDataAdapterAction_description);
		setToolTipText(Messages.ImportDataAdapterAction_tooltip);
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(
				"org.eclipse.ui", "$nl$/icons/full/etool16/import_wiz.gif")); //$NON-NLS-1$ //$NON-NLS-2$
		setDisabledImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(
				"org.eclipse.ui", "$nl$/icons/full/dtool16/import_wiz.gif")); //$NON-NLS-1 //$NON-NLS-1$ //$NON-NLS-2$

	}

	@Override
	public boolean isEnabled() {
		Object firstElement = ((TreeSelection) treeViewer.getSelection()).getFirstElement();
		return firstElement != null && (firstElement instanceof MDataAdapter || firstElement instanceof MDataAdapters);
	}

	@Override
	public void run() {
		Object obj = ((TreeSelection) treeViewer.getSelection()).getFirstElement();
		MDataAdapters mDataAdapters = null;
		if (obj instanceof MDataAdapters)
			mDataAdapters = (MDataAdapters) obj;
		if (obj instanceof MDataAdapter)
			mDataAdapters = (MDataAdapters) ((MDataAdapter) obj).getParent();
		if (mDataAdapters != null) {
			WizardDialog dialog = new WizardDialog(UIUtils.getShell(), new ImportDAWizard(mDataAdapters));
			dialog.open();

			//			Job job = new WorkspaceJob("Searching DataAdapters") { //$NON-NLS-1$
			// public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
			// IWorkspace workspace = ResourcesPlugin.getWorkspace();
			// IProject[] projects = workspace.getRoot().getProjects();
			// for (IProject prj : projects) {
			// if (prj.isOpen())
			// scanFolder(prj.members());
			// }
			//
			// return Status.OK_STATUS;
			// }
			//
			// protected void scanFolder(IResource[] fileResources) throws CoreException {
			// for (IResource r : fileResources) {
			// if (r instanceof IFolder)
			// scanFolder(((IFolder) r).members());
			// else if (r instanceof IFile)
			// checkFile((IFile) r);
			// }
			// }
			//
			// protected void checkFile(IFile file) throws CoreException {
			//					if (file.getName().endsWith(".xml")) { //$NON-NLS-1$
			// try {
			// final DataAdapterDescriptor das = FileDataAdapterStorage.readDataADapter(file.getContents(),
			// file.getProject());
			// if (das != null) {
			// Display.getDefault().asyncExec(new Runnable() {
			//
			// public void run() {
			// DataAdapterDescriptor oldDas = storage.findDataAdapter(das.getName());
			// if (oldDas != null)
			// ; // DataAdapterManager.removeDataAdapter(oldDas); replace?
			// else
			//											storage.addDataAdapter(das); //$NON-NLS-1$
			// }
			// });
			//
			// }
			// } catch (Throwable e) {
			// e.printStackTrace();
			// }
			// }
			// }
			// };
			// job.schedule();
		}
	}
}
