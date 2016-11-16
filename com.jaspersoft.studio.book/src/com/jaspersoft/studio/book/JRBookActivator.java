/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book;

import org.osgi.framework.BundleContext;

import net.sf.jasperreports.eclipse.AbstractJRUIPlugin;

/**
 * The activator class controls the plug-in life cycle
 */
public class JRBookActivator extends AbstractJRUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.jaspersoft.studio.book"; //$NON-NLS-1$

	// The shared instance
	private static JRBookActivator plugin;
	
	/**
	 * The constructor
	 */
	public JRBookActivator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
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
	public static JRBookActivator getDefault() {
		return plugin;
	}

	@Override
	public String getPluginID() {
		return PLUGIN_ID;
	}

}
