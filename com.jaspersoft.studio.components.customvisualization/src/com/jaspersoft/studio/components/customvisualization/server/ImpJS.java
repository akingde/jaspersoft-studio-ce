/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.server;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.model.MXmlFile;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ImpJS extends AImpResource {
	public ImpJS(JasperReportsConfiguration jrConfig) {
		super(jrConfig);
	}

	protected ResourceDescriptor createResource(MReportUnit mrunit) {
		return MXmlFile.createDescriptor(mrunit);
	}

}
