package com.jaspersoft.studio.model.sortfield;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.model.sortfield.messages"; //$NON-NLS-1$
	public static String MSortField_name;
	public static String MSortField_name_description;
	public static String MSortField_order;
	public static String MSortField_order_description;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
