/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.model;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.server.ServerIconDescriptor;
import com.jaspersoft.studio.server.model.server.MServerProfile;

public class MContentResource extends AFileResource {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MContentResource(ANode parent, ResourceDescriptor rd, int index) {
		super(parent, rd, index);
	}

	private static IIconDescriptor iconDescriptor;

	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new ServerIconDescriptor("contentresource"); //$NON-NLS-1$
		return iconDescriptor;
	}

	@Override
	public IIconDescriptor getThisIconDescriptor() {
		return getIconDescriptor();
	}

	public static ResourceDescriptor createDescriptor(ANode parent) {
		ResourceDescriptor rd = AMResource.createDescriptor(parent);
		rd.setWsType(ResourceDescriptor.TYPE_CONTENT_RESOURCE);
		return rd;
	}

	@Override
	public String getDefaultFileExtension() {
		return "";
	}

	public ICopyable.RESULT isCopyable2(Object parent) {
		if (parent instanceof MFolder || parent instanceof MServerProfile)
			return ICopyable.RESULT.COPYABLE;
		return ICopyable.RESULT.NOT_COPYABLE;
	}
}
