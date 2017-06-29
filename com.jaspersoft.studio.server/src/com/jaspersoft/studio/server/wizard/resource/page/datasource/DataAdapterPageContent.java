/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.resource.page.datasource;

import org.eclipse.core.databinding.DataBindingContext;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.wizard.resource.page.AFileResourcePageContent;

public class DataAdapterPageContent extends AFileResourcePageContent {

	public DataAdapterPageContent(ANode parent, AMResource resource, DataBindingContext bindingContext) {
		super(parent, resource, bindingContext);
	}

	public DataAdapterPageContent(ANode parent, AMResource resource) {
		super(parent, resource);
	}

	@Override
	public String getPageName() {
		return "com.jaspersoft.studio.server.page.dataadapter";
	}

	@Override
	public String getName() {
		return Messages.RDDataAdapterPage_Title;
	}

	@Override
	protected String[] getFilter() {
		return new String[] { "*.*", "*.xml" }; //$NON-NLS-1$
	}

}
