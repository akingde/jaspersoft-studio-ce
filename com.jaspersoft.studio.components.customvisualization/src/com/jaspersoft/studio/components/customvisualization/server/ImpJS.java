/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.server;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.model.MContentResource;
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
