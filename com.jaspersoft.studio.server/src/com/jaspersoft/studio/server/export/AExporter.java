/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.server.export;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.QualifiedName;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.server.Activator;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.model.AFileResource;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.model.server.ServerProfile;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;

public class AExporter {
	public static Map<String, IFile> fileurimap = new HashMap<String, IFile>();
	public static final String PROP_SERVERURL = "ireport.jasperserver.url";
	public static final String PROP_USER = "ireport.jasperserver.user";
	public static final String PROP_REPORTRESOURCE = "ireport.jasperserver.report.resource";
	public static final String PROP_REPORTUNIT = "ireport.jasperserver.reportUnit";

	protected IPath path;

	public AExporter(IPath path) {
		this.path = path;
	}

	public IFile exportToIFile(AMResource res, ResourceDescriptor rd, String fkeyname, IProgressMonitor monitor)
			throws Exception {
		IFile f = getTempFile(res, rd, fkeyname, getExtension(res), monitor);
		setServerLocation(res, f);
		return f;
	}

	public static void setServerLocation(AMResource res, IFile f) throws CoreException {
		if (f != null) {
			MServerProfile sp = (MServerProfile) res.getRoot();
			if (sp != null) {
				ServerProfile v = sp.getValue();
				try {
					f.setPersistentProperty(new QualifiedName(Activator.PLUGIN_ID, PROP_SERVERURL), v.getUrl());
					f.setPersistentProperty(new QualifiedName(Activator.PLUGIN_ID, PROP_USER),
							v.getUser() + (v.getOrganisation() != null ? "|" + v.getOrganisation() : ""));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
			}
			f.setPersistentProperty(new QualifiedName(Activator.PLUGIN_ID, PROP_REPORTRESOURCE),
					res.getValue().getUriString());
		}
	}

	protected String getExtension(AMResource res) {
		if (res instanceof AFileResource)
			return "." + ((AFileResource) res).getDefaultFileExtension();
		return "";
	}

	protected IFile getTempFile(AMResource res, ResourceDescriptor rd, String fkeyname, String dextention,
			IProgressMonitor monitor) throws Exception {
		IFile f = fileurimap.get(fkeyname);
		if (path != null) {
			f = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
			fileurimap.put(fkeyname, f);
			return downloadFile(res, rd, f, monitor);
		}
		// if (f == null) {
		INode root = res.getRoot();
		IFolder ttroot = null;
		if (root != null && root instanceof MServerProfile)
			ttroot = ((MServerProfile) root).getTmpDir(monitor);
		else
			ttroot = FileUtils.getInProjectFolder(FileUtils.createTempDir().toURI(), monitor);
		IResource r = ttroot.findMember(rd.getParentFolder());
		if (r != null && r instanceof IFile) {
			r.delete(true, monitor);
			r = null;
		}
		if (r == null || !r.exists()) {
			r = ttroot.getFolder(rd.getParentFolder());
			FileUtils.prepareFolder((IFolder) r, monitor);
		}
		IFolder troot = (IFolder) r;
		String path = getNewFileName(rd, dextention);
		r = troot.findMember(path);
		if (r != null && r instanceof IFolder) {
			r.delete(true, monitor);
			r = null;
		}
		if (r == null || !r.exists()) {
			f = troot.getFile(path); 
			File file = f.getFullPath().toFile();
			if (f.getLocationURI() != null)
				file = f.getLocation().toFile();
			file.getParentFile().mkdirs();
			file.createNewFile();
			String ffp = file.toURI().toASCIIString();
			for (IProject p : ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
				String ppath = p.getLocationURI().toASCIIString() + "/";
				if (ffp.startsWith(ppath)) {
					p.refreshLocal(IResource.DEPTH_INFINITE, monitor);
					f = p.getFile(ffp.substring(ppath.length()));
				}
			}
		} else
			f = (IFile) r;
		fileurimap.put(fkeyname, f);
		// }
		return downloadFile(res, rd, f, monitor);
	}

	private IFile downloadFile(AMResource res, ResourceDescriptor rd, IFile f, IProgressMonitor monitor) {
		try {
			WSClientHelper.getResource(monitor, res, rd, f.getRawLocation().toFile());
			f.refreshLocal(1, monitor);
		} catch (Exception e) {
			UIUtils.showError(e);
			return null;
		}
		return f;
	}

	public static String getNewFileName(ResourceDescriptor rd, String dextention) {
		String path = rd.getName();
		if (rd.getWsType().equals(ResourceDescriptor.TYPE_IMAGE)) {
			String fname = path.toLowerCase();
			if (fname.endsWith(".jpg") || fname.endsWith(".jpeg") || fname.endsWith(".gif") || fname.endsWith(".tiff"))
				return path;
		}
		if (!path.endsWith(dextention))
			path += dextention;
		return path;
	}
}
