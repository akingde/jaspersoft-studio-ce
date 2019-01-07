/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.editor;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.JavaCore;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.storage.ADataAdapterStorage;
import com.jaspersoft.studio.data.storage.FileDataAdapterStorage;
import com.jaspersoft.studio.editor.context.AEditorContext;
import com.jaspersoft.studio.editor.context.JSSClasspathListener;
import com.jaspersoft.studio.server.ServerManager;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.server.ServerProfile;

import net.sf.jasperreports.eclipse.classpath.JavaProjectClassLoader;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRPropertiesUtil;
import net.sf.jasperreports.engine.JRPropertiesUtil.PropertySuffix;
import net.sf.jasperreports.engine.util.CompositeClassloader;
import net.sf.jasperreports.repo.RepositoryService;

public class JRSEditorContext extends AEditorContext {
	public static final String JRS_ID = "jrs"; //$NON-NLS-1$
	public static final String JRS_NAME = Messages.JRSEditorContext_1;

	@Override
	public String getName() {
		return JRS_NAME;
	}

	@Override
	public List<String> getRepositoryRoots() {
		Path fpath = Paths.get(f.getLocation().toFile().getAbsolutePath());
		Path ppath = Paths.get(f.getProject().getLocation().toFile().getAbsolutePath());
		IContainer root = ResourcesPlugin.getWorkspace().getRoot();
		for (ServerProfile sp : ServerManager.getServerList()) {
			if (sp.getProjectPath() == null)
				continue;
			IResource r = root.findMember(sp.getProjectPath());
			if (r == null)
				continue;
			Path jrsp = Paths.get(r.getLocation().toFile().getAbsolutePath());
			if (jrsp.startsWith(ppath) && fpath.startsWith(jrsp)) {
				List<String> res = new ArrayList<>();
				res.add(jrsp.toAbsolutePath().toString());
				return res;
			}
		}
		return super.getRepositoryRoots();
	}

	@Override
	protected void configRepositoryPaths(List<RepositoryService> list) {
		Set<String> rset = new HashSet<>();
		if (f.isLinked())
			add(list, rset, f.getRawLocation().toFile().getParentFile().getAbsolutePath());
		if (!f.getParent().isVirtual())
			add(list, rset, f.getParent().getLocation().toFile().getAbsolutePath());

		Path fpath = Paths.get(f.getLocation().toFile().getAbsolutePath());
		Path ppath = Paths.get(f.getProject().getLocation().toFile().getAbsolutePath());
		IContainer root = ResourcesPlugin.getWorkspace().getRoot();
		for (ServerProfile sp : ServerManager.getServerList()) {
			if (sp.getProjectPath() == null)
				continue;
			IResource r = root.findMember(sp.getProjectPath());
			if (r == null)
				continue;
			Path jrsp = Paths.get(r.getLocation().toFile().getAbsolutePath());
			if (jrsp.startsWith(ppath) && fpath.startsWith(jrsp)) {
				add(list, rset, r.getLocation().toOSString());
				break;
			}
		}
		add(list, rset, f.getProject().getLocation().toFile().getAbsolutePath());
	}

	@Override
	public void initClassloader() {
		if (javaclassloader != null && classpathlistener != null) {
			javaclassloader.removeClasspathListener(classpathlistener);
			jConf.remove(JavaProjectClassLoader.JAVA_PROJECT_CLASS_LOADER_KEY);
		}
		if (cLoader != null) {
			cLoader.dispose();
			cLoader = null;
		}
		try {
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			if (f != null) {
				cl = createJrsClassLoader(cl);

				IProject project = f.getProject();
				if (project != null && project.getNature(JavaCore.NATURE_ID) != null) {
					javaclassloader = JavaProjectClassLoader.instance(JavaCore.create(project), cl);
					jConf.put(JavaProjectClassLoader.JAVA_PROJECT_CLASS_LOADER_KEY, javaclassloader);
					classpathlistener = new JSSClasspathListener(this, jConf);
					javaclassloader.addClasspathListener(classpathlistener);
					cl = javaclassloader;
				}
			}
			cl = JaspersoftStudioPlugin.getDriversManager().getClassLoader(cl);
			cl = new CompositeClassloader(cl, this.getClass().getClassLoader()) {
				@Override
				protected URL findResource(String name) {
					if (name.endsWith("GroovyEvaluator.groovy")) //$NON-NLS-1$
						return null;
					return super.findResource(name);
				}

				@Override
				protected Class<?> findClass(String className) throws ClassNotFoundException {
					if (className.endsWith("GroovyEvaluator")) //$NON-NLS-1$
						throw new ClassNotFoundException(className);
					return super.findClass(className);
				}
			};
			setClassLoader(cl);
		} catch (

		CoreException e) {
			e.printStackTrace();
		}
	}

	private JrsClassLoader cLoader;

	private ClassLoader createJrsClassLoader(ClassLoader cl) {
		cLoader = new JrsClassLoader(cl, f.getParent());
		return cLoader;
	}

	@Override
	public void dispose() {
		super.dispose();
		cLoader.dispose();
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
		return "any"; //$NON-NLS-1$
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
