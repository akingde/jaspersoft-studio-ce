package com.jaspersoft.studio.server.wizard.resource.page;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.model.MXmlFile;

public class RDXmlFile extends AFileResourcePage {
	public RDXmlFile(ANode parent, MXmlFile resource) {
		super("rdxmlfile", parent, resource);
		setTitle("XML File");
		setDescription("XML File");
	}

	@Override
	protected String[] getFilter() {
		return new String[] { "*.xml" };
	}

}
