/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.server.wizard.resource.page;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.MRQuery;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.UIUtils;

public class RDQueryPage extends AResourcePage {

	public RDQueryPage(ANode parent, MRQuery resource) {
		super(Messages.RDQueryPage_id, parent, resource);
		setTitle(Messages.RDQueryPage_title);
		setDescription(Messages.RDQueryPage_desc);
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
		item.setText(Messages.RDQueryPage_textquery);

		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		item.setControl(composite);

		UIUtils.createLabel(composite, Messages.RDQueryPage_language);

		Combo clang = new Combo(composite, SWT.BORDER);
		clang.setItems(ModelUtils.getQueryLanguages());

		UIUtils.createLabel(composite, Messages.RDQueryPage_query);

		Text tsql = new Text(composite, SWT.BORDER | SWT.WRAP);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.minimumHeight = 100;
		gd.widthHint = 400;
		tsql.setLayoutData(gd);

		bindingContext.bindValue(SWTObservables.observeText(clang),
				PojoObservables.observeValue(getProxy(r), "language")); //$NON-NLS-1$
		bindingContext.bindValue(SWTObservables.observeText(tsql, SWT.Modify),
				PojoObservables.observeValue(r, "sql")); //$NON-NLS-1$
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
			lang = ModelUtils.getLanguage(lang);
			rd.setResourceProperty(ResourceDescriptor.PROP_QUERY_LANGUAGE, lang);
		}

		public String getLanguage() {
			return rd
					.getResourcePropertyValue(ResourceDescriptor.PROP_QUERY_LANGUAGE);
		}
	}
}
