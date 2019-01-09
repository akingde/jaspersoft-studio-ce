/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.svgimporter.messages;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.svgimporter.messages.messages"; //$NON-NLS-1$
	public static String ImportContentAction_actionName;
	public static String ImportContentAction_actionTooltip;
	public static String InkscapeMessageDialog_cancelOption;
	public static String InkscapeMessageDialog_dialogMessage;
	public static String InkscapeMessageDialog_dialogTitle;
	public static String InkscapeMessageDialog_fileDialogTitle;
	public static String InkscapeMessageDialog_partentLabel;
	public static String InkscapeMessageDialog_retryOption;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
