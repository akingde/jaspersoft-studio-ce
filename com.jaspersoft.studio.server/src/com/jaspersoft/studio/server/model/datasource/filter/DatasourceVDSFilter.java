package com.jaspersoft.studio.server.model.datasource.filter;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;

public class DatasourceVDSFilter implements IDatasourceFilter {

	@Override
	public boolean isDatasource(ResourceDescriptor r) {
		return r.getWsType().equals(ResourceDescriptor.TYPE_DATASOURCE)
				|| r.getWsType()
						.equals(ResourceDescriptor.TYPE_DATASOURCE_BEAN)
				|| r.getWsType()
						.equals(ResourceDescriptor.TYPE_DATASOURCE_JDBC)
				|| r.getWsType()
						.equals(ResourceDescriptor.TYPE_DATASOURCE_JNDI);
	}

}
