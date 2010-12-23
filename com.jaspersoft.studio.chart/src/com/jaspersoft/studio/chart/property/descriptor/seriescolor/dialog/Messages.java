package com.jaspersoft.studio.chart.property.descriptor.seriescolor.dialog;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.chart.property.descriptor.seriescolor.dialog.messages"; //$NON-NLS-1$
	public static String common_series_colors;
	public static String SeriesColorPage_add;
	public static String SeriesColorPage_color;
	public static String SeriesColorPage_delete;
	public static String SeriesColorPage_description;
	public static String SeriesColorPage_move_down;
	public static String SeriesColorPage_move_up;
	public static String SeriesColorPage_table_is_empty;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
