package com.jaspersoft.studio.data.sql.messages;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.data.sql.messages.messages"; //$NON-NLS-1$
	public static String SQLQueryDesigner_diagram;
	public static String SQLQueryDesigner_outline;
	public static String SQLQueryDesigner_readmetadata;
	public static String SQLQueryDesigner_text;
	public static String SQLEditorPreferencesPage_comboLabel;
	public static String SQLEditorPreferencesPage_dialogTitle;
	public static String Text2Model_warn;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
