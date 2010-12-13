package com.jaspersoft.studio.model.group.command;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.model.group.command.messages"; //$NON-NLS-1$
	public static String CreateGroupCommand_group_name;
	public static String CreateGroupCommand_group_name_dialog;
	public static String ReorderGroupCommand_reorder_elements;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
