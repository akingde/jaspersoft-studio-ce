/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.utils.jasper;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jface.util.IPropertyChangeListener;

import com.jaspersoft.studio.ExternalStylesManager;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.customadapters.JSSCastorUtil;
import com.jaspersoft.studio.editor.context.AEditorContext;
import com.jaspersoft.studio.editor.context.EditorContextUtil;
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

import net.sf.jasperreports.charts.ChartThemeBundle;
import net.sf.jasperreports.data.BuiltinDataFileServiceFactory;
import net.sf.jasperreports.data.DataAdapterParameterContributorFactory;
import net.sf.jasperreports.eclipse.IDisposeListener;
import net.sf.jasperreports.eclipse.MScopedPreferenceStore;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FilePrefUtil;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.eclipse.util.query.EmptyQueryExecuterFactoryBundle;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRReportTemplate;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.SimpleJasperReportsContext;
import net.sf.jasperreports.engine.component.ComponentsBundle;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignReportTemplate;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.fill.DefaultChartTheme;
import net.sf.jasperreports.engine.fonts.FontExtensionsCollector;
import net.sf.jasperreports.engine.fonts.FontFamily;
import net.sf.jasperreports.engine.fonts.FontSet;
import net.sf.jasperreports.engine.fonts.SimpleFontExtensionHelper;
import net.sf.jasperreports.engine.query.JRQueryExecuterFactoryBundle;
import net.sf.jasperreports.engine.util.MessageProviderFactory;
import net.sf.jasperreports.engine.util.ResourceBundleMessageProviderFactory;
import net.sf.jasperreports.functions.FunctionsBundle;
import net.sf.jasperreports.repo.RepositoryService;
import net.sf.jasperreports.util.CastorMapping;
import net.sf.jasperreports.utils.JRExtensionsUtils;

public class JasperReportsConfiguration extends SimpleJasperReportsContext {

	public static final String KEY_JASPERDESIGN = "JasperDesign";

	public static final String PROPERTY_JRPROPERTY_PREFIX = "ireport.jrproperty.";

	public static final String KEY_JRPARAMETERS = "KEY_PARAMETERS";

	/**
	 * Key used to store the drawer used to paint the JRElements, it is stored in
	 * the configuration to be easily accessible
	 */
	public static final String KEY_DRAWER = "REPORT_DRAWER";

	/**
	 * Key used to store the report converter used to paint the JRElements, it is
	 * stored in the configuration to be easily accessible
	 */
	public static final String KEY_CONVERTER = "REPORT_CONVERTER";

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
				componentBundles.invalidate();
			} else if (property.equals(FilePrefUtil.NET_SF_JASPERREPORTS_JRPROPERTIES)) {
				componentBundles.invalidate();
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
	 * When an event of resource loaded happen it rebuild the extrenral styles in
	 * the report drawer and trigger a refresh of the editor
	 * 
	 * @author Orlandin Marco
	 *
	 */
	private final class ResourceLoadedListener implements PropertyChangeListener {

		private void refreshImages() {
			// clear the image cache
			LazyImageConverter.getInstance().removeCachedImages(JasperReportsConfiguration.this);
		}

		private void refreshStyles() {
			// clear the style cache of this configuration, since a resource could be
			// changed for it
			// and styles need to be loaded another time
			ExternalStylesManager.removeCachedStyles(JasperReportsConfiguration.this);
			// Not sure if the resource is a style, so this call will regenerate first the
			// styles
			// and trigger a complete refresh of the editor. Doing so it will cover every
			// case of
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

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if (evt.getPropertyName().equals(ResourceChangeEvent.RESOURCE_LOADED)) {
				if (evt instanceof ResourceChangeEvent) {
					ResourceChangeEvent rEvt = (ResourceChangeEvent) evt;
					switch (rEvt.getResourceType()) {
					case IMAGE:
						refreshImages();
						break;
					case TEMPLATE_STYLE:
						refreshStyles();
						break;
					case ALL:
					default:
						refreshImages();
						refreshStyles();
						break;
					}
				} else {
					refreshImages();
					refreshStyles();
				}
			}
		}

	}

	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

	private PreferenceListener preferenceListener;
	private ResourceLoadedListener resourceLoadedListener;
	private String[] fontList;
	private boolean refreshFonts = true;
	private FontExtensionsCollector lst;
	private ExtensionCache<ComponentsBundle> componentBundles = new ExtensionCache<>();
	private ExtensionCache<FunctionsBundle> functionsBundles = new ExtensionCache<>();
	private ExtensionCache<CastorMapping> castorBundles = new ExtensionCache<>();
	private ExtensionCache<ChartThemeBundle> chartThemesBundles = new ExtensionCache<>();
	private ExtensionCache<MessageProviderFactory> messageProviderFactory = new ExtensionCache<>();
	private static JasperReportsConfiguration instance;

	private List<JRQueryExecuterFactoryBundle> qExecutors;
	private Map<Object, Object> map;
	private MScopedPreferenceStore pstore;
	private List<IDisposeListener> toDispose;
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

	public MScopedPreferenceStore getPrefStore() {
		return pstore;
	}

	public PropertyChangeSupport getPropertyChangeSupport() {
		return propertyChangeSupport;
	}

	protected AEditorContext cntx;

	public AEditorContext getEditorContext() {
		return cntx;
	}

	public void changeContext(String c, boolean reset) {
		IFile oldFile = (IFile) get(FileUtils.KEY_FILE);
		try {
			if (oldFile.exists())
				oldFile.setPersistentProperty(
						new QualifiedName(JaspersoftStudioPlugin.getUniqueIdentifier(), AEditorContext.EDITOR_CONTEXT),
						reset ? null : c);
		} catch (CoreException e) {
			UIUtils.showError(e);
		}

		remove(FileUtils.KEY_FILE);
		isPropsCached = false;
		init(oldFile);
		getProperties();
	}

	public void init(IFile file) {
		IFile oldFile = (IFile) get(FileUtils.KEY_FILE);
		if (oldFile != null && oldFile == file)
			return;
		String qualifier = JaspersoftStudioPlugin.getUniqueIdentifier();
		pstore = (MScopedPreferenceStore) JaspersoftStudioPlugin.getInstance().getPreferenceStore(file, qualifier);
		// if (service == null) {
		// service = Platform.getPreferencesService();

		// }
		cntx = EditorContextUtil.getEditorContext(file, this);
		cntx.initClassloader();
		IProject project = null;
		if (file != null) {
			put(FileUtils.KEY_FILE, file);
			project = file.getProject();
			put(FileUtils.KEY_IPROJECT, project);
			if (project != null) {
				// lookupOrders = new String[] { ResourceScope.SCOPE, ProjectScope.SCOPE,
				// InstanceScope.SCOPE };
				// contexts = new IScopeContext[] { new ResourceScope(file), new
				// ProjectScope(project), INSTANCE_SCOPE };
			}
			cntx.configureRepositoryService();
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

	public void resetCaches(PropertyChangeEvent arg0) {
		refreshFonts = true;
		fontList = null;
		messageProviderFactory.invalidate();
		functionsBundles.invalidate();
		componentBundles.invalidate();
		chartThemesBundles.invalidate();
		ExpressionUtil.removeAllReportInterpreters(this);
		propertyChangeSupport.firePropertyChange(new PropertyChangeEvent(this, "classpath", null, arg0));

		castorBundles.invalidate();
	}

	public JSSFileRepositoryService getFileRepositoryService() {
		if (cntx.getRepositoryServices() != null)
			for (RepositoryService rs : cntx.getRepositoryServices())
				if (rs instanceof JSSFileRepositoryService)
					return (JSSFileRepositoryService) rs;
		return null;
	}

	public void setClassLoader(ClassLoader classLoader) {
		cntx.setClassLoader(classLoader);
	}

	public void dispose() {
		ExpressionUtil.removeAllReportInterpreters(this);
		ExternalStylesManager.removeCachedStyles(this);
		LazyImageConverter.getInstance().removeCachedImages(this);
		WidgetsDefinitionManager.disposedConfiguration(this);
		JaspersoftStudioPlugin.getInstance().removePreferenceListener(preferenceListener);
		getPropertyChangeSupport().removePropertyChangeListener(resourceLoadedListener);
		cntx.dispose();
		for (PropertyChangeListener l : Arrays.asList(propertyChangeSupport.getPropertyChangeListeners())) {
			propertyChangeSupport.removePropertyChangeListener(l);
		}
		if (toDispose != null)
			for (IDisposeListener d : toDispose)
				d.dispose();
	}

	public void addDisposeListener(IDisposeListener listener) {
		if (toDispose == null)
			toDispose = new ArrayList<>();
		toDispose.add(listener);
	}

	public void removeDisposeListener(IDisposeListener listener) {
		if (toDispose == null)
			return;
		toDispose.remove(listener);
	}

	public void put(String key, Object value) {
		if (key == null)
			return;
		if (value == null)
			removeValue(key);
		else
			setValue(key, value);
	}

	public Object get(String key) {
		return getValue(key);
	}

	public void remove(String key) {
		removeValue(key);
	}

	public ClassLoader getClassLoader() {
		return cntx.getClassLoader();
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

	public void refreshProperties() {
		isPropsCached = false;
		getProperties();
	}

	@Override
	public Map<String, String> getProperties() {
		if (isPropsCached)
			return getPropertiesMap();
		setPropertiesMap(null);
		Map<String, String> smap = super.getProperties();
		Map<String, String> propmap = new HashMap<>();
		if (smap != null && !smap.isEmpty())
			propmap.putAll(smap);
		setPropertiesMap(propmap);
		// get properties from eclipse stored jr properties (eclipse, project, file
		// level)
		Properties props = cntx.getJrProperties();
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

	@Override
	public String getProperty(String key) {
		pstore.setWithDefault(false);
		String val = Platform.getPreferencesService().get(key, null, pstore.getPreferenceNodes(true));

		// pstore.getString(key);// Misc.nullIfEmpty(pstore.getString(key));

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
				return v.charAt(0);
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
			return p.charAt(0);
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
	 * Return the font extension both by resolving the property of the current
	 * project and from the commons extension. If it is available instead of request
	 * the extension from the superclass it search it in the common cache
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
				SimpleFontExtensionHelper.getInstance().loadFontExtensions(this,
						new ByteArrayInputStream(strprop.getBytes()), lst, true);

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
		// it is not necessary to call the read fonts since the getFontList will
		// indirectly call it
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
	 * Return the components extension both by resolving the property of the current
	 * project and from the commons extension. If it is available instead of request
	 * the extension from the superclass it search it in the common cache
	 * 
	 * @return a not null components extension
	 */
	private List<ComponentsBundle> getExtensionComponents() {
		if (!componentBundles.isValid()) {
			List<ComponentsBundle> componentBundlesList = super.getExtensions(ComponentsBundle.class);
			// remove all duplicates
			Set<ComponentsBundle> components = new LinkedHashSet<>(componentBundlesList);
			componentBundlesList = new ArrayList<>(components);
			for (ComponentsBundle cb : componentBundlesList)
				JaspersoftStudioPlugin.getComponentConverterManager().setupComponentConvertor(cb);
			componentBundles.setValue(componentBundlesList);
		}
		return componentBundles.getValue();
	}

	/**
	 * Return the functions extension both by resolving the property of the current
	 * project and from the commons extension. If it is available instead of request
	 * the extension from the superclass it search it in the common cache
	 * 
	 * @return a not null functions extension
	 */
	private List<FunctionsBundle> getExtensionFunctions() {
		if (!functionsBundles.isValid()) {
			Set<FunctionsBundle> fBundlesSet = new LinkedHashSet<>(
					JRExtensionsUtils.getReloadedExtensions(FunctionsBundle.class, "functions"));
			functionsBundles.setValue(new ArrayList<FunctionsBundle>(fBundlesSet));
		}
		return functionsBundles.getValue();
	}

	/**
	 * Return the castor extension both by resolving the property of the current
	 * project and from the commons extension. If it is available instead of request
	 * the extension from the superclass it search it in the common cache
	 * 
	 * @return a not null functions extension
	 */
	private List<CastorMapping> getExtensionCastors() {
		if (!castorBundles.isValid()) {
			JSSCastorUtil.clearCache(this);
			Set<CastorMapping> fBundlesSet = new LinkedHashSet<>(
					JRExtensionsUtils.getReloadedExtensions(CastorMapping.class, "castor.mapping"));
			castorBundles.setValue((List<CastorMapping>) new ArrayList<>(fBundlesSet));
		}
		return castorBundles.getValue();
	}

	/**
	 * Return the Chart Theme extension both by resolving the property of the
	 * current project and from the commons extension. If it is available instead of
	 * request the extension from the superclass it search it in the common cache
	 * 
	 * @return a not null functions extension
	 */
	private List<ChartThemeBundle> getExtensionChartThemes() {
		if (!chartThemesBundles.isValid()) {
			Set<ChartThemeBundle> fBundlesSet = new LinkedHashSet<>(
					JRExtensionsUtils.getReloadedExtensions(ChartThemeBundle.class, "chart.theme"));
			List<ChartThemeBundle> bundlesList = new ArrayList<>(fBundlesSet);
			fBundlesSet = new LinkedHashSet<>(
					JRExtensionsUtils.getReloadedExtensions(ChartThemeBundle.class, "xml.chart.themes"));
			bundlesList.addAll(fBundlesSet);
			bundlesList.add(0, DefaultChartTheme.BUNDLE);
			chartThemesBundles.setValue(bundlesList);
		}
		return chartThemesBundles.getValue();
	}

	public List<MessageProviderFactory> getExtensionMessageProviderFactories() {
		if (!messageProviderFactory.isValid()) {
			List<MessageProviderFactory> factories = new ArrayList<>();
			factories.add(new ResourceBundleMessageProviderFactory(getClassLoader()));
			messageProviderFactory.setValue(factories);
		}
		return messageProviderFactory.getValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void setExtensions(Class<T> extensionType, List<? extends T> extensions) {
		if (extensionType == ChartThemeBundle.class) {
			// In some case the extension is set manually, like in the initialization or the
			// JRCTX editor,
			// in this case we don't want to invalidate and reload the extension on a
			// classpath change, since
			// it was manually set to a specific value
			chartThemesBundles.setValue((List<ChartThemeBundle>) extensions);
			chartThemesBundles.setAvoidInvalidation(true);
		} else
			super.setExtensions(extensionType, extensions);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getExtensions(Class<T> extensionType) {
		ClassLoader oldCL = Thread.currentThread().getContextClassLoader();
		List<T> result = null;
		try {
			if (cntx.getClassLoader() != null) {
				Thread.currentThread().setContextClassLoader(cntx.getClassLoader());
			}

			if (extensionType == FontFamily.class) {
				result = (List<T>) getExtensionFonts();
			} else if (extensionType == FontSet.class) {
				result = (List<T>) getExtensionFontSets();
			} else if (extensionType == CastorMapping.class) {
				result = (List<T>) getExtensionCastors();
			} else if (extensionType == ChartThemeBundle.class) {
				result = (List<T>) getExtensionChartThemes();
			} else if (extensionType == ComponentsBundle.class) {
				result = (List<T>) getExtensionComponents();
			} else if (extensionType == FunctionsBundle.class) {
				result = (List<T>) getExtensionFunctions();
			} else if (extensionType == MessageProviderFactory.class) {
				result = (List<T>) getExtensionMessageProviderFactories();
			} else if (extensionType == JRQueryExecuterFactoryBundle.class) {
				try {
					if (qExecutors == null) {
						qExecutors = new ArrayList<>();
						qExecutors.add(EmptyQueryExecuterFactoryBundle.getInstance(this));
					}
					result = (List<T>) qExecutors;
				} catch (Throwable e) {
					e.printStackTrace();
				}
			} else if (cntx.getRepositoryServices() != null && extensionType == RepositoryService.class) {
				result = (List<T>) cntx.getRepositoryServices();
			} else {
				try {
					result = super.getExtensions(extensionType);
					if (result != null) {
						List<T> toDel = new ArrayList<>();
						for (T item : result)
							if (item != null
									&& (item.getClass().getName().equals(BuiltinDataFileServiceFactory.class.getName())
											|| item instanceof DataAdapterParameterContributorFactory))
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
		if (result != null && result.indexOf(null) >= 0) {
			// this theoretically should not happen, but practically, we have it sometimes
			try {
				result.removeAll(Collections.singleton(null));
			} catch (UnsupportedOperationException e) {
				result = new ArrayList<>(result);
				result.removeAll(Collections.singleton(null));
			}
		}
		return result;
	}

	/*
	 * private <T> List<T> getCachedExtension(Class<T> extensionType){ if (parent ==
	 * DefaultJasperReportsContext.getInstance()){ Object cache =
	 * extensionCache.get(extensionType); if (cache != null ) return
	 * (List<T>)parent; }
	 */

	public Map<Object, Object> getMap() {
		if (map == null)
			map = new HashMap<>();
		return map;
	}

	/**
	 * @return a default {@link JasperReportsConfiguration} instance, based on the
	 *         {@link DefaultJasperReportsContext}.
	 */
	public static JasperReportsConfiguration getDefaultJRConfig() {
		return new JasperReportsConfiguration(DefaultJasperReportsContext.getInstance(), null);
	}

	public static JasperReportsConfiguration getDefaultJRConfig(IFile f) {
		return new JasperReportsConfiguration(DefaultJasperReportsContext.getInstance(), f);
	}

	/**
	 * @return a default {@link JasperReportsConfiguration} instance, based on the
	 *         {@link DefaultJasperReportsContext}.
	 */
	public static JasperReportsConfiguration getDefaultInstance() {
		if (instance == null)
			instance = new JasperReportsConfiguration(DefaultJasperReportsContext.getInstance(), null);
		return instance;
	}

	public void refreshClasspath() {
		cntx.refreshClasspath();
	}

	/**
	 * @return the associated report file if any, <code>null</code> otherwise
	 */
	public IFile getAssociatedReportFile() {
		return (IFile) get(FileUtils.KEY_FILE);
	}

	/**
	 * Force the reload of the styles for jasperreports, should be called when an
	 * used external style change, this will discard all the loaded styles and
	 * reload them another time. Then it trigger the repaint of every element in the
	 * report
	 */
	public void refreshCachedStyles() {
		JSSReportConverter reportConverter = getReportConverter();
		if (reportConverter != null) {
			reportConverter.refreshCachedStyles();
			JasperDesign design = getJasperDesign();
			//Since JasperReports doesn't render correctly the element with a style reference at design time we use the trick
			//to set an external style as local one, so set in the element the JRDesignStyle object of the external style, along
			//with the name reference. So when a reference is updated we need to iterate all style and if thy are using a reference
			//refresh its copy of the internal style as well
			for(JRStyle style : design.getStyles()) {
				if (style.getStyleNameReference() != null) {
					((JRDesignStyle)style).setParentStyle(ExternalStylesManager.getExternalStyle(style.getStyleNameReference(), this));
				}
			}
			PropertyChangeEvent changeEvent = new PropertyChangeEvent(design, MGraphicElement.FORCE_GRAPHICAL_REFRESH, false, true);
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
