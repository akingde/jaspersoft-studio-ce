package com.jaspersoft.studio.property.descriptor.subreport.parameter.dialog;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.property.descriptor.subreport.parameter.dialog.messages"; //$NON-NLS-1$
	public static String SubreportPropertyEditor_properties;
	public static String SubreportPropertyPage_add;
	public static String SubreportPropertyPage_delete;
	public static String SubreportPropertyPage_description;
	public static String SubreportPropertyPage_expression;
	public static String SubreportPropertyPage_name;
	public static String SubreportPropertyPage_subreport_parameters;
	public static String SubreportPropertyPage_table_is_empty;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
