package com.jaspersoft.studio.preferences.util;

import java.util.List;
import java.util.Properties;

import net.sf.jasperreports.engine.util.JRProperties;
import net.sf.jasperreports.engine.util.JRProperties.PropertySuffix;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;

import com.jaspersoft.studio.JaspersoftStudioPlugin;

public class PropertiesHelper {

	private IPreferencesService service;
	private String qualifier;
	private String[] lookupOrders;
	private IScopeContext[] contexts;

	public PropertiesHelper(IProject project) {
		service = Platform.getPreferencesService();
		qualifier = JaspersoftStudioPlugin.getUniqueIdentifier();
		lookupOrders = new String[] { InstanceScope.SCOPE };
		contexts = new IScopeContext[] { new InstanceScope() };
		if (project != null) {
			lookupOrders = new String[] { ProjectScope.SCOPE, InstanceScope.SCOPE };
			contexts = new IScopeContext[] { new ProjectScope(project), new InstanceScope() };
		}
		service.setDefaultLookupOrder(qualifier, null, lookupOrders);
	}

	@SuppressWarnings("unchecked")
	public Properties getProperties() {
		Properties p = new Properties();
		List<PropertySuffix> lst = JRProperties.getProperties("");
		for (PropertySuffix ps : lst) {
			// JRProperties.setProperty(ps.getKey(), getString(ps.getKey()));

			// p.setProperty(ps.getKey(), getString(ps.getKey()));
		}
		return p;
	}

	public String getString(String key) {
		return service.getString(qualifier, key, null, contexts);
	}
}
