/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.resource.page.runit;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.utils.IPageCompleteListener;
import com.jaspersoft.studio.server.wizard.resource.APageContent;
import com.jaspersoft.studio.server.wizard.resource.page.selector.SelectorQueryWithNon;

public class ReportUnitQueryContent extends APageContent {

	private SelectorQueryWithNon sQuery;

	public ReportUnitQueryContent(ANode parent, MResource resource,
			DataBindingContext bindingContext) {
		super(parent, resource, bindingContext);
	}

	public ReportUnitQueryContent(ANode parent, MResource resource) {
		super(parent, resource);
	}

	@Override
	public boolean isPageComplete() {
		if(sQuery!=null) {
			return sQuery.isPageComplete();
		}
		return super.isPageComplete();
	}

	@Override
	public String getName() {
		return Messages.ReportUnitQueryContent_0;
	}

	@Override
	public String getPageName() {
		return Messages.ReportUnitQueryContent_1;
	}

	@Override
	public Control createContent(Composite parent) {
		sQuery = new SelectorQueryWithNon();
		Composite cmp = (Composite) sQuery.createControls(parent, pnode, res);

		sQuery.addPageCompleteListener(new IPageCompleteListener() {

			@Override
			public void pageCompleted(boolean completed) {
				setPageComplete(sQuery.isPageComplete());
				if (sQuery.isPageComplete())
					page.setErrorMessage(null);
				else
					page.setErrorMessage(Messages.ReportUnitQueryContent_2);
			}
		});
		rebind();
		return cmp;
	}

	@Override
	protected void rebind() {

	}

}
