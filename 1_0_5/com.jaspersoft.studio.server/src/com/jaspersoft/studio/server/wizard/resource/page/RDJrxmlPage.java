package com.jaspersoft.studio.server.wizard.resource.page;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.model.MJrxml;

public class RDJrxmlPage extends AFileResourcePage {

	public RDJrxmlPage(ANode parent, MJrxml resource) {
		super("rdresourcebundle", parent, resource);
		setTitle("Report File");
		setDescription("Report file");
	}

	@Override
	protected String[] getFilter() {
		return new String[] { ".jrxml" };
	}
}
