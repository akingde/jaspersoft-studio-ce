/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.server.model.datasource.filter;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.model.datasource.MRDatasourceAWS;
import com.jaspersoft.studio.server.model.datasource.MRDatasourceVDS;

public class DatasourcesAllFilter implements IDatasourceFilter {

	@Override
	public boolean isDatasource(ResourceDescriptor r) {
		String wsType = r.getWsType();
		return wsType.equals(ResourceDescriptor.TYPE_DATASOURCE) || wsType.equals(ResourceDescriptor.TYPE_DATASOURCE_BEAN) || wsType.equals(ResourceDescriptor.TYPE_DATASOURCE_CUSTOM)
				|| wsType.equals(ResourceDescriptor.TYPE_DATASOURCE_JDBC) || wsType.equals(ResourceDescriptor.TYPE_DATASOURCE_JNDI) || wsType.equals(MRDatasourceVDS.TYPE_DATASOURCE_VDS)
				|| wsType.equals(MRDatasourceAWS.TYPE_AWS) || wsType.equals("Domain") //$NON-NLS-1$
				|| (wsType.equals("custom") //$NON-NLS-1$
						&& r.getResourcePropertyValue("PROP_RESOURCE_TYPE") != null && r //$NON-NLS-1$
						.getResourcePropertyValue("PROP_RESOURCE_TYPE") //$NON-NLS-1$
						.equals("com.jaspersoft.jasperserver.api.metadata.jasperreports.domain.CustomReportDataSource")); //$NON-NLS-1$
	}

}
