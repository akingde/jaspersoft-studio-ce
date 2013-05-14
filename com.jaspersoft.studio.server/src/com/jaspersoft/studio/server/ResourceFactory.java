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
package com.jaspersoft.studio.server;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.wizard.IWizardPage;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceProperty;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MDummy;
import com.jaspersoft.studio.server.model.MRDataAdapter;
import com.jaspersoft.studio.server.model.MDataType;
import com.jaspersoft.studio.server.model.MFolder;
import com.jaspersoft.studio.server.model.MInputControl;
import com.jaspersoft.studio.server.model.MJar;
import com.jaspersoft.studio.server.model.MJrxml;
import com.jaspersoft.studio.server.model.MListOfValues;
import com.jaspersoft.studio.server.model.MRAccessGrantSchema;
import com.jaspersoft.studio.server.model.MRCSS;
import com.jaspersoft.studio.server.model.MRDashboard;
import com.jaspersoft.studio.server.model.MRFont;
import com.jaspersoft.studio.server.model.MRImage;
import com.jaspersoft.studio.server.model.MRQuery;
import com.jaspersoft.studio.server.model.MRStyleTemplate;
import com.jaspersoft.studio.server.model.MReference;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.model.MReportUnitOptions;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.model.MResourceBundle;
import com.jaspersoft.studio.server.model.MUnknown;
import com.jaspersoft.studio.server.model.MXmlFile;
import com.jaspersoft.studio.server.model.datasource.MRDatasource;
import com.jaspersoft.studio.server.model.datasource.MRDatasourceAWS;
import com.jaspersoft.studio.server.model.datasource.MRDatasourceBean;
import com.jaspersoft.studio.server.model.datasource.MRDatasourceCustom;
import com.jaspersoft.studio.server.model.datasource.MRDatasourceDiagnostic;
import com.jaspersoft.studio.server.model.datasource.MRDatasourceJDBC;
import com.jaspersoft.studio.server.model.datasource.MRDatasourceJNDI;
import com.jaspersoft.studio.server.model.datasource.MRDatasourceVDS;
import com.jaspersoft.studio.server.model.datasource.MRMondrianSchema;
import com.jaspersoft.studio.server.model.datasource.MROlapMondrianConnection;
import com.jaspersoft.studio.server.model.datasource.MROlapUnit;
import com.jaspersoft.studio.server.model.datasource.MROlapXmlaConnection;
import com.jaspersoft.studio.server.plugin.ExtensionManager;
import com.jaspersoft.studio.server.utils.ResourceDescriptorUtil;
import com.jaspersoft.studio.server.wizard.resource.APageContent;
import com.jaspersoft.studio.server.wizard.resource.page.CSSPageContent;
import com.jaspersoft.studio.server.wizard.resource.page.DataTypePageContent;
import com.jaspersoft.studio.server.wizard.resource.page.FontPageContent;
import com.jaspersoft.studio.server.wizard.resource.page.ImagePageContent;
import com.jaspersoft.studio.server.wizard.resource.page.InputControlPageContent;
import com.jaspersoft.studio.server.wizard.resource.page.JarPageContent;
import com.jaspersoft.studio.server.wizard.resource.page.JrxmlPageContent;
import com.jaspersoft.studio.server.wizard.resource.page.LovPageContent;
import com.jaspersoft.studio.server.wizard.resource.page.QueryPageContent;
import com.jaspersoft.studio.server.wizard.resource.page.ReferencePageContent;
import com.jaspersoft.studio.server.wizard.resource.page.ResourceBundlePageContent;
import com.jaspersoft.studio.server.wizard.resource.page.ResourcePageContent;
import com.jaspersoft.studio.server.wizard.resource.page.StyleTemplatePageContent;
import com.jaspersoft.studio.server.wizard.resource.page.XmlPageContent;
import com.jaspersoft.studio.server.wizard.resource.page.datasource.DataAdapterPageContent;
import com.jaspersoft.studio.server.wizard.resource.page.datasource.DatasourceAWSPageContent;
import com.jaspersoft.studio.server.wizard.resource.page.datasource.DatasourceBeanPageContent;
import com.jaspersoft.studio.server.wizard.resource.page.datasource.DatasourceJDBCPageContent;
import com.jaspersoft.studio.server.wizard.resource.page.datasource.DatasourceJndiPageContent;
import com.jaspersoft.studio.server.wizard.resource.page.datasource.DatasourceVDSPageContent;
import com.jaspersoft.studio.server.wizard.resource.page.olap.OLAPXmlaPageContent;
import com.jaspersoft.studio.server.wizard.resource.page.runit.ReportUnitContent;
import com.jaspersoft.studio.server.wizard.resource.page.runit.ReportUnitDatasourceContent;
import com.jaspersoft.studio.server.wizard.resource.page.runit.ReportUnitInputControlContent;
import com.jaspersoft.studio.server.wizard.resource.page.runit.ReportUnitOptionsContent;
import com.jaspersoft.studio.server.wizard.resource.page.runit.ReportUnitQueryContent;

public class ResourceFactory {

	private Map<Class<? extends MResource>, IWizardPage[]> pagemap = new HashMap<Class<? extends MResource>, IWizardPage[]>();

	public IWizardPage[] getResourcePage(ANode parent, MResource resource) {
		IWizardPage[] page = pagemap.get(resource.getClass());
		if (page == null) {
			page = Activator.getExtManager().getResourcePage(parent, resource);
			if (page == null) {
				if (resource instanceof MRImage)
					page = APageContent.getPages(resource,
							new ResourcePageContent(parent, resource),
							new ImagePageContent(parent, resource));
				else if (resource instanceof MRCSS)
					page = APageContent.getPages(resource,
							new ResourcePageContent(parent, resource),
							new CSSPageContent(parent, resource));

				else if (resource instanceof MRFont)
					page = APageContent.getPages(resource,
							new ResourcePageContent(parent, resource),
							new FontPageContent(parent, resource));
				else if (resource instanceof MJar)
					page = APageContent.getPages(resource,
							new ResourcePageContent(parent, resource),
							new JarPageContent(parent, resource));
				else if (resource instanceof MResourceBundle)
					page = APageContent.getPages(resource,
							new ResourcePageContent(parent, resource),
							new ResourceBundlePageContent(parent, resource));
				else if (resource instanceof MJrxml)
					page = APageContent.getPages(resource,
							new ResourcePageContent(parent, resource),
							new JrxmlPageContent(parent, resource));
				else if (resource instanceof MReference)
					page = APageContent.getPages(resource,
							new ResourcePageContent(parent, resource),
							new ReferencePageContent(parent, resource));
				else if (resource instanceof MRDatasourceVDS)
					page = APageContent.getPages(resource,
							new ResourcePageContent(parent, resource),
							new DatasourceVDSPageContent(parent, resource));
				else if (resource instanceof MRDatasourceJNDI)
					page = APageContent.getPages(resource,
							new ResourcePageContent(parent, resource),
							new DatasourceJndiPageContent(parent, resource));
				else if (resource instanceof MRDatasourceAWS)
					page = APageContent.getPages(resource,
							new ResourcePageContent(parent, resource),
							new DatasourceAWSPageContent(parent, resource));
				else if (resource instanceof MRDatasourceJDBC)
					page = APageContent.getPages(resource,
							new ResourcePageContent(parent, resource),
							new DatasourceJDBCPageContent(parent, resource));
				else if (resource instanceof MRDatasourceBean)
					page = APageContent.getPages(resource,
							new ResourcePageContent(parent, resource),
							new DatasourceBeanPageContent(parent, resource));
				else if (resource instanceof MRDatasourceCustom
						|| resource instanceof MRDatasource
						|| resource instanceof MFolder)
					page = APageContent.getPages(resource,
							new ResourcePageContent(parent, resource));
				else if (resource instanceof MReportUnit)
					if (ReportUnitQueryContent.hasTypeQuery(resource))
						page = APageContent.getPages(resource,
								new ResourcePageContent(parent, resource),
								new ReportUnitContent(parent, resource),
								new ReportUnitDatasourceContent(parent,
										resource), new ReportUnitQueryContent(
										parent, resource),
								new ReportUnitInputControlContent(parent,
										resource));
					else
						page = APageContent.getPages(resource,
								new ResourcePageContent(parent, resource),
								new ReportUnitContent(parent, resource),
								new ReportUnitDatasourceContent(parent,
										resource),
								new ReportUnitInputControlContent(parent,
										resource));
				else if (resource instanceof MInputControl)
					page = APageContent.getPages(resource,
							new ResourcePageContent(parent, resource),
							new InputControlPageContent(parent, resource));
				else if (resource instanceof MDataType)
					page = APageContent.getPages(resource,
							new ResourcePageContent(parent, resource),
							new DataTypePageContent(parent, resource));
				else if (resource instanceof MRQuery)
					page = APageContent.getPages(resource,
							new ResourcePageContent(parent, resource),
							new QueryPageContent(parent, resource),
							new ReportUnitDatasourceContent(parent, resource));
				else if (resource instanceof MListOfValues)
					page = APageContent.getPages(resource,
							new ResourcePageContent(parent, resource),
							new LovPageContent(parent, resource));
				else if (resource instanceof MReportUnitOptions)
					page = APageContent.getPages(resource,
							new ResourcePageContent(parent, resource),
							new ReportUnitOptionsContent(parent, resource));
				else if (resource instanceof MXmlFile)
					page = APageContent.getPages(resource,
							new ResourcePageContent(parent, resource),
							new XmlPageContent(parent, resource));
				else if (resource instanceof MUnknown)
					page = APageContent.getPages(resource,
							new ResourcePageContent(parent, resource));
				else if (resource instanceof MRStyleTemplate)
					page = APageContent.getPages(resource,
							new ResourcePageContent(parent, resource),
							new StyleTemplatePageContent(parent, resource));
				else if (resource instanceof MRDataAdapter)
					page = APageContent.getPages(resource,
							new ResourcePageContent(parent, resource),
							new DataAdapterPageContent(parent, resource));

				if (resource instanceof MRDashboard)
					page = APageContent.getPages(resource,
							new ResourcePageContent(parent, resource));
				if (resource instanceof MRMondrianSchema
						|| resource instanceof MRAccessGrantSchema
						|| resource instanceof MROlapUnit
						|| resource instanceof MROlapMondrianConnection)
					page = APageContent.getPages(resource,
							new ResourcePageContent(parent, resource));
				if (resource instanceof MROlapXmlaConnection)
					page = APageContent.getPages(resource,
							new ResourcePageContent(parent, resource),
							new OLAPXmlaPageContent(parent, resource));

			}
			if (page != null)
				pagemap.put(resource.getClass(), page);
		}
		return page;
	}

	public static MResource getResource(ANode parent,
			ResourceDescriptor resource, int index) {
		ExtensionManager extManager = Activator.getExtManager();
		MResource m = extManager.getResource(parent, resource, index);
		if (m != null)
			return m;
		String wstype = resource.getWsType();
		if (wstype.equals(ResourceDescriptor.TYPE_FOLDER)) {
			MFolder folder = new MFolder(parent, resource, index);
			new MDummy(folder);
			return folder;
		}
		if (wstype.equals(ResourceDescriptor.TYPE_INPUT_CONTROL))
			return new MInputControl(parent, resource, index);

		if (wstype.equals(ResourceDescriptor.TYPE_JRXML))
			return new MJrxml(parent, resource, index);

		if (wstype.equals(ResourceDescriptor.TYPE_IMAGE))
			return new MRImage(parent, resource, index);

		if (wstype.equals(ResourceDescriptor.TYPE_REFERENCE))
			return new MReference(parent, resource, index);

		if (wstype.equals(ResourceDescriptor.TYPE_REPORTUNIT)) {
			MReportUnit runit = new MReportUnit(parent, resource, index);
			new MDummy(runit);
			return runit;
		}

		if (wstype.equals(ResourceDescriptor.TYPE_LOV))
			return new MListOfValues(parent, resource, index);

		if (wstype.equals(ResourceDescriptor.TYPE_UNKNOW))
			return new MUnknown(parent, resource, index);

		if (wstype.equals(ResourceDescriptor.TYPE_CLASS_JAR))
			return new MJar(parent, resource, index);

		if (wstype.equals(ResourceDescriptor.TYPE_RESOURCE_BUNDLE))
			return new MResourceBundle(parent, resource, index);

		if (wstype.equals(ResourceDescriptor.TYPE_QUERY))
			return new MRQuery(parent, resource, index);

		if (wstype.equals(ResourceDescriptor.TYPE_DATA_TYPE))
			return new MDataType(parent, resource, index);

		if (wstype.equals(ResourceDescriptor.TYPE_FONT))
			return new MRFont(parent, resource, index);

		if (wstype.equals(ResourceDescriptor.TYPE_STYLE_TEMPLATE))
			return new MRStyleTemplate(parent, resource, index);

		if (wstype.equals(ResourceDescriptor.TYPE_DATASOURCE))
			return new MRDatasource(parent, resource, index);

		if (wstype.equals(ResourceDescriptor.TYPE_DATASOURCE_BEAN))
			return new MRDatasourceBean(parent, resource, index);

		if (wstype.equals(ResourceDescriptor.TYPE_DATASOURCE_CUSTOM)) {
			ResourceProperty rp = ResourceDescriptorUtil.getProperty(
					MRDatasourceCustom.PROP_DATASOURCE_CUSTOM_SERVICE_CLASS,
					resource.getProperties());
			if (rp != null) {
				if (rp.getValue().equals(MRDatasourceDiagnostic.CUSTOM_CLASS))
					return new MRDatasourceDiagnostic(parent, resource, index);
			}
			return new MRDatasourceCustom(parent, resource, index);
		}

		if (wstype.equals(ResourceDescriptor.TYPE_DATASOURCE_JDBC))
			return new MRDatasourceJDBC(parent, resource, index);
		if (wstype.equals(MRDatasourceVDS.TYPE_DATASOURCE_VDS))
			return new MRDatasourceVDS(parent, resource, index);
		if (wstype.equals(ResourceDescriptor.TYPE_DATASOURCE_JNDI))
			return new MRDatasourceJNDI(parent, resource, index);
		if (wstype.equals(MRDatasourceAWS.TYPE_AWS))
			return new MRDatasourceAWS(parent, resource, index);

		if (wstype.equals("ReportOptionsResource"))
			return new MReportUnitOptions(parent, resource, index);

		if (wstype.equals(ResourceDescriptor.TYPE_XML_FILE))
			return new MXmlFile(parent, resource, index);

		if (wstype.equals(ResourceDescriptor.TYPE_DASHBOARDUNIT))
			return new MRDashboard(parent, resource, index);
		if (wstype.equals(ResourceDescriptor.TYPE_MONDRIAN_SCHEMA))
			return new MRMondrianSchema(parent, resource, index);
		if (wstype.equals(ResourceDescriptor.TYPE_OLAP_MONDRIAN_CONNECTION))
			return new MROlapMondrianConnection(parent, resource, index);
		if (wstype.equals(ResourceDescriptor.TYPE_OLAP_XMLA_CONNECTION))
			return new MROlapXmlaConnection(parent, resource, index);
		if (wstype.equals(ResourceDescriptor.TYPE_OLAPUNIT))
			return new MROlapUnit(parent, resource, index);
		if (wstype.equals(ResourceDescriptor.TYPE_ACCESS_GRANT_SCHEMA))
			return new MRAccessGrantSchema(parent, resource, index);
		// if (wstype.equals(MRCSS.WSTYPE_CSS))
		// return new MRCSS(parent, resource, index);

		return new MUnknown(parent, resource, index);
	}

	public static boolean isFileResourceType(ResourceDescriptor ref) {
		String t = ref.getWsType();
		return t.equals(ResourceDescriptor.TYPE_IMAGE)
				|| t.equals(ResourceDescriptor.TYPE_FONT)
				|| t.equals(ResourceDescriptor.TYPE_JRXML)
				|| t.equals(ResourceDescriptor.TYPE_CLASS_JAR)
				|| t.equals(ResourceDescriptor.TYPE_RESOURCE_BUNDLE)
				|| t.equals(ResourceDescriptor.TYPE_STYLE_TEMPLATE)
				|| t.equals(ResourceDescriptor.TYPE_XML_FILE);
	}
}
