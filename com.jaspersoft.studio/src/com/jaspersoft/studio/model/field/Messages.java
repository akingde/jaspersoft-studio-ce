package com.jaspersoft.studio.model.field;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.model.field.messages"; //$NON-NLS-1$
	public static String MField_class;
	public static String MField_class_description;
	public static String MField_description;
	public static String MField_description_description;
	public static String MField_name;
	public static String MField_name_description;
	public static String MField_properties;
	public static String MField_properties_description;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
