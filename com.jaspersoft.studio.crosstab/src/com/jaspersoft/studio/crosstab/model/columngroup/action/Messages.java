package com.jaspersoft.studio.crosstab.model.columngroup.action;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.crosstab.model.columngroup.action.messages"; //$NON-NLS-1$
	public static String CreateColumnGroupAction_create_column_group;
	public static String CreateColumnAction_create_column_group_tool_tip;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
