package com.jaspersoft.studio.crosstab.model.header.action;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.crosstab.model.header.action.messages"; //$NON-NLS-1$
	public static String CreateCrosstabHeaderAction_create_crosstab_header;
	public static String CreateCrosstabHeaderAction_create_crosstab_header_too_tip;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
