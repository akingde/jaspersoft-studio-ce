package com.jaspersoft.studio.editor.action.size;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.editor.action.size.messages"; //$NON-NLS-1$
	public static String MatchSizeAction_match_size;
	public static String MatchSizeAction_match_size_tool_tip;
	public static String Size2BorderAction_fit_both;
	public static String Size2BorderAction_fit_both_tool_tip;
	public static String Size2BorderAction_fit_height;
	public static String Size2BorderAction_fit_height_tool_tip;
	public static String Size2BorderAction_fit_width;
	public static String Size2BorderAction_fit_width_tool_tip;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
