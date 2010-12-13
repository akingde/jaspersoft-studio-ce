package com.jaspersoft.studio.model.datasource.xml;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.model.datasource.xml.messages"; //$NON-NLS-1$
	public static String MXMLDataSource_xpath_locale;
	public static String MXMLDataSource_xpath_select;
	public static String MXMLDataSource_xpath_timezone;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
