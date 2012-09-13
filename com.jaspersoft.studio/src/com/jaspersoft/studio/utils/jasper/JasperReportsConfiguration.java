/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.utils.jasper;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.eclipse.builder.JasperReportCompiler;
import net.sf.jasperreports.eclipse.util.JavaProjectClassLoader;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.fonts.FontFamily;
import net.sf.jasperreports.engine.fonts.SimpleFontExtensionHelper;
import net.sf.jasperreports.engine.util.CompositeClassloader;
import net.sf.jasperreports.engine.util.FileResolver;
import net.sf.jasperreports.engine.util.LocalJasperReportsContext;
import net.sf.jasperreports.engine.util.SimpleFileResolver;
import net.sf.jasperreports.repo.FileRepositoryService;
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
import com.jaspersoft.studio.preferences.fonts.FontsPreferencePage;

public class JasperReportsConfiguration extends LocalJasperReportsContext {

	/**
	 * The key which identified the file being edited
	 */
	public static final String REPORT_FILE = "REPORTFILEWIZARD"; //$NON-NLS-1$
	public static final String REPORT_DESIGN = "REPORTDESIGNWIZARD"; //$NON-NLS-1$

	private final class PreferenceListener implements IPropertyChangeListener {

		public void propertyChange(org.eclipse.jface.util.PropertyChangeEvent event) {
			if (event.getProperty().equals(FontsPreferencePage.FPP_FONT_LIST)) {
				fill = true;
			}
		}
	}

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
		IProject project = null;
		if (file != null) {
			put(IEditorContributor.KEY_FILE, file);
			project = file.getProject();
			initFileResolver(file);
		}
		service = Platform.getPreferencesService();
		qualifier = JaspersoftStudioPlugin.getUniqueIdentifier();
		if (project != null) {
			lookupOrders = new String[] { ProjectScope.SCOPE, InstanceScope.SCOPE };
			contexts = new IScopeContext[] { new ProjectScope(project), INSTANCE_SCOPE };
			try {
				if (project.getNature(JavaCore.NATURE_ID) != null) {
					ClassLoader cl = JavaProjectClassLoader.instance(JavaCore.create(project), this.getClass().getClassLoader());
					cl = JaspersoftStudioPlugin.getDriversManager().getClassLoader(cl);
					cl = new CompositeClassloader(cl, getClass().getClassLoader());
					setClassLoader(cl);
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		} else {
			lookupOrders = new String[] { InstanceScope.SCOPE };
			contexts = new IScopeContext[] { INSTANCE_SCOPE };
		}
		service.setDefaultLookupOrder(qualifier, null, lookupOrders);
		preferenceListener = new PreferenceListener();
		JaspersoftStudioPlugin.getInstance().getPreferenceStore().addPropertyChangeListener(preferenceListener);
	}

	private void initFileResolver(IFile file) {
		List<RepositoryService> list = getExtensions(RepositoryService.class);
		if (file != null) {
			list.add(new FileRepositoryService(this, file.getParent().getLocationURI().toASCIIString(), true));
			list.add(new FileRepositoryService(this, ".", true));
			list.add(new FileRepositoryService(this, file.getProject().getLocationURI().toASCIIString(), true));
		}
		setExtensions(RepositoryService.class, list);

		ProxyFileResolver resolver = new ProxyFileResolver();
//		 SimpleFileResolver fileResolver = null;
//		 if (file == null)
//					fileResolver = new SimpleFileResolver(Arrays.asList(new File[] { new File("."), //$NON-NLS-1$
//		 }));
//		 else
//		 fileResolver = new SimpleFileResolver(Arrays.asList(new File[] { new File(file.getParent().getLocationURI()),
//							new File("."), //$NON-NLS-1$
//		 new File(file.getProject().getLocationURI()) }));
//		 fileResolver.setResolveAbsolutePath(true);
//		 resolver.addResolver(fileResolver);
		setFileResolver(resolver);
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
		put(JasperReportCompiler.CURRENTCLASSLOADER, classLoader);
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

	@Override
	public String getProperty(String key) {
		String val = service.getString(qualifier, key, null, contexts);
		if (val == null)
			val = service.getString(qualifier, PROPERTY_JRPROPERTY_PREFIX + key, null, contexts);

		if (val == null)
			return super.getProperty(key);
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
		if (p != null)
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
			return Integer.getInteger(p);
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
			return Long.getLong(p);
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

	private boolean fill = true;
	private List<FontFamily> lst;

	@Override
	public <T> List<T> getExtensions(Class<T> extensionType) {
		if (extensionType == FontFamily.class) {
			if (lst == null)
				lst = new ArrayList<FontFamily>();
			if (fill) {
				String strprop = getProperty(FontsPreferencePage.FPP_FONT_LIST);
				if (strprop != null) {
					lst.clear();
					List<FontFamily> fonts = SimpleFontExtensionHelper.getInstance().loadFontFamilies(this,
							new ByteArrayInputStream(strprop.getBytes()));
					if (fonts != null && !fonts.isEmpty())
						lst.addAll(fonts);
				}
				fill = false;
			}
			return (List<T>) lst;
		}
		return super.getExtensions(extensionType);
	}
}
