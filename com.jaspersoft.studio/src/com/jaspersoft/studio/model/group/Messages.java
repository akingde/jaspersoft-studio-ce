package com.jaspersoft.studio.model.group;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.model.group.messages"; //$NON-NLS-1$
	public static String MGroup_expression;
	public static String MGroup_expression_description;
	public static String MGroup_name;
	public static String MGroup_name_description;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
