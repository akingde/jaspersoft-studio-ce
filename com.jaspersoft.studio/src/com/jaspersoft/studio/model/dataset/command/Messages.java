package com.jaspersoft.studio.model.dataset.command;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.model.dataset.command.messages"; //$NON-NLS-1$
	public static String ReorderDatasetCommand_reorder_elements;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
