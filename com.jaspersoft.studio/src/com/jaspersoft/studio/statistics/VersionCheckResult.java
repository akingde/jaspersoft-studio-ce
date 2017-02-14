/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.statistics;

import net.sf.jasperreports.eclipse.util.FileUtils;

/**
 * Contains the result for the check of a newer version of the JSS
 * 
 * @author Orlandin Marco
 *
 */
public class VersionCheckResult {

	/**
	 * The version on the server
	 */
	private String serverVersion;
	
	/**
	 * An optional message returned by the server
	 */
	private String optionalMessage;
	
	/**
	 * The currently installed version
	 */
	private String currentVersion;
	
	/**
	 * Create an empty container with all the fields set to null
	 */
	public VersionCheckResult(){
		this.serverVersion = null;
		this.optionalMessage = null;
		this.currentVersion = null;
	}
	
	/**
	 * Create a container and initialize the fields
	 * 
	 * @param serverVersion The version on the server
	 * @param optionalMessage An optional message returned by the server
	 * @param currentVersion The currently installed version
	 */
	public VersionCheckResult(String serverVersion, String optionalMessage, String currentVersion){
		this.serverVersion = serverVersion;
		this.optionalMessage = optionalMessage;
		this.currentVersion = currentVersion;
	}

	/**
	 * Return the version on the server
	 * 
	 * @return the JSS version on the server, can be null
	 */
	public String getServerVersion() {
		return serverVersion;
	}

	/**
	 * Return the optional message provided by the server
	 * 
	 * @return a message, can be null
	 */
	public String getOptionalMessage() {
		return optionalMessage;
	}

	/**
	 * Validate the information to know if the update is available. The update
	 * is available if the version on the server is not null and greater from
	 * the currently installed version. If there is any error it return 
	 * false
	 * 
	 * @return true if the there is an update available, false otherwise
	 */
	public boolean canUpdate(){
		try{ 
			return serverVersion != null && !serverVersion.equals("ko") && FileUtils.versionCompare(serverVersion, currentVersion) > 0;
		} catch (Exception ex){
			return false;
		}
	}
}

