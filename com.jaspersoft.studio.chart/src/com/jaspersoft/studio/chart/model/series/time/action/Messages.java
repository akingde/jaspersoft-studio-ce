package com.jaspersoft.studio.chart.model.series.time.action;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.chart.model.series.time.action.messages"; //$NON-NLS-1$
	public static String CreateTimeAction_create_time_series;
	public static String CreateTimeAction_create_time_series_tool_tip;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
