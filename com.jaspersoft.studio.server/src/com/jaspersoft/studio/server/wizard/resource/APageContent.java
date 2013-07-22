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
package com.jaspersoft.studio.server.wizard.resource;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.model.MResource;

public abstract class APageContent {
	protected AWizardPage page;
	protected ANode pnode;
	protected MResource res;
	protected DataBindingContext bindingContext;

	public APageContent(ANode parent, MResource resource) {
		this(parent, resource, new DataBindingContext());
	}

	public APageContent(ANode parent, MResource resource,
			DataBindingContext bindingContext) {
		this.res = resource;
		this.pnode = parent;
		this.bindingContext = bindingContext;
	}

	public void setBindingContext(DataBindingContext bindingContext) {
		this.bindingContext = bindingContext;
	}

	public DataBindingContext getBindingContext() {
		return bindingContext;
	}

	public abstract String getName();

	public abstract String getPageName();

	public abstract Control createContent(Composite parent);

	public static IWizardPage[] getPages(MResource res,
			APageContent... rcontent) {
		if (res.getValue() != null && res.getValue().getIsNew()) {
			IWizardPage[] pages = new IWizardPage[rcontent.length];
			for (int i = 0; i < pages.length; i++)
				pages[i] = new NewResourcePage(rcontent[i]);
			return pages;
		}
		return new IWizardPage[] { new EditResourcePage(rcontent) };
	}

	public void setPage(AWizardPage page) {
		this.page = page;
	}

	public boolean isPageComplete() {
		return true;
	}

	public void setPageComplete(boolean complete) {
		page.setPageComplete(complete);
	}
	
	public String getHelpContext(){
		return null;
	}

	public void dispose() {

	}
}
