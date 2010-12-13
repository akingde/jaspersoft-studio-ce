package com.jaspersoft.studio.editor.action;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.editor.action.messages"; //$NON-NLS-1$
	public static String ShowPropertyViewAction_show_properties;
	public static String ShowPropertyViewAction_show_properties_tool_tip;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
