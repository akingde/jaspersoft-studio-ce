package com.jaspersoft.studio.chart.model.series.time;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.chart.model.series.time.messages"; //$NON-NLS-1$
	public static String MTimeSeries_item_hyperlink;
	public static String MTimeSeries_item_hyperlink_description;
	public static String MTimeSeries_label_expression;
	public static String MTimeSeries_label_expression_description;
	public static String MTimeSeries_series_expression;
	public static String MTimeSeries_series_expression_description;
	public static String MTimeSeries_time_period_expression;
	public static String MTimeSeries_time_period_expression_description;
	public static String MTimeSeries_value_expression;
	public static String MTimeSeries_value_expression_description;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
