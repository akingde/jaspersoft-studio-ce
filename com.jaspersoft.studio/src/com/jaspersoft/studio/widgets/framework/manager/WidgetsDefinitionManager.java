/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;

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
	private static HashMap<String, CacheContainer> descriptrosCache = new HashMap<String, CacheContainer>();
	
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
		String key = resolver.getKey(jConfig, URL);
		CacheContainer result = descriptrosCache.get(key);
		if (result == null){
			WidgetsDescriptor desc = resolver.loadDescriptor(jConfig, URL);
			if (desc != null){
				result = new CacheContainer(desc, jConfig, resolver.unloadOnConfigurationDispose());
				descriptrosCache.put(key, result);
			}
		}
		return result != null ? result.getDescriptor() : null;
	}
	
	/**
	 * Dispose all the Definition loaded trough the passed {@link JasperReportsConfiguration}, except
	 * for the one that are marked to be keep in memory
	 */
	public static void disposedConfiguration(JasperReportsConfiguration jConfig){
		List<Entry<String, CacheContainer>> entries = new ArrayList<Entry<String, CacheContainer>>(descriptrosCache.entrySet());
		for(Entry<String, CacheContainer> entry: entries){
			CacheContainer cacheContainer = entry.getValue();
			if (cacheContainer.getjConfig() == jConfig && cacheContainer.isUnloadOnConfigDispose()){
				entries.remove(entry.getKey());
			}
		}
	}
}
