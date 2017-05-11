/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.storage;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

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
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jdt.core.JavaCore;
import org.exolab.castor.mapping.Mapping;
import org.xml.sax.InputSource;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterFactory;
import com.jaspersoft.studio.data.DataAdapterManager;
import com.jaspersoft.studio.data.DefaultDataAdapterDescriptor;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.XMLUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.eclipse.classpath.JavaProjectClassLoader;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.CastorHelper;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.util.CastorUtil;

public class FileDataAdapterStorage extends ADataAdapterStorage {
	
	private IProject project;
	
	private final class ResourceVisitor implements IResourceProxyVisitor {
		public boolean visit(IResourceProxy proxy) throws CoreException {
			if (proxy.isTeamPrivateMember())
				return false;
			if (proxy.getType() == IResource.FOLDER)
				return true;
			if (proxy.getType() == IResource.FILE) {
				IPath fpath = proxy.requestFullPath();
				if (fpath != null) {
					String fext = fpath.getFileExtension();
					if (fext != null && fext.equals("xml")) //$NON-NLS-1$
						checkFile((IFile) proxy.requestResource());
				}
			}
			return true;
		}
	}

	public FileDataAdapterStorage(IProject project) {
		this.project = project;
	}

	@Override
	public void findAll() {
		try {
			if (project.isOpen()) {
				IResource[] members = project.members();
				if (members != null && members.length > 0) {
					Job job = new WorkspaceJob(Messages.FileDataAdapterStorage_1) {
						public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
							listenWorkspace();
							monitor.beginTask(Messages.FileDataAdapterStorage_2 + project.getName(), 10);
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
									final IResourceDelta delta = event.getDelta();
									if (delta == null)
										return;
									IResourceDelta docDelta = delta.findMember(project.getFullPath());
									if (docDelta == null)
										return;
									if (!(delta.getKind() == IResourceDelta.ADDED || delta.getKind() == IResourceDelta.REMOVED || (delta
											.getKind() == IResourceDelta.CHANGED && ((delta.getFlags() & (IResourceDelta.CONTENT
											| IResourceDelta.ADDED | IResourceDelta.MOVED_TO | IResourceDelta.CHANGED
											| IResourceDelta.COPIED_FROM | IResourceDelta.REPLACED | IResourceDelta.SYNC | IResourceDelta.MOVED_FROM)) == 0))))
										return;
									// we get event of file creation but file is empty, so we have to wait 1sec to give the possibility to
									// write into it
									new WorkspaceJob(Messages.FileDataAdapterStorage_1) {
										public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
											monitor.beginTask(Messages.FileDataAdapterStorage_2 + project.getName(), 10);
											processEvent(delta);
											if (monitor.isCanceled())
												return Status.CANCEL_STATUS;
											monitor.internalWorked(10);

											return Status.OK_STATUS;
										}
									}.schedule(10000);
								}

								protected void processEvent(IResourceDelta delta) {
									try {
										delta.accept(new IResourceDeltaVisitor() {

											public boolean visit(IResourceDelta delta) throws CoreException {
												final IResource res = delta.getResource();
												if (res.getType() == IResource.FILE && "xml".equalsIgnoreCase(res.getFileExtension())) //$NON-NLS-1$
													switch (delta.getKind()) {
													case IResourceDelta.ADDED:
														checkFile((IFile) res);
														break;
													case IResourceDelta.REMOVED:
														UIUtils.getDisplay().asyncExec(new Runnable() {

															public void run() {
																DataAdapterDescriptor das = findDataAdapter(res.getProjectRelativePath().toOSString());
																removeDataAdapter(das);
															}
														});
														break;
													case IResourceDelta.CHANGED:
														UIUtils.getDisplay().asyncExec(new Runnable() {

															public void run() {
																
																DataAdapterDescriptor das = findDataAdapter(res.getProjectRelativePath().toOSString());
																if (das != null) {
																	FileDataAdapterStorage.super.removeDataAdapter(das);
																	try {
																		IFile file = (IFile) res;
																		das = readDataADapter(file.getContents(), file.getProject());
																		das.setName(file.getProjectRelativePath().toOSString());
																		FileDataAdapterStorage.super.addDataAdapter(das);
																	} catch (CoreException e) {
																		UIUtils.showError(e);
																	}
																}
															}
														});
														break;
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
	public boolean addDataAdapter(DataAdapterDescriptor adapter) {
		boolean result = super.addDataAdapter(adapter);
		if (result) {
			IFile file = project.getFile(adapter.getName());
			String xml = DataAdapterManager.toDataAdapterFile(adapter, JasperReportsConfiguration.getDefaultJRConfig(file));
			try {
				if (file.exists())
					file.setContents(new ByteArrayInputStream(xml.getBytes()), true, true, new NullProgressMonitor());
				else
					file.create(new ByteArrayInputStream(xml.getBytes()), true, new NullProgressMonitor());
				return true;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean removeDataAdapter(DataAdapterDescriptor da) {
		boolean result = super.removeDataAdapter(da);
		if (result) {
			IFile file = project.getFile(da.getName());
			if (file.exists()) {
				try {
					file.delete(true, new NullProgressMonitor());
					return true;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return false;
	}

	private void checkFile(final IFile file) throws CoreException {
		if (!file.isAccessible() || file.isDerived() || file.isPhantom() || file.isHidden() || file.isVirtual())
			return;
		String ext = file.getFileExtension();
		if (ext.equals("xml")) { //$NON-NLS-1$
			final DataAdapterDescriptor das = readDataADapter(file.getContents(), file.getProject());
			if (das != null) {
				das.setName(file.getProjectRelativePath().toOSString());
				UIUtils.getDisplay().asyncExec(new Runnable() {

					public void run() {
						FileDataAdapterStorage.super.addDataAdapter(das);
					}
				});

			}
		}
	}

	private static XMLInputFactory inputFactory = XMLInputFactory.newInstance();

	private static String readXML(InputStream in) {
		try {
			XMLStreamReader streamReader = inputFactory.createXMLStreamReader(in);
			streamReader.nextTag();
			for (int i = 0; i < streamReader.getAttributeCount(); i++)
				if (streamReader.getAttributeName(i).getLocalPart().equals("class")) //$NON-NLS-1$
					return streamReader.getAttributeValue(i);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static DataAdapterDescriptor readDataADapter(InputStream in, IProject project) {
		DataAdapterDescriptor dad = null;
		try {
			in = new BufferedInputStream(in);
			in.mark(Integer.MAX_VALUE);
			String className = readXML(in);
			if (!Misc.isNullOrEmpty(className)) {
				in.reset();
				DataAdapterFactory factory = DataAdapterManager.findFactoryByDataAdapterClass(className);
				if (factory == null) {
					if (project != null) {
						DefaultDataAdapterDescriptor ddad = new DefaultDataAdapterDescriptor();
						ClassLoader cl = JavaProjectClassLoader.instance(JavaCore.create(project), project.getClass()
								.getClassLoader());
						Class<?> clazz = cl.loadClass(className);
						if (clazz != null) {
							InputStream mis = cl.getResourceAsStream(clazz.getName().replace(".", "/") + ".xml"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
							if (mis != null) {
								try {
									Mapping mapping = new Mapping(cl);
									mapping.loadMapping(new InputSource(mis));

									DataAdapter dataAdapter = (DataAdapter) CastorHelper.read(XMLUtils.parseNoValidation(in)
											.getDocumentElement(), mapping);
									if (dataAdapter != null) {
										ddad.setDataAdapter(dataAdapter);
										dad = ddad;
									}
								} finally {
									FileUtils.closeStream(mis);
								}
							}
						}
					} else
						// we should at least log a warning here....
						JaspersoftStudioPlugin
								.getInstance()
								.getLog()
								.log(
										new Status(Status.WARNING, JaspersoftStudioPlugin.getUniqueIdentifier(), Status.OK,
												Messages.DataAdapterManager_nodataadapterfound + className, null));
				} else {
					DataAdapterDescriptor dataAdapterDescriptor = factory.createDataAdapter();
					DataAdapter dataAdapter = dataAdapterDescriptor.getDataAdapter();
					dataAdapter = (DataAdapter) CastorUtil.getInstance(JasperReportsConfiguration.getDefaultInstance()).read(in);
					dataAdapterDescriptor.setDataAdapter(dataAdapter);
					dad = dataAdapterDescriptor;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			FileUtils.closeStream(in);
		}
		return dad;
	}

	public IProject getProject() {
		return project;
	}

	@Override
	public String getStorageName() {
		return project.getName();
	}
}
