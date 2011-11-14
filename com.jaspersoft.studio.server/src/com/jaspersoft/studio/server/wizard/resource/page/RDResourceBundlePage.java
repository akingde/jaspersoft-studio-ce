package com.jaspersoft.studio.server.wizard.resource.page;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.model.MResourceBundle;

public class RDResourceBundlePage extends AFileResourcePage {

	public RDResourceBundlePage(ANode parent, MResourceBundle resource) {
		super("rdresourcebundle", parent, resource);
		setTitle("Resource Bundle");
		setDescription("Resource Bundle");
	}

	@Override
	protected String[] getFilter() {
		return new String[] { "*.propeties" };
	}
}
