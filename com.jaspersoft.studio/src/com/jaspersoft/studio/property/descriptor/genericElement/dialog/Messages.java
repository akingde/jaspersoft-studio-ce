package com.jaspersoft.studio.property.descriptor.genericElement.dialog;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.property.descriptor.genericElement.dialog.messages"; //$NON-NLS-1$
	public static String ParameterEditor_properties;
	public static String ParameterPage_add;
	public static String ParameterPage_delete;
	public static String ParameterPage_description;
	public static String ParameterPage_expression;
	public static String ParameterPage_generic_elements_parameters;
	public static String ParameterPage_parameter;
	public static String ParameterPage_skip_empty;
	public static String ParameterPage_table_is_empty;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
