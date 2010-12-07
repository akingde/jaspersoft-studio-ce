package com.jaspersoft.studio.list.commands;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.list.commands.messages"; //$NON-NLS-1$
	public static String OrphanListCommand_orphan_child;
	public static String ReorderListCommand_reorder_elements;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
