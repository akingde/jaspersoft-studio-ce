package com.jaspersoft.studio.table.model.table.command;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.barcode.command.messages"; //$NON-NLS-1$
	public static String TableWizard_window_title;
	public static String TableWizardPage_wizard;
	public static String TableWizardPage_wizard_description_a;
	public static String TableWizardPage_wizard_description_b;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
