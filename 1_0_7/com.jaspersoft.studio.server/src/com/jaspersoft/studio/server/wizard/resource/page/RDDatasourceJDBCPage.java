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
import com.jaspersoft.studio.server.model.MRDatasourceJDBC;
import com.jaspersoft.studio.utils.UIUtils;

public class RDDatasourceJDBCPage extends AResourcePage {

	public RDDatasourceJDBCPage(ANode parent, MRDatasourceJDBC resource) {
		super("rdjdbcdatasource", parent, resource);
		setTitle("Datasource JDBC");
		setDescription("JDBC Datasource");
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
		item.setText("Datasource");

		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		item.setControl(composite);

		UIUtils.createLabel(composite, "Driver");

		Text tdriver = new Text(composite, SWT.BORDER);
		tdriver.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		UIUtils.createLabel(composite, "URL");

		Text turl = new Text(composite, SWT.BORDER);
		turl.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		UIUtils.createLabel(composite, "User");

		Text tuser = new Text(composite, SWT.BORDER);
		tuser.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		UIUtils.createLabel(composite, "Password");

		Text tpass = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		tpass.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		bindingContext.bindValue(
				SWTObservables.observeText(tdriver, SWT.Modify),
				PojoObservables.observeValue(res.getValue(), "driverClass"));
		bindingContext.bindValue(SWTObservables.observeText(turl, SWT.Modify),
				PojoObservables.observeValue(res.getValue(), "connectionUrl"));
		bindingContext.bindValue(SWTObservables.observeText(tuser, SWT.Modify),
				PojoObservables.observeValue(res.getValue(), "username"));
		bindingContext.bindValue(SWTObservables.observeText(tpass, SWT.Modify),
				PojoObservables.observeValue(res.getValue(), "password"));
	}
}
