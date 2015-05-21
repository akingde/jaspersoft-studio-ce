/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * Licensed under commercial Jaspersoft Subscription License Agreement
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization;

import java.io.IOException;

import net.sf.jasperreports.eclipse.AbstractJRUIPlugin;

import org.eclipse.jface.util.Util;
import org.osgi.framework.BundleContext;

import com.jaspersoft.jasperreports.customvisualization.CVConstants;
import com.jaspersoft.studio.preferences.util.PreferencesUtils;

/**
 * The activator class controls the plug-in life cycle
 */
public class CustomVisualizationActivator extends AbstractJRUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.jaspersoft.studio.components.customvisualization"; //$NON-NLS-1$
	
	public static final String JAVA_PATH_PROPERTY = "com.jaspersoft.studio.components.customvisualization.javapath";
	
	public static final String SCRIPTS_PATH = "net.sf.jasperreports.web.resource.pattern.customvisualization.scripts";

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
			String pathToRequireJs = getFileLocation("resources/scripts/require-2.1.6.src.js"); //$NON-NLS-1$
			while (pathToRequireJs.startsWith("/")) {
				pathToRequireJs = pathToRequireJs.substring(1);
			}
			// On windows, Phantomjs does not seem to like to the "file:/" , so we avoid to add it on this specific platform.
			// With PhantomJS 2.0, also in windows is necessary to use the file:/ syntax!
			//if (!Util.isWindows())
			//{
			pathToRequireJs = "file:/" + pathToRequireJs;
			//}
			
			// We store the property only in the JasperReports property store, so the property can be actually changed by the user.
			// The plugin will not override existing keys.
			
			if (PreferencesUtils.getJasperReportsProperty(CVConstants.CV_REQUIREJS_PROPERTY) == null)
			{
				PreferencesUtils.storeJasperReportsProperty(CVConstants.CV_REQUIREJS_PROPERTY, pathToRequireJs);
			}
			
			if (PreferencesUtils.getJasperReportsProperty(SCRIPTS_PATH) == null)
			{
				PreferencesUtils.storeJasperReportsProperty(SCRIPTS_PATH, "com/jaspersoft/jasperreports/customvisualization/resources/require/.*");
			}
	}
}
