package com.jaspersoft.studio.model.field.command;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.model.field.command.messages"; //$NON-NLS-1$
	public static String CreateFieldCommand_field_name;
	public static String CreateFieldCommand_field_name_dialog;
	public static String ReorderFieldCommand_reorder_elements;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
