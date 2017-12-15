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
import com.jaspersoft.studio.server.model.datasource.MRMondrianSchema;
import com.jaspersoft.studio.server.wizard.resource.APageContent;
import com.jaspersoft.studio.server.wizard.resource.page.selector.SelectorOlapMondrianSchema;

public class OLAPMondrianSchemaContent extends APageContent {
	private SelectorOlapMondrianSchema scompo;

	public OLAPMondrianSchemaContent(ANode parent, AMResource resource, DataBindingContext bindingContext) {
		super(parent, resource, bindingContext);
	}

	public OLAPMondrianSchemaContent(ANode parent, AMResource resource) {
		super(parent, resource);
	}

	@Override
	public String getName() {
		return MRMondrianSchema.getIconDescriptor().getTitle();
	}

	@Override
	public String getPageName() {
		return "com.jaspersoft.studio.server.page.olapmondrianschema";
	}

	@Override
	public String getHelpContext() {
		return "com.jaspersoft.studio.doc.editOlapMondrianSchema";
	}

	@Override
	public Control createContent(Composite parent) {
		scompo = new SelectorOlapMondrianSchema();
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
