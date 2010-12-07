package com.jaspersoft.studio.table.model;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.table.model.messages"; //$NON-NLS-1$
	public static String MTable_dataset_run;
	public static String MTable_dataset_run_description;
	public static String MTable_properties_category;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
