/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.server.export;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.utils.FileUtils;
import com.jaspersoft.studio.utils.UIUtils;

public abstract class AExporter {
	protected static Map<String, String> fileurimap = new HashMap<String, String>();

	public File exportFile(MResource res, ResourceDescriptor rd, String fkeyname)
			throws Exception {
		File f = getTempFile(res, rd, fkeyname, getExtension());
		if (f != null)
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
		try {
			WSClientHelper.getResource(res, rd, f);
		} catch (Exception e) {
			UIUtils.showError(e);
			return null;
		}
		return f;
	}
}
