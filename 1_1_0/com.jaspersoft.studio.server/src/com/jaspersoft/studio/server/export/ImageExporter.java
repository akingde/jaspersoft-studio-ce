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

import net.sf.jasperreports.engine.type.ImageTypeEnum;
import net.sf.jasperreports.engine.util.JRTypeSniffer;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.utils.FileUtils;

public class ImageExporter extends AExporter {

	@Override
	public File exportFile(MResource res, ResourceDescriptor rd, String fkeyname)
			throws Exception {
		File f = super.exportFile(res, rd, fkeyname);
		if (f != null) {
			String filename = f.getAbsolutePath();
			int dotPos = filename.lastIndexOf(".");
			String strFilename = filename.substring(0, dotPos);
			ImageTypeEnum itype = JRTypeSniffer.getImageTypeValue(FileUtils
					.getBytes(f));
			if (itype == ImageTypeEnum.GIF) {
				f = FileUtils.fileRenamed(f, strFilename, ".gif", false);
			} else if (itype == ImageTypeEnum.JPEG) {
				f = FileUtils.fileRenamed(f, strFilename, ".jpeg", false);
			} else if (itype == ImageTypeEnum.PNG) {
				f = FileUtils.fileRenamed(f, strFilename, ".png", false);
			} else if (itype == ImageTypeEnum.TIFF) {
				f = FileUtils.fileRenamed(f, strFilename, ".tiff", false);
			}
			fileurimap.put(fkeyname, f.getAbsolutePath());
		}
		return f;
	}

	@Override
	public String getExtension() {
		return ".png";
	}

}
