/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.resource.page;

import org.eclipse.core.databinding.DataBindingContext;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.wizard.resource.page.selector.ATextFileResourcePageContent;

public class CSSPageContent extends ATextFileResourcePageContent {

	public CSSPageContent(ANode parent, AMResource resource, DataBindingContext bindingContext) {
		super(parent, resource, bindingContext);
	}

	public CSSPageContent(ANode parent, AMResource resource) {
		super(parent, resource);
	}

	@Override
	public String getPageName() {
		return "com.jaspersoft.studio.server.page.css";
	}

	@Override
	public String getName() {
		return "CSS File";
	}

	@Override
	protected String[] getFilter() {
		return new String[] { "*.*", "*.css" }; //$NON-NLS-1$ //$NON-NLS-2$
	}

}
