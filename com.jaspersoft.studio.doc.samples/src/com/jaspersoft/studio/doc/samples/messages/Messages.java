/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.doc.samples.messages;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.doc.samples.messages.messages"; //$NON-NLS-1$
	public static String ImportSamplesWizardHandler_name_label;
	public static String ImportSamplesWizardHandler_plugin_exist;
	public static String ImportSamplesWizardHandler_suggested_name;
	public static String OpenReportHandler_warningmessage_text1;
	public static String OpenReportHandler_warningmessage_text2;
	public static String OpenReportHandler_warningmessage_title;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
