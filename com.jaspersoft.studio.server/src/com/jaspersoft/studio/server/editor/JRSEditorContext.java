/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.editor;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;

import com.jaspersoft.studio.data.storage.ADataAdapterStorage;
import com.jaspersoft.studio.data.storage.FileDataAdapterStorage;
import com.jaspersoft.studio.editor.context.AEditorContext;
import com.jaspersoft.studio.server.ServerManager;
import com.jaspersoft.studio.server.model.server.ServerProfile;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRPropertiesUtil;
import net.sf.jasperreports.engine.JRPropertiesUtil.PropertySuffix;
import net.sf.jasperreports.repo.RepositoryService;

public class JRSEditorContext extends AEditorContext {
	public static final String JRS_ID = "jrs";
	public static final String JRS_NAME = "JasperReports Server";

	@Override
	public String getName() {
		return JRS_NAME;
	}

	@Override
	protected void configRepositoryPaths(List<RepositoryService> list) {
		Set<String> rset = new HashSet<>();
		if (f.isLinked())
			add(list, rset, f.getRawLocation().toFile().getParentFile().getAbsolutePath());
		if (!f.getParent().isVirtual())
			add(list, rset, f.getParent().getLocation().toFile().getAbsolutePath());

		Path fpath = Paths.get(f.getLocationURI().toASCIIString());
		Path ppath = Paths.get(f.getProject().getLocationURI().toASCIIString());
		IContainer root = ResourcesPlugin.getWorkspace().getRoot();
		for (ServerProfile sp : ServerManager.getServerList()) {
			IResource r = root.findMember(sp.getProjectPath());
			if (r == null)
				continue;
			Path jrsp = Paths.get(r.getLocationURI().toASCIIString());
			if (jrsp.startsWith(ppath) && fpath.startsWith(jrsp)) {
				add(list, rset, sp.getProjectPath());
				break;
			}
		}
		add(list, rset, f.getProject().getLocation().toFile().getAbsolutePath());
	}

	@Override
	public void initClassloader() {
		// here, he should look into report unit, then into JRS (project) classpath
		super.initClassloader();
	}

	@Override
	public boolean needCompilation() {
		return false;
	}

	@Override
	public boolean saveOnPreview() {
		return true;
	}

	@Override
	public boolean hasBookmarks() {
		return false;
	}

	@Override
	public boolean hasExporterSettings() {
		return false;
	}

	@Override
	public boolean hasSortFields() {
		return false;
	}

	@Override
	public String jrVersion() {
		return "any";
	}

	@Override
	public Properties getJrProperties() {
		Properties props = new Properties();
		List<PropertySuffix> lst = JRPropertiesUtil.getInstance(DefaultJasperReportsContext.getInstance())
				.getProperties("");//$NON-NLS-1$
		for (PropertySuffix ps : lst)
			props.setProperty(ps.getKey(), ps.getValue());

		// here setup jr properties from jrs?

		return props;
	}

	@Override
	public boolean isDataAdapterStorage(ADataAdapterStorage storage) {
		return storage instanceof FileDataAdapterStorage
				&& ((FileDataAdapterStorage) storage).getProject().equals(f.getProject());
	}
}
