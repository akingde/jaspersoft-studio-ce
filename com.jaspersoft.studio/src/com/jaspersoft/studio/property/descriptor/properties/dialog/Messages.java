package com.jaspersoft.studio.property.descriptor.properties.dialog;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.property.descriptor.properties.dialog.messages"; //$NON-NLS-1$
	public static String JRPropertyEditor_properties;
	public static String JRPropertyPage_add;
	public static String JRPropertyPage_description;
	public static String JRPropertyPage_name;
	public static String JRPropertyPage_properties;
	public static String JRPropertyPage_table_is_empty;
	public static String JRPropertyPage_value;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
