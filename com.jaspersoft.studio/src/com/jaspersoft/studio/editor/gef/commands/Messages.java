package com.jaspersoft.studio.editor.gef.commands;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.editor.gef.commands.messages"; //$NON-NLS-1$
	public static String BandConstraintCommand_band_resized;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
