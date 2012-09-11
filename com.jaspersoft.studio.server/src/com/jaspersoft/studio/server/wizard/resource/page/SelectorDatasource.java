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
package com.jaspersoft.studio.server.wizard.resource.page;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.publish.wizard.DatasourceSelectionComposite;


/**
 * This class allows to create a datasource selection panel and 
 * contains a list of static utility methods suitable for operations
 * on {@link ResourceDescriptor} elements and their datasource information associated. 
 */
public class SelectorDatasource {

	/**
	 * Creates a tabitem containing the datasource composite widget that 
	 * allows to edit the datasource information associated to a specific resource.
	 * 
	 * @param tabFolder the parent tabfolder for the new item
	 * @param parent the anode that will contain information regarding the remote JasperServer 
	 * @param res the resource which datasource information must be modified
	 */
	public void createDatasource(TabFolder tabFolder, final ANode parent,
			final MResource res) {
		TabItem item = new TabItem(tabFolder, SWT.NONE);
		item.setText(Messages.SelectorDatasource_TabTitle);

		DatasourceSelectionComposite dsSelectionCmp = new DatasourceSelectionComposite(tabFolder, SWT.NONE);
		dsSelectionCmp.configurePage(parent, res);
		item.setControl(dsSelectionCmp);		

	}

	/**
	 * Replaces the datasource a previously existing datasource associated to a 
	 * specified resource.
	 * 
	 * @param res the resource element 
	 * @param rd the resource descriptor representing the new datasource information
	 */
	public static void replaceDatasource(final MResource res, ResourceDescriptor rd) {
		ResourceDescriptor rdel = getDatasource(res.getValue());
		if (rdel != null) {
			int index = res.getValue().getChildren().indexOf(rdel);
			if (index >= 0)
				res.getValue().getChildren().remove(index);
		}
		res.getValue().getChildren().add(0, rd);
	}

	/**
	 * Gets, if it exists, the datasource information associated to 
	 * the specified {@link ResourceDescriptor} element.
	 *  
	 * @param ru the input resource descriptor
	 * @return the resource descriptor representing the datasource associated if found,
	 * 			<code>null</code> otherwise
	 */
	public static ResourceDescriptor getDatasource(ResourceDescriptor ru) {
		for (Object obj : ru.getChildren()) {
			ResourceDescriptor r = (ResourceDescriptor) obj;
			if (isDatasource(r)) {
				return r;
			}
		}
		return null;
	}

	/**
	 * Clones the input {@link ResourceDescriptor} instance.
	 * 
	 * @param rd the resource descriptor to clone
	 * @return the cloned resource descriptor
	 */
	public static ResourceDescriptor cloneResource(ResourceDescriptor rd) {
		ResourceDescriptor rnew = new ResourceDescriptor();
		rnew.setIsNew(rd.getIsNew());
		rnew.setIsReference(rd.getIsReference());
		rnew.setName(rd.getName());
		rnew.setLabel(rd.getLabel());
		rnew.setDescription(rd.getDescription());
		rnew.setUriString(rd.getUriString());
		rnew.setParentFolder(rd.getParentFolder());
		rnew.setDataSourceType(rd.getDataSourceType());
		rnew.setWsType(rd.getWsType());

		rnew.setJndiName(rd.getJndiName());

		rnew.setBeanMethod(rd.getBeanMethod());
		rnew.setBeanName(rd.getBeanName());

		rnew.setDriverClass(rd.getDriverClass());
		rnew.setUsername(rd.getUsername());
		rnew.setPassword(rd.getPassword());
		rnew.setConnectionUrl(rd.getConnectionUrl());
		return rnew;
	}

	/**
	 * Copies the information from a source {@link ResourceDescriptor}
	 * to a target one.
	 * 
	 * @param rd the source target descriptor
	 * @param rnew the target resource descriptor
	 */
	public static void copyDSFields(ResourceDescriptor rd,
			ResourceDescriptor rnew) {
		rnew.setJndiName(rd.getJndiName());

		rnew.setBeanMethod(rd.getBeanMethod());
		rnew.setBeanName(rd.getBeanName());

		rnew.setDriverClass(rd.getDriverClass());
		rnew.setUsername(rd.getUsername());
		rnew.setPassword(rd.getPassword());
		rnew.setConnectionUrl(rd.getConnectionUrl());
	}

	/**
	 * Checks if the specified {@link ResourceDescriptor} element is a datasource.
	 * 
	 * @param r the resource to check
	 * @return <code>true</code> if the resource is a datasource, <code>false</code> otherwise
	 */
	public static boolean isDatasource(ResourceDescriptor r) {
		return r.getWsType().equals(ResourceDescriptor.TYPE_DATASOURCE)
				|| r.getWsType()
						.equals(ResourceDescriptor.TYPE_DATASOURCE_BEAN)
				|| r.getWsType().equals(
						ResourceDescriptor.TYPE_DATASOURCE_CUSTOM)
				|| r.getWsType()
						.equals(ResourceDescriptor.TYPE_DATASOURCE_JDBC)
				|| r.getWsType()
						.equals(ResourceDescriptor.TYPE_DATASOURCE_JNDI)
				|| r.getWsType().equals("Domain") //$NON-NLS-1$
				|| (r.getWsType().equals("custom") //$NON-NLS-1$
						&& r.getResourcePropertyValue("PROP_RESOURCE_TYPE") != null && r //$NON-NLS-1$
						.getResourcePropertyValue("PROP_RESOURCE_TYPE") //$NON-NLS-1$
						.equals("com.jaspersoft.jasperserver.api.metadata.jasperreports.domain.CustomReportDataSource")); //$NON-NLS-1$
	}
	
	/**
	 * Enumeration representing the type of datasource.
	 */
	public enum SelectionType {
		REMOTE_DATASOURCE, LOCAL_DATASOURCE, NO_DATASOURCE
	}
}
