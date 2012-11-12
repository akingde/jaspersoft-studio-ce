/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.data.mongodb.server;

import org.eclipse.jface.wizard.IWizardPage;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceProperty;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.model.datasource.MRDatasourceCustom;
import com.jaspersoft.studio.server.plugin.IResourceFactory;
import com.jaspersoft.studio.server.utils.ResourceDescriptorUtil;

public class MongoResourceFactory implements IResourceFactory {

	public MResource getResource(ANode parent, ResourceDescriptor resource,
			int index) {
		if (resource.getWsType().equals(
				ResourceDescriptor.TYPE_DATASOURCE_CUSTOM)) {
			ResourceProperty rp = ResourceDescriptorUtil.getProperty(
					MRDatasourceCustom.PROP_DATASOURCE_CUSTOM_SERVICE_CLASS,
					resource.getProperties());
			if (rp != null
					&& rp.getValue().equals(MRDatasourceMongoDB.CUSTOM_CLASS))
				return new MRDatasourceMongoDB(parent, resource, index);
		}
		return null;
	}

	public IWizardPage getResourcePage(ANode parent, MResource resource) {
		if (resource instanceof MRDatasourceMongoDB)
			return new RDDatasourceMongoDBPage(parent,
					(MRDatasourceMongoDB) resource);
		return null;
	}

	public ANode createNewResource(MRoot root, ANode parent) {
		return null;
	}

	@Override
	public ANode createNewDatasource(MRoot root, ANode parent) {
		return new MRDatasourceMongoDB(root,
				MRDatasourceMongoDB.createDescriptor(parent), -1);
	}

}
