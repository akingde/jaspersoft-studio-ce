package com.jaspersoft.studio.barcode.command;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.barcode.command.messages"; //$NON-NLS-1$
	public static String BarcodeWizard_window_title;
	public static String BarcodeWizardPage_barbecue_types;
	public static String BarcodeWizardPage_barcode_wizard;
	public static String BarcodeWizardPage_barcode_wizard_description_a;
	public static String BarcodeWizardPage_barcode_wizard_description_b;
	public static String BarcodeWizardPage_barcode4j_types;
	public static String BarcodeWizardPage_name;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
