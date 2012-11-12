package com.jaspersoft.studio.server.model.datasource.filter;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;

public interface IDatasourceFilter {
	public boolean isDatasource(ResourceDescriptor r);
}
