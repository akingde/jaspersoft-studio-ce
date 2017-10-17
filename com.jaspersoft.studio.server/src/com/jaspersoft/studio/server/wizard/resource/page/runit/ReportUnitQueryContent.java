/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.resource.page.runit;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.utils.IPageCompleteListener;
import com.jaspersoft.studio.server.wizard.resource.APageContent;
import com.jaspersoft.studio.server.wizard.resource.page.selector.SelectorQueryWithNon;

public class ReportUnitQueryContent extends APageContent {

	private SelectorQueryWithNon sQuery;

	public ReportUnitQueryContent(ANode parent, AMResource resource,
			DataBindingContext bindingContext) {
		super(parent, resource, bindingContext);
	}

	public ReportUnitQueryContent(ANode parent, AMResource resource) {
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
