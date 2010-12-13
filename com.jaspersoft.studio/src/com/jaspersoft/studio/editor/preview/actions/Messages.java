package com.jaspersoft.studio.editor.preview.actions;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.editor.preview.actions.messages"; //$NON-NLS-1$
	public static String ReloadAction_reload;
	public static String ReloadAction_reload_description;
	public static String ReloadAction_reload_tool_tip;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
