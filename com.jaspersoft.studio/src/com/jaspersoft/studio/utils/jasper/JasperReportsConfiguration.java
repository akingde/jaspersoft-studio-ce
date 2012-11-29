/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.utils.jasper;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import net.sf.jasperreports.data.AbstractClasspathAwareDataAdapterService;
import net.sf.jasperreports.eclipse.util.JavaProjectClassLoader;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.component.ComponentsBundle;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.fonts.FontFamily;
import net.sf.jasperreports.engine.fonts.SimpleFontExtensionHelper;
import net.sf.jasperreports.engine.util.CompositeClassloader;
import net.sf.jasperreports.engine.util.FileResolver;
import net.sf.jasperreports.engine.util.LocalJasperReportsContext;
import net.sf.jasperreports.extensions.DefaultExtensionsRegistry;
import net.sf.jasperreports.extensions.ExtensionsEnvironment;
import net.sf.jasperreports.repo.FileRepositoryPersistenceServiceFactory;
import net.sf.jasperreports.repo.FileRepositoryService;
import net.sf.jasperreports.repo.PersistenceServiceFactory;
import net.sf.jasperreports.repo.RepositoryService;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.util.IPropertyChangeListener;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.plugin.IEditorContributor;
import com.jaspersoft.studio.preferences.editor.properties.PropertyListFieldEditor;
import com.jaspersoft.studio.preferences.fonts.FontsPreferencePage;
import com.jaspersoft.studio.utils.FileUtils;

public class JasperReportsConfiguration extends LocalJasperReportsContext {

	/**
	 * The key which identified the file being edited
	 */
	public static final String REPORT_FILE = "REPORTFILEWIZARD"; //$NON-NLS-1$
	public static final String REPORT_DESIGN = "REPORTDESIGNWIZARD"; //$NON-NLS-1$

	private final class PreferenceListener implements IPropertyChangeListener {

		public void propertyChange(org.eclipse.jface.util.PropertyChangeEvent event) {
			String property = event.getProperty();
			if (property.equals(FontsPreferencePage.FPP_FONT_LIST)
					|| property.equals(PropertyListFieldEditor.NET_SF_JASPERREPORTS_JRPROPERTIES)) {
				refreshFonts = true;
				refreshBundles = true;
			}
		}
	}

	private class ClasspathListener implements PropertyChangeListener {

		@Override
		public void propertyChange(PropertyChangeEvent arg0) {
			refreshFonts = true;
			refreshBundles = true;
			try {
				DefaultExtensionsRegistry extensionsRegistry = new DefaultExtensionsRegistry();
				ExtensionsEnvironment.setSystemExtensionsRegistry(extensionsRegistry);
				ExtensionsEnvironment.setThreadExtensionsRegistry(extensionsRegistry);
			} catch (Throwable e) {
			}
		}
	}

	private ClasspathListener classpathlistener;
	private PreferenceListener preferenceListener;
	public static final IScopeContext INSTANCE_SCOPE = new InstanceScope();
	private IPreferencesService service;
	private String qualifier;
	private String[] lookupOrders;
	private IScopeContext[] contexts;

	public JasperReportsConfiguration(JasperReportsContext parent, IFile file) {
		super(parent);
		init(file);
	}

	public void init(IFile file) {
		service = Platform.getPreferencesService();
		qualifier = JaspersoftStudioPlugin.getUniqueIdentifier();
		IProject project = null;
		if (file != null) {
			put(IEditorContributor.KEY_FILE, file);
			project = file.getProject();
			if (project != null) {
				lookupOrders = new String[] { ProjectScope.SCOPE, InstanceScope.SCOPE };
				contexts = new IScopeContext[] { new ProjectScope(project), INSTANCE_SCOPE };
				try {
					if (project.getNature(JavaCore.NATURE_ID) != null) {
						ClassLoader tcl = Thread.currentThread().getContextClassLoader();
						javaclassloader = JavaProjectClassLoader.instance(JavaCore.create(project), tcl);
						ClassLoader cl = javaclassloader;
						cl = JaspersoftStudioPlugin.getDriversManager().getClassLoader(cl);
						cl = new CompositeClassloader(cl, this.getClass().getClassLoader());
						setClassLoader(cl);
						classpathlistener = new ClasspathListener();
						javaclassloader.addClasspathListener(classpathlistener);
					}
				} catch (CoreException e) {
					e.printStackTrace();
				}
			}
			initFileResolver(file);
		} else {
			lookupOrders = new String[] { InstanceScope.SCOPE };
			contexts = new IScopeContext[] { INSTANCE_SCOPE };
		}
		service.setDefaultLookupOrder(qualifier, null, lookupOrders);
		preferenceListener = new PreferenceListener();
		JaspersoftStudioPlugin.getInstance().addPreferenceListener(preferenceListener);
	}

	public void dispose() {
		JaspersoftStudioPlugin.getInstance().removePreferenceListener(preferenceListener);
		if (javaclassloader != null)
			javaclassloader.removeClasspathListener(classpathlistener);
	}

	private void initFileResolver(IFile file) {
		List<RepositoryService> list = getExtensions(RepositoryService.class);
		if (file != null) {
			list.add(new FileRepositoryService(this, file.getParent().getLocation().toFile().getAbsolutePath(), true));
			list.add(new FileRepositoryService(this, ".", true));
			list.add(new FileRepositoryService(this, file.getProject().getLocation().toFile().getAbsolutePath(), true));
		}
		setExtensions(RepositoryService.class, list);

		List<PersistenceServiceFactory> persistenceServiceFactoryList = getExtensions(PersistenceServiceFactory.class);
		if (persistenceServiceFactoryList != null) {
			persistenceServiceFactoryList.add(FileRepositoryPersistenceServiceFactory.getInstance());
		}
		setExtensions(PersistenceServiceFactory.class, persistenceServiceFactoryList);

		setFileResolver(new ProxyFileResolver());
	}

	public void put(String key, Object value) {
		setValue(key, value);
	}

	public Object get(String key) {
		return getValue(key);
	}

	public void remove(String key) {
		removeValue(key);
	}

	private ClassLoader classLoader;
	private FileResolver fileResolver;

	public ClassLoader getClassLoader() {
		return classLoader;
	}

	@Override
	public void setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
		super.setClassLoader(classLoader);
		put(AbstractClasspathAwareDataAdapterService.CURRENT_CLASS_LOADER, classLoader);
	}

	public FileResolver getFileResolver() {
		return fileResolver;
	}

	@Override
	public void setFileResolver(FileResolver fileResolver) {
		this.fileResolver = fileResolver;
		super.setFileResolver(fileResolver);
	}

	public static final String KEY_JASPERDESIGN = "JasperDesign";

	public JasperDesign getJasperDesign() {
		return (JasperDesign) get(KEY_JASPERDESIGN);
	}

	public void setJasperDesign(JasperDesign jd) {
		if (jd == null)
			remove(KEY_JASPERDESIGN);
		else
			put(KEY_JASPERDESIGN, jd);
	}

	public static final String KEY_JRPARAMETERS = "KEY_PARAMETERS";

	public void setJRParameters(Map<String, Object> value) {
		put(KEY_JRPARAMETERS, value);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getJRParameters() {
		return (Map<String, Object>) get(KEY_JRPARAMETERS);
	}

	@SuppressWarnings("unchecked")
	public <T> T get(String key, T def) {
		Object value = get(key);
		if (value != null && def != null && value.getClass().isAssignableFrom(def.getClass()))
			return (T) value;
		return def;
	}

	@Override
	public Map<String, String> getProperties() {
		Map<String, String> map = super.getProperties();

		if (map == null)
			map = new HashMap<String, String>();

		for (String key : map.keySet()) {
			String val = service.getString(qualifier, key, null, contexts);
			if (val != null)
				map.put(key, val);
		}

		return map;
	}

	public static final String PROPERTY_JRPROPERTY_PREFIX = "ireport.jrproperty.";
	private Properties props;

	@Override
	public String getProperty(String key) {
		if (props == null)
			props = new Properties();
		if (refreshFonts) {
			try {
				props = FileUtils.load(service.getString(qualifier, "net.sf.jasperreports.JRPROPERTIES", null, contexts));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String val = super.getProperty(key);
		if (val == null)
			val = service.getString(qualifier, key, null, contexts);
		if (val == null)
			val = service.getString(qualifier, PROPERTY_JRPROPERTY_PREFIX + key, null, contexts);
		if (val == null)
			val = props.getProperty(key);
		if (val == null)
			val = props.getProperty(PROPERTY_JRPROPERTY_PREFIX + key);

		// if (val == null)
		// return super.getProperty(key);
		return val;
	}

	public String getProperty(String key, String def) {
		String p = getProperty(key);
		if (p == null)
			return def;
		return p;
	}

	public Character getPropertyCharacter(String key) {
		String p = getProperty(key);
		if (p != null && !p.isEmpty())
			return new Character(p.charAt(0));
		return null;
	}

	public Boolean getPropertyBoolean(String key) {
		String p = getProperty(key);
		if (p != null)
			return Boolean.parseBoolean(p);
		return null;
	}

	public Boolean getPropertyBoolean(String key, boolean def) {
		Boolean p = getPropertyBoolean(key);
		if (p == null)
			return def;
		return p;
	}

	public Integer getPropertyInteger(String key) {
		String p = getProperty(key);
		if (p != null)
			return Integer.valueOf(p);
		return null;
	}

	public Integer getPropertyInteger(String key, int def) {
		Integer p = getPropertyInteger(key);
		if (p == null)
			return def;
		return p;
	}

	public Long getPropertyLong(String key) {
		String p = getProperty(key);
		if (p != null)
			return Long.valueOf(p);
		return null;
	}

	public Float getPropertyFloat(String key) {
		String p = getProperty(key);
		if (p != null)
			return Float.valueOf(p);
		return null;
	}

	public Double getPropertyDouble(String key) {
		String p = getProperty(key);
		if (p != null)
			return Double.valueOf(p);
		return null;
	}

	public Float getPropertyFloat(String key, float def) {
		Float p = getPropertyFloat(key);
		if (p == null)
			return def;
		return p;
	}

	private boolean refreshFonts = true;
	private boolean refreshBundles = true;
	private List<FontFamily> lst;
	private JavaProjectClassLoader javaclassloader;
	private List<ComponentsBundle> bundles;

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getExtensions(Class<T> extensionType) {
		ClassLoader oldCL = Thread.currentThread().getContextClassLoader();
		List<T> result = null;
		try {
			if (classLoader != null)
				Thread.currentThread().setContextClassLoader(classLoader);
			if (extensionType == FontFamily.class) {
				if (lst == null)
					lst = new ArrayList<FontFamily>();
				if (refreshFonts) {
					String strprop = getProperty(FontsPreferencePage.FPP_FONT_LIST);
					if (strprop != null) {
						lst.clear();
						List<FontFamily> fonts = SimpleFontExtensionHelper.getInstance().loadFontFamilies(this,
								new ByteArrayInputStream(strprop.getBytes()));
						if (fonts != null && !fonts.isEmpty())
							lst.addAll(fonts);
					}

					List<FontFamily> superlist = (List<FontFamily>) super.getExtensions(extensionType);
					if (superlist != null)
						lst.addAll(superlist);

					refreshFonts = false;
				}
				result = (List<T>) lst;
			} else if (extensionType == ComponentsBundle.class) {
				if (bundles == null || refreshBundles) {
					bundles = super.getExtensions(ComponentsBundle.class);
					// remove all duplicates
					Set<ComponentsBundle> components = new LinkedHashSet<ComponentsBundle>(bundles);
					bundles = new ArrayList<ComponentsBundle>(components);
					refreshBundles = false;
				}
				result = (List<T>) bundles;
			} else {
				result = super.getExtensions(extensionType);
			}
		} finally {
			Thread.currentThread().setContextClassLoader(oldCL);
		}
		return result;
	}
}
