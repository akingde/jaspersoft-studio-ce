/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.hibernate3.messages;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.hibernate3.messages.messages"; //$NON-NLS-1$
	public static String Hibernate3ClasspathContainer_Description;
	public static String Hibernate3ClasspathContainerPage_Content;
	public static String Hibernate3ClasspathContainerPage_Description;
	public static String Hibernate3ClasspathContainerPage_Name;
	public static String Hibernate3ClasspathContainerPage_Title;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
