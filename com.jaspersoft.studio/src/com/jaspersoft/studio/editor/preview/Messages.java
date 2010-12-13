package com.jaspersoft.studio.editor.preview;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.editor.preview.messages"; //$NON-NLS-1$
	public static String ParametersDialog_report_parameters;
	public static String PreviewEditor_connection_could_not_be_established;
	public static String PreviewEditor_fill_report;
	public static String PreviewEditor_no_datasource;
	public static String PreviewEditor_preview_a;
	public static String PreviewEditor_preview_b;
	public static String PreviewEditor_reloading;
	public static String PreviewEditor_report_fill_canceled;
	public static String PreviewEditor_starting;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
