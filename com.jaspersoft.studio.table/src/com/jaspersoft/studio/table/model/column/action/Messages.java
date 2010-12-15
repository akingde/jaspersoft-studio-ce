package com.jaspersoft.studio.table.model.column.action;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.table.model.column.action.messages"; //$NON-NLS-1$
	public static String CreateColumnAction_create_column;
	public static String CreateColumnAction_create_column_tool_tip;

	public static String CreateColumnCellAction_create_column;
	public static String CreateColumnCellAction_create_column_tool_tip;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
