/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.messages;

import java.util.ArrayList;
import java.util.List;

import com.jaspersoft.translation.resources.AbstractResourceDefinition;
import com.jaspersoft.translation.resources.PackageResourceDefinition;

/**
 * Publish the resource that can be translated through the translation plugin
 * 
 * @author Orlandin Marco
 *
 */
public class ResourcePublisher extends com.jaspersoft.studio.messages.ResourcePublisher{

	@Override
	public String getPluginName() {
		return "com.jaspersoft.studio.server";
	}

	protected List<AbstractResourceDefinition> initializeProperties(){
		List<AbstractResourceDefinition> result = new ArrayList<AbstractResourceDefinition>();
		result.add(new PackageResourceDefinition("en_EN", 
												 "com.jaspersoft.studio.server.messages", 
												 "messages.properties", 
												 "In this file there are the strings used by the server plugin, that allow the connection beetween JasperSoft Studio and JasperReports Server",
												 getClassLoader(),
												 "com/jaspersoft/studio/server/messages/messages.properties", this));
		
		result.add(new PackageResourceDefinition("en_EN", 
												 null, 
												 "plugin.properties", 
												 "In this file there are the strings used by the server plugin, that allow the connection beetween JasperSoft Studio and JasperReports Server",
												 getClassLoader(),
												 "plugin.properties", this));
		
		propertiesCache.put(getPluginName(), result);
		return result;
	}


}
