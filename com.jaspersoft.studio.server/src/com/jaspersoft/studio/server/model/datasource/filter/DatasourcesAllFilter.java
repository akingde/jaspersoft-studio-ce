package com.jaspersoft.studio.server.model.datasource.filter;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.model.datasource.MRDatasourceVDS;

public class DatasourcesAllFilter implements IDatasourceFilter {

	@Override
	public boolean isDatasource(ResourceDescriptor r) {
		return r.getWsType().equals(ResourceDescriptor.TYPE_DATASOURCE)
				|| r.getWsType()
						.equals(ResourceDescriptor.TYPE_DATASOURCE_BEAN)
				|| r.getWsType().equals(
						ResourceDescriptor.TYPE_DATASOURCE_CUSTOM)
				|| r.getWsType()
						.equals(ResourceDescriptor.TYPE_DATASOURCE_JDBC)
				|| r.getWsType()
						.equals(ResourceDescriptor.TYPE_DATASOURCE_JNDI)
				|| r.getWsType().equals(MRDatasourceVDS.TYPE_DATASOURCE_VDS)
				|| r.getWsType().equals("Domain") //$NON-NLS-1$
				|| (r.getWsType().equals("custom") //$NON-NLS-1$
						&& r.getResourcePropertyValue("PROP_RESOURCE_TYPE") != null && r //$NON-NLS-1$
						.getResourcePropertyValue("PROP_RESOURCE_TYPE") //$NON-NLS-1$
						.equals("com.jaspersoft.jasperserver.api.metadata.jasperreports.domain.CustomReportDataSource")); //$NON-NLS-1$
	}

}
