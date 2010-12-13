package com.jaspersoft.studio.editor.gef.rulers.command;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.editor.gef.rulers.command.messages"; //$NON-NLS-1$
	public static String CreateGuideCommand_create_guide;
	public static String DeleteGuideCommand_delete_guide;
	public static String MoveGuideCommand_move_guide;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
