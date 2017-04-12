/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.editor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.ide.IDE;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.editor.JrxmlEditor;
import com.jaspersoft.studio.server.Activator;
import com.jaspersoft.studio.server.ResourceFactory;
import com.jaspersoft.studio.server.ServerManager;
import com.jaspersoft.studio.server.export.AExporter;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.protocol.IConnection;
import com.jaspersoft.studio.server.publish.PublishUtil;
import com.jaspersoft.studio.server.utils.ReferenceResolver;
import com.jaspersoft.studio.utils.CacheMap;
import com.jaspersoft.studio.utils.Callback;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.jasper.JSSFileRepositoryService;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.repo.FileRepositoryService;
import net.sf.jasperreports.repo.RepositoryService;
import net.sf.jasperreports.repo.Resource;

public class JRSRepositoryService implements RepositoryService {
	private JSSFileRepositoryService parent;
	private MServerProfile msp;
	private IConnection c;
	private String rpath;
	private String serverUri;
	private JasperDesign jDesign;
	private String runitUri;
	private JasperReportsConfiguration jConfig;
	private FileRepositoryService repService;

	public JRSRepositoryService(JSSFileRepositoryService parent, JasperReportsConfiguration jConfig) {
		this.parent = parent;
		this.jConfig = jConfig;
	}

	public FileRepositoryService getFileRepositoryService() {
		return repService;
	}

	private <K extends Resource> boolean hasServerUrl(final String objuri, final Class<K> resourceType) {
		String uri = null;
		String serverUser = null;
		if (jDesign == null)
			jDesign = jConfig.getJasperDesign();
		if (jDesign != null) {
			uri = jDesign.getProperty(AExporter.PROP_SERVERURL);
			if (uri == null)
				return false;
			serverUser = jDesign.getProperty(AExporter.PROP_USER);
			runitUri = jDesign.getProperty(AExporter.PROP_REPORTUNIT);
		} else {
			// let's look into the file props
			IFile f = (IFile) jConfig.get(FileUtils.KEY_FILE);
			if (f != null) {
				try {
					List<String[]> paths = PublishUtil.loadPath(new NullProgressMonitor(), f);
					if (Misc.isNullOrEmpty(paths) && paths.size() > 0) {
						uri = paths.get(0)[1];
						if (paths.size() > 1)
							serverUser = paths.get(1)[1];
					}

					uri = f.getPersistentProperty(new QualifiedName(Activator.PLUGIN_ID, AExporter.PROP_SERVERURL));
					serverUser = f.getPersistentProperty(new QualifiedName(Activator.PLUGIN_ID, AExporter.PROP_USER));
				} catch (CoreException e) {
					e.printStackTrace();
				}
			}
			if (uri == null) {
				uri = jConfig.getProperty(AExporter.PROP_SERVERURL);
				serverUser = jConfig.getProperty(AExporter.PROP_USER);
			}
			if (uri != null && serverUser != null) {
				String[] usrs = serverUser.split("\\|");
				if (usrs.length == 1)
					serverUser = usrs[0];
				else if (usrs.length > 1 && Misc.isNullOrEmpty(usrs[1]))
					serverUser = usrs[0];
			}
		}
		if (uri != null && !uri.equals(serverUri))

		{
			serverUri = uri;
			c = null;
		}
		if (c == null && !isConnecting) {
			isConnecting = true;
			msp = ServerManager.getServerByUrl(serverUri, serverUser);
			if (msp != null) {
				setupConnection(msp.getWsClient(new Callback<IConnection>() {

					@Override
					public void completed(IConnection value) {
						setupConnection(value);
						if (c != null)
							getResource(objuri, resourceType);
					}
				}));
			}
		}
		return true;
	}

	private void setupConnection(IConnection conn) {
		c = conn;
		try {
			initRPath();
			if (rpath == null)
				return;
			List<RepositoryService> servs = parent.getRepositoryServices();
			if (repService != null)
				servs.remove(repService);
			repService = new FileRepositoryService(jConfig, rpath, true);
			int ind = servs.indexOf(JRSRepositoryService.this);
			servs.add(Math.max(0, Math.max(ind - 2, ind - 1)), repService);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			isConnecting = false;
		}
	}

	private void initRPath() throws IOException, CoreException {
		IFolder tmpDir = msp.getTmpDir(new NullProgressMonitor());
		if (tmpDir.getRawLocation() != null)
			rpath = tmpDir.getRawLocation().toOSString();
		else if (tmpDir.getFullPath() != null)
			rpath = tmpDir.getFullPath().toOSString();
		else {
			IFile file = (IFile) jConfig.get(FileUtils.KEY_FILE);
			if (file != null)
				rpath = file.getProject().getRawLocation().toOSString();
		}
	}

	private boolean isConnecting = false;

	@Override
	public Resource getResource(String uri) {
		return null;
	}

	@Override
	public void saveResource(String uri, Resource resource) {
	}

	private List<ResourceDescriptor> reportUnitResources = null;
	private CacheMap<String, String> negCache = new CacheMap<String, String>(1000);

	@Override
	public synchronized <K extends Resource> K getResource(String uri, Class<K> resourceType) {
		// System.out.println("getResource: " + uri);
		if (hasServerUrl(uri, resourceType) && c != null) {
			if (uri.startsWith("repo:")) {
				// it's possible to have a resource with id=repo:something (from
				// practice)
				K r = doGetResource(uri.startsWith("repo:") ? uri : "repo:" + uri, resourceType);
				if (r != null)
					return r;
			}
			return doGetResource(uri, resourceType);
		}
		if (c == null && uri.startsWith("repo:")) {
			K r = doGetResource(uri.startsWith("repo:") ? uri : "repo:" + uri, resourceType);
			if (r != null)
				return r;
		}
		return repService != null ? repService.getResource(uri, resourceType) : null;
	}

	private <K extends Resource> K addToCache(K res, String uri) {
		// System.out.println("Add to " + (res == null ? "neg" : "pos") + "
		// cache: " + uri);
		if (res == null)
			negCache.put(uri, null);
		return res;
	}

	protected <K extends Resource> K doGetResource(String uri, Class<K> resourceType) {
		// System.out.println("doGetResource: " + uri);
		if (negCache.containsKey(uri)) {
			// System.out.println("in negative cache " + uri);
			return null;
		}
		String objectUri = uri;
		if (uri.startsWith("repo:")) { //$NON-NLS-1$
			objectUri = uri.substring(5);
			K r = getFromParent(objectUri, resourceType);
			if (r != null)
				return addToCache(r, uri);
		}
		if (c != null)
			try {
				IProgressMonitor monitor = new NullProgressMonitor();
				// System.out.println("get from server " + uri);
				if (objectUri.contains("/")) { //$NON-NLS-1$
					// Locate the resource inside the repository...
					ResourceDescriptor r = new ResourceDescriptor();
					r.setUriString(objectUri);
					r = c.get(monitor, r, null);
					if (r.getIsReference())
						r = ReferenceResolver.resolveReference(c, r, null);
					if (rpath == null)
						initRPath();
					String fpath = Misc.nvl(rpath);
					if (!objectUri.startsWith("/")) //$NON-NLS-1$
						fpath += "/"; //$NON-NLS-1$
					fpath += objectUri;
					File f = new File(fpath);
					if (f.getParentFile() != null)
						f.getParentFile().mkdirs();
					if (f.createNewFile()) {
						if (!r.getIsReference() && r.getHasData() && r.getData() != null) {
							org.apache.commons.io.FileUtils.writeByteArrayToFile(f, r.getData());
						} else
							c.get(monitor, r, f);
					}
					fileTypes.put(f, r.getWsType());
				} else if (runitUri != null) {
					// Locate the resource inside the report unit, if any...
					if (reportUnitResources == null) {
						ResourceDescriptor rd = new ResourceDescriptor();
						rd.setWsType(ResourceDescriptor.TYPE_REPORTUNIT);
						rd.setUriString(runitUri);
						rd = c.get(monitor, rd, null);
						reportUnitResources = c.list(monitor, rd);
						if (reportUnitResources == null)
							reportUnitResources = new ArrayList<ResourceDescriptor>();
					}

					// find the resource...
					for (ResourceDescriptor r : reportUnitResources) {
						if (r.getName() == null || !r.getName().equals(objectUri))
							continue;
						if (r.getIsReference())
							r = ReferenceResolver.resolveReference(c, r, monitor);
						if (ResourceFactory.isFileResourceType(r)) {
							IFile file = (IFile) jConfig.get(FileUtils.KEY_FILE);

							File fp = null;
							IContainer pf = file.getParent();
							if (pf.getRawLocation() != null)
								fp = pf.getRawLocation().toFile();
							else if (pf.getLocationURI() != null)
								fp = new File(pf.getLocationURI());
							else
								return addToCache(null, uri);

							File f = new File(fp, objectUri);
							if (f.getParentFile() != null && !f.getParentFile().mkdirs() && f.createNewFile())
								c.get(monitor, r, f);
							fileTypes.put(f, r.getWsType());
							break;
						}
					}
				}
				refresh();
				String u = uri;
				if (u.startsWith("repo:"))
					u = u.substring(5);
				return addToCache(getFromParent(u, resourceType), uri);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		return addToCache(null, uri);
	}

	protected <K extends Resource> K getFromParent(String uri, Class<K> resourceType) {
		// System.out.println("get from parent " + uri);
		for (RepositoryService rs : parent.getRepositoryServices()) {
			if (rs == this)
				continue;
			try {
				K r = parent.doGetResource(uri, resourceType, rs);
				if (r != null)
					return r;
			} catch (JRRuntimeException e) {
			}
		}
		// System.out.println("get from server not found " + uri);
		return null;
	}

	private boolean isRefreshing = false;
	private boolean needNewRefresh = false;
	private Map<File, String> fileTypes = new HashMap<File, String>();
	private IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

	private void refresh() {
		needNewRefresh = true;
		if (isRefreshing)
			return;
		isRefreshing = true;
		Job job = new Job(Messages.JRSRepositoryService_4) {
			protected IStatus run(IProgressMonitor monitor) {
				needNewRefresh = false;
				try {
					IFolder tmpDir = msp.getTmpDir(monitor);
					if (tmpDir != null)
						tmpDir.refreshLocal(IResource.DEPTH_INFINITE, monitor);
					jConfig.getPropertyChangeSupport().firePropertyChange(JasperReportsConfiguration.RESOURCE_LOADED,
							true, false);
					List<File> keys = new ArrayList<File>(fileTypes.keySet());
					for (File f : keys) {
						IFile[] fs = root.findFilesForLocationURI(f.toURI());
						if (!Misc.isNullOrEmpty(fs)) {
							for (IFile ifile : fs) {
								String id = null;
								String wsType = fileTypes.get(f);
								if (wsType.equals(ResourceDescriptor.TYPE_STYLE_TEMPLATE))
									id = "com.jaspersoft.studio.JRtxEditor";
								else if (wsType.equals(ResourceDescriptor.TYPE_JRXML))
									id = JrxmlEditor.JRXML_EDITOR_ID;
								if (id != null)
									IDE.setDefaultEditor(ifile, id);
								fileTypes.remove(f);
							}
						}
					}
				} catch (Exception e) {
					// e.printStackTrace();
				} finally {
					isRefreshing = false;
					UIUtils.getDisplay().asyncExec(new Runnable() {

						@Override
						public void run() {
							if (needNewRefresh)
								refresh();
						}
					});
				}
				return Status.OK_STATUS;
			}
		};
		job.setPriority(Job.LONG);
		job.setSystem(true);
		job.schedule(2000);
	}
}
