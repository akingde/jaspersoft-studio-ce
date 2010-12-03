package com.jaspersoft.studio.crosstab.model.measure.action;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.crosstab.model.measure.action.messages"; //$NON-NLS-1$
	public static String CreateMeasureAction_create_measure;
	public static String CreateMeasureAction_create_measure_tool_tip;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
