/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.svimporter.activator;

import org.osgi.framework.BundleContext;

import net.sf.jasperreports.eclipse.AbstractJRUIPlugin;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractJRUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.jaspersoft.studio.svgimporter"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
	/**
	 * The constructor
	 */
	public Activator() {
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.
	 * BundleContext )
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	@Override
	public String getPluginID() {
		return PLUGIN_ID;
	}

}
