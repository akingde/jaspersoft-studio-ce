package com.jaspersoft.studio.crosstab.model.parameter.command;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.crosstab.model.parameter.command.messages"; //$NON-NLS-1$
	public static String CreateParameterCommand_parameter;
	public static String CreateParameterCommand_parameter_name;
	public static String CreateParameterCommand_parameter_text_dialog;
	public static String ReorderParameterCommand_reorder_elements;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
