package com.jaspersoft.studio.model.genericElement;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.model.genericElement.messages"; //$NON-NLS-1$
	public static String MGenericElement_evaluation_group_name;
	public static String MGenericElement_evaluation_group_name_description;
	public static String MGenericElement_evaluation_time;
	public static String MGenericElement_evaluation_time_description;
	public static String MGenericElement_generic_type_name;
	public static String MGenericElement_generic_type_name_description;
	public static String MGenericElement_generic_type_namespace;
	public static String MGenericElement_generic_type_namespace_description;
	public static String MGenericElement_parameters;
	public static String MGenericElement_parameters_description;
	public static String MGenericElement_properties_category;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
