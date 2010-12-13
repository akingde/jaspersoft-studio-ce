package com.jaspersoft.studio.property.descriptor.expression.dialog;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.property.descriptor.expression.dialog.messages"; //$NON-NLS-1$
	public static String JRExpressionEditor_expression_editor;
	public static String JRExpressionPage_description;
	public static String JRExpressionPage_expression;
	public static String JRExpressionPage_expression_editor;
	public static String JRExpressionPage_value_class_name;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
