package com.jaspersoft.studio.model.parameter;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.model.parameter.messages"; //$NON-NLS-1$
	public static String MParameter_default_value_expression;
	public static String MParameter_default_value_expression_description;
	public static String MParameter_description;
	public static String MParameter_description_description;
	public static String MParameter_is_for_prompting;
	public static String MParameter_is_for_prompting_description;
	public static String MParameter_nested_type_name;
	public static String MParameter_nested_type_name_description;
	public static String MParameter_properties;
	public static String MParameter_properties_description;
	public static String MParameterSystem_class;
	public static String MParameterSystem_class_description;
	public static String MParameterSystem_name;
	public static String MParameterSystem_name_description;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
