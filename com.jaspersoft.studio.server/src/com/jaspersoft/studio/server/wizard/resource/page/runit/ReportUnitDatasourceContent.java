/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.resource.page.runit;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.MAdHocDataView;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.protocol.Feature;
import com.jaspersoft.studio.server.protocol.IConnection;
import com.jaspersoft.studio.server.protocol.Version;
import com.jaspersoft.studio.server.publish.wizard.page.DatasourceSelectionComposite;
import com.jaspersoft.studio.server.publish.wizard.page.DatasourceSelectionListener;
import com.jaspersoft.studio.server.wizard.resource.APageContent;
import com.jaspersoft.studio.server.wizard.resource.page.selector.SelectorDatasource;

public class ReportUnitDatasourceContent extends APageContent implements
		DatasourceSelectionListener {
	private boolean mandatory = false;
	private DatasourceSelectionComposite datasourceSelectionCmp;

	public ReportUnitDatasourceContent(ANode parent, AMResource resource,
			DataBindingContext bindingContext) {
		super(parent, resource, bindingContext);
	}

	public ReportUnitDatasourceContent(ANode parent, AMResource resource,
			boolean mandatory) {
		this(parent, resource);
		this.mandatory = mandatory;
	}

	public ReportUnitDatasourceContent(ANode parent, AMResource resource) {
		super(parent, resource);
	}

	@Override
	public String getName() {
		return Messages.SelectorDatasource_TabTitle;
	}

	@Override
	public String getPageName() {
		return "com.jaspersoft.studio.server.page.runit.datasource";
	}

	@Override
	public String getHelpContext() {
		return "com.jaspersoft.studio.doc.editReportUnitDSContent";
	}

	public static String[] getExcludedTypes(AMResource r) {
		if (r != null && r.getWsClient() != null) {
			if (Version.isXMLACoonnectionSupported(r.getWsClient()))
				return new String[] { ResourceDescriptor.TYPE_OLAP_XMLA_CONNECTION };
		}
		return null;
	}

	@Override
	public Control createContent(Composite parent) {
		datasourceSelectionCmp = new SelectorDatasource().createDatasource(
				parent, pnode, res, mandatory, getExcludedTypes(res));
		datasourceSelectionCmp.addDatasourceSelectionListener(this);
		rebind();
		return datasourceSelectionCmp;
	}

	@Override
	protected void rebind() {
		final ResourceDescriptor rd = res.getValue();
		final IConnection con = getWsClient();
		if (!rd.getIsNew() && !con.isSupported(Feature.SEARCHREPOSITORY)
				&& res instanceof MAdHocDataView) {
			datasourceSelectionCmp.setEnabled(false);
			UIUtils.getDisplay().asyncExec(new Runnable() {

				@Override
				public void run() {
					setPageComplete(false);
					page.setDescription("AdHoc Views are not editable with SOAP connections.");
				}
			});
		}
	}

	@Override
	public boolean isPageComplete() {
		return datasourceSelectionCmp != null
				&& datasourceSelectionCmp.isDatasourceSelectionValid();
	}

	private boolean refresh = false;

	@Override
	public void datasourceSelectionChanged() {
		if (refresh)
			return;
		refresh = true;
		setPageComplete(isPageComplete());
		if (datasourceSelectionCmp.isDatasourceSelectionValid())
			page.setErrorMessage(null);
		else
			page.setErrorMessage("There is a problem with selected Datasource which is not valid");
		refresh = false;
	}
}
