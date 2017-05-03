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
import com.jaspersoft.studio.server.ic.ICParameterContributor;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.model.server.ServerProfile;
import com.jaspersoft.studio.server.publish.JrxmlPublishContributor;

import net.sf.jasperreports.annotations.properties.PropertyScope;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.properties.PropertyMetadata;
import net.sf.jasperreports.properties.StandardPropertyMetadata;

public class AExporter {
	public static Map<String, IFile> fileurimap = new HashMap<String, IFile>();
	public static final String PROP_SERVERURL = "ireport.jasperserver.url";
	public static final String PROP_USER = "ireport.jasperserver.user";
	public static final String PROP_REPORTRESOURCE = "ireport.jasperserver.report.resource";
	public static final String PROP_REPORTUNIT = "ireport.jasperserver.reportUnit";
	public static final String COM_JASPERSOFT_STUDIO_REPORT_UNIT_DESCRIPTION = "com.jaspersoft.studio.report.unit.description";

	public static void initMetadata() {
		List<PropertyMetadata> pm = new ArrayList<PropertyMetadata>();

		StandardPropertyMetadata spm = new StandardPropertyMetadata();
		spm.setName(PROP_SERVERURL);
		spm.setLabel("Server URL");
		spm.setDescription("URL for JasperReports Server.");
		spm.setValueType(URL.class.getName());
		List<PropertyScope> scopes = new ArrayList<PropertyScope>();
		scopes.add(PropertyScope.DATASET);
		spm.setScopes(scopes);
		spm.setCategory("com.jaspersoft.studio.jrs.category:JasperReports.server");
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(PROP_USER);
		spm.setLabel("User");
		spm.setDescription("User identity.");
		spm.setValueType(String.class.getName());
		scopes = new ArrayList<PropertyScope>();
		scopes.add(PropertyScope.DATASET);
		spm.setScopes(scopes);
		spm.setCategory("com.jaspersoft.studio.jrs.category:JasperReports.server");
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(PROP_REPORTRESOURCE);
		spm.setLabel("Report Resource Path");
		spm.setDescription("Resource path.");
		spm.setValueType(ICParameterContributor.ICPATH);
		scopes = new ArrayList<PropertyScope>();
		scopes.add(PropertyScope.DATASET);
		spm.setScopes(scopes);
		spm.setCategory("com.jaspersoft.studio.jrs.category:JasperReports.server");
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(PROP_REPORTUNIT);
		spm.setLabel("Report Unit Path");
		spm.setDescription("Report Unit path.");
		spm.setValueType(ICParameterContributor.ICPATH);
		scopes = new ArrayList<PropertyScope>();
		scopes.add(PropertyScope.DATASET);
		spm.setScopes(scopes);
		spm.setCategory("com.jaspersoft.studio.jrs.category:JasperReports.server");
		pm.add(spm);

		spm = new StandardPropertyMetadata();
		spm.setName(JrxmlPublishContributor.COM_JASPERSOFT_JRS_DATA_SOURCE);
		spm.setLabel("Report Unit Datasource Path");
		spm.setDescription("Report Unit data source path.");
		spm.setValueType(ICParameterContributor.ICPATH);
		scopes = new ArrayList<PropertyScope>();
		scopes.add(PropertyScope.DATASET);
		spm.setScopes(scopes);
		spm.setCategory("com.jaspersoft.studio.jrs.category:JasperReports.server");
		pm.add(spm);
		
		spm = new StandardPropertyMetadata();
		spm.setName(COM_JASPERSOFT_STUDIO_REPORT_UNIT_DESCRIPTION);
		spm.setLabel("Report Unit Description");
		spm.setDescription("Report unitnit description.");
		spm.setValueType(String.class.getName());
		scopes = new ArrayList<PropertyScope>();
		scopes.add(PropertyScope.REPORT);
		spm.setScopes(scopes);
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
		// if (res instanceof AFileResource)
		// return "." + ((AFileResource) res).getDefaultFileExtension();
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
		if (r == null || !r.exists())
			r = ttroot.getFolder(rd.getParentFolder());
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
