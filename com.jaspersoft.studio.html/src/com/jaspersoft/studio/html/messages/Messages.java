/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.html.messages;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.html.messages.messages"; //$NON-NLS-1$

	public static String common_properties_category;
	
	public static String MHtml_cliponoverflow;

	public static String MHtml_cliponoverflow_desc;

	public static String MHtml_content_expression;
	public static String MHtml_content_expression_description;
	public static String MHtml_evaluation_group;
	public static String MHtml_evaluation_group_description;
	public static String MHtml_evaluation_time;
	public static String MHtml_evaluation_time_description;
	public static String MHtml_scaletype;
	public static String MHtml_scaletype_description;
	public static String MHtml_horizontalalign;
	public static String MHtml_horizontalalign_description;
	public static String MHtml_verticalalign;
	public static String MHtml_verticalalign_description;
	
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
