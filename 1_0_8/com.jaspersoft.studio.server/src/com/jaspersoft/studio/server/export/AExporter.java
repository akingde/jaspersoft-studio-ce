/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.server.export;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.utils.FileUtils;

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
		else
			f = FileUtils.createTempFile("jrsres", dextention);

		WSClientHelper.getResource(res, rd, f);
		return f;
	}
}
