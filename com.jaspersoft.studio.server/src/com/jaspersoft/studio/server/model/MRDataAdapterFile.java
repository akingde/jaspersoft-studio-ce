package com.jaspersoft.studio.server.model;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;

public class MRDataAdapterFile extends MXmlFile {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MRDataAdapterFile(ANode parent, ResourceDescriptor rd, int index) {
		super(parent, rd, index);
	}

}
