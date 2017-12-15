/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.doc.messages;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.doc.messages.messages"; //$NON-NLS-1$
	public static String OpenQueryHandler_message_text;
	public static String OpenQueryHandler_message_title;
	public static String UploadReportCheatAction_no_server_title;
	public static String UploadReportCheatAction_no_server_warning;
	public static String UploadReportCheatAction_warning_message;
	public static String UploadReportCheatAction_warning_title;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
