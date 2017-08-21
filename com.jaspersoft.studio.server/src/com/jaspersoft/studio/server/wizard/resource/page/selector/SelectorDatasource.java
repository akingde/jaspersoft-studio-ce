/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.resource.page.selector;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.model.datasource.filter.DatasourcesAllFilter;
import com.jaspersoft.studio.server.model.datasource.filter.IDatasourceFilter;
import com.jaspersoft.studio.server.publish.wizard.page.DatasourceSelectionComposite;

/**
 * This class allows to create a datasource selection panel and contains a list
 * of static utility methods suitable for operations on
 * {@link ResourceDescriptor} elements and their datasource information
 * associated.
 */
public class SelectorDatasource {

	/**
	 * Creates a tabitem containing the datasource composite widget that allows to
	 * edit the datasource information associated to a specific resource.
	 * 
	 * @param tabFolder
	 *            the parent tabfolder for the new item
	 * @param parent
	 *            the anode that will contain information regarding the remote
	 *            JasperServer
	 * @param res
	 *            the resource which datasource information must be modified
	 */
	public DatasourceSelectionComposite createDatasource(TabFolder tabFolder, final ANode parent, final AMResource res,
			boolean mandatory) {
		TabItem item = new TabItem(tabFolder, SWT.NONE);
		item.setText(Messages.SelectorDatasource_TabTitle);

		DatasourceSelectionComposite dsSelectionCmp = createDatasource(tabFolder, parent, res, mandatory);

		item.setControl(dsSelectionCmp);
		return dsSelectionCmp;
	}

	public DatasourceSelectionComposite createDatasource(Composite parent, final ANode pnode, final AMResource res,
			boolean mandatory, String[] excludeTypes) {
		DatasourceSelectionComposite dsSelectionCmp = new DatasourceSelectionComposite(parent, SWT.NONE, mandatory,
				excludeTypes);
		dsSelectionCmp.configurePage(pnode, res);
		return dsSelectionCmp;
	}

	/**
	 * Replaces the datasource a previously existing datasource associated to a
	 * specified resource.
	 * 
	 * @param res
	 *            the resource element
	 * @param rd
	 *            the resource descriptor representing the new datasource
	 *            information
	 */
	public static void replaceDatasource(final AMResource res, ResourceDescriptor rd) {
		ResourceDescriptor rdel = getDatasource(res.getValue(), res);
		if (rdel != null) {
			int index = res.getValue().getChildren().indexOf(rdel);
			if (index >= 0)
				res.getValue().getChildren().remove(index);
		}
		res.getValue().getChildren().add(0, rd);
	}

	/**
	 * Gets, if it exists, the datasource information associated to the specified
	 * {@link ResourceDescriptor} element.
	 * 
	 * @param ru
	 *            the input resource descriptor
	 * @return the resource descriptor representing the datasource associated if
	 *         found, <code>null</code> otherwise
	 */
	public static ResourceDescriptor getDatasource(ResourceDescriptor ru, AMResource res) {
		for (Object obj : ru.getChildren()) {
			if (obj == null)
				continue;
			ResourceDescriptor r = (ResourceDescriptor) obj;
			ResourceDescriptor tmp = checkReference(r, res);
			if (tmp != null)
				r = tmp;
			else if (ru.getChildren().indexOf(r) == 0 && r.getIsReference())
				return r; // this is a datasource, it's possible we can't read the type because we don't
							// have permission

			if (r != null && isDatasource(r))
				return r;
		}
		return null;
	}

	protected static ResourceDescriptor checkReference(ResourceDescriptor r, AMResource res) {
		if (r.getWsType().equals(ResourceDescriptor.TYPE_REFERENCE))
			try {
				if (r.getReferenceType() != null)
					return r;
				return WSClientHelper.getReference(new NullProgressMonitor(), res, r);
			} catch (Exception e) {
				System.out.println(r.getReferenceUri() + "   " + r.getReferenceType());
				e.printStackTrace();
			}
		return null;
	}

	/**
	 * Checks if the specified {@link ResourceDescriptor} element is a datasource.
	 * 
	 * @param r
	 *            the resource to check
	 * @return <code>true</code> if the resource is a datasource, <code>false</code>
	 *         otherwise
	 */
	public static boolean isDatasource(ResourceDescriptor r) {
		return datasourceFilter.isDatasource(r);
	}

	private static IDatasourceFilter datasourceFilter = new DatasourcesAllFilter();

	/**
	 * Enumeration representing the type of datasource.
	 */
	public enum SelectionType {
		REMOTE_DATASOURCE, LOCAL_DATASOURCE, NO_DATASOURCE
	}
}
