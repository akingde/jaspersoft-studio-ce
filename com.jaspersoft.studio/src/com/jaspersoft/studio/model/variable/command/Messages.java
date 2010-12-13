package com.jaspersoft.studio.model.variable.command;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.model.variable.command.messages"; //$NON-NLS-1$
	public static String ReorderVariableCommand_reorder_elements;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
