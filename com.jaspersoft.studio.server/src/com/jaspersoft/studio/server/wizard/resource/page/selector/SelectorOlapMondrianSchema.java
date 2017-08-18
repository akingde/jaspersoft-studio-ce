/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.resource.page.selector;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.dto.resources.ResourceMediaType;
import com.jaspersoft.jasperserver.dto.resources.ClientFile.FileType;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.model.datasource.MRMondrianSchema;

public class SelectorOlapMondrianSchema extends ASelector {

	@Override
	protected ResourceDescriptor createLocal(AMResource res) {
		ResourceDescriptor rd = MRMondrianSchema.createDescriptor(res);
		rd.setName(MRMondrianSchema.getIconDescriptor().getTitle());
		rd.setLabel(rd.getName());
		return rd;
	}

	@Override
	protected boolean isResCompatible(AMResource r) {
		return r instanceof MRMondrianSchema;
	}

	protected ResourceDescriptor getResourceDescriptor(ResourceDescriptor ru) {
		for (Object obj : ru.getChildren()) {
			ResourceDescriptor r = (ResourceDescriptor) obj;
			ResourceDescriptor tmp = checkReference(r);
			if (tmp != null)
				r = tmp;
			String t = r.getWsType();
			if (r.getIsReference() && r.getReferenceType() != null)
				t = r.getReferenceType();
			if (t.equals(ResourceDescriptor.TYPE_MONDRIAN_SCHEMA))
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
