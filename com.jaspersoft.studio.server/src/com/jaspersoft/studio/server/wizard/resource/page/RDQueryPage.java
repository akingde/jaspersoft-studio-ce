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

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.model.MRQuery;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.UIUtils;

public class RDQueryPage extends AResourcePage {

	public RDQueryPage(ANode parent, MRQuery resource) {
		super("rdquery", parent, resource);
		setTitle("Query");
		setDescription("Query");
	}

	@Override
	protected void createTabs(TabFolder tabFolder) {
		super.createTabs(tabFolder);
		createDatasourceTab(bindingContext, tabFolder, res.getValue());
		new SelectorDatasource().createDatasource(tabFolder, parent, res);
	}

	public static void createDatasourceTab(DataBindingContext bindingContext,
			TabFolder tabFolder, ResourceDescriptor r) {
		TabItem item = new TabItem(tabFolder, SWT.NONE);
		item.setText("Query");

		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		item.setControl(composite);

		UIUtils.createLabel(composite, "Language");

		CCombo clang = new CCombo(composite, SWT.BORDER);
		clang.setItems(ModelUtils.getQueryLanguages());

		UIUtils.createLabel(composite, "Query");

		Text tsql = new Text(composite, SWT.BORDER | SWT.WRAP);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.minimumHeight = 100;
		gd.widthHint = 400;
		tsql.setLayoutData(gd);

		bindingContext.bindValue(SWTObservables.observeText(clang),
				PojoObservables.observeValue(getProxy(r), "language"));
		bindingContext.bindValue(SWTObservables.observeText(tsql, SWT.Modify),
				PojoObservables.observeValue(r, "sql"));
	}

	private static QProxy getProxy(ResourceDescriptor rd) {
		QProxy proxy = new QProxy();

		proxy.setResourceDescriptor(rd);
		return proxy;
	}

	public static class QProxy {
		private ResourceDescriptor rd;

		public void setResourceDescriptor(ResourceDescriptor rd) {
			this.rd = rd;
		}

		public void setLanguage(String lang) {
			rd.setResourceProperty(ResourceDescriptor.PROP_QUERY_LANGUAGE, lang);
		}

		public String getLanguage() {
			return rd
					.getResourcePropertyValue(ResourceDescriptor.PROP_QUERY_LANGUAGE);
		}
	}
}
