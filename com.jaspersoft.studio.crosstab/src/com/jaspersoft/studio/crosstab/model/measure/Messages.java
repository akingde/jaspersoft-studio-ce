package com.jaspersoft.studio.crosstab.model.measure;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.crosstab.model.measure.messages"; //$NON-NLS-1$
	public static String MMeasure_calculation;
	public static String MMeasure_calculation_description;
	public static String MMeasure_increment_factory_class_name;
	public static String MMeasure_increment_factory_class_name_description;
	public static String MMeasure_name;
	public static String MMeasure_name_description;
	public static String MMeasure_percentage_calculation_class_name;
	public static String MMeasure_percentage_calculation_class_name_description;
	public static String MMeasure_percentage_of_type;
	public static String MMeasure_percentage_of_type_description;
	public static String MMeasure_value_class;
	public static String MMeasure_value_class_desription;
	public static String MMeasure_value_expression;
	public static String MMeasure_value_expression_description;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
