/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.model.datasource.filter;

import java.util.HashSet;
import java.util.Set;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;

public class DatasourceVDSFilter implements IDatasourceFilter {
	private final static Set<String> types = new HashSet<String>();
	static {
		types.add(ResourceDescriptor.TYPE_DATASOURCE);
		types.add(ResourceDescriptor.TYPE_DATASOURCE_BEAN);
		types.add(ResourceDescriptor.TYPE_DATASOURCE_JNDI);
		types.add(ResourceDescriptor.TYPE_DATASOURCE_JDBC);
	}

	public static Set<String> getTypes() {
		return types;
	}

	@Override
	public boolean isDatasource(ResourceDescriptor r) {
		if (r.getIsReference() && r.getReferenceType() != null)
			return types.contains(r.getReferenceType());
		return types.contains(r.getWsType().toLowerCase());
	}

	@Override
	public Set<String> getFilterTypes() {
		return types;
	}

}
