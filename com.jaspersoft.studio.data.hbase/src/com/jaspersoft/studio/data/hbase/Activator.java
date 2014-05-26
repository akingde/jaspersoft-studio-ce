package com.jaspersoft.studio.data.hbase;

import net.sf.jasperreports.eclipse.AbstractJRUIPlugin;

import org.osgi.framework.BundleContext;

/**
 * 
 * @author Eric Diaz
 * 
 */
public class Activator extends AbstractJRUIPlugin {
	public static final String PLUGIN_ID = "com.jaspersoft.studio.data.hbase"; //$NON-NLS-1$
	private static Activator plugin;

	public Activator() {
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	public static Activator getDefault() {
		return plugin;
	}

	@Override
	public String getPluginID() {
		return PLUGIN_ID;
	}
}