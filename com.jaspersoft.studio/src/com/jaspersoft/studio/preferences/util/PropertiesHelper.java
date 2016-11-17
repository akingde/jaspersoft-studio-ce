/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.preferences.util;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRPropertiesUtil;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class PropertiesHelper {
	public static final IScopeContext INSTANCE_SCOPE = new InstanceScope();
	private IPreferencesService service;
	private String qualifier;
	private String[] lookupOrders;
	private IScopeContext[] contexts;
	public static JRPropertiesUtil DPROP = JRPropertiesUtil.getInstance(DefaultJasperReportsContext.getInstance());

	private PropertiesHelper() {
		service = Platform.getPreferencesService();
		qualifier = JaspersoftStudioPlugin.getUniqueIdentifier();
		lookupOrders = new String[] { InstanceScope.SCOPE };
		contexts = new IScopeContext[] { INSTANCE_SCOPE };
		service.setDefaultLookupOrder(qualifier, null, lookupOrders);
	}

	public void setString(String key, String value, String scope) {
		service.getRootNode().node(scope).node(qualifier).put(key, value);
	}
	
	public void removeString(String key, String scope) {
		service.getRootNode().node(scope).node(qualifier).remove(key);
	}

	public void setBoolean(String key, boolean value, String scope) {
		service.getRootNode().node(scope).node(qualifier).putBoolean(key, value);
	}

	public String getString(String key, String def) {
		String str = getString(key);
		if (str == null)
			return def;
		return str;
	}

	public String getString(String key) {
		return service.getString(qualifier, key, null, contexts);
	}

	public Boolean getBoolean(String key) {
		String val = getString(key);
		if (val == null)
			return null;
		return new Boolean(val);
	}

	public Boolean getBoolean(String key, boolean def) {
		String val = getString(key);
		if (val == null)
			return def;
		return new Boolean(val);
	}

	public Integer getInteger(String key, Integer def) {
		Integer val = getInteger(key);
		if (val == null)
			return def;
		return val;
	}

	public Integer getInteger(String key) {
		String val = getString(key);
		if (val == null)
			return null;
		return new Integer(val);
	}

	public Float getFloat(String key) {
		String val = getString(key);
		if (val == null)
			return null;
		return new Float(val);
	}

	public Float getFloat(String key, Float def) {
		Float val = getFloat(key);
		if (val == null)
			return def;
		return val;
	}

	public Character getCharacter(String key) {
		String val = getString(key);
		if (val == null || val.isEmpty())
			return null;
		return new Character(val.charAt(0));
	}

	private static PropertiesHelper inst;

	/**
	 * Get a {@link PropertiesHelper} instance from the specified {@link JasperReportsConfiguration}. If a valid
	 * JasperReports context is not found then a default {@link PropertiesHelper} is returned.
	 * 
	 * @param jrContext
	 *          the context from which to extract a {@link PropertiesHelper} instance
	 * @return the properties helper found, a default one otherwise
	 */
	public static PropertiesHelper getInstance() {
		if (inst == null)
			inst = new PropertiesHelper();
		return inst;
	}
}
