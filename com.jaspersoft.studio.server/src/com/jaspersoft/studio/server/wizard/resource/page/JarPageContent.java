/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.resource.page;

import org.eclipse.core.databinding.DataBindingContext;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.AMResource;

public class JarPageContent extends AFileResourcePageContent {

	public JarPageContent(ANode parent, AMResource resource, DataBindingContext bindingContext) {
		super(parent, resource, bindingContext);
	}

	public JarPageContent(ANode parent, AMResource resource) {
		super(parent, resource);
	}

	@Override
	public String getPageName() {
		return "com.jaspersoft.studio.server.page.jar";
	}

	@Override
	public String getName() {
		return Messages.RDJarPage_title;
	}

	@Override
	protected String[] getFilter() {
		return new String[] { "*.*", "*.zip", "*.jar" }; //$NON-NLS-1$ //$NON-NLS-2$
	}

}
