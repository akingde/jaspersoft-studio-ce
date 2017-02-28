/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.metadata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.properties.PropertyMetadata;

public class PropertyMetadataRegistry {
	private static Map<String, PropertyMetadata> pmap = new HashMap<String, PropertyMetadata>();

	public static void addMetadata(List<PropertyMetadata> md) {
		for (PropertyMetadata pm : md)
			pmap.put(pm.getName(), pm);
	}

	public static Map<String, PropertyMetadata> getPropertiesMetadata() {
		return pmap;
	}
}
