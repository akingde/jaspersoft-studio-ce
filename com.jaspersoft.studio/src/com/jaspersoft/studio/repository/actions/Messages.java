package com.jaspersoft.studio.repository.actions;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.repository.actions.messages"; //$NON-NLS-1$
	public static String CreateDataSourceAction_create;
	public static String CreateDataSourceAction_description;
	public static String CreateDataSourceAction_tool_tip;
	public static String EditDataSourceAction_description;
	public static String EditDataSourceAction_edit;
	public static String EditDataSourceAction_tool_tip;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
