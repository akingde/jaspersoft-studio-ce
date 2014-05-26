/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.data.hive.messages;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.data.hive.messages.messages"; //$NON-NLS-1$
	public static String HiveDataAdapterComposite_labelPassword;
	public static String HiveDataAdapterComposite_labelurl;
	public static String HiveDataAdapterComposite_labelUsername;
	public static String HiveDataAdapterFactory_description;
	public static String HiveDataAdapterFactory_label;
	public static String RDDatasourceHivePage_desc;
	public static String RDDatasourceHivePage_tiitle;
	public static String RDDatasourceHivePage_title;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
