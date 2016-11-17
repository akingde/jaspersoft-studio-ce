/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.model.datasource;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.server.ServerIconDescriptor;
import com.jaspersoft.studio.server.model.AMResource;

public class MROlapXmlaConnection extends AMResource {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public static final String PROP_XMLA_URI = "PROP_XMLA_URI";
	public static final String PROP_XMLA_CATALOG = "PROP_XMLA_CATALOG";
	public static final String PROP_XMLA_DATASOURCE = "PROP_XMLA_DATASOURCE";
	public static final String PROP_XMLA_USERNAME = "PROP_XMLA_USERNAME";
	public static final String PROP_XMLA_PASSWORD = "PROP_XMLA_PASSWORD";

	public MROlapXmlaConnection(ANode parent, ResourceDescriptor rd, int index) {
		super(parent, rd, index);
	}

	private static IIconDescriptor iconDescriptor;

	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new ServerIconDescriptor("olap-xmla-connection"); //$NON-NLS-1$
		return iconDescriptor;
	}

	@Override
	public IIconDescriptor getThisIconDescriptor() {
		return getIconDescriptor();
	}

	public static ResourceDescriptor createDescriptor(ANode parent) {
		ResourceDescriptor rd = AMResource.createDescriptor(parent);
		rd.setWsType(ResourceDescriptor.TYPE_OLAP_XMLA_CONNECTION);

		rd.setResourceProperty(MROlapXmlaConnection.PROP_XMLA_URI, "uri");
		rd.setResourceProperty(MROlapXmlaConnection.PROP_XMLA_CATALOG,
				"catalog");
		rd.setResourceProperty(MROlapXmlaConnection.PROP_XMLA_DATASOURCE,
				"datasource");
		rd.setResourceProperty(MROlapXmlaConnection.PROP_XMLA_USERNAME,
				"username");
		rd.setResourceProperty(MROlapXmlaConnection.PROP_XMLA_PASSWORD, "");

		return rd;
	}

	@Override
	public String getJRSUrl() throws UnsupportedEncodingException {
		return "flow.html?_flowId=olapClientConnectionFlow&isEdit=true&selectedResource="
				+ URLEncoder.encode(getValue().getUriString(), "ISO-8859-1")
				+ "&ParentFolderUri="
				+ URLEncoder.encode(getValue().getParentFolder(), "ISO-8859-1");
	}
}
