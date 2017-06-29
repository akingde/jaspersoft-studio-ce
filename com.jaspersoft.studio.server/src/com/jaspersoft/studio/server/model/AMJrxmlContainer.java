/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.model;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;

public abstract class AMJrxmlContainer extends AFileResource {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public AMJrxmlContainer(ANode parent, ResourceDescriptor rd, int index) {
		super(parent, rd, index);
	}

}
