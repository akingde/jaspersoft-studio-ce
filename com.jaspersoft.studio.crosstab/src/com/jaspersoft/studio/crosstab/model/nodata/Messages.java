package com.jaspersoft.studio.crosstab.model.nodata;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.crosstab.model.nodata.messages"; //$NON-NLS-1$
	public static String MCrosstabWhenNoData_when_no_data;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
