package com.jaspersoft.studio.crosstab.model.rowgroup;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.crosstab.model.rowgroup.messages"; //$NON-NLS-1$
	public static String MRowGroup_row_position;
	public static String MRowGroup_row_position_description;
	public static String MRowGroup_width;
	public static String MRowGroup_width_description;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
