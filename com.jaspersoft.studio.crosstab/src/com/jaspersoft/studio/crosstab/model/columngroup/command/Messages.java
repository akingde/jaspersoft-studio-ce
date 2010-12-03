package com.jaspersoft.studio.crosstab.model.columngroup.command;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.crosstab.model.columngroup.command.messages"; //$NON-NLS-1$
	public static String CreateColumnGroupCommand_column_group;
	public static String CreateColumnGroupCommand_column_group_name;
	public static String CreateColumnGroupCommand_column_group_text_dialog;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
