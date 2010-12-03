package com.jaspersoft.studio.crosstab.model.rowgroup.action;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.crosstab.model.rowgroup.action.messages"; //$NON-NLS-1$
	public static String CreateRowGroupAction_create_row_group;
	public static String CreateRowGroupAction_create_row_group_tool_tip;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
