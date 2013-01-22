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
package com.jaspersoft.studio.community;

/**
 * Constants related to the Community Site REST API.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class CommunityConstants {

	public static final String LOGIN_URL = "http://community.jaspersoft.com/community-api/user/login";
	public static final String FILE_UPLOAD_URL = "http://community.jaspersoft.com/community-api/file.json";
	public static final String ISSUE_CREATION_URL = "http://community.jaspersoft.com/community-api/node.json";
	public static final String JSON_CONTENT_TYPE = "application/json";
	public static final String REQUEST_CHARSET = "UTF-8";
	public static final String SECURE_PREFSTORE_PATHNAME = "jaspersoft_community_site";
	public static final int JSSPROJECT_COMMUNITY_ID = 496;
	
	private CommunityConstants(){
		// prevent instantiation...
	}
}
