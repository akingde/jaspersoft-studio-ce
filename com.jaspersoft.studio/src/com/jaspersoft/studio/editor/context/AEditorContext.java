/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.context;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.JavaCore;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.storage.ADataAdapterStorage;
import com.jaspersoft.studio.editor.preview.actions.RunStopAction;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.jasper.JSSFileRepositoryService;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.data.AbstractClasspathAwareDataAdapterService;
import net.sf.jasperreports.eclipse.MScopedPreferenceStore;
import net.sf.jasperreports.eclipse.classpath.JavaProjectClassLoader;
import net.sf.jasperreports.eclipse.util.FilePrefUtil;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.util.CompositeClassloader;
import net.sf.jasperreports.repo.DefaultRepositoryService;
import net.sf.jasperreports.repo.FileRepositoryPersistenceServiceFactory;
import net.sf.jasperreports.repo.FileRepositoryService;
import net.sf.jasperreports.repo.PersistenceServiceFactory;
import net.sf.jasperreports.repo.RepositoryService;

public class AEditorContext {
	public static final String NAME = "project"; //$NON-NLS-1$
	public static final String EDITOR_CONTEXT = "editor.context"; //$NON-NLS-1$
	protected IFile f;
	protected JasperReportsConfiguration jConf;
	private String id = NAME;

	public final void setId(String id) {
		this.id = id;
	}

	public final String getId() {
		return id;
	}

	public void init(IFile f, JasperReportsConfiguration jConf) {
		this.f = f;
		this.jConf = jConf;
	}

	public String getName() {
		return Messages.AEditorContext_2;
	}

	public void dispose() {
		if (javaclassloader != null)
			javaclassloader.removeClasspathListener(classpathlistener);
		jConf.remove(JavaProjectClassLoader.JAVA_PROJECT_CLASS_LOADER_KEY);
	}

	private List<RepositoryService> repositoryServices;

	public List<RepositoryService> getRepositoryServices() {
		return repositoryServices;
	}

	public void configureRepositoryService() {
		List<RepositoryService> list = jConf.getExtensions(RepositoryService.class);
		if (list == null)
			list = new ArrayList<>();
		if (f != null) {
			Set<RepositoryService> toDel = new HashSet<>();
			for (RepositoryService rs : list)
				if (rs instanceof FileRepositoryService)
					toDel.add(rs);
			list.removeAll(toDel);

			configRepositoryPaths(list);
		}
		setupProxy(list);
		repositoryServices = new ArrayList<>();
		repositoryServices.add(new JSSFileRepositoryService(jConf, list));
		jConf.setExtensions(RepositoryService.class, repositoryServices);
		List<PersistenceServiceFactory> persistenceServiceFactoryList = jConf
				.getExtensions(PersistenceServiceFactory.class);
		if (persistenceServiceFactoryList != null)
			persistenceServiceFactoryList.add(FileRepositoryPersistenceServiceFactory.getInstance());
		jConf.setExtensions(PersistenceServiceFactory.class, persistenceServiceFactoryList);
	}

	protected void configRepositoryPaths(List<RepositoryService> list) {
		Set<String> rset = new HashSet<>();
		if (f.isLinked())
			add(list, rset, f.getRawLocation().toFile().getParentFile().getAbsolutePath());
		if (!f.getParent().isVirtual())
			add(list, rset, f.getParent().getLocation().toFile().getAbsolutePath());
		add(list, rset, f.getProject().getLocation().toFile().getAbsolutePath());
	}

	protected String add(List<RepositoryService> list, Set<String> rset, String root) {
		if (rset.contains(root))
			return null;
		rset.add(root);
		list.add(new FileRepositoryService(jConf, root, true));
		return root;
	}

	protected void setupProxy(List<RepositoryService> rs) {
		for (int i = 0; i < rs.size(); i++) {
			RepositoryService r = rs.get(i);
			if (r instanceof DefaultRepositoryService) {
				if (jssDRepService == null)
					jssDRepService = new JSSDefaultRepositoryService(jConf);
				rs.set(i, jssDRepService);
				break;
			}
		}
	}

	private JSSDefaultRepositoryService jssDRepService;
	protected ClassLoader classLoader;
	protected JavaProjectClassLoader javaclassloader;
	protected JSSClasspathListener classpathlistener;

	public ClassLoader getClassLoader() {
		return classLoader;
	}

	public void initClassloader() {
		if (javaclassloader != null && classpathlistener != null) {
			javaclassloader.removeClasspathListener(classpathlistener);
			jConf.remove(JavaProjectClassLoader.JAVA_PROJECT_CLASS_LOADER_KEY);
		}
		try {
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			if (f != null) {
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
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	public void setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
		List<RepositoryService> rs = jConf.getExtensions(RepositoryService.class);
		for (RepositoryService r : rs)
			if (r instanceof DefaultRepositoryService)
				((DefaultRepositoryService) r).setClassLoader(classLoader);
		jConf.put(AbstractClasspathAwareDataAdapterService.CURRENT_CLASS_LOADER, classLoader);
	}

	public void refreshClasspath() {
		classpathlistener.propertyChange(null);
	}

	public Properties getJrProperties() {
		Properties props = null;
		MScopedPreferenceStore pStore = jConf.getPrefStore();
		try {
			pStore.setWithDefault(false);
			props = FileUtils.load(pStore.getString(FilePrefUtil.NET_SF_JASPERREPORTS_JRPROPERTIES));
		} catch (IOException e) {
			e.printStackTrace();
			props = new Properties();
		} finally {
			pStore.setWithDefault(true);
		}
		return props;
	}

	public boolean needCompilation() {
		return true;
	}

	public boolean saveOnPreview() {
		return false;
	}

	public boolean hasBookmarks() {
		return true;
	}

	public boolean hasExporterSettings() {
		return true;
	}

	public boolean hasSortFields() {
		return true;
	}

	public String jrVersion() {
		return "any"; //$NON-NLS-1$
	}

	public boolean isDataAdapterStorage(ADataAdapterStorage storage) {
		return true;
	}

	public String getDefaultRunMode() {
		return RunStopAction.MODERUN_LOCAL;
	}

	public boolean isAllowOtherRunners() {
		return true;
	}
}
