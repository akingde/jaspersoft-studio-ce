/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.manager;

import java.util.HashMap;

import org.eclipse.core.resources.IFile;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;

import net.sf.jasperreports.eclipse.util.FileUtils;

/**
 * Handle the load of the caching of the {@link WidgetsDescriptor}
 * 
 * @author Orlandin Marco
 *
 */
public class WidgetsDefinitionManager {

	/**
	 * The cache map for the descriptors
	 */
	private static HashMap<String, WidgetsDescriptor> descriptrosCache = new HashMap<String, WidgetsDescriptor>();
	
	/**
	 * Search for a {@link WidgetsDescriptor} starting from an URL and a {@link JasperReportsConfiguration}, first it look
	 * in the cache. If the resource is found it is returned, otherwise it try to load it using 
	 * the passed {@link IWidgetsDescriptorResolver}
	 * 
	 * @param jConfig the current {@link JasperReportsConfiguration}, must be not null
	 * @param URL the current URL of the resource, must be not null
	 * @param resolver the current {@link IWidgetsDescriptorResolver} should be not null, it is not used if the resource is 
	 * in the cache, so in same cases it could be null, but since the cache is an internal structure it is impossible to say
	 * from outside if it is safe to call this with a null resolver
	 * @return the {@link WidgetsDescriptor} loaded from the provided data or null if it can't be resolved
	 */
	public static WidgetsDescriptor getWidgetsDefinition(JasperReportsConfiguration jConfig, String URL, IWidgetsDescriptorResolver resolver){
		String key = getKey(jConfig, URL);
		WidgetsDescriptor result = descriptrosCache.get(key);
		if (result == null){
			result = resolver.loadDescriptor(jConfig, URL);
			descriptrosCache.put(key, result);
		}
		return result;
	}
	
	/**
	 * Create the key for a specific definition location information
	 * 
	 * @param jConfig the current {@link JasperReportsConfiguration}
	 * @param url the url of the loaded location
	 * @return an unique key for the resource
	 */
	protected static String getKey(JasperReportsConfiguration jConfig, String url){
		IFile project = (IFile) jConfig.get(FileUtils.KEY_FILE);
		String projectPath = project.getLocation().toPortableString();
		String key = projectPath + url;		
		return key;
	}	
}
