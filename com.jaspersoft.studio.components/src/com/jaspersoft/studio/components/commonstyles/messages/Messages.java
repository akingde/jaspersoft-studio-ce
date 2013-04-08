package com.jaspersoft.studio.components.commonstyles.messages;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.components.commonstyles.messages.messages"; //$NON-NLS-1$
	public static String CommonViewProvider_createStyleLabel;
	public static String CommonViewProvider_createStyleToolButton;
	public static String CommonViewProvider_deleteStyleLabel;
	public static String CommonViewProvider_deleteStyleQuestionText;
	public static String CommonViewProvider_deleteStyleQuestionTitle;
	public static String CommonViewProvider_deleteStyleToolButton;
	public static String CommonViewProvider_editStyleLabel;
	public static String CommonViewProvider_editStyleToolButton;
	public static String CommonViewProvider_finishLabel;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
