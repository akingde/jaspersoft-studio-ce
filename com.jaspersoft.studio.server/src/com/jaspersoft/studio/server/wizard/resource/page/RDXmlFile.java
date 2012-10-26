package com.jaspersoft.studio.server.wizard.resource.page;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.MXmlFile;

public class RDXmlFile extends AFileResourcePage {
	public RDXmlFile(ANode parent, MXmlFile resource) {
		super(Messages.RDXmlFile_id, parent, resource);
		setTitle(Messages.RDXmlFile_title);
		setDescription(Messages.RDXmlFile_desc);
	}

	@Override
	protected String[] getFilter() {
		return new String[] { "*.xml" }; //$NON-NLS-1$
	}

}
