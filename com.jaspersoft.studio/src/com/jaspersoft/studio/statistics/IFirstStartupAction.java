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
