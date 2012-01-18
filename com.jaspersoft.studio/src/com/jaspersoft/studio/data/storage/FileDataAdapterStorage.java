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
package com.jaspersoft.studio.data.storage;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.XmlUtil;
import net.sf.jasperreports.engine.util.JRXmlUtils;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceProxy;
import org.eclipse.core.resources.IResourceProxyVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Display;
import org.w3c.dom.Document;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterFactory;
import com.jaspersoft.studio.data.DataAdapterManager;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.UIUtils;

public class FileDataAdapterStorage extends ADataAdapterStorage {
	private final class ResourceVisitor implements IResourceProxyVisitor {
		public boolean visit(IResourceProxy proxy) throws CoreException {
			if (proxy.getType() == IResource.FILE)
				checkFile((IFile) proxy.requestResource());
			return true;
		}
	}

	private IProject project;

	public FileDataAdapterStorage(IProject project) {
		this.project = project;
	}

	@Override
	public void findAll() {
		Job job = new WorkspaceJob("Searching DataAdapters") {
			public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
				IWorkspace workspace = ResourcesPlugin.getWorkspace();
				listenWorkspace();
				IProject[] projects = workspace.getRoot().getProjects();
				monitor.beginTask("Search DataAdapters in workspace", projects.length);
				for (IProject prj : projects) {
					monitor.subTask("Searching project " + prj.getName());
					prj.accept(new ResourceVisitor(), IResource.NONE);

					if (monitor.isCanceled())
						return Status.CANCEL_STATUS;
					monitor.internalWorked(1);
				}
				return Status.OK_STATUS;
			}

			protected void listenWorkspace() {
				IWorkspace wspace = ResourcesPlugin.getWorkspace();
				IResourceChangeListener rcl = new IResourceChangeListener() {
					public void resourceChanged(IResourceChangeEvent event) {
						IResourceDelta delta = event.getDelta();
						try {
							delta.accept(new IResourceDeltaVisitor() {

								public boolean visit(IResourceDelta delta) throws CoreException {
									IResource res = delta.getResource();
									if (res.getType() == IResource.FILE) {
										IFile f = (IFile) res;
										if (f.getName().endsWith(".xml")) {
											switch (delta.getKind()) {
											case IResourceDelta.ADDED:
												checkFile(f);
												break;
											case IResourceDelta.REMOVED:
												delete(f.getProjectRelativePath().toPortableString());
												break;
											case IResourceDelta.CHANGED:
												checkFile(f);
												break;
											}
										}
									}
									return true;
								}
							});
						} catch (CoreException e) {
							UIUtils.showError(e);
						}
					}
				};
				wspace.addResourceChangeListener(rcl);
			}
		};
		job.schedule();
	}

	@Override
	public void save(final String url, final DataAdapterDescriptor adapter) {
		Job job = new WorkspaceJob("Creating DataAdapter") {
			public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
				String xml = DataAdapterManager.toDataAdapterFile(adapter);
				IFile file = project.getFile(url);
				if (file.exists())
					file.setContents(new ByteArrayInputStream(xml.getBytes()), true, true, monitor);
				else
					file.create(new ByteArrayInputStream(xml.getBytes()), true, monitor);
				return Status.OK_STATUS;
			}
		};
		job.schedule();
	}

	@Override
	public void delete(final String url) {
		Job job = new WorkspaceJob("Deleting DataAdapter") {
			public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
				IFile file = project.getFile(url);
				if (file.exists())
					file.delete(true, monitor);
				return Status.OK_STATUS;
			}
		};
		job.schedule();
	}

	protected void checkFile(final IFile file) throws CoreException {
		if (file.getName().endsWith(".xml")) {
			final DataAdapterDescriptor das = readDataADapter(file.getContents());
			if (das != null) {
				Display.getDefault().asyncExec(new Runnable() {

					public void run() {
						addDataAdapter(file.getProjectRelativePath().toPortableString(), das);
					}
				});

			}
		}
	}

	public static DataAdapterDescriptor readDataADapter(InputStream in) {
		try {
			Document document = JRXmlUtils.parse(in);
			String adapterClassName = document.getDocumentElement().getAttribute("class");
			if (adapterClassName == null || adapterClassName.isEmpty())
				return null;
			DataAdapterFactory factory = DataAdapterManager.findFactoryByDataAdapterClass(adapterClassName);
			if (factory == null) {
				// we should at least log a warning here....
				JaspersoftStudioPlugin
						.getInstance()
						.getLog()
						.log(
								new Status(Status.WARNING, JaspersoftStudioPlugin.getUniqueIdentifier(), Status.OK,
										Messages.DataAdapterManager_nodataadapterfound + adapterClassName, null));
			} else {
				DataAdapterDescriptor dataAdapterDescriptor = factory.createDataAdapter();
				DataAdapter dataAdapter = dataAdapterDescriptor.getDataAdapter();
				dataAdapter = (DataAdapter) XmlUtil.read(document.getDocumentElement(), dataAdapter.getClass());
				dataAdapterDescriptor.setDataAdapter(dataAdapter);
				return dataAdapterDescriptor;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
