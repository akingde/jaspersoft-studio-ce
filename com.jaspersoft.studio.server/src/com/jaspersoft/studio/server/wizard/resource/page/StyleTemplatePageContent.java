/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.resource.page;

import org.eclipse.core.databinding.DataBindingContext;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.wizard.resource.page.selector.ATextFileResourcePageContent;

public class StyleTemplatePageContent extends ATextFileResourcePageContent {

	public StyleTemplatePageContent(ANode parent, AMResource resource, DataBindingContext bindingContext) {
		super(parent, resource, bindingContext);
	}

	public StyleTemplatePageContent(ANode parent, AMResource resource) {
		super(parent, resource);
	}

	@Override
	public String getPageName() {
		return "com.jaspersoft.studio.server.page.styletemplate";
	}

	@Override
	public String getName() {
		return Messages.RDStyleTemplatePage_title;
	}

	@Override
	protected String[] getFilter() {
		return new String[] { "*.*", "*.jrtx" }; //$NON-NLS-1$
	}

}
