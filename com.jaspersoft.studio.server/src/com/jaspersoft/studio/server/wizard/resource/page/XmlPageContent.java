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

public class XmlPageContent extends ATextFileResourcePageContent {

	public XmlPageContent(ANode parent, AMResource resource, DataBindingContext bindingContext) {
		super(parent, resource, bindingContext);
	}

	private String title;

	public XmlPageContent(ANode parent, AMResource resource) {
		super(parent, resource);
	}

	public XmlPageContent(ANode parent, AMResource resource, String title) {
		this(parent, resource);
		this.title = title;
	}

	@Override
	public String getPageName() {
		return "com.jaspersoft.studio.server.page.xml";
	}

	@Override
	public String getName() {
		if (title != null)
			return title;
		return Messages.RDXmlFile_title;
	}

	@Override
	protected String[] getFilter() {
		return new String[] { "*.*", "*.xml" }; //$NON-NLS-1$
	}

}
