package com.jaspersoft.studio.model.variable;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.model.variable.messages"; //$NON-NLS-1$
	public static String MVariable_calculation;
	public static String MVariable_calculation_description;
	public static String MVariable_expression;
	public static String MVariable_expression_description;
	public static String MVariable_increment_group;
	public static String MVariable_increment_group_description;
	public static String MVariable_increment_type;
	public static String MVariable_increment_type_description;
	public static String MVariable_incrementer_factory_class_name;
	public static String MVariable_incrementer_factory_class_name_description;
	public static String MVariable_initial_value_expression;
	public static String MVariable_initial_value_expression_description;
	public static String MVariable_reset_group;
	public static String MVariable_reset_group_description;
	public static String MVariable_reset_type;
	public static String MVariable_reset_type_description;
	public static String MVariableSystem_name;
	public static String MVariableSystem_name_description;
	public static String MVariableSystem_value_class_name;
	public static String MVariableSystem_value_class_name_description;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
