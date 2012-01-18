package com.jaspersoft.studio.server.export;

import java.io.File;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.model.MResource;

public class JrxmlExporter extends AExporter {
	@Override
	public File exportFile(MResource res, ResourceDescriptor rd, String fkeyname)
			throws Exception {
		// create in a folder , inside workspace?

		File f = super.exportFile(res, rd, fkeyname);

		return f;
	}

	@Override
	public String getExtension() {
		return ".jrxml";
	}

}
