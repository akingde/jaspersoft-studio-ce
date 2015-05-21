/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.backward;

/**
 * Definition of a JR, contains the url to download it and it's version
 * 
 * @author Orlandin Marco
 *
 */
public class JRDefinition {

	/**
	 * URL to download the JR zip
	 */
	private String resourceURL;
	
	/**
	 * Version of the JR that will be downloaded
	 */
	private String version;
	
	/**
	 * Create a new JR definition 
	 * 
	 * @param resourceURL URL to download the JR zip
	 * @param version Version of the JR that will be downloaded
	 */
	public JRDefinition(String resourceURL, String version){
		this.resourceURL = resourceURL;
		this.version = version;
	}

	/**
	 * Return the url of the zip of jr
	 * 
	 * @return an url as string
	 */
	public String getResourceURL() {
		return resourceURL;
	}

	/**
	 * Get the version of JR pointed by the url
	 * 
	 * @return a version in the format X.Y.Z
	 */
	public String getVersion() {
		return version;
	}
	
}
