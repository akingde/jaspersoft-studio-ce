package com.jaspersoft.studio.server.wizard.resource.page;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.model.MRImage;

public class RDImagePage extends AFileResourcePage {

	public RDImagePage(ANode parent, MRImage resource) {
		super("rdimage", parent, resource);
		setTitle("Image");
		setDescription("Image resource");
	}

	@Override
	protected String[] getFilter() {
		return new String[] { "*.png", "*.jpg", "*.jpeg", "*.gif" };
	}
}
