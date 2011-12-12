package com.jaspersoft.studio.server.wizard.resource.page;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.model.MRFont;

public class RDFontPage extends AFileResourcePage {

	public RDFontPage(ANode parent, MRFont resource) {
		super("rdfont", parent, resource);
		setTitle("Font");
		setDescription("Font resource");
	}

	@Override
	protected String[] getFilter() {
		return new String[] { "*.ttf", "*.TTF" };
	}
}
