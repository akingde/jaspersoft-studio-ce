package com.jaspersoft.studio.crosstab;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.crosstab.messages"; //$NON-NLS-1$
	public static String CrosstabComponentFactory_detail;
	public static String CrosstabComponentFactory_header;
	public static String CrosstabComponentFactory_total;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
