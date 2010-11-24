package com.jaspersoft.studio.chart.model.chartAxis;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.chart.model.chartAxis.messages"; //$NON-NLS-1$
	public static String MChartAxes_chart;
	public static String MChartAxes_chart_description;
	public static String MChartAxes_position;
	public static String MChartAxes_position_description;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
