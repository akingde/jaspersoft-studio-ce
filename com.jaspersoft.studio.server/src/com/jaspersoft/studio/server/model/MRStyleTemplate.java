/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.model;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.style.MStyleTemplate;
import com.jaspersoft.studio.model.util.IIconDescriptor;

public class MRStyleTemplate extends AFileResource {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MRStyleTemplate(ANode parent, ResourceDescriptor rd, int index) {
		super(parent, rd, index);
	}

	public static IIconDescriptor getIconDescriptor() {
		return MStyleTemplate.getIconDescriptor();
	}

	@Override
	public IIconDescriptor getThisIconDescriptor() {
		return getIconDescriptor();
	}

	public static ResourceDescriptor createDescriptor(ANode parent) {
		ResourceDescriptor rd = AMResource.createDescriptor(parent);
		rd.setWsType(ResourceDescriptor.TYPE_STYLE_TEMPLATE);
		return rd;
	}

	@Override
	public String getDefaultFileExtension() {
		return "jrtx";
	}
}
