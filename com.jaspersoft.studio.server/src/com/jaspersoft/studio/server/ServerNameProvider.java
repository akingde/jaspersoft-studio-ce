/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server;

import java.io.File;

import org.w3c.dom.Node;

import com.jaspersoft.studio.ConfigurationManager;
import com.jaspersoft.studio.IConversionFilenameProvider;

/**
 * Name provider for the dserver configuration files inside a storage
 * 
 * @author Orlandin Marco
 *
 */
public class ServerNameProvider implements IConversionFilenameProvider {

	/**
	 * Get the name for a server configuration element. It uses
	 * the content of the tag name as base name for the server configuration.
	 * If the tag name can't be found it fallback and uses a static string plus 
	 * a counter to avoid duplicates
	 */
	@Override
	public String getFileName(Node configurationElementNode) {
		String baseName = "serverProfile_";
		int index = 0;
		File storage = ConfigurationManager.getStorage(ServerManager.PREF_TAG);
		File testName = new File(storage, baseName+index);
		while(testName.exists()){
			index++;
			testName = new File(storage, baseName+index);
		}
		return baseName+index;
	}
}
