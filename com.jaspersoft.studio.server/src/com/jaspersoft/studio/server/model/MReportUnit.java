/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.server.ServerIconDescriptor;
import com.jaspersoft.studio.server.model.server.MServerProfile;

public class MReportUnit extends AMJrxmlContainer implements IInputControlsContainer {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	public static final String RU_SUFFIX = "_files";

	public MReportUnit(ANode parent, ResourceDescriptor rd, int index) {
		super(parent, rd, index);
	}

	public MReportUnit(ANode parent, ResourceDescriptor rd) {
		super(parent, rd, -1);
	}

	private static IIconDescriptor iconDescriptor;

	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new ServerIconDescriptor("reportunit"); //$NON-NLS-1$
		return iconDescriptor;
	}

	@Override
	public IIconDescriptor getThisIconDescriptor() {
		return getIconDescriptor();
	}

	@Override
	public ICopyable.RESULT isCopyable2(Object parent) {
		if (parent instanceof MFolder || parent instanceof MServerProfile)
			return ICopyable.RESULT.COPYABLE;
		return ICopyable.RESULT.CHECK_PARENT;
	}

	public static ResourceDescriptor createDescriptor(ANode parent) {
		ResourceDescriptor rd = AMResource.createDescriptor(parent);
		rd.setWsType(ResourceDescriptor.TYPE_REPORTUNIT);
		return rd;
	}

	@Override
	public String getDefaultFileExtension() {
		return "";
	}

	@Override
	public String getJRSUrl() throws UnsupportedEncodingException {
		return "flow.html?_flowId=reportUnitFlow&selectedResource="
				+ URLEncoder.encode(getValue().getUriString(), "ISO-8859-1") + "&ParentFolderUri="
				+ URLEncoder.encode(getValue().getParentFolder(), "ISO-8859-1");
	}
}
