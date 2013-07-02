package com.jaspersoft.studio.data.sql;

import net.sf.jasperreports.eclipse.AbstractJRUIPlugin;

public class SQLActivator extends AbstractJRUIPlugin {

	@Override
	public String getPluginID() {
		return "com.jaspersoft.studio.data.sql";
	}

	private static SQLActivator INSTANCE;

	public static SQLActivator getInstance() {
		return INSTANCE;
	}
}
