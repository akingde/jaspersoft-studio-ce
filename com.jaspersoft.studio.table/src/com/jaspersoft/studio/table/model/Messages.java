package com.jaspersoft.studio.table.model;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.table.model.messages"; //$NON-NLS-1$
	public static String MCell_height;
	public static String MCell_line_box;
	public static String MCell_line_box_description;
	public static String MCell_parent_style;
	public static String MCell_parent_style_description;
	public static String MCell_properties_category;
	public static String MColumn_column_width;
	public static String MColumn_print_when_expression;
	public static String MColumn_print_when_expression_description;
	public static String MColumn_properties_category;
	public static String MTable_dataset_run;
	public static String MTable_dataset_run_description;
	public static String MTable_properties_category;
	public static String MTableGroupFooter_group_footer;
	public static String MTableGroupHeader_group_header;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
