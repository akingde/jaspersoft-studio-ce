/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
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
