/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.hibernate.messages;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.hibernate.messages.messages"; //$NON-NLS-1$
	public static String HibernateClasspathContainer_Description;
	public static String HibernateClasspathContainerPage_Content;
	public static String HibernateClasspathContainerPage_Description;
	public static String HibernateClasspathContainerPage_Name;
	public static String HibernateClasspathContainerPage_Title;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
