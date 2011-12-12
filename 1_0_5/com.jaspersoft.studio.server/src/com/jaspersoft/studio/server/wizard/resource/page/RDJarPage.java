package com.jaspersoft.studio.server.wizard.resource.page;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.model.MJar;

public class RDJarPage extends AFileResourcePage {

	public RDJarPage(ANode parent, MJar resource) {
		super("rdjar", parent, resource);
		setTitle("JAR file");
		setDescription("JAR resource");
	}

	@Override
	protected String[] getFilter() {
		return new String[] { "*.zip", "*.jar" };
	}
}
