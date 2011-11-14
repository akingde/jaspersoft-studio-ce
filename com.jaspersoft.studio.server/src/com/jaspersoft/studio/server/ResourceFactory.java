/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.server;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.wizard.IWizardPage;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.model.MDataType;
import com.jaspersoft.studio.server.model.MFolder;
import com.jaspersoft.studio.server.model.MInputControl;
import com.jaspersoft.studio.server.model.MJar;
import com.jaspersoft.studio.server.model.MJrxml;
import com.jaspersoft.studio.server.model.MListOfValues;
import com.jaspersoft.studio.server.model.MRDatasource;
import com.jaspersoft.studio.server.model.MRDatasourceBean;
import com.jaspersoft.studio.server.model.MRDatasourceCustom;
import com.jaspersoft.studio.server.model.MRDatasourceJDBC;
import com.jaspersoft.studio.server.model.MRDatasourceJNDI;
import com.jaspersoft.studio.server.model.MRFont;
import com.jaspersoft.studio.server.model.MRImage;
import com.jaspersoft.studio.server.model.MRQuery;
import com.jaspersoft.studio.server.model.MRStyleTemplate;
import com.jaspersoft.studio.server.model.MReference;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.model.MResourceBundle;
import com.jaspersoft.studio.server.model.MUnknown;
import com.jaspersoft.studio.server.wizard.resource.page.RDDatasourceBeanPage;
import com.jaspersoft.studio.server.wizard.resource.page.RDDatasourceJDBCPage;
import com.jaspersoft.studio.server.wizard.resource.page.RDDatasourceJNDIPage;
import com.jaspersoft.studio.server.wizard.resource.page.RDDatasourcePage;
import com.jaspersoft.studio.server.wizard.resource.page.RDFolderPage;
import com.jaspersoft.studio.server.wizard.resource.page.RDFontPage;
import com.jaspersoft.studio.server.wizard.resource.page.RDImagePage;
import com.jaspersoft.studio.server.wizard.resource.page.RDInputControlPage;
import com.jaspersoft.studio.server.wizard.resource.page.RDJarPage;
import com.jaspersoft.studio.server.wizard.resource.page.RDJrxmlPage;
import com.jaspersoft.studio.server.wizard.resource.page.RDQueryPage;
import com.jaspersoft.studio.server.wizard.resource.page.RDReferencePage;
import com.jaspersoft.studio.server.wizard.resource.page.RDReportUnitPage;
import com.jaspersoft.studio.server.wizard.resource.page.RDResourceBundlePage;

public class ResourceFactory {

	private Map<Class<? extends MResource>, IWizardPage> pagemap = new HashMap<Class<? extends MResource>, IWizardPage>();

	public IWizardPage getResourcePage(ANode parent, MResource resource) {
		IWizardPage page = pagemap.get(resource.getClass());
		if (page == null) {
			if (resource instanceof MFolder)
				page = new RDFolderPage(parent, (MFolder) resource);
			else if (resource instanceof MRImage)
				page = new RDImagePage(parent, (MRImage) resource);
			else if (resource instanceof MRFont)
				page = new RDFontPage(parent, (MRFont) resource);
			else if (resource instanceof MJar)
				page = new RDJarPage(parent, (MJar) resource);
			else if (resource instanceof MResourceBundle)
				page = new RDResourceBundlePage(parent,
						(MResourceBundle) resource);
			else if (resource instanceof MJrxml)
				page = new RDJrxmlPage(parent, (MJrxml) resource);
			else if (resource instanceof MReference)
				page = new RDReferencePage(parent, (MReference) resource);
			else if (resource instanceof MRDatasourceJNDI)
				page = new RDDatasourceJNDIPage(parent,
						(MRDatasourceJNDI) resource);
			else if (resource instanceof MRDatasourceJDBC)
				page = new RDDatasourceJDBCPage(parent,
						(MRDatasourceJDBC) resource);
			else if (resource instanceof MRDatasourceBean)
				page = new RDDatasourceBeanPage(parent,
						(MRDatasourceBean) resource);
			else if (resource instanceof MRDatasourceCustom)
				page = new RDDatasourcePage(parent,
						(MRDatasourceCustom) resource);
			else if (resource instanceof MRDatasource)
				page = new RDDatasourcePage(parent, (MRDatasource) resource);
			else if (resource instanceof MReportUnit)
				page = new RDReportUnitPage(parent, (MReportUnit) resource);
			else if (resource instanceof MInputControl)
				page = new RDInputControlPage(parent, (MInputControl) resource);

			else if (resource instanceof MRQuery)
				page = new RDQueryPage(parent, (MRQuery) resource);

			if (page != null)
				pagemap.put(resource.getClass(), page);
		}
		return page;
	}

	public static MResource getResource(ANode parent,
			ResourceDescriptor resource, int index) {
		if (resource.getWsType().equals(ResourceDescriptor.TYPE_FOLDER))
			return new MFolder(parent, resource, index);

		if (resource.getWsType().equals(ResourceDescriptor.TYPE_INPUT_CONTROL))
			return new MInputControl(parent, resource, index);

		if (resource.getWsType().equals(ResourceDescriptor.TYPE_JRXML))
			return new MJrxml(parent, resource, index);

		if (resource.getWsType().equals(ResourceDescriptor.TYPE_IMAGE))
			return new MRImage(parent, resource, index);

		if (resource.getWsType().equals(ResourceDescriptor.TYPE_REFERENCE))
			return new MReference(parent, resource, index);

		if (resource.getWsType().equals(ResourceDescriptor.TYPE_REPORTUNIT))
			return new MReportUnit(parent, resource, index);

		if (resource.getWsType().equals(ResourceDescriptor.TYPE_LOV))
			return new MListOfValues(parent, resource, index);

		if (resource.getWsType().equals(ResourceDescriptor.TYPE_UNKNOW))
			return new MUnknown(parent, resource, index);

		if (resource.getWsType().equals(ResourceDescriptor.TYPE_CLASS_JAR))
			return new MJar(parent, resource, index);

		if (resource.getWsType()
				.equals(ResourceDescriptor.TYPE_RESOURCE_BUNDLE))
			return new MResourceBundle(parent, resource, index);

		if (resource.getWsType().equals(ResourceDescriptor.TYPE_QUERY))
			return new MRQuery(parent, resource, index);

		if (resource.getWsType().equals(ResourceDescriptor.TYPE_DATA_TYPE))
			return new MDataType(parent, resource, index);

		if (resource.getWsType().equals(ResourceDescriptor.TYPE_FONT))
			return new MRFont(parent, resource, index);

		if (resource.getWsType().equals(ResourceDescriptor.TYPE_STYLE_TEMPLATE))
			return new MRStyleTemplate(parent, resource, index);

		if (resource.getWsType().equals(ResourceDescriptor.TYPE_DATASOURCE))
			return new MRDatasource(parent, resource, index);
		if (resource.getWsType()
				.equals(ResourceDescriptor.TYPE_DATASOURCE_BEAN))
			return new MRDatasourceBean(parent, resource, index);

		if (resource.getWsType().equals(
				ResourceDescriptor.TYPE_DATASOURCE_CUSTOM))
			return new MRDatasourceCustom(parent, resource, index);

		if (resource.getWsType()
				.equals(ResourceDescriptor.TYPE_DATASOURCE_JDBC))
			return new MRDatasourceJDBC(parent, resource, index);
		if (resource.getWsType()
				.equals(ResourceDescriptor.TYPE_DATASOURCE_JNDI))
			return new MRDatasourceJNDI(parent, resource, index);
		return new MUnknown(parent, resource, index);
	}
}
