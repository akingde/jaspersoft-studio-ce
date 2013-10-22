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
import com.jaspersoft.studio.server.wizard.resource.APageContent;
import com.jaspersoft.studio.server.wizard.resource.page.ResourcePageContent;

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

	public IWizardPage[] getResourcePage(ANode parent, MResource resource) {
		if (resource instanceof MRDatasourceMongoDB)
			return APageContent.getPages(resource, new ResourcePageContent(
					parent, resource), new DatasourceMongoDBPageContent(parent,
					resource));
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
