package com.jaspersoft.studio.table.model.column.command;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.table.model.column.command.messages"; //$NON-NLS-1$
	public static String CreateColumnCommand_column;
	public static String CreateColumnCommand_column_name;
	public static String CreateColumnCommand_column_text_dialog;
	public static String ReorderColumnCommand_column_text_dialog;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
