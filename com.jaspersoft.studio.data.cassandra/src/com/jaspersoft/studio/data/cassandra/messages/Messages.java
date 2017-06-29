/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.cassandra.messages;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.data.cassandra.messages.messages"; //$NON-NLS-1$
	public static String CassandraDataAdapterComposite_labelurl;
	public static String CassandraDataAdapterFactory_0;
	public static String CassandraDataAdapterFactory_1;
	public static String RDDatasourceCassandraPage_title;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
