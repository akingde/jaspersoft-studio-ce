package com.jaspersoft.studio.server.export;

import java.io.File;

import net.sf.jasperreports.engine.JRRenderable;
import net.sf.jasperreports.engine.util.JRTypeSniffer;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.utils.FileUtils;

public class ImageExporter extends AExporter {

	@Override
	public File exportFile(MResource res, ResourceDescriptor rd, String fkeyname)
			throws Exception {
		File f = super.exportFile(res, rd, fkeyname);
		String filename = f.getAbsolutePath();
		int dotPos = filename.lastIndexOf(".");
		String strFilename = filename.substring(0, dotPos);
		switch (JRTypeSniffer.getImageType(FileUtils.getBytes(f))) {
		case JRRenderable.IMAGE_TYPE_GIF:
			f = FileUtils.fileRenamed(f, strFilename, ".gif", false);
			break;
		case JRRenderable.IMAGE_TYPE_JPEG:
			f = FileUtils.fileRenamed(f, strFilename, ".jpeg", false);
			break;
		case JRRenderable.IMAGE_TYPE_PNG:
			f = FileUtils.fileRenamed(f, strFilename, ".png", false);
			break;
		case JRRenderable.IMAGE_TYPE_TIFF:
			f = FileUtils.fileRenamed(f, strFilename, ".tiff", false);
			break;
		}
		fileurimap.put(fkeyname, f.getAbsolutePath());
		return f;
	}

	@Override
	public String getExtension() {
		return ".png";
	}

}
