/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.resource.page.selector;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.dto.resources.ResourceMediaType;
import com.jaspersoft.jasperserver.dto.resources.ClientFile.FileType;
import com.jaspersoft.studio.server.model.MRAccessGrantSchema;
import com.jaspersoft.studio.server.model.AMResource;

public class SelectorAccessGrantSchema extends ASelector {

	@Override
	protected ResourceDescriptor createLocal(AMResource res) {
		ResourceDescriptor rd = MRAccessGrantSchema.createDescriptor(res);
		rd.setName(MRAccessGrantSchema.getIconDescriptor().getTitle());
		rd.setLabel(rd.getName());
		return rd;
	}

	@Override
	protected boolean isResCompatible(AMResource r) {
		return r instanceof MRAccessGrantSchema;
	}

	protected ResourceDescriptor getResourceDescriptor(ResourceDescriptor ru) {
		for (Object obj : ru.getChildren()) {
			ResourceDescriptor r = (ResourceDescriptor) obj;
			ResourceDescriptor tmp = checkReference(r);
			if (tmp != null)
				r = tmp;
			if (r.getIsReference() && r.getReferenceType() != null
					&& r.getReferenceType().equals(ResourceDescriptor.TYPE_ACCESS_GRANT_SCHEMA))
				return r;
			if (r.getWsType().equals(ResourceDescriptor.TYPE_ACCESS_GRANT_SCHEMA))
				return r;
		}
		return null;
	}

	@Override
	protected String[] getIncludeTypes() {
		boolean sv = res.getWsClient().getServerInfo().getVersion().compareTo("5.5") >= 0;
		return new String[] { sv ? FileType.xml.name() : ResourceMediaType.FILE_CLIENT_TYPE };
	}

	@Override
	protected String[] getExcludeTypes() {
		return null;
	}

}
