package com.jaspersoft.studio.data.sql;

import net.sf.jasperreports.eclipse.AbstractJRUIPlugin;

public class Activator extends AbstractJRUIPlugin {

	public static final String PLUGIN_ID = "com.jaspersoft.studio.data.sql.ui";

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
}
