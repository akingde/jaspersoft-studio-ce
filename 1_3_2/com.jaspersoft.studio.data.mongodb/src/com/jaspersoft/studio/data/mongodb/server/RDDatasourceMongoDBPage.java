package com.jaspersoft.studio.data.mongodb.server;

import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceProperty;
import com.jaspersoft.studio.data.mongodb.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.model.datasource.MRDatasourceCustom;
import com.jaspersoft.studio.server.utils.ResourceDescriptorUtil;
import com.jaspersoft.studio.server.wizard.resource.page.AResourcePage;
import com.jaspersoft.studio.utils.UIUtils;

public class RDDatasourceMongoDBPage extends AResourcePage {

	public RDDatasourceMongoDBPage(ANode parent, MRDatasourceMongoDB resource) {
		super("rdmongodatasource", parent, resource); //$NON-NLS-1$
		setTitle(Messages.RDDatasourceMongoDBPage_title);
		setDescription(Messages.RDDatasourceMongoDBPage_desc);
	}

	@Override
	protected void createTabs(TabFolder tabFolder) {
		super.createTabs(tabFolder);
		createDatasourceTab(tabFolder);
		if (!res.getValue().getIsNew())
			tabFolder.setSelection(1);
	}

	protected void createDatasourceTab(TabFolder tabFolder) {
		TabItem item = new TabItem(tabFolder, SWT.NONE);
		item.setText(Messages.RDDatasourceMongoDBPage_title);

		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		item.setControl(composite);

		UIUtils.createLabel(composite,
				Messages.RDDatasourceMongoDBPage_labelurl);

		Text turi = new Text(composite, SWT.BORDER);
		turi.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		UIUtils.createLabel(composite,
				Messages.RDDatasourceMongoDBPage_username);

		Text tusername = new Text(composite, SWT.BORDER);
		tusername.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		UIUtils.createLabel(composite, Messages.RDDatasourceMongoDBPage_pass);

		Text tpass = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		tpass.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		ResourceProperty resprop = ResourceDescriptorUtil.getProperty(
				MRDatasourceCustom.PROP_DATASOURCE_CUSTOM_PROPERTY_MAP, res
						.getValue().getProperties());

		ResourceProperty rsp = ResourceDescriptorUtil.getProperty(
				MRDatasourceMongoDB.MONGO_URI, resprop.getProperties());
		bindingContext.bindValue(SWTObservables.observeText(turi, SWT.Modify),
				PojoObservables.observeValue(rsp, "value")); //$NON-NLS-1$

		rsp = ResourceDescriptorUtil.getProperty(MRDatasourceMongoDB.USERNAME,
				resprop.getProperties());
		bindingContext.bindValue(
				SWTObservables.observeText(tusername, SWT.Modify),
				PojoObservables.observeValue(rsp, "value")); //$NON-NLS-1$

		rsp = ResourceDescriptorUtil.getProperty(MRDatasourceMongoDB.PASSWORD,
				resprop.getProperties());
		bindingContext.bindValue(SWTObservables.observeText(tpass, SWT.Modify),
				PojoObservables.observeValue(rsp, "value")); //$NON-NLS-1$
	}
}
