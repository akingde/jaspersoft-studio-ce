package com.jaspersoft.studio.model.datasource;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.model.datasource.messages"; //$NON-NLS-1$
	public static String AMDatasource_datasource_name;
	public static String AMFileDataSource_date_format;
	public static String AMFileDataSource_fine_name;
	public static String AMFileDataSource_number_format;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
