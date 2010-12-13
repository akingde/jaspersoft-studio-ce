package com.jaspersoft.studio.property.descriptor.jrQuery.dialog;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.property.descriptor.jrQuery.dialog.messages"; //$NON-NLS-1$
	public static String JRQueryEditor_query_editor;
	public static String JRQueryPage_description;
	public static String JRQueryPage_language;
	public static String JRQueryPage_query;
	public static String JRQueryPage_query_editor;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
