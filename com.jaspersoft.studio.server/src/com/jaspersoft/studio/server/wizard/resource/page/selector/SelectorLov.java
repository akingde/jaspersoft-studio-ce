/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.resource.page.selector;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.dto.resources.ResourceMediaType;
import com.jaspersoft.studio.server.model.MListOfValues;
import com.jaspersoft.studio.server.model.AMResource;

public class SelectorLov extends ASelector {

	@Override
	protected ResourceDescriptor createLocal(AMResource res) {
		ResourceDescriptor rd = MListOfValues.createDescriptor(res);
		rd.setName("ListOfValues");
		rd.setLabel(rd.getName());
		return rd;
	}

	@Override
	protected boolean isResCompatible(AMResource r) {
		return r instanceof MListOfValues;
	}

	@Override
	protected ResourceDescriptor getResourceDescriptor(ResourceDescriptor ru) {
		for (Object obj : ru.getChildren()) {
			ResourceDescriptor r = (ResourceDescriptor) obj;
			ResourceDescriptor tmp = checkReference(r);
			if (tmp != null)
				r = tmp;
			if (r.getWsType().equals(ResourceDescriptor.TYPE_LOV))
				return r;
		}
		return null;
	}

	@Override
	protected String[] getIncludeTypes() {
		return new String[] { ResourceMediaType.LIST_OF_VALUES_CLIENT_TYPE };
	}

	@Override
	protected String[] getExcludeTypes() {
		return null;
	}

}
