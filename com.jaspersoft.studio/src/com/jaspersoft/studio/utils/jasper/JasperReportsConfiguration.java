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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.eclipse.builder.JasperReportCompiler;
import net.sf.jasperreports.eclipse.util.JavaProjectClassLoader;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.fonts.FontFamily;
import net.sf.jasperreports.engine.fonts.SimpleFontExtensionHelper;
import net.sf.jasperreports.engine.util.FileResolver;
import net.sf.jasperreports.engine.util.LocalJasperReportsContext;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jdt.core.JavaCore;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.plugin.IEditorContributor;
import com.jaspersoft.studio.preferences.fonts.FontsPreferencePage;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;

public class JasperReportsConfiguration extends LocalJasperReportsContext {

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
		}
		service = Platform.getPreferencesService();
		qualifier = JaspersoftStudioPlugin.getUniqueIdentifier();
		if (project != null) {
			lookupOrders = new String[] { ProjectScope.SCOPE, InstanceScope.SCOPE };
			contexts = new IScopeContext[] { new ProjectScope(project), INSTANCE_SCOPE };
			try {
				if (project.getNature(JavaCore.NATURE_ID) != null)
					setClassLoader(JavaProjectClassLoader.instance(JavaCore.create(project), this.getClass().getClassLoader()));
			} catch (CoreException e) {
				e.printStackTrace();
			}
		} else {
			lookupOrders = new String[] { InstanceScope.SCOPE };
			contexts = new IScopeContext[] { INSTANCE_SCOPE };
		}
		service.setDefaultLookupOrder(qualifier, null, lookupOrders);

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
	private String strproperties;

	@Override
	public String getProperty(String key) {
		String val = service.getString(qualifier, key, null, contexts);
		if (val == null)
			val = service.getString(qualifier, PROPERTY_JRPROPERTY_PREFIX + key, null, contexts);

		if (val == null)
			return super.getProperty(key);
		return val;
	}

}
