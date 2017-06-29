/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.statistics;

import java.io.File;

/**
 * Interface for an action that should be executed the first time eclipse is started
 * 
 * @author Orlandin Marco
 *
 */
public interface IFirstStartupAction {

	/**
	 * Execute the action
	 * 
	 * @param configurationDirectory not null directory where the configuration information
	 * of the currently running JSS are saved
	 */
	public void executeFirstStartupAction(File configurationDirectory);
	
}
