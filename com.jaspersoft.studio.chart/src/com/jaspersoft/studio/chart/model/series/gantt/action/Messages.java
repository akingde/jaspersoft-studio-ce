package com.jaspersoft.studio.chart.model.series.gantt.action;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.chart.model.series.gantt.action.messages"; //$NON-NLS-1$
	public static String CreateGanttAction_create_gantt_series;
	public static String CreateGanttAction_create_gantt_series_tool_tip;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
