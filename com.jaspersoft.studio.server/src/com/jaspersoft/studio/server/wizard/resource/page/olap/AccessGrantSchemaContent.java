/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.resource.page.olap;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.model.MRAccessGrantSchema;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.wizard.resource.APageContent;
import com.jaspersoft.studio.server.wizard.resource.page.selector.SelectorAccessGrantSchema;

public class AccessGrantSchemaContent extends APageContent {
	private SelectorAccessGrantSchema scompo;
	private String title;

	public AccessGrantSchemaContent(ANode parent, AMResource resource, DataBindingContext bindingContext) {
		super(parent, resource, bindingContext);
	}

	public AccessGrantSchemaContent(ANode parent, AMResource resource, String title) {
		super(parent, resource);
		this.title = title;
	}

	@Override
	public String getName() {
		if (title != null)
			return title;
		return MRAccessGrantSchema.getIconDescriptor().getTitle();
	}

	@Override
	public String getPageName() {
		return "com.jaspersoft.studio.server.page.accessGrantSchema";
	}

	@Override
	public String getHelpContext() {
		return "com.jaspersoft.studio.doc.editAccessGrantSchema";
	}

	@Override
	public Control createContent(Composite parent) {
		scompo = new SelectorAccessGrantSchema();
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
