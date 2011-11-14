package com.jaspersoft.studio.server.wizard.resource.page;

import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.model.MRDatasourceBean;
import com.jaspersoft.studio.utils.UIUtils;

public class RDDatasourceBeanPage extends AResourcePage {

	public RDDatasourceBeanPage(ANode parent, MRDatasourceBean resource) {
		super("rdbeandatasource", parent, resource);
		setTitle("Datasource Bean");
		setDescription("Bean Datasource");
	}

	@Override
	protected void createTabs(TabFolder tabFolder) {
		super.createTabs(tabFolder);
		createDatasourceTab(tabFolder);
	}

	protected void createDatasourceTab(TabFolder tabFolder) {
		TabItem item = new TabItem(tabFolder, SWT.NONE);
		item.setText("Datasource");

		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		item.setControl(composite);

		UIUtils.createLabel(composite, "Bean Name");

		Text tname = new Text(composite, SWT.BORDER);
		tname.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		UIUtils.createLabel(composite, "Bean Method");

		Text tmethod = new Text(composite, SWT.BORDER);
		tmethod.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		bindingContext.bindValue(SWTObservables.observeText(tname, SWT.Modify),
				PojoObservables.observeValue(res.getValue(), "beanName"));
		bindingContext.bindValue(
				SWTObservables.observeText(tmethod, SWT.Modify),
				PojoObservables.observeValue(res.getValue(), "beanMethod"));
	}
}
