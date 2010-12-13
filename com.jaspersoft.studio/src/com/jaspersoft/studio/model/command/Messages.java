package com.jaspersoft.studio.model.command;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.model.command.messages"; //$NON-NLS-1$
	public static String OrphanElementCommand_orphan_child;
	public static String OrphanElementGroupCommand_orphan_child;
	public static String ReorderElementCommand_reorder_elements;
	public static String ReorderElementGroupCommand_reorder_elements;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
