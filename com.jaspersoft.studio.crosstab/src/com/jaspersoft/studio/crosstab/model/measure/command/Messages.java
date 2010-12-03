package com.jaspersoft.studio.crosstab.model.measure.command;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.crosstab.model.measure.command.messages"; //$NON-NLS-1$
	public static String CreateMeasureCommand_measure;
	public static String CreateMeasureCommand_measure_name;
	public static String CreateMeasureCommand_measure_text_dialog;
	public static String ReorderMeasureCommand_reorder_elements;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
