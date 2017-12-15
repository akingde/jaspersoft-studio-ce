/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.manager;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;

/**
 * Class that keep the informations of a loaded definition
 */
public class CacheContainer {
	
	/**
	 * The loaded definition
	 */
	private WidgetsDescriptor descriptor;
	
	/**
	 * The config that triggered the load of the definition
	 */
	private JasperReportsConfiguration jConfig;
	
	/**
	 * Flag to know if the definition should be unloaded when the config is disposed
	 */
	private boolean unloadOnConfigDispose;
	
	public CacheContainer(WidgetsDescriptor descriptor, JasperReportsConfiguration jConfig, boolean unloadOnConfigDispose){
		this.descriptor = descriptor;
		this.jConfig = jConfig;
		this.unloadOnConfigDispose = unloadOnConfigDispose;
	}

	public WidgetsDescriptor getDescriptor() {
		return descriptor;
	}

	public JasperReportsConfiguration getjConfig() {
		return jConfig;
	}

	/**
	 * Return if the descriptor should be unloaded when the config requested it is disposed
	 * 
	 * @return true if the descriptor should be unloaded, false otherwise
	 */
	public boolean isUnloadOnConfigDispose() {
		return unloadOnConfigDispose;
	}
	
}
