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
	
}
