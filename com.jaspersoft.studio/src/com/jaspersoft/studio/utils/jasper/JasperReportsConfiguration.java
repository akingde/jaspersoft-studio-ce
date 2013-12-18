/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
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
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

import net.sf.jasperreports.components.ComponentsExtensionsRegistryFactory;
import net.sf.jasperreports.components.ComponentsManager;
import net.sf.jasperreports.data.AbstractClasspathAwareDataAdapterService;
import net.sf.jasperreports.eclipse.MScopedPreferenceStore;
import net.sf.jasperreports.eclipse.classpath.JavaProjectClassLoader;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.query.EmptyQueryExecuterFactoryBundle;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.component.ComponentManager;
import net.sf.jasperreports.engine.component.ComponentsBundle;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.fonts.FontFamily;
import net.sf.jasperreports.engine.fonts.SimpleFontExtensionHelper;
import net.sf.jasperreports.engine.query.JRQueryExecuterFactoryBundle;
import net.sf.jasperreports.engine.util.CompositeClassloader;
import net.sf.jasperreports.engine.util.FileResolver;
import net.sf.jasperreports.engine.util.LocalJasperReportsContext;
import net.sf.jasperreports.engine.util.MessageProviderFactory;
import net.sf.jasperreports.engine.util.ResourceBundleMessageProviderFactory;
import net.sf.jasperreports.extensions.DefaultExtensionsRegistry;
import net.sf.jasperreports.extensions.ExtensionsEnvironment;
import net.sf.jasperreports.functions.FunctionsBundle;
import net.sf.jasperreports.repo.FileRepositoryPersistenceServiceFactory;
import net.sf.jasperreports.repo.FileRepositoryService;
import net.sf.jasperreports.repo.PersistenceServiceFactory;
import net.sf.jasperreports.repo.RepositoryService;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.jasper.MapDesignConverter;
import com.jaspersoft.studio.preferences.editor.properties.PropertyListFieldEditor;
import com.jaspersoft.studio.preferences.fonts.FontsPreferencePage;
import com.jaspersoft.studio.preferences.fonts.utils.FontUtils;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.ModelUtils;

public class JasperReportsConfiguration extends LocalJasperReportsContext {

	// public static final IScopeContext INSTANCE_SCOPE = new InstanceScope();
	public static final String KEY_JASPERDESIGN = "JasperDesign";
	public static final String KEY_JRPARAMETERS = "KEY_PARAMETERS";

	private ClasspathListener classpathlistener;
	private PreferenceListener preferenceListener;
	// private IPreferencesService service;
	private String qualifier;
	// private String[] lookupOrders;
	// private IScopeContext[] contexts;
	private String[] fontList;
	private boolean refreshFonts = true;
	private boolean refreshBundles = true;
	private boolean refreshMessageProviderFactory = true;
	private boolean refreshFunctionsBundles = true;
	private List<FontFamily> lst;
	private JavaProjectClassLoader javaclassloader;
	private List<ComponentsBundle> bundles;
	private List<FunctionsBundle> functionsBundles;
	private MessageProviderFactory messageProviderFactory;

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
				fontList = null;
				props = null;
				getProperties();
				qExecutors = null;
			}
		}
	}

	private class ClasspathListener implements PropertyChangeListener {

		@Override
		public void propertyChange(PropertyChangeEvent arg0) {
			refreshFonts = true;
			refreshBundles = true;
			refreshMessageProviderFactory = true;
			refreshFunctionsBundles = true;
			functionsBundles = null;
			messageProviderFactory = null;
			fontList = null;
			try {
				DefaultExtensionsRegistry extensionsRegistry = new DefaultExtensionsRegistry();
				ExtensionsEnvironment.setSystemExtensionsRegistry(extensionsRegistry);
				// if (ExtensionsEnvironment.getThreadExtensionsRegistry() != null)
				// ExtensionsEnvironment.setThreadExtensionsRegistry(extensionsRegistry);
			} catch (Throwable e) {
				JaspersoftStudioPlugin.getInstance().logError(
						"Cannot complete operations successfully after a classpath change occurred.", e);
			}
		}
	}

	/**
	 * @param parent
	 * @param file
	 */
	public JasperReportsConfiguration(JasperReportsContext parent, IFile file) {
		super(parent);
		init(file);
	}

	private MScopedPreferenceStore pstore;

	public ScopedPreferenceStore getPrefStore() {
		return pstore;
	}

	public void init(IFile file) {
		IFile oldFile = (IFile) get(FileUtils.KEY_FILE);
		if (oldFile != null && oldFile == file)
			return;
		qualifier = JaspersoftStudioPlugin.getUniqueIdentifier();
		pstore = (MScopedPreferenceStore) JaspersoftStudioPlugin.getInstance().getPreferenceStore(file, qualifier);
		// if (service == null) {
		// service = Platform.getPreferencesService();

		// }
		initClassloader(file);
		IProject project = null;
		if (file != null) {
			put(FileUtils.KEY_FILE, file);
			project = file.getProject();
			if (project != null) {
				// lookupOrders = new String[] { ResourceScope.SCOPE, ProjectScope.SCOPE, InstanceScope.SCOPE };
				// contexts = new IScopeContext[] { new ResourceScope(file), new ProjectScope(project), INSTANCE_SCOPE };
			}
			initFileResolver(file);
		} else {
			// lookupOrders = new String[] { InstanceScope.SCOPE };
			// contexts = new IScopeContext[] { INSTANCE_SCOPE };
		}
		// service.setDefaultLookupOrder(qualifier, null, lookupOrders);
		if (preferenceListener == null) {
			preferenceListener = new PreferenceListener();
			JaspersoftStudioPlugin.getInstance().addPreferenceListener(preferenceListener);
		}
	}

	protected void initClassloader(IFile file) {
		try {
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			if (file != null) {
				IProject project = file.getProject();
				// insert a classloader for . resolving resource bundles

				if (project != null && project.getNature(JavaCore.NATURE_ID) != null) {
					javaclassloader = JavaProjectClassLoader.instance(JavaCore.create(project), cl);
					classpathlistener = new ClasspathListener();
					javaclassloader.addClasspathListener(classpathlistener);
					cl = javaclassloader;
				}
			}
			cl = JaspersoftStudioPlugin.getDriversManager().getClassLoader(cl);
			cl = new CompositeClassloader(cl, this.getClass().getClassLoader()) {
				@Override
				protected URL findResource(String name) {
					if (name.endsWith("GroovyEvaluator.groovy"))
						return null;
					return super.findResource(name);
				}

				@Override
				protected Class findClass(String className) throws ClassNotFoundException {
					if (className.endsWith("GroovyEvaluator"))
						throw new ClassNotFoundException(className);
					return super.findClass(className);
				}
			};
			setClassLoader(cl);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	private void initFileResolver(IFile file) {
		List<RepositoryService> list = getExtensions(RepositoryService.class);
		if (list == null)
			list = new ArrayList<RepositoryService>();
		if (file != null) {
			list.add(new FileRepositoryService(this, file.getParent().getLocation().toFile().getAbsolutePath(), true));
			// list.add(new FileRepositoryService(this, ".", true));
			list.add(new FileRepositoryService(this, file.getProject().getLocation().toFile().getAbsolutePath(), true));
		}
		setExtensions(RepositoryService.class, list);
		List<PersistenceServiceFactory> persistenceServiceFactoryList = getExtensions(PersistenceServiceFactory.class);
		if (persistenceServiceFactoryList != null)
			persistenceServiceFactoryList.add(FileRepositoryPersistenceServiceFactory.getInstance());
		setExtensions(PersistenceServiceFactory.class, persistenceServiceFactoryList);
		setFileResolver(new ProxyFileResolver());
	}

	@Override
	public void setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
		super.setClassLoader(classLoader);
		put(AbstractClasspathAwareDataAdapterService.CURRENT_CLASS_LOADER, classLoader);
	}

	public void dispose() {
		JaspersoftStudioPlugin.getInstance().removePreferenceListener(preferenceListener);
		if (javaclassloader != null)
			javaclassloader.removeClasspathListener(classpathlistener);
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

	public FileResolver getFileResolver() {
		return fileResolver;
	}

	@Override
	public void setFileResolver(FileResolver fileResolver) {
		this.fileResolver = fileResolver;
		super.setFileResolver(fileResolver);
	}

	public JasperDesign getJasperDesign() {
		return (JasperDesign) get(KEY_JASPERDESIGN);
	}

	public void setJasperDesign(JasperDesign jd) {
		if (jd == null)
			remove(KEY_JASPERDESIGN);
		else
			put(KEY_JASPERDESIGN, jd);
	}

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
		if (map != null && isPropsCached)
			return map;
		if (map == null) {
			map = new HashMap<String, String>();
			setPropertiesMap(map);
		}
		getJRProperties();
		if (!isPropsCached) {
			for (Object key : props.keySet()) {
				if (!(key instanceof String))
					continue;
				String val = getProperty((String) key);
				if (val != null)
					map.put((String) key, val);
			}
			isPropsCached = true;
		}
		pstore.setWithDefault(false);
		for (String key : map.keySet()) {
			String val = Misc.nullIfEmpty(pstore.getString(key));
			if (val != null)
				map.put(key, val);
		}
		pstore.setWithDefault(true);
		return map;
	}

	private boolean isPropsCached = false;
	public static final String PROPERTY_JRPROPERTY_PREFIX = "ireport.jrproperty.";
	private Properties props;

	private Properties getJRProperties() {
		if (props == null) {
			isPropsCached = false;
			try {
				pstore.setWithDefault(false);
				props = FileUtils.load(pstore.getString(PropertyListFieldEditor.NET_SF_JASPERREPORTS_JRPROPERTIES));
			} catch (IOException e) {
				e.printStackTrace();
				props = new Properties();
			} finally {
				pstore.setWithDefault(true);
			}
		}
		return props;
	}

	@Override
	public String getProperty(String key) {
		pstore.setWithDefault(false);
		String val = Misc.nullIfEmpty(pstore.getString(key));
		pstore.setWithDefault(true);
		if (val != null)
			return val;
		return super.getProperty(key);

		// let's try with ireport prefix prefix ? why we need it?
		// val = Misc.nullIfEmpty(pstore.getString(PROPERTY_JRPROPERTY_PREFIX + key));
		// if (val != null)
		// return val;
		// val = props.getProperty(PROPERTY_JRPROPERTY_PREFIX + key);
		// if (val != null)
		// return val;
		// return super.getProperty(PROPERTY_JRPROPERTY_PREFIX + key);
	}

	public String getPropertyDef(String key, String def) {
		String p = getProperty(key);
		if (p == null)
			p = pstore.getDefaultString(key);
		if (p == null)
			p = def;
		return p;
	}

	public String getProperty(String key, String def) {
		String p = getProperty(key);
		if (p == null)
			return def;
		return p;
	}

	public Character getPropertyCharacterDef(String key, Character def) {
		Character p = getPropertyCharacter(key);
		if (p == null) {
			String v = pstore.getDefaultString(key);
			if (v != null && !v.isEmpty())
				return new Character(v.charAt(0));
		}
		if (p == null)
			p = def;
		return p;
	}

	public Character getPropertyCharacter(String key, Character def) {
		Character p = getPropertyCharacter(key);
		if (p == null)
			p = def;
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

	public Boolean getPropertyBooleanDef(String key, boolean def) {
		Boolean p = getPropertyBoolean(key);
		if (p == null)
			p = pstore.getDefaultBoolean(key);
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

	public Integer getPropertyIntegerDef(String key, int def) {
		Integer p = getPropertyInteger(key);
		if (p == null)
			p = pstore.getDefaultInt(key);
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

	public Long getPropertyIntegerDef(String key, long def) {
		Long p = getPropertyLong(key);
		if (p == null)
			p = pstore.getDefaultLong(key);
		if (p == null)
			return def;
		return p;
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

	public Double getPropertyDoubleDef(String key, double def) {
		Double p = getPropertyDouble(key);
		if (p == null)
			p = pstore.getDefaultDouble(key);
		if (p == null)
			return def;
		return p;
	}

	public Float getPropertyFloat(String key, float def) {
		Float p = getPropertyFloat(key);
		if (p == null)
			return def;
		return p;
	}

	public Float getPropertyFloatDef(String key, float def) {
		Float p = getPropertyFloat(key);
		if (p == null)
			p = pstore.getDefaultFloat(key);
		if (p == null)
			return def;
		return p;
	}

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
						if (fonts != null && !fonts.isEmpty()) {
							for (FontFamily f : fonts)
								if (f != null)
									lst.add(f);
						}
					}

					List<FontFamily> superlist = (List<FontFamily>) super.getExtensions(extensionType);
					if (superlist != null) {
						for (FontFamily f : superlist)
							if (f != null)
								lst.add(f);
					}

					refreshFonts = false;
				}
				result = (List<T>) lst;
			} else if (extensionType == ComponentsBundle.class) {
				if (bundles == null || refreshBundles) {
					bundles = super.getExtensions(ComponentsBundle.class);
					// remove all duplicates
					Set<ComponentsBundle> components = new LinkedHashSet<ComponentsBundle>(bundles);
					bundles = new ArrayList<ComponentsBundle>(components);
					for (ComponentsBundle cb : bundles) {
						try {
							ComponentManager cm = cb.getComponentManager(ComponentsExtensionsRegistryFactory.MAP_COMPONENT_NAME);
							if (cm != null && cm instanceof ComponentsManager)
								((ComponentsManager) cm).setDesignConverter(MapDesignConverter.getInstance());
						} catch (Exception e) {
						}
					}
					refreshBundles = false;
				}
				result = (List<T>) bundles;
			} else if (extensionType == FunctionsBundle.class) {
				if (functionsBundles == null || refreshFunctionsBundles) {
					// We need to be sure that the resource bundles are fresh new
					// NOTE: Let's use this for now as quick solution, in case of
					// bad performances we'll have to fix this approach
					ResourceBundle.clearCache(getClassLoader());
					functionsBundles = super.getExtensions(FunctionsBundle.class);
					Set<FunctionsBundle> fBundlesSet = new LinkedHashSet<FunctionsBundle>(functionsBundles);
					functionsBundles = new ArrayList<FunctionsBundle>(fBundlesSet);
				}
				result = (List<T>) functionsBundles;
			} else if (extensionType == MessageProviderFactory.class) {
				if (messageProviderFactory == null || refreshMessageProviderFactory) {
					messageProviderFactory = new ResourceBundleMessageProviderFactory(getClassLoader());
					refreshFunctionsBundles = false;
				}
				result = (List<T>) Collections.singletonList(messageProviderFactory);
			} else if (extensionType == JRQueryExecuterFactoryBundle.class) {
				try {
					if (qExecutors == null) {
						qExecutors = new ArrayList<JRQueryExecuterFactoryBundle>();
						qExecutors.add(EmptyQueryExecuterFactoryBundle.getInstance(this));
					}
					result = (List<T>) qExecutors;
				} catch (Throwable e) {
					e.printStackTrace();
				}
			} else {
				try {
					result = super.getExtensions(extensionType);
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		} finally {
			Thread.currentThread().setContextClassLoader(oldCL);
		}
		return result;
	}

	private List<JRQueryExecuterFactoryBundle> qExecutors;
	private Map<Object, Object> map;

	public Map<Object, Object> getMap() {
		if (map == null)
			map = new HashMap<Object, Object>();
		return map;
	}

	public String[] getFontList() {
		if (fontList == null)
			fontList = FontUtils.stringToItems(ModelUtils.getFontNames(this));
		return fontList;
	}

	/**
	 * @return a default {@link JasperReportsConfiguration} instance, based on the {@link DefaultJasperReportsContext}.
	 */
	public static JasperReportsConfiguration getDefaultJRConfig() {
		return new JasperReportsConfiguration(DefaultJasperReportsContext.getInstance(), null);
	}

	private static JasperReportsConfiguration instance;

	/**
	 * @return a default {@link JasperReportsConfiguration} instance, based on the {@link DefaultJasperReportsContext}.
	 */
	public static JasperReportsConfiguration getDefaultInstance() {
		if (instance == null)
			instance = new JasperReportsConfiguration(DefaultJasperReportsContext.getInstance(), null);
		return instance;
	}
}
