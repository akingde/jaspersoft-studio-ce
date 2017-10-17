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
import com.jaspersoft.studio.server.model.AMResource;

/**
 * marker for datasources
 * 
 * @author veaceslavchicu
 * 
 */
public abstract class AMRDatasource extends AMResource {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public AMRDatasource(ANode parent, ResourceDescriptor rd, int index) {
		super(parent, rd, index);
	}

	@Override
	public String getJRSUrl() throws UnsupportedEncodingException {
		return "flow.html?_flowId=addDataSourceFlow&resource="
				+ URLEncoder.encode(getValue().getUriString(), "ISO-8859-1")
				+ "&ParentFolderUri="
				+ URLEncoder.encode(getValue().getParentFolder(), "ISO-8859-1");
	}
}
