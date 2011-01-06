package com.jaspersoft.studio.list.messages;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.list.messages.messages"; //$NON-NLS-1$
	public static String ListEditor_list;
	public static String MList_cell_height;
	public static String MList_cell_height_description;
	public static String MList_cell_width;
	public static String MList_cell_width_description;
	public static String MList_dataset_run;
	public static String MList_dataset_run_description;
	public static String MList_ignore_width;
	public static String MList_ignore_width_description;
	public static String MList_list_properties_category;
	public static String MList_print_order;
	public static String MList_print_order_description;
	public static String OrphanListCommand_orphan_child;
	public static String ReorderListCommand_reorder_elements;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
