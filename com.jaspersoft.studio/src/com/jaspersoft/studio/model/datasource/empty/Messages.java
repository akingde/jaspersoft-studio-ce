package com.jaspersoft.studio.model.datasource.empty;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.model.datasource.empty.messages"; //$NON-NLS-1$
	public static String MEmptyDataSource_size;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
