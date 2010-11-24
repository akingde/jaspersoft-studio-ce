package com.jaspersoft.studio.chart.model.series.category;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.chart.model.series.category.messages"; //$NON-NLS-1$
	public static String MCategorySeries_category_expression;
	public static String MCategorySeries_category_expression_description;
	public static String MCategorySeries_item_hyperlink;
	public static String MCategorySeries_item_hyperlink_description;
	public static String MCategorySeries_label_expression;
	public static String MCategorySeries_label_expression_description;
	public static String MCategorySeries_series_expression;
	public static String MCategorySeries_series_expression_description;
	public static String MCategorySeries_value_expression;
	public static String MCategorySeries_value_expression_description;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
