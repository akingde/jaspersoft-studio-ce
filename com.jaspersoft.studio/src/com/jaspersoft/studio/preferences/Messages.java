package com.jaspersoft.studio.preferences;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.preferences.messages"; //$NON-NLS-1$
	public static String DesignerPreferencePage_description;
	public static String DesignerPreferencePage_element_design_border_style;
	public static String DesignerPreferencePage_page_border_style;
	public static String RulersGridPreferencePage_description;
	public static String RulersGridPreferencePage_grid_options;
	public static String RulersGridPreferencePage_grid_spacing_x;
	public static String RulersGridPreferencePage_grid_spacing_y;
	public static String RulersGridPreferencePage_ruler_options;
	public static String RulersGridPreferencePage_show_grid;
	public static String RulersGridPreferencePage_show_rulers;
	public static String RulersGridPreferencePage_snap_to_grid;
	public static String RulersGridPreferencePage_snap_to_guides;
	public static String RulersGridPreferencePage_snape_to_geometry;
	public static String StudioPreferencePage_example_text;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
