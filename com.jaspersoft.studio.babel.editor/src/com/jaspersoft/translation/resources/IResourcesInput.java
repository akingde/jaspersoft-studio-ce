/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.translation.resources;

import java.util.List;

/**
 * Abstract class to provide a resources that can be translated. The resources container is inspired by  
 * a plugin but it can be essentially any container. view it like a container with a name and a 
 * list of resource definition that belong to that container
 * 
 * @author Orlandin Marco
 *
 */
public abstract class IResourcesInput implements Comparable<IResourcesInput> {

	/**
	 * Return the name of the container of the resources
	 * 
	 * @return a string representing the name of the resources container
	 */
	public abstract String getPluginName();
	
	/**
	 * Return the list of resources available in the container
	 * 
	 * @return not null list of resources
	 */
	public abstract List<AbstractResourceDefinition> getResourcesElements();
	
	/**
	 * This is used to contribute contextual help information for the plugin
	 * 
	 * @return the id of the information to shows when the user press the help keu
	 */
	public abstract String getContextId();
	
	@Override
	public int compareTo(IResourcesInput o) {
		return getPluginName().compareTo(o.getPluginName());
	}
}
