/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
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

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;

import org.eclipse.core.resources.IFile;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.model.MResource;

public abstract class AExporter {
	protected static Map<String, String> fileurimap = new HashMap<String, String>();

	public IFile exportToIFile(MResource res, ResourceDescriptor rd,
			String fkeyname) throws Exception {
		return FileUtils
				.getInProjectFile(exportFile(res, rd, fkeyname).toURI());
	}

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
		else {
			String fname = rd.getName();
			int lastpoint = fname.lastIndexOf(".");
			if (lastpoint > 0 && lastpoint < fname.length())
				fname = fname.substring(0, lastpoint) + "_";
			f = FileUtils.createTempFile(fname, dextention);
		}
		try {
			WSClientHelper.getResource(res, rd, f);
		} catch (Exception e) {
			UIUtils.showError(e);
			return null;
		}
		return f;
	}
}
