/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
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
	public static String MHtml_width;
	public static String MHtml_width_description;
	public static String MHtml_height;
	public static String MHtml_height_description;
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
