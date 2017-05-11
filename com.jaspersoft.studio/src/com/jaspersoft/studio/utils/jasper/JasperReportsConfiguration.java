/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.utils.jasper;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import com.jaspersoft.studio.ExternalStylesManager;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.jasper.JSSReportConverter;
import com.jaspersoft.studio.jasper.LazyImageConverter;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.style.MStyleTemplate;
import com.jaspersoft.studio.preferences.fonts.FontsPreferencePage;
import com.jaspersoft.studio.preferences.fonts.utils.FontUtils;
import com.jaspersoft.studio.prm.ParameterSet;
import com.jaspersoft.studio.prm.ParameterSetProvider;
import com.jaspersoft.studio.property.JSSStyleResolver;
import com.jaspersoft.studio.utils.ExpressionUtil;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.widgets.framework.manager.WidgetsDefinitionManager;

import net.sf.jasperreports.data.AbstractClasspathAwareDataAdapterService;
import net.sf.jasperreports.data.BuiltinDataFileServiceFactory;
import net.sf.jasperreports.data.DataAdapterParameterContributorFactory;
import net.sf.jasperreports.eclipse.IDisposeListener;
import net.sf.jasperreports.eclipse.MScopedPreferenceStore;
import net.sf.jasperreports.eclipse.classpath.JavaProjectClassLoader;
import net.sf.jasperreports.eclipse.util.FilePrefUtil;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.HttpUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.eclipse.util.query.EmptyQueryExecuterFactoryBundle;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRReportTemplate;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.component.ComponentsBundle;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignReportTemplate;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.fonts.FontExtensionsCollector;
import net.sf.jasperreports.engine.fonts.FontFamily;
import net.sf.jasperreports.engine.fonts.FontSet;
import net.sf.jasperreports.engine.fonts.SimpleFontExtensionHelper;
import net.sf.jasperreports.engine.query.JRQueryExecuterFactoryBundle;
import net.sf.jasperreports.engine.util.CompositeClassloader;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRResourcesUtil;
import net.sf.jasperreports.engine.util.LocalJasperReportsContext;
import net.sf.jasperreports.engine.util.MessageProviderFactory;
import net.sf.jasperreports.engine.util.ResourceBundleMessageProviderFactory;
import net.sf.jasperreports.functions.FunctionsBundle;
import net.sf.jasperreports.repo.DefaultRepositoryService;
import net.sf.jasperreports.repo.FileRepositoryPersistenceServiceFactory;
import net.sf.jasperreports.repo.FileRepositoryService;
import net.sf.jasperreports.repo.PersistenceServiceFactory;
import net.sf.jasperreports.repo.RepositoryService;

public class JasperReportsConfiguration extends LocalJasperReportsContext implements JasperReportsContext {

	public static final String KEY_JASPERDESIGN = "JasperDesign";

	public static final String PROPERTY_JRPROPERTY_PREFIX = "ireport.jrproperty.";

	public static final String KEY_JRPARAMETERS = "KEY_PARAMETERS";

	/**
	 * Key used to store the drawer used to paint the JRElements, it is stored in the configuration to be easily
	 * accessible
	 */
	public static final String KEY_DRAWER = "REPORT_DRAWER";

	/**
	 * Key used to store the report converter used to paint the JRElements, it is stored in the configuration to be easily
	 * accessible
	 */
	public static final String KEY_CONVERTER = "REPORT_CONVERTER";

	/**
	 * Key of the event that must be fired on the {@link JasperReportsConfiguration} to notify that a physical resource
	 * not available before was loaded and can be used. Doing this we can refresh some resources on the editor (ie image &
	 * styles) when new resource are available
	 */
	public static final String RESOURCE_LOADED = "RESOURCE_LOADED";

	/**
	 * The key which identified the file being edited
	 */
	public static final String REPORT_FILE = "REPORTFILEWIZARD"; //$NON-NLS-1$

	public static final String REPORT_DESIGN = "REPORTDESIGNWIZARD"; //$NON-NLS-1$

	private final class PreferenceListener implements IPropertyChangeListener {

		public void propertyChange(org.eclipse.jface.util.PropertyChangeEvent event) {
			String property = event.getProperty();
			if (property.equals(FontsPreferencePage.FPP_FONT_LIST)) {
				refreshFonts();
				refreshBundles = true;
			} else if (property.equals(FilePrefUtil.NET_SF_JASPERREPORTS_JRPROPERTIES)) {
				refreshBundles = true;
				isPropsCached = false;
				getProperties();
				qExecutors = null;
			} else if (prmProvider != null && property.startsWith(ParameterSet.PARAMETER_SET)) {
				prmProvider.reset();
			} else if (property.startsWith("com.jaspersoft.studio.")) {
				isPropsCached = false;
				getProperties();
			}
			propertyChangeSupport.firePropertyChange(new PropertyChangeEvent(this, "preferences", null, event));
		}
	}

	/**
	 * When an event of resource loaded happen it rebuild the extrenral styles in the report drawer and trigger a refresh
	 * of the editor
	 * 
	 * @author Orlandin Marco
	 *
	 */
	private final class ResourceLoadedListener implements PropertyChangeListener {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if (evt.getPropertyName().equals(RESOURCE_LOADED)) {
				//clear the image cache
				LazyImageConverter.getInstance().removeCachedImages(JasperReportsConfiguration.this);
				// clear the style cache of this configuration, since a resource could be changed for it
				// and styles need to be loaded another time
				ExternalStylesManager.removeCachedStyles(JasperReportsConfiguration.this);
				// Not sure if the resource is a style, so this call will regenerate first the styles
				// and trigger a complete refresh of the editor. Doing so it will cover every case of
				// late loading of a resource
				refreshCachedStyles();

				// Fire an event to the template style to ask an update of the nodes
				JRReportTemplate[] templates = getJasperDesign().getTemplates();
				if (templates != null) {
					for (int i = 0; i < templates.length; i++) {
						((JRDesignReportTemplate) templates[i]).getEventSupport()
								.firePropertyChange(MStyleTemplate.FORCE_UPDATE_CHILDREN, null, null);
					}
				}
			}
		}

	}

	private class ClasspathListener implements PropertyChangeListener {

		@Override
		public void propertyChange(PropertyChangeEvent arg0) {
			initClassloader((IFile) get(FileUtils.KEY_FILE));
			refreshFonts = true;
			refreshBundles = true;
			refreshMessageProviderFactory = true;
			refreshFunctionsBundles = true;
			functionsBundles = null;
			messageProviderFactory = null;
			fontList = null;
			ExpressionUtil.removeAllReportInterpreters(JasperReportsConfiguration.this);
			propertyChangeSupport.firePropertyChange(new PropertyChangeEvent(this, "classpath", null, arg0));
			// try {
			// DefaultExtensionsRegistry extensionsRegistry = new DefaultExtensionsRegistry();
			// ExtensionsEnvironment.setSystemExtensionsRegistry(extensionsRegistry);
			// } catch (Throwable e) {
			// JaspersoftStudioPlugin.getInstance().logError(
			// "Cannot complete operations successfully after a classpath change occurred.", e);
			// }
			// Clear the old extensions
			// JDTUtils.clearJRRegistry(classLoader);
			JasperDesign jd = getJasperDesign();
			if (jd != null) {
				List<JRDesignElement> allElements = ModelUtils.getAllGElements(jd);
				for (JRDesignElement element : allElements) {
					element.getEventSupport().firePropertyChange(MGraphicElement.FORCE_GRAPHICAL_REFRESH, true, false);
				}
			}
		}
	}

	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	private ClasspathListener classpathlistener;
	private PreferenceListener preferenceListener;
	private ResourceLoadedListener resourceLoadedListener;
	private String qualifier;
	private String[] fontList;
	private boolean refreshFonts = true;
	private boolean refreshBundles = true;
	private boolean refreshMessageProviderFactory = true;
	private boolean refreshFunctionsBundles = true;
	private FontExtensionsCollector lst;
	private JavaProjectClassLoader javaclassloader;
	private List<ComponentsBundle> bundles;
	private List<FunctionsBundle> functionsBundles;
	private MessageProviderFactory messageProviderFactory;
	private static JasperReportsConfiguration instance;
	private List<RepositoryService> repositoryServices;
	private List<JRQueryExecuterFactoryBundle> qExecutors;
	private Map<Object, Object> map;
	private MScopedPreferenceStore pstore;
	private List<IDisposeListener> toDispose;
	private ClassLoader classLoader;
	private boolean isPropsCached = false;
	private ParameterSetProvider prmProvider;

	protected JSSStyleResolver resolver;

	/**
	 * @param parent
	 * @param file
	 */
	public JasperReportsConfiguration(JasperReportsContext parent, IFile file) {
		super(parent);
		init(file);
		resolver = new JSSStyleResolver(this);
	}

	public ScopedPreferenceStore getPrefStore() {
		return pstore;
	}

	public PropertyChangeSupport getPropertyChangeSupport() {
		return propertyChangeSupport;
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
			put(FileUtils.KEY_IPROJECT, project);
			if (project != null) {
				// lookupOrders = new String[] { ResourceScope.SCOPE, ProjectScope.SCOPE, InstanceScope.SCOPE };
				// contexts = new IScopeContext[] { new ResourceScope(file), new ProjectScope(project), INSTANCE_SCOPE };
			}
			initRepositoryService(file);
		} else {
			// lookupOrders = new String[] { InstanceScope.SCOPE };
			// contexts = new IScopeContext[] { INSTANCE_SCOPE };
		}
		// file changed, reset properties
		isPropsCached = false;

		// service.setDefaultLookupOrder(qualifier, null, lookupOrders);
		if (preferenceListener == null) {
			preferenceListener = new PreferenceListener();
			JaspersoftStudioPlugin.getInstance().addPreferenceListener(preferenceListener);
		}

		if (resourceLoadedListener == null) {
			resourceLoadedListener = new ResourceLoadedListener();
			getPropertyChangeSupport().addPropertyChangeListener(resourceLoadedListener);
		}
	}

	protected void initClassloader(IFile file) {
		if (javaclassloader != null && classpathlistener != null) {
			javaclassloader.removeClasspathListener(classpathlistener);
			remove(JavaProjectClassLoader.JAVA_PROJECT_CLASS_LOADER_KEY);
		}
		try {
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			if (file != null) {
				IProject project = file.getProject();
				if (project != null && project.getNature(JavaCore.NATURE_ID) != null) {
					javaclassloader = JavaProjectClassLoader.instance(JavaCore.create(project), cl);
					put(JavaProjectClassLoader.JAVA_PROJECT_CLASS_LOADER_KEY, javaclassloader);
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
				protected Class<?> findClass(String className) throws ClassNotFoundException {
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

	private void initRepositoryService(IFile file) {
		List<RepositoryService> list = getExtensions(RepositoryService.class);
		if (list == null)
			list = new ArrayList<RepositoryService>();
		if (file != null) {
			Set<String> rset = new HashSet<String>();
			if (file.isLinked()) {
				add(list, rset, file.getRawLocation().toFile().getParentFile().getAbsolutePath());
			}
			if (!file.getParent().isVirtual()) {
				add(list, rset, file.getParent().getLocation().toFile().getAbsolutePath());
			}
			add(list, rset, file.getProject().getLocation().toFile().getAbsolutePath());
		}
		repositoryServices = new ArrayList<RepositoryService>();
		repositoryServices.add(new JSSFileRepositoryService(this, list));
		setExtensions(RepositoryService.class, repositoryServices);
		List<PersistenceServiceFactory> persistenceServiceFactoryList = getExtensions(PersistenceServiceFactory.class);
		if (persistenceServiceFactoryList != null)
			persistenceServiceFactoryList.add(FileRepositoryPersistenceServiceFactory.getInstance());
		setExtensions(PersistenceServiceFactory.class, persistenceServiceFactoryList);
	}

	public JSSFileRepositoryService getFileRepositoryService() {
		if (repositoryServices != null)
			for (RepositoryService rs : repositoryServices)
				if (rs instanceof JSSFileRepositoryService)
					return (JSSFileRepositoryService) rs;
		return null;
	}

	private String add(List<RepositoryService> list, Set<String> rset, String root) {
		if (rset.contains(root))
			return null;
		rset.add(root);
		list.add(new FileRepositoryService(this, root, true));
		return root;
	}

	@Override
	public void setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
		super.setClassLoader(classLoader);
		put(AbstractClasspathAwareDataAdapterService.CURRENT_CLASS_LOADER, classLoader);
	}

	public void dispose() {
		ExpressionUtil.removeAllReportInterpreters(this);
		ExternalStylesManager.removeCachedStyles(this);
		LazyImageConverter.getInstance().removeCachedImages(this);
		WidgetsDefinitionManager.disposedConfiguration(this);
		JaspersoftStudioPlugin.getInstance().removePreferenceListener(preferenceListener);
		getPropertyChangeSupport().removePropertyChangeListener(resourceLoadedListener);
		if (javaclassloader != null)
			javaclassloader.removeClasspathListener(classpathlistener);
		for (PropertyChangeListener l : Arrays.asList(propertyChangeSupport.getPropertyChangeListeners())) {
			propertyChangeSupport.removePropertyChangeListener(l);
		}
		if (toDispose != null)
			for (IDisposeListener d : toDispose)
				d.dispose();
	}

	public void addDisposeListener(IDisposeListener listener) {
		if (toDispose == null)
			toDispose = new ArrayList<IDisposeListener>();
		toDispose.add(listener);
	}

	public void removeDisposeListener(IDisposeListener listener) {
		if (toDispose == null)
			return;
		toDispose.remove(listener);
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

	public ClassLoader getClassLoader() {
		return classLoader;
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
		if (value != null) {
			if (prmProvider == null)
				prmProvider = new ParameterSetProvider(this);
			prmProvider.initParameterValues(value);
		}
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
		if (isPropsCached)
			return getPropertiesMap();
		setPropertiesMap(null);
		Map<String, String> smap = super.getProperties();
		Map<String, String> propmap = new HashMap<String, String>();
		if (smap != null && !smap.isEmpty())
			propmap.putAll(smap);
		setPropertiesMap(propmap);
		// get properties from eclipse stored jr properties (eclipse, project, file level)
		Properties props = getJRProperties();
		for (Object key : props.keySet()) {
			if (!(key instanceof String))
				continue;
			String val = props.getProperty((String) key);
			if (val != null)
				propmap.put((String) key, val);
		}
		isPropsCached = true;
		// let's look also into the preferences maybe there are some properties
		pstore.setWithDefault(false);
		for (String key : propmap.keySet()) {
			String val = Misc.nullIfEmpty(pstore.getString(key));
			if (val != null)
				propmap.put(key, val);
		}
		pstore.setWithDefault(true);
		return propmap;
	}

	private Properties getJRProperties() {
		Properties props = null;
		try {
			pstore.setWithDefault(false);
			props = FileUtils.load(pstore.getString(FilePrefUtil.NET_SF_JASPERREPORTS_JRPROPERTIES));
		} catch (IOException e) {
			e.printStackTrace();
			props = new Properties();
		} finally {
			pstore.setWithDefault(true);
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
		if (p == null) {
			p = pstore.getDefaultBoolean(key);
		}
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

	/**
	 * Return the font extension both by resolving the property of the current project and from the commons extension. If
	 * it is available instead of request the extension from the superclass it search it in the common cache
	 * 
	 * @return a not null font extension
	 */
	private List<FontFamily> getExtensionFonts() {
		readFonts();
		return lst != null ? lst.getFontFamilies() : null;
	}

	private List<FontSet> getExtensionFontSets() {
		readFonts();
		return lst != null ? lst.getFontSets() : null;
	}

	protected void readFonts() {
		if (refreshFonts) {
			lst = new FontExtensionsCollector();

			List<FontFamily> superlist = super.getExtensions(FontFamily.class);
			if (superlist != null) {
				for (FontFamily f : superlist)
					if (f != null)
						lst.getFontFamilies().add(f);
			}
			List<FontSet> sets = super.getExtensions(FontSet.class);
			if (sets != null) {
				for (FontSet f : sets)
					if (f != null)
						lst.getFontSets().add(f);
			}

			String strprop = getProperty(FontsPreferencePage.FPP_FONT_LIST);
			if (strprop != null)
				SimpleFontExtensionHelper.getInstance().loadFontExtensions(this, new ByteArrayInputStream(strprop.getBytes()),
						lst, true);

			refreshFonts = false;
		}
	}

	/**
	 * Refresh the list of fonts loaded
	 */
	public void refreshFonts() {
		refreshFonts = true;
		fontList = null;
		getFontList();
		// it is not necessary to call the read fonts since the getFontList will indirectly call it
		// readFonts();
	}

	public synchronized String[] getFontList() {
		if (refreshFonts || fontList == null) {
			refreshFonts = true;
			fontList = FontUtils.stringToItems(ModelUtils.getFontNames(this));
		}
		return fontList;
	}

	/**
	 * Return the components extension both by resolving the property of the current project and from the commons
	 * extension. If it is available instead of request the extension from the superclass it search it in the common cache
	 * 
	 * @return a not null components extension
	 */
	private List<ComponentsBundle> getExtensionComponents() {
		if (bundles == null || refreshBundles) {
			bundles = super.getExtensions(ComponentsBundle.class);
			// remove all duplicates
			Set<ComponentsBundle> components = new LinkedHashSet<ComponentsBundle>(bundles);
			bundles = new ArrayList<ComponentsBundle>(components);
			for (ComponentsBundle cb : bundles)
				JaspersoftStudioPlugin.getComponentConverterManager().setupComponentConvertor(cb);
			refreshBundles = false;
		}
		return bundles;
	}

	/**
	 * Return the functions extension both by resolving the property of the current project and from the commons
	 * extension. If it is available instead of request the extension from the superclass it search it in the common cache
	 * 
	 * @return a not null functions extension
	 */
	private List<FunctionsBundle> getExtensionFunctions() {
		if (functionsBundles == null || refreshFunctionsBundles) {
			// We need to be sure that the resource bundles are fresh new
			// NOTE: Let's use this for now as quick solution, in case of
			// bad performances we'll have to fix this approach
			ResourceBundle.clearCache(getClassLoader());
			functionsBundles = super.getExtensions(FunctionsBundle.class);
			Set<FunctionsBundle> fBundlesSet = new LinkedHashSet<FunctionsBundle>(functionsBundles);
			functionsBundles = new ArrayList<FunctionsBundle>(fBundlesSet);
			refreshFunctionsBundles = false;
		}
		return functionsBundles;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getExtensions(Class<T> extensionType) {
		ClassLoader oldCL = Thread.currentThread().getContextClassLoader();
		List<T> result = null;
		try {
			if (classLoader != null) {
				Thread.currentThread().setContextClassLoader(classLoader);
			}

			if (extensionType == FontFamily.class) {
				result = (List<T>) getExtensionFonts();
			} else if (extensionType == FontSet.class) {
				result = (List<T>) getExtensionFontSets();
			} else if (extensionType == ComponentsBundle.class) {
				result = (List<T>) getExtensionComponents();
			} else if (extensionType == FunctionsBundle.class) {
				result = (List<T>) getExtensionFunctions();
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
			} else if (repositoryServices != null && extensionType == RepositoryService.class) {
				result = (List<T>) repositoryServices;
			} else {
				try {
					result = super.getExtensions(extensionType);
					if (result != null) {
						List<T> toDel = new ArrayList<T>();
						for (T item : result)
							if (item.getClass().getName().equals(BuiltinDataFileServiceFactory.class.getName())
									|| item instanceof DataAdapterParameterContributorFactory)
								toDel.add(item);
						result.removeAll(toDel);
					}
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		} finally {
			Thread.currentThread().setContextClassLoader(oldCL);
		}
		return result;
	}

	/*
	 * private <T> List<T> getCachedExtension(Class<T> extensionType){ if (parent ==
	 * DefaultJasperReportsContext.getInstance()){ Object cache = extensionCache.get(extensionType); if (cache != null )
	 * return (List<T>)parent; }
	 */

	public Map<Object, Object> getMap() {
		if (map == null)
			map = new HashMap<Object, Object>();
		return map;
	}

	/**
	 * @return a default {@link JasperReportsConfiguration} instance, based on the {@link DefaultJasperReportsContext}.
	 */
	public static JasperReportsConfiguration getDefaultJRConfig() {
		return new JasperReportsConfiguration(DefaultJasperReportsContext.getInstance(), null);
	}

	public static JasperReportsConfiguration getDefaultJRConfig(IFile f) {
		return new JasperReportsConfiguration(DefaultJasperReportsContext.getInstance(), f);
	}

	/**
	 * @return a default {@link JasperReportsConfiguration} instance, based on the {@link DefaultJasperReportsContext}.
	 */
	public static JasperReportsConfiguration getDefaultInstance() {
		if (instance == null)
			instance = new JasperReportsConfiguration(DefaultJasperReportsContext.getInstance(), null);
		return instance;
	}

	public void refreshClasspath() {
		classpathlistener.propertyChange(null);
	}

	protected DefaultRepositoryService getLocalRepositoryService() {
		if (localRepositoryService == null) {
			localRepositoryService = new DefaultRepositoryService(this) {
				@Override
				public InputStream getInputStream(String uri) {
					if (Misc.isNullOrEmpty(uri) || uri.startsWith("repo:"))
						return null;
					try {
						URL url = JRResourcesUtil.createURL(uri, urlHandlerFactory);
						if (url != null) {
							if (url.getProtocol().toLowerCase().equals("http") || url.getProtocol().toLowerCase().equals("https")) {
								try {
									URI uuri = url.toURI();
									Executor exec = Executor.newInstance();
									HttpUtils.setupProxy(exec, uuri);

									Request req = Request.Get("http://somehost/");
									HttpUtils.setupProxy(exec, uuri, req);
									return exec.execute(req).returnContent().asStream();
								} catch (URISyntaxException e) {
									e.printStackTrace();
								} catch (ClientProtocolException e) {
									new JRException(JRLoader.EXCEPTION_MESSAGE_KEY_INPUT_STREAM_FROM_URL_OPEN_ERROR, new Object[] { url },
											e);
								} catch (IOException e) {
									new JRException(JRLoader.EXCEPTION_MESSAGE_KEY_INPUT_STREAM_FROM_URL_OPEN_ERROR, new Object[] { url },
											e);
								}

							}
							return JRLoader.getInputStream(url);
						}

						File file = JRResourcesUtil.resolveFile(uri, fileResolver);
						if (file != null) {
							return JRLoader.getInputStream(file);
						}

						url = JRResourcesUtil.findClassLoaderResource(uri, classLoader);
						if (url != null) {
							return JRLoader.getInputStream(url);
						}
					} catch (JRException e) {
						throw new JRRuntimeException(e);
					}

					return null;
				}
			};
		}
		return localRepositoryService;
	}

	/**
	 * @return the associated report file if any, <code>null</code> otherwise
	 */
	public IFile getAssociatedReportFile() {
		return (IFile) get(FileUtils.KEY_FILE);
	}

	/**
	 * Force the reload of the styles for jasperreports, should be called when an used external style change, this will
	 * discard all the loaded styles and reload them another time. Then it trigger the repaint of every element in the
	 * report
	 */
	public void refreshCachedStyles() {
		JSSReportConverter reportConverter = getReportConverter();
		if (reportConverter != null) {
			reportConverter.refreshCachedStyles();
			JasperDesign design = getJasperDesign();
			PropertyChangeEvent changeEvent = new PropertyChangeEvent(design, MGraphicElement.FORCE_GRAPHICAL_REFRESH, false,
					true);
			for (JRDesignElement element : ModelUtils.getAllElements(design)) {
				element.getEventSupport().firePropertyChange(changeEvent);
			}
		}
	}

	/**
	 * Return the report converter used to paint the report elements
	 * 
	 * @return return the current report converter or null if it can't be resolved
	 */
	public JSSReportConverter getReportConverter() {
		Object converter = get(JasperReportsConfiguration.KEY_CONVERTER);
		if (converter instanceof JSSReportConverter) {
			return (JSSReportConverter) converter;
		} else {
			return null;
		}
	}

	/**
	 * Return the style resolver for the current report
	 * 
	 * @return a not null {@link JSSStyleResolver}
	 */
	public JSSStyleResolver getStyleResolver() {
		return resolver;
	}

}
