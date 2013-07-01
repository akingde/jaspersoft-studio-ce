/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.data.sql;

import java.util.Collections;
import java.util.Map;

import net.sf.jasperreports.eclipse.AbstractJRUIPlugin;

import org.apache.log4j.Logger;
import org.eclipse.xtext.ui.shared.SharedStateModule;
import org.eclipse.xtext.util.Modules2;

import com.google.common.collect.Maps;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.jaspersoft.studio.data.SqlRuntimeModule;
import com.jaspersoft.studio.data.ui.SqlUiModule;

public class Activator extends AbstractJRUIPlugin {

	public static final String PLUGIN_ID = "com.jaspersoft.studio.data.sql.ui";
	private static final Logger logger = Logger.getLogger(Activator.class);

	@Override
	public String getPluginID() {
		return PLUGIN_ID;
	}

	private static Activator plugin;

	public Activator() {
		plugin = this;
	}

	public static Activator getDefault() {
		if (plugin == null)
			plugin = new Activator();
		return plugin;
	}

	public static final String COM_JASPERSOFT_STUDIO_DATA_SQL_SQL_EDITOR = "com.jaspersoft.studio.data.sql.editor";
	private Map<String, Injector> injectors = Collections.synchronizedMap(Maps.<String, Injector> newHashMapWithExpectedSize(1));

	public Injector getInjector(String language) {
		synchronized (injectors) {
			Injector injector = injectors.get(language);
			if (injector == null) {
				injectors.put(language, injector = createInjector(language));
			}
			return injector;
		}
	}

	protected Injector createInjector(String language) {
		try {
			Module runtimeModule = getRuntimeModule(language);
			Module sharedStateModule = getSharedStateModule();
			Module uiModule = getUiModule(language);
			Module mergedModule = Modules2.mixin(runtimeModule, sharedStateModule, uiModule);
			return Guice.createInjector(mergedModule);
		} catch (Exception e) {
			logger.error("Failed to create injector for " + language);
			logger.error(e.getMessage(), e);
			throw new RuntimeException("Failed to create injector for " + language, e);
		}
	}

	protected Module getRuntimeModule(String grammar) {
		if (COM_JASPERSOFT_STUDIO_DATA_SQL_SQL_EDITOR.equals(grammar)) {
			return new SqlRuntimeModule();
		}

		throw new IllegalArgumentException(grammar);
	}

	protected Module getUiModule(String grammar) {
		if (COM_JASPERSOFT_STUDIO_DATA_SQL_SQL_EDITOR.equals(grammar)) {
			return new SqlUiModule(this);
		}

		throw new IllegalArgumentException(grammar);
	}

	protected Module getSharedStateModule() {
		return new SharedStateModule();
	}
}
