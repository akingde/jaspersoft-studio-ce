package com.jaspersoft.studio.data.hive.server;

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
import com.jaspersoft.studio.data.hive.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.model.datasource.MRDatasourceCustom;
import com.jaspersoft.studio.server.utils.ResourceDescriptorUtil;
import com.jaspersoft.studio.server.wizard.resource.page.AResourcePage;
import com.jaspersoft.studio.utils.UIUtils;

public class RDDatasourceHivePage extends AResourcePage {

	public RDDatasourceHivePage(ANode parent, MRDatasourceHadoopHive resource) {
		super("rdhivedatasource", parent, resource);
		setTitle(Messages.RDDatasourceHivePage_title);
		setDescription(Messages.RDDatasourceHivePage_desc);
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
		item.setText(Messages.RDDatasourceHivePage_tiitle);

		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		item.setControl(composite);

		UIUtils.createLabel(composite,
				Messages.HiveDataAdapterComposite_labelurl);

		Text tname = new Text(composite, SWT.BORDER);
		tname.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		ResourceProperty resprop = ResourceDescriptorUtil.getProperty(
				MRDatasourceCustom.PROP_DATASOURCE_CUSTOM_PROPERTY_MAP, res
						.getValue().getProperties());

		resprop = ResourceDescriptorUtil.getProperty(
				MRDatasourceHadoopHive.JDBC_URL, resprop.getProperties());

		bindingContext.bindValue(SWTObservables.observeText(tname, SWT.Modify),
				PojoObservables.observeValue(resprop, "value")); //$NON-NLS-1$
	}
}
