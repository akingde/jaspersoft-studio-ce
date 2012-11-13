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
import net.sf.jasperreports.eclipse.util.JavaProjectClassLoader;
import net.sf.jasperreports.util.CastorUtil;

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
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.swt.widgets.Display;
import org.exolab.castor.mapping.Mapping;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterFactory;
import com.jaspersoft.studio.data.DataAdapterManager;
import com.jaspersoft.studio.data.DefaultDataAdapterDescriptor;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.UIUtils;
import com.jaspersoft.studio.utils.XMLUtils;

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
		try {
			if (project.isOpen()) {
				IResource[] members = project.members();
				if (members != null && members.length > 0) {
					Job job = new WorkspaceJob("Searching DataAdapters") {
						public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
							listenWorkspace();
							monitor.beginTask("Search DataAdapters in project " + project.getName(), 10);
							monitor.subTask("Searching project " + project.getName());
							project.accept(new ResourceVisitor(), IResource.NONE);

							if (monitor.isCanceled())
								return Status.CANCEL_STATUS;
							monitor.internalWorked(10);

							return Status.OK_STATUS;
						}

						protected void listenWorkspace() {
							IWorkspace wspace = ResourcesPlugin.getWorkspace();
							IResourceChangeListener rcl = new IResourceChangeListener() {
								public void resourceChanged(IResourceChangeEvent event) {
									IResourceDelta delta = event.getDelta();
									if (delta == null)
										return;
									try {
										delta.accept(new IResourceDeltaVisitor() {

											public boolean visit(IResourceDelta delta) throws CoreException {
												IResource res = delta.getResource();
												if (res.getProject() != project)
													return true;
												if (res.getType() == IResource.FILE) {
													final IFile f = (IFile) res;
													if (f.getName().endsWith(".xml")) {
														switch (delta.getKind()) {
														case IResourceDelta.ADDED:
															checkFile(f);
															break;
														case IResourceDelta.REMOVED:
															if (f.getName().endsWith(".xml")) {
																Display.getDefault().asyncExec(new Runnable() {

																	public void run() {
																		removeDataAdapter(f.getProjectRelativePath().toPortableString());
																	}
																});
															}
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
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
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
			final DataAdapterDescriptor das = readDataADapter(file.getContents(), file.getProject());
			if (das != null) {
				Display.getDefault().asyncExec(new Runnable() {

					public void run() {
						addDataAdapter(file.getProjectRelativePath().toPortableString(), das);
					}
				});

			}
		}
	}

	public static DataAdapterDescriptor readDataADapter(InputStream in, IProject project) {
		try {
			Document document = XMLUtils.parseNoValidation(in);
			String adapterClassName = document.getDocumentElement().getAttribute("class");
			if (adapterClassName == null || adapterClassName.isEmpty())
				return null;
			DataAdapterFactory factory = DataAdapterManager.findFactoryByDataAdapterClass(adapterClassName);
			if (factory == null) {
				if (project != null) {
					DefaultDataAdapterDescriptor ddad = new DefaultDataAdapterDescriptor();
					ClassLoader cl = JavaProjectClassLoader.instance(JavaCore.create(project), project.getClass()
							.getClassLoader());
					Class<?> clazz = cl.loadClass(adapterClassName);
					if (clazz != null) {
						InputStream mis = cl.getResourceAsStream(clazz.getName().replace(".", "/") + ".xml");
						if (mis != null) {
							Mapping mapping = new Mapping(cl);
							mapping.loadMapping(new InputSource(mis));

							DataAdapter dataAdapter = (DataAdapter) CastorUtil.read(document.getDocumentElement(), mapping);
							if (dataAdapter != null) {
								ddad.setDataAdapter(dataAdapter);
								return ddad;
							}
						}
					}
				}// we should at least log a warning here....
				JaspersoftStudioPlugin
						.getInstance()
						.getLog()
						.log(
								new Status(Status.WARNING, JaspersoftStudioPlugin.getUniqueIdentifier(), Status.OK,
										Messages.DataAdapterManager_nodataadapterfound + adapterClassName, null));
			} else {
				DataAdapterDescriptor dataAdapterDescriptor = factory.createDataAdapter();
				DataAdapter dataAdapter = dataAdapterDescriptor.getDataAdapter();
				dataAdapter = (DataAdapter) CastorUtil.read(document.getDocumentElement(), dataAdapter.getClass());
				dataAdapterDescriptor.setDataAdapter(dataAdapter);
				return dataAdapterDescriptor;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
