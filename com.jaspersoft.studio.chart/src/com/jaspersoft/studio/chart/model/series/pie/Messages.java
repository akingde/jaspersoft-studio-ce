package com.jaspersoft.studio.chart.model.series.pie;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.chart.model.series.pie.messages"; //$NON-NLS-1$
	public static String MPieSeries_key_expression;
	public static String MPieSeries_key_expression_description;
	public static String MPieSeries_label_expression;
	public static String MPieSeries_label_expression_description;
	public static String MPieSeries_section_hyperlink;
	public static String MPieSeries_section_hyperlink_description;
	public static String MPieSeries_value_expression;
	public static String MPieSeries_value_expression_description;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
