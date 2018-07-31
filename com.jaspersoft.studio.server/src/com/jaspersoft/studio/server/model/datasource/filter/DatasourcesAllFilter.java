/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.model.datasource.filter;

import java.util.HashSet;
import java.util.Set;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.model.datasource.MRDatasourceAWS;
import com.jaspersoft.studio.server.model.datasource.MRDatasourceVDS;
import com.jaspersoft.studio.server.protocol.restv2.WsTypes;

public class DatasourcesAllFilter implements IDatasourceFilter {
	private static final Set<String> types = new HashSet<>();
	static {
		types.add(ResourceDescriptor.TYPE_DATASOURCE);
		types.add(ResourceDescriptor.TYPE_DATASOURCE_BEAN);
		types.add(ResourceDescriptor.TYPE_DATASOURCE_CUSTOM);
		types.add(ResourceDescriptor.TYPE_DATASOURCE_JNDI);
		types.add(ResourceDescriptor.TYPE_DATASOURCE_JDBC);
		types.add(MRDatasourceVDS.TYPE_DATASOURCE_VDS);
		types.add(MRDatasourceAWS.TYPE_AWS);
		types.add(ResourceDescriptor.TYPE_ADHOC_DATA_VIEW);
		types.add(ResourceDescriptor.TYPE_DATASOURCE_DOMAIN);
		types.add(ResourceDescriptor.TYPE_DATASOURCE_DOMAIN1);
		types.add(ResourceDescriptor.TYPE_OLAP_MONDRIAN_CONNECTION);
		types.add(ResourceDescriptor.TYPE_SECURE_MONDRIAN_CONNECTION);
		types.add(ResourceDescriptor.TYPE_OLAP_XMLA_CONNECTION);
		types.add("azuresql");
	}

	public static Set<String> getTypes() {
		return types;
	}

	public static String[] getTypesArray() {
		return types.toArray(new String[types.size()]);
	}

	public static String[] getTypesArrayRest() {
		String[] tps = getTypesArray();
		for (int i = 0; i < types.size(); i++)
			tps[i] = WsTypes.INST().toRestType(tps[i]);
		return tps;
	}

	@Override
	public Set<String> getFilterTypes() {
		return getTypes();
	}

	@Override
	public boolean isDatasource(ResourceDescriptor r) {
		if (r.getIsReference() && r.getReferenceType() != null)
			return types.contains(r.getReferenceType());
		return types.contains(r.getWsType());
	}

}
