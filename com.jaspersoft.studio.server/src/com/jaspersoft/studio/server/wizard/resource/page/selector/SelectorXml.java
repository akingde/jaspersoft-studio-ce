/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.resource.page.selector;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.dto.resources.ResourceMediaType;
import com.jaspersoft.jasperserver.dto.resources.ClientFile.FileType;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.model.MXmlFile;

public class SelectorXml extends ASelector {
	@Override
	protected ResourceDescriptor createLocal(AMResource res) {
		ResourceDescriptor rd = MXmlFile.createDescriptor(res);
		rd.setName(MXmlFile.getIconDescriptor().getTitle());
		rd.setLabel(rd.getName());
		return rd;
	}

	@Override
	protected boolean isResCompatible(AMResource r) {
		return r instanceof MXmlFile;
	}

	protected ResourceDescriptor getResourceDescriptor(ResourceDescriptor ru) {
		for (Object obj : ru.getChildren()) {
			ResourceDescriptor r = (ResourceDescriptor) obj;
			if (r.getWsType().equals(ResourceDescriptor.TYPE_XML_FILE) && isGoodResourceDescriptor(ru))
				return r;
		}
		return null;
	}

	protected boolean isGoodResourceDescriptor(ResourceDescriptor ru) {
		return true;
	}

	@Override
	protected String[] getIncludeTypes() {
		boolean sv = res.getWsClient().getServerInfo().getVersion().compareTo("6.3.1") >= 0;
		return new String[] { sv ? FileType.xml.name() : ResourceMediaType.FILE_CLIENT_TYPE };
	}

	@Override
	protected String[] getExcludeTypes() {
		return null;
	}
}
