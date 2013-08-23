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
package com.jaspersoft.translation.resources;

import java.util.List;

/**
 * Interface to provide a resources that can be translated. The resources container is inspired by  
 * a plugin but it can be essentially any container. view it like a container with a name and a 
 * list of resource definition that belong to that container
 * 
 * @author Orlandin Marco
 *
 */
public interface IResourcesInput {

	/**
	 * Return the name of the container of the resources
	 * 
	 * @return a string representing the name of the resources container
	 */
	public String getPluginName();
	
	/**
	 * Return the list of resources available in the container
	 * 
	 * @return not null list of resources
	 */
	public List<AbstractResourceDefinition> getResourcesElements();
}
