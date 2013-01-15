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
package com.jaspersoft.studio.server.wizard.resource.page;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.wizard.resource.APageContent;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.UIUtils;

public class QueryPageContent extends APageContent {

	public QueryPageContent(ANode parent, MResource resource,
			DataBindingContext bindingContext) {
		super(parent, resource, bindingContext);
	}

	public QueryPageContent(ANode parent, MResource resource) {
		super(parent, resource);
	}

	@Override
	public String getPageName() {
		return "com.jaspersoft.studio.server.page.query";
	}

	@Override
	public String getName() {
		return Messages.RDQueryPage_textquery;
	}

	public Control createContent(Composite parent) {
		return createContentComposite(parent, bindingContext, res.getValue());
	}

	public static Control createContentComposite(Composite parent,
			DataBindingContext bindingContext, ResourceDescriptor r) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

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

		return composite;
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
