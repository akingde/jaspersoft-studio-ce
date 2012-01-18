package com.jaspersoft.studio.server.export;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.model.MResource;

public abstract class AExporter {
	protected static Map<String, String> fileurimap = new HashMap<String, String>();

	public File exportFile(MResource res, ResourceDescriptor rd, String fkeyname)
			throws Exception {
		File f = getTempFile(res, rd, fkeyname, getExtension());
		WSClientHelper.getResource(res, rd, f);
		fileurimap.put(fkeyname, f.getAbsolutePath());
		return f;
	}

	public abstract String getExtension();

	private File getTempFile(MResource res, ResourceDescriptor rd,
			String fkeyname, String dextention) throws Exception {
		String filename = fileurimap.get(fkeyname);

		File f = null;
		if (filename != null)
			f = new File(filename);
		else {
			f = File.createTempFile("jrsres", dextention);
			f.deleteOnExit();
		}
		WSClientHelper.getResource(res, rd, f);
		return f;
	}
}
