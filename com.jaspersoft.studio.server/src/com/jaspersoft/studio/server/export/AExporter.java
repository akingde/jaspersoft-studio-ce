/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.server.export;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.QualifiedName;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.server.Activator;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.model.server.MServerProfile;

public abstract class AExporter {
	public static Map<String, IFile> fileurimap = new HashMap<String, IFile>();
	public static final String PROP_SERVERURL = "ireport.jasperserver.url";
	public static final String PROP_REPORTRESOURCE = "ireport.jasperserver.report.resource";
	public static final String PROP_REPORTUNIT = "ireport.jasperserver.reportUnit";

	public IFile exportToIFile(MResource res, ResourceDescriptor rd, String fkeyname, IProgressMonitor monitor) throws Exception {
		IFile f = getTempFile(res, rd, fkeyname, getExtension(), monitor);
		setServerLocation(res, f);
		return f;
	}

	public static void setServerLocation(MResource res, IFile f) throws CoreException {
		if (f != null) {
			MServerProfile sp = (MServerProfile) res.getRoot();
			if (sp != null)
				f.setPersistentProperty(new QualifiedName(Activator.PLUGIN_ID, PROP_SERVERURL), sp.getValue().getUrl());
			f.setPersistentProperty(new QualifiedName(Activator.PLUGIN_ID, PROP_REPORTRESOURCE), res.getValue().getUriString());
		}
	}

	public abstract String getExtension();

	protected IFile getTempFile(MResource res, ResourceDescriptor rd, String fkeyname, String dextention, IProgressMonitor monitor) throws Exception {
		IFile f = fileurimap.get(fkeyname);
		// if (f == null) {
		INode root = res.getRoot();
		IFolder troot = null;
		if (root != null && root instanceof MServerProfile)
			troot = ((MServerProfile) root).getTmpDir(monitor);
		else
			troot = FileUtils.getInProjectFolder(FileUtils.createTempDir().toURI(), monitor);
		IResource r = troot.findMember(rd.getParentFolder());
		if (r != null && r instanceof IFile) {
			r.delete(true, monitor);
			r = null;
		}
		if (r == null || !r.exists()) {
			r = troot.getFolder(rd.getParentFolder());
			FileUtils.prepareFolder((IFolder) r, monitor);
		}
		troot = (IFolder) r;
		String path = rd.getName();
		if (!path.endsWith(dextention)) {
			path += dextention;
		}
		r = troot.findMember(path);
		if (r != null && r instanceof IFolder) {
			r.delete(true, monitor);
			r = null;
		}
		if (r == null || !r.exists()) {
			f = troot.getFile(path);
			f.create(new ByteArrayInputStream("".getBytes("UTF-8")), true, monitor);
		} else
			f = (IFile) r;
		fileurimap.put(fkeyname, f);
		// }
		try {
			WSClientHelper.getResource(res, rd, new File(f.getRawLocationURI()));
			f.refreshLocal(1, monitor);
		} catch (Exception e) {
			UIUtils.showError(e);
			return null;
		}
		return f;
	}
}
