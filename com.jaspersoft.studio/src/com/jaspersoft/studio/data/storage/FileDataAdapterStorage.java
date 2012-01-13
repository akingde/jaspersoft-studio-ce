package com.jaspersoft.studio.data.storage;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.XmlUtil;
import net.sf.jasperreports.engine.util.JRXmlUtils;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
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

public class FileDataAdapterStorage extends ADataAdapterStorage {
	private IProject project;

	public FileDataAdapterStorage(IProject project) {
		this.project = project;
	}

	@Override
	public void findAll() {
		Job job = new WorkspaceJob("Searching DataAdapters") {
			public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
				IWorkspace workspace = ResourcesPlugin.getWorkspace();
				IProject[] projects = workspace.getRoot().getProjects();
				for (IProject prj : projects)
					scanFolder(prj.members());

				IWorkspace wspace = ResourcesPlugin.getWorkspace();
				IResourceChangeListener rcl = new IResourceChangeListener() {
					public void resourceChanged(IResourceChangeEvent event) {
						IResourceDelta delta = event.getDelta();
						IResourceDelta[] projectDeltas = delta.getAffectedChildren(IResourceDelta.ADDED | IResourceDelta.CHANGED
								| IResourceDelta.REMOVED);
						for (int i = 0; i < projectDeltas.length; i++) {
							IResourceDelta projectDelta = projectDeltas[i];
							IResource resource = projectDelta.getResource();
							if (resource.getType() == IResource.FILE) {
								IFile f = (IFile) resource;
								if (f.getFileExtension().equals(".xml")) {
									try {
										switch (projectDelta.getKind()) {
										case IResourceDelta.ADDED:
											checkFile(f);
											break;
										case IResourceDelta.CHANGED:
											// ??
											break;
										case IResourceDelta.REMOVED:
											// remove dataadapter if exists
											break;
										}
									} catch (CoreException e) {
										e.printStackTrace();
									}
								}
							}
						}
					}
				};
				wspace.addResourceChangeListener(rcl);

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
				project.getFile(url).delete(true, monitor);
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
						DataAdapterDescriptor oldDas = findDataAdapter(das.getName());
						if (oldDas != null)
							; // DataAdapterManager.removeDataAdapter(oldDas); replace?
						else
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
