/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.resource.page.selector;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.dto.resources.ResourceMediaType;
import com.jaspersoft.jasperserver.dto.resources.ClientFile.FileType;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.model.MJrxml;

public class SelectorJrxml2 extends ASelector {
	@Override
	protected ResourceDescriptor createLocal(AMResource res) {
		ResourceDescriptor rd = MJrxml.createDescriptor(res);
		rd.setName(MJrxml.getIconDescriptor().getTitle());
		rd.setLabel(rd.getName());
		return rd;
	}

	@Override
	protected void setupResource(ResourceDescriptor rd) {
		rd.setMainReport(true);
		rd.setName("main_jrxml");
		rd.setLabel("Main Jrxml");
	}

	@Override
	protected boolean isResCompatible(AMResource r) {
		return r instanceof MJrxml && !r.getValue().getParentFolder().endsWith("_files");
	}

	protected ResourceDescriptor getResourceDescriptor(ResourceDescriptor ru) {
		for (Object obj : ru.getChildren()) {
			ResourceDescriptor r = (ResourceDescriptor) obj;
			if (r.getWsType().equals(ResourceDescriptor.TYPE_JRXML) && r.isMainReport())
				return r;
		}
		return null;
	}

	@Override
	protected String[] getIncludeTypes() {
		boolean sv = res.getWsClient().getServerInfo().getVersion().compareTo("6.3.1") >= 0;
		return new String[] { sv ? FileType.jrxml.name() : ResourceMediaType.FILE_CLIENT_TYPE };
	}

	@Override
	protected String[] getExcludeTypes() {
		return null;
	}

}
