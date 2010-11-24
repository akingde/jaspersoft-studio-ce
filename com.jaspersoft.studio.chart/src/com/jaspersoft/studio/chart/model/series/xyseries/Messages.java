package com.jaspersoft.studio.chart.model.series.xyseries;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.chart.model.series.xyseries.messages"; //$NON-NLS-1$
	public static String MXYSeries_item_hyperlink;
	public static String MXYSeries_item_hyperlink_description;
	public static String MXYSeries_label_expression;
	public static String MXYSeries_label_expression_description;
	public static String MXYSeries_series_expression;
	public static String MXYSeries_series_expression_description;
	public static String MXYSeries_x_value_expression;
	public static String MXYSeries_x_value_expression_description;
	public static String MXYSeries_y_value_expression;
	public static String MXYSeries_y_value_expression_description;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
