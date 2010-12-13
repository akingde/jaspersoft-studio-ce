package com.jaspersoft.studio.editor.outline;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.editor.outline.messages"; //$NON-NLS-1$
	public static String JDReportOutlineView_show_outline_tool_tip;
	public static String JDReportOutlineView_show_overview_tool_tip;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
