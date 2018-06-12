/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization;

import java.io.IOException;

import org.osgi.framework.BundleContext;

import com.jaspersoft.studio.preferences.util.PreferencesUtils;

import net.sf.jasperreports.customvisualization.CVConstants;
import net.sf.jasperreports.eclipse.AbstractJRUIPlugin;

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

	@Override
	protected void postStartOperations() {
		try {
			initCustomVisualizationComponentProperties();
		} catch (IOException e) {
			logError(e);
		}
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
			
			// Force the properties values in order to avoid problems with different Studio versions
			// See Bugzilla #42275.
			PreferencesUtils.storeJasperReportsProperty(CVConstants.CV_REQUIREJS_PROPERTY, pathToRequireJs);
			PreferencesUtils.storeJasperReportsProperty(SCRIPTS_PATH, "com/jaspersoft/jasperreports/customvisualization/resources/require/.*"); //$NON-NLS-1$
	}
}
