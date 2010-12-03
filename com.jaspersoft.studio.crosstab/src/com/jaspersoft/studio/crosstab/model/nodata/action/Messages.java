package com.jaspersoft.studio.crosstab.model.nodata.action;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.crosstab.model.nodata.action.messages"; //$NON-NLS-1$
	public static String CreateCrosstabWhenNoDataAction_create_when_no_data_cell;
	public static String CreateCrosstabWhenNoDataAction_create_when_no_data_cell_tool_tip;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
