/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.export;

import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.type.ImageTypeEnum;
import net.sf.jasperreports.engine.util.JRTypeSniffer;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.model.AMResource;

public class ImageExporter extends AExporter {

	public ImageExporter(IPath path) {
		super(path);
	}

	@Override
	public IFile exportToIFile(AMResource res, ResourceDescriptor rd, String fkeyname, IProgressMonitor monitor) throws Exception {
		IFile f = super.exportToIFile(res, rd, fkeyname, monitor);
		if (path == null && f != null) {
			String filename = f.getFullPath().toOSString();
			int dotPos = filename.lastIndexOf(".");
			if (dotPos >= 0)
				filename = filename.substring(0, dotPos);
			ImageTypeEnum itype = JRTypeSniffer.getImageTypeValue(FileUtils.getBytes(f));
			if (itype == ImageTypeEnum.UNKNOWN)
				itype = ImageTypeEnum.PNG;
			if (itype == ImageTypeEnum.GIF) {
				f = FileUtils.fileRenamed(f, filename, ".gif", false, monitor);
			} else if (itype == ImageTypeEnum.JPEG) {
				f = FileUtils.fileRenamed(f, filename, ".jpeg", false, monitor);
			} else if (itype == ImageTypeEnum.PNG) {
				f = FileUtils.fileRenamed(f, filename, ".png", false, monitor);
			} else if (itype == ImageTypeEnum.TIFF) {
				f = FileUtils.fileRenamed(f, filename, ".tiff", false, monitor);
			}
			fileurimap.put(fkeyname, f);
		}
		return f;
	}

	@Override
	public String getExtension(AMResource res) {
		return "";
	}

}
