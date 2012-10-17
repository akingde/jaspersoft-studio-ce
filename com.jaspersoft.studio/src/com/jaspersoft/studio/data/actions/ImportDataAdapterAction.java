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
package com.jaspersoft.studio.data.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.MDataAdapter;
import com.jaspersoft.studio.data.MDataAdapters;
import com.jaspersoft.studio.data.storage.ADataAdapterStorage;
import com.jaspersoft.studio.data.storage.FileDataAdapterStorage;

public class ImportDataAdapterAction extends Action {
	public static final String ID = "importDataAdapteraction"; //$NON-NLS-1$
	private TreeViewer treeViewer;

	public ImportDataAdapterAction(TreeViewer treeViewer) {
		super();
		setId(ID);
		this.treeViewer = treeViewer;
		setText("Import From Workspace");
		setDescription("Find and Import all DataAdapters from workspace");
		setToolTipText("Find and Import all DataAdapters from workspace");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(
				"org.eclipse.ui", "$nl$/icons/full/etool16/import_wiz.gif")); //$NON-NLS-1$
		setDisabledImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(
				"org.eclipse.ui", "$nl$/icons/full/dtool16/import_wiz.gif")); //$NON-NLS-1

	}

	@Override
	public boolean isEnabled() {
		Object firstElement = ((TreeSelection) treeViewer.getSelection()).getFirstElement();
		return firstElement != null && (firstElement instanceof MDataAdapter || firstElement instanceof MDataAdapters);
	}

	@Override
	public void run() {
		Object obj = ((TreeSelection) treeViewer.getSelection()).getFirstElement();
		if (obj instanceof MDataAdapter) {
			MDataAdapter mDataAdapter = (MDataAdapter) obj;
			final ADataAdapterStorage storage = ((MDataAdapters) mDataAdapter.getParent()).getValue();

			Job job = new WorkspaceJob("Searching DataAdapters") {
				public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
					IWorkspace workspace = ResourcesPlugin.getWorkspace();
					IProject[] projects = workspace.getRoot().getProjects();
					for (IProject prj : projects)
						scanFolder(prj.members());

					return Status.OK_STATUS;
				}

				protected void scanFolder(IResource[] fileResources) throws CoreException {
					for (IResource r : fileResources) {
						if (r instanceof IFolder)
							scanFolder(((IFolder) r).members());
						else if (r instanceof IFile)
							checkFile((IFile) r);
					}
				}

				protected void checkFile(IFile file) throws CoreException {
					if (file.getName().endsWith(".xml")) {
						final DataAdapterDescriptor das = FileDataAdapterStorage.readDataADapter(file.getContents(),
								file.getProject());
						if (das != null) {
							Display.getDefault().asyncExec(new Runnable() {

								public void run() {
									DataAdapterDescriptor oldDas = storage.findDataAdapter(das.getName());
									if (oldDas != null)
										; // DataAdapterManager.removeDataAdapter(oldDas); replace?
									else
										storage.addDataAdapter("", das);
								}
							});

						}
					}
				}
			};
			job.schedule();
		}
	}
}
