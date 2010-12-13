package com.jaspersoft.studio.wizards;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.wizards.messages"; //$NON-NLS-1$
	public static String ReportNewWizard_new_report;
	public static String ReportNewWizardPage_browse;
	public static String ReportNewWizardPage_container;
	public static String ReportNewWizardPage_description;
	public static String ReportNewWizardPage_file_container_must_be_specified;
	public static String ReportNewWizardPage_file_container_must_exist;
	public static String ReportNewWizardPage_file_extension_must_be_jrxml;
	public static String ReportNewWizardPage_file_name;
	public static String ReportNewWizardPage_file_name_must_be_specified;
	public static String ReportNewWizardPage_file_name_must_be_valid;
	public static String ReportNewWizardPage_project_must_be_writable;
	public static String ReportNewWizardPage_select_new_file_container;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
