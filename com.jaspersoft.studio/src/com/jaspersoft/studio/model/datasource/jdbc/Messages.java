package com.jaspersoft.studio.model.datasource.jdbc;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.model.datasource.jdbc.messages"; //$NON-NLS-1$
	public static String MJDBCDataSource_classpath;
	public static String MJDBCDataSource_connection;
	public static String MJDBCDataSource_driver_class;
	public static String MJDBCDataSource_jdbc_url;
	public static String MJDBCDataSource_password;
	public static String MJDBCDataSource_username;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
