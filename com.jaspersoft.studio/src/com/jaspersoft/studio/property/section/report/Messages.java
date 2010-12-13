package com.jaspersoft.studio.property.section.report;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.property.section.report.messages"; //$NON-NLS-1$
	public static String PageMarginSection_bottom_margin_tool_tip;
	public static String PageMarginSection_left_margin_tool_tip;
	public static String PageMarginSection_margin;
	public static String PageMarginSection_right_margin_tool_tip;
	public static String PageMarginSection_top_margin_tool_tip;
	public static String PageSizeSection_height_tool_tip;
	public static String PageSizeSection_landscape;
	public static String PageSizeSection_landscape_tool_tip;
	public static String PageSizeSection_page_size;
	public static String PageSizeSection_portrait;
	public static String PageSizeSection_portrait_tool_tip;
	public static String PageSizeSection_width_tool_tip;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
