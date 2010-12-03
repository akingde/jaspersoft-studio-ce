package com.jaspersoft.studio.crosstab.model.header;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.crosstab.model.header.messages"; //$NON-NLS-1$
	public static String MCrosstabHeader_crosstab_header;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
