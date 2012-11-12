package com.jaspersoft.studio.data.hive.messages;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.data.hive.messages.messages"; //$NON-NLS-1$
	public static String HiveDataAdapterComposite_labelurl;
	public static String RDDatasourceHivePage_desc;
	public static String RDDatasourceHivePage_tiitle;
	public static String RDDatasourceHivePage_title;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
