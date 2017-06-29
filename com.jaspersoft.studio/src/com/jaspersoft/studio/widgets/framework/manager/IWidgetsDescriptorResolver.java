/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.manager;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;

/**
 * Interface for a descriptor resolver. A descriptor resolver uses an URL and a {@link JasperReportsConfiguration}
 * to instantiate a {@link WidgetsDescriptor} from that informations. If this is used trough the
 * {@link WidgetsDefinitionManager} all the caching is provided directly, without needed to implement it
 * inside here
 * 
 * @author Orlandin Marco
 *
 */
public interface IWidgetsDescriptorResolver {

	/**
	 * Create a widget descriptor from a url
	 * 
	 * @param jConfig the current {@link JasperReportsConfiguration}
	 * @param URL the url of the resource to load
	 * @return a widget descriptor, could be null if something goes wrong during the loading
	 */
	public WidgetsDescriptor loadDescriptor(JasperReportsConfiguration jConfig, String URL);
	
	/**
	 * Return an unique identifier for this specific definition, it will be used for caching
	 * 
	 * @param jConfig the configuration that triggered the load of the definition
	 * @param URL the url of the loaded resource
	 * @return a not null key for this definition
	 */
	public String getKey(JasperReportsConfiguration jConfig, String URL);
	
	/**
	 * Used to know if a loaded definition with this Resolver should be unloaded after the config
	 * that triggered its loading is closed
	 * 
	 * @return true if the definition should be unloaded with the {@link JasperReportsConfiguration}, flase otherwise
	 */
	public boolean unloadOnConfigurationDispose();
}
