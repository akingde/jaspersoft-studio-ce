package com.jaspersoft.studio.crosstab.model.columngroup;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.crosstab.model.columngroup.messages"; //$NON-NLS-1$
	public static String MColumnGroup_column_position;
	public static String MColumnGroup_column_position_description;
	public static String MColumnGroup_height;
	public static String MColumnGroup_height_description;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
