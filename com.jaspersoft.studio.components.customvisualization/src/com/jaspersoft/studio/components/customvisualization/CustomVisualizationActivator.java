/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * Licensed under commercial Jaspersoft Subscription License Agreement
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization;

import java.io.IOException;

import net.sf.jasperreports.eclipse.AbstractJRUIPlugin;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;

import org.osgi.framework.BundleContext;

import com.jaspersoft.jasperreports.customvisualization.CVConstants;
import com.jaspersoft.studio.preferences.util.PreferencesUtils;

/**
 * The activator class controls the plug-in life cycle
 */
public class CustomVisualizationActivator extends AbstractJRUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.jaspersoft.studio.components.customvisualization"; //$NON-NLS-1$

	// The shared instance
	private static CustomVisualizationActivator plugin;
	
	/**
	 * The constructor
	 */
	public CustomVisualizationActivator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		initCustomVisualizationComponentProperties();
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
	public static CustomVisualizationActivator getDefault() {
		return plugin;
	}

	@Override
	public String getPluginID() {
		return PLUGIN_ID;
	}

	
	/*
	 * Initializes the mandatory properties related to RequireJS.
	 */
	private void initCustomVisualizationComponentProperties() throws IOException {
		
			
			String pathToRequireJs = "file:"+ getFileLocation("resources/scripts/require-2.1.6.src.js"); //$NON-NLS-1$
		
			PreferencesUtils.getJaspersoftStudioPrefStore().setValue(CVConstants.CV_REQUIREJS_PROPERTY, pathToRequireJs);
			PreferencesUtils.storeJasperReportsProperty(CVConstants.CV_REQUIREJS_PROPERTY, pathToRequireJs);
			DefaultJasperReportsContext.getInstance().setProperty(CVConstants.CV_REQUIREJS_PROPERTY, pathToRequireJs);
			
			
			PreferencesUtils.getJaspersoftStudioPrefStore().setValue("net.sf.jasperreports.web.resource.pattern.customvisualization.scripts", "com/jaspersoft/jasperreports/customvisualization/resources/require");
			PreferencesUtils.storeJasperReportsProperty(CVConstants.CV_REQUIREJS_PROPERTY, pathToRequireJs);
			DefaultJasperReportsContext.getInstance().setProperty(CVConstants.CV_REQUIREJS_PROPERTY, pathToRequireJs);
	}
}
