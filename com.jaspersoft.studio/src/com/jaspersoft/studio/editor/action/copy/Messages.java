package com.jaspersoft.studio.editor.action.copy;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.editor.action.copy.messages"; //$NON-NLS-1$
	public static String CopyAction_copy;
	public static String CutAction_cut;
	public static String PasteAction_paste;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
