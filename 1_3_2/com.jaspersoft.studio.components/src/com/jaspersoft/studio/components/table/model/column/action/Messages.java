package com.jaspersoft.studio.components.table.model.column.action;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.components.table.model.column.action.messages"; //$NON-NLS-1$
	public static String CreateColumnAfterAction_desc;
	public static String CreateColumnAfterAction_title;
	public static String CreateColumnBeforeAction_desc;
	public static String CreateColumnBeforeAction_title;
	public static String CreateColumnBeginAction_desc;
	public static String CreateColumnBeginAction_title;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
