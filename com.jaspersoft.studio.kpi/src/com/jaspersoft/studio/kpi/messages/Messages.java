package com.jaspersoft.studio.kpi.messages;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.kpi.messages.messages"; //$NON-NLS-1$
	public static String KPIWizardDialog_deleteButton;
	public static String KPIWizardDialog_removeErrorMessage;
	public static String KPIWizardDialog_removeErrorTitle;
	public static String KPIWizardDialog_removeMessage;
	public static String KPIWizardDialog_removeSuccess;
	public static String KPIWizardDialog_removeTitle;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
