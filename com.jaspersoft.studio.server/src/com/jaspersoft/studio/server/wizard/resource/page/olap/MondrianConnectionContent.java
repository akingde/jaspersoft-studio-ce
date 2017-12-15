/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.resource.page.olap;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.model.datasource.MROlapMondrianConnection;
import com.jaspersoft.studio.server.wizard.resource.APageContent;
import com.jaspersoft.studio.server.wizard.resource.page.selector.SelectorMondrianConnection;

public class MondrianConnectionContent extends APageContent {
	protected SelectorMondrianConnection scompo;

	public MondrianConnectionContent(ANode parent, AMResource resource, DataBindingContext bindingContext) {
		super(parent, resource, bindingContext);
	}

	public MondrianConnectionContent(ANode parent, AMResource resource) {
		super(parent, resource);
	}

	@Override
	public String getName() {
		return MROlapMondrianConnection.getIconDescriptor().getTitle();
	}

	@Override
	public String getPageName() {
		return "com.jaspersoft.studio.server.page.mondrianConnection";
	}

	@Override
	public String getHelpContext() {
		return "com.jaspersoft.studio.doc.editMondrianConnection";
	}

	@Override
	public Control createContent(Composite parent) {
		scompo = new SelectorMondrianConnection();
		scompo.addPageCompleteListener(this);
		rebind();
		return scompo.createControls(parent, pnode, res);
	}

	@Override
	protected void rebind() {
	}

	@Override
	public boolean isPageComplete() {
		return scompo != null && scompo.isPageComplete();
	}

}
