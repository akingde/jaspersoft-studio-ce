package com.jaspersoft.studio.server.wizard.resource.page;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.model.MFolder;

public class RDFolderPage extends AResourcePage {

	public RDFolderPage(ANode parent, MFolder resource) {
		super("rdfolder", parent, resource);
		setTitle("Folder");
		setDescription("JasperServer folder resource");
	}

}
