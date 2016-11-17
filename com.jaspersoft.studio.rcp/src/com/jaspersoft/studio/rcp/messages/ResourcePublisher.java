/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.rcp.messages;

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
		return "com.jaspersoft.studio.rcp";
	}

	protected List<AbstractResourceDefinition> initializeProperties(){
		List<AbstractResourceDefinition> result = new ArrayList<AbstractResourceDefinition>();
		result.add(new PackageResourceDefinition("en_EN", 
												 "com.jaspersoft.studio.rcp.messages", 
												 "messages.properties", 
												 "In this file there are some strings used only by the RCP (standalone) versione of Jaspersoft Studio",
												 getClassLoader(),
												 "com/jaspersoft/studio/rcp/messages/messages.properties", this));
		
		result.add(new PackageResourceDefinition("en_EN", 
												 null, 
												 "plugin.properties", 
												 "In this file there are some strings used only by the RCP (standalone) versione of Jaspersoft Studio",
												 getClassLoader(),
												 "plugin.properties", this));
		
		propertiesCache.put(getPluginName(), result);
		return result;
	}


}
