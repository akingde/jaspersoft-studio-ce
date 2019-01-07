/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.export;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.jaspersoft.studio.property.metadata.PropertyMetadataRegistry;
import com.jaspersoft.studio.server.Activator;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.model.server.ServerProfile;
import com.jaspersoft.studio.server.publish.JrxmlPublishContributor;

import net.sf.jasperreports.annotations.properties.PropertyScope;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.properties.PropertyMetadata;
import net.sf.jasperreports.properties.StandardPropertyMetadata;

public class AExporter {
	public static Map<String, IFile> fileurimap = new HashMap<>();
	public static final String PROP_SERVERURL = "ireport.jasperserver.url"; //$NON-NLS-1$
	public static final String PROP_USER = "ireport.jasperserver.user"; //$NON-NLS-1$
	public static final String PROP_REPORTRESOURCE = "ireport.jasperserver.report.resource"; //$NON-NLS-1$
	public static final String PROP_REPORTUNIT = "ireport.jasperserver.reportUnit"; //$NON-NLS-1$
	public static final String COM_JASPERSOFT_STUDIO_REPORT_UNIT_DESCRIPTION = "com.jaspersoft.studio.report.unit.description"; //$NON-NLS-1$

	public static void initMetadata() {
		List<PropertyMetadata> pm = new ArrayList<>();

		StandardPropertyMetadata spm = new StandardPropertyMetadata();
		spm.setName(PROP_SERVERURL);
		spm.setLabel(Messages.AExporter_5);
		spm.setDescription(Messages.AExporter_6);
		spm.setValueType(URL.class.getName());
		List<PropertyScope> scopes = new ArrayList<>();
		scopes.add(PropertyScope.DATASET);
		spm.setScopes(scopes);
		spm.setCategory(Activator.SERVER_CATEGORY);
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(PROP_USER);
		spm.setLabel(Messages.AExporter_7);
		spm.setDescription(Messages.AExporter_8);
		spm.setValueType(String.class.getName());
		scopes.add(PropertyScope.DATASET);
		spm.setScopes(scopes);
		spm.setCategory(Activator.SERVER_CATEGORY);
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(PROP_REPORTRESOURCE);
		spm.setLabel(Messages.AExporter_9);
		spm.setDescription(Messages.AExporter_10);
		spm.setValueType(Activator.REPPATH);
		scopes.add(PropertyScope.DATASET);
		spm.setScopes(scopes);
		spm.setCategory(Activator.SERVER_CATEGORY);
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(PROP_REPORTUNIT);
		spm.setLabel(Messages.AExporter_11);
		spm.setDescription(Messages.AExporter_12);
		spm.setValueType(Activator.RUPATH);
		scopes.add(PropertyScope.DATASET);
		spm.setScopes(scopes);
		spm.setCategory(Activator.SERVER_CATEGORY);
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(JrxmlPublishContributor.COM_JASPERSOFT_JRS_DATA_SOURCE);
		spm.setLabel(Messages.AExporter_13);
		spm.setDescription(Messages.AExporter_14);
		spm.setValueType(Activator.DSPATH);
		scopes.add(PropertyScope.DATASET);
		spm.setScopes(scopes);
		spm.setCategory(Activator.SERVER_CATEGORY);
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(COM_JASPERSOFT_STUDIO_REPORT_UNIT_DESCRIPTION);
		spm.setLabel(Messages.AExporter_15);
		spm.setDescription(Messages.AExporter_16);
		spm.setValueType(String.class.getName());
		scopes.add(PropertyScope.REPORT);
		spm.setScopes(scopes);
		spm.setCategory(Activator.SERVER_CATEGORY);
		pm.add(spm);

		PropertyMetadataRegistry.addMetadata(pm);
	}

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
					f.setPersistentProperty(new QualifiedName(Activator.PLUGIN_ID, PROP_USER), encodeUsr(v));
				} catch (MalformedURLException | URISyntaxException e) {
					e.printStackTrace();
				}
			}
			f.setPersistentProperty(new QualifiedName(Activator.PLUGIN_ID, PROP_REPORTRESOURCE),
					res.getValue().getUriString());
		}
	}

	public static String encodeUsr(ServerProfile v) {
		String r = v.isUseSSO() ? v.getSsoUuid() : v.getUser();
		if (!Misc.isNullOrEmpty(v.getOrganisation()))
			r += "|" + v.getOrganisation(); //$NON-NLS-1$
		return r;
	}

	protected String getExtension(AMResource res) {
		return ""; //$NON-NLS-1$
	}

	protected IFile getTempFile(AMResource res, ResourceDescriptor rd, String fkeyname, String dextention,
			IProgressMonitor monitor) throws Exception {
		IFile f = fileurimap.get(fkeyname);
		if (path != null) {
			f = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
			fileurimap.put(fkeyname, f);
			return downloadFile(res, rd, f, monitor);
		}
		INode root = res.getRoot();
		IFolder ttroot = null;
		if (root != null && root instanceof MServerProfile)
			ttroot = ((MServerProfile) root).getTmpDir(monitor);
		else
			ttroot = FileUtils.getInProjectFolder(FileUtils.createTempDir().toURI(), monitor);
		String pfolder = rd.getParentFolder();
		if (pfolder.endsWith("_files"))
			pfolder = pfolder.substring(0, pfolder.lastIndexOf("_files"));
		IResource r = ttroot.findMember(pfolder);
		if (r != null && r instanceof IFile) {
			r.delete(true, monitor);
			r = null;
		}
		if (r == null || !r.exists())
			r = ttroot.getFolder(pfolder);
		IFolder troot = (IFolder) r;
		String newpath = getNewFileName(rd, dextention);
		r = troot.findMember(newpath);
		if (r != null && r instanceof IFolder) {
			r.delete(true, monitor);
			r = null;
		}
		if (r == null || !r.exists()) {
			f = troot.getFile(newpath);
			File file = f.getFullPath().toFile();
			if (f.getLocationURI() != null)
				file = f.getLocation().toFile();
			file.getParentFile().mkdirs();
			file.createNewFile();
			String ffp = file.toURI().toASCIIString();
			for (IProject p : ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
				String ppath = p.getLocationURI().toASCIIString() + "/"; //$NON-NLS-1$
				if (ffp.startsWith(ppath)) {
					p.refreshLocal(IResource.DEPTH_INFINITE, monitor);
					f = p.getFile(ffp.substring(ppath.length()));
				}
			}
		} else
			f = (IFile) r;
		fileurimap.put(fkeyname, f);
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
			if (fname.endsWith(".jpg") || fname.endsWith(".jpeg") || fname.endsWith(".gif") || fname.endsWith(".tiff")) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				return path;
		}
		if (!path.endsWith(dextention))
			path += dextention;
		return path;
	}
}
