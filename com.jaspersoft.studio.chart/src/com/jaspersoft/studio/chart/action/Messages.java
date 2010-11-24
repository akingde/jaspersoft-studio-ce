package com.jaspersoft.studio.chart.action;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.chart.action.messages"; //$NON-NLS-1$
	public static String CreateChartAxisAction_create_chartaxis;
	public static String CreateChartAxisAction_create_chartaxis_tool_tip;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
