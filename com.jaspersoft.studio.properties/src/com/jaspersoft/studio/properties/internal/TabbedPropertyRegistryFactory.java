/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.properties.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jaspersoft.studio.properties.view.ITabbedPropertySheetPageContributor;

/**
 * tabbed property registry factory. Caches the tabbed property registry by
 * tabbed property contributor ID.
 * 
 * @author Anthony Hunter
 */
public class TabbedPropertyRegistryFactory {

	class CacheData {
		TabbedPropertyRegistry registry;
		List<ITabbedPropertySheetPageContributor> references;
	}

	/**
	 * singleton instance of this class
	 */
	private static TabbedPropertyRegistryFactory INSTANCE = new TabbedPropertyRegistryFactory();

	/**
	 * get the singleton instance of this class.
	 * 
	 * @return the TabbedPropertyRegistryFactory instance.
	 */
	public static TabbedPropertyRegistryFactory getInstance() {
		return INSTANCE;
	}

	/**
	 * private constructor.
	 */
	private TabbedPropertyRegistryFactory() {
		super();
		idToCacheData = new HashMap<>();
	}

	protected Map<String, CacheData> idToCacheData; // cache

	/**
	 * Creates a registry for the given contributor.
	 * 
	 * @param target
	 *            the contributor.
	 * @return a registry for the given contributor.
	 */
	public TabbedPropertyRegistry createRegistry(ITabbedPropertySheetPageContributor target) {
		/**
		 * Get the contributor id from the ITabbedPropertySheetPageContributor interface
		 */
		String key = target.getContributorId();
		CacheData data = idToCacheData.get(key);
		if (data == null) {
			data = new CacheData();
			data.registry = new TabbedPropertyRegistry(key);
			data.references = new ArrayList<>(5);
			idToCacheData.put(key, data);
		}
		data.references.add(target);
		// keeps track of contributor using the same registry
		return data.registry;
	}

	/**
	 * Indicates that the given contributor no longer needs a registry. The registry
	 * will be disposed when no other contributor of the same type needs it.
	 * 
	 * @param target
	 *            the contributor;
	 */
	public void disposeRegistry(ITabbedPropertySheetPageContributor target) {
		/**
		 * Get the contributor id from the ITabbedPropertySheetPageContributor interface
		 */
		String key = target.getContributorId();
		CacheData data = idToCacheData.get(key);
		if (data != null) {
			data.references.remove(target);
			if (data.references.isEmpty()) {
				data.registry.dispose();
				idToCacheData.remove(key);
			}
		}
	}
}
