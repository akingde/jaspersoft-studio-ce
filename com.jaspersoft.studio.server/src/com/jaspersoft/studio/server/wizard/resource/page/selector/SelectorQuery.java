/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.resource.page.selector;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.dto.resources.ResourceMediaType;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.model.MRQuery;

import net.sf.jasperreports.eclipse.util.Misc;

public class SelectorQuery extends ASelector {

	@Override
	protected ResourceDescriptor createLocal(AMResource res) {
		ResourceDescriptor rd = MRQuery.createDescriptor(res);
		rd.setName(Messages.SelectorQuery_0);
		rd.setLabel(rd.getName());
		return rd;
	}

	@Override
	protected boolean isResCompatible(AMResource r) {
		return r instanceof MRQuery;
	}

	private ResourceDescriptor getQuery(ResourceDescriptor ru) {
		if (ru != null)
			for (Object obj : ru.getChildren()) {
				ResourceDescriptor r = (ResourceDescriptor) obj;
				if (r == null)
					continue;
				ResourceDescriptor tmp = checkReference(r);
				if (tmp != null)
					r = tmp;
				if (r.getIsReference() && r.getReferenceType() != null
						&& r.getReferenceType().equals(ResourceDescriptor.TYPE_QUERY))
					return r;
				if (r.getWsType().equals(ResourceDescriptor.TYPE_QUERY))
					return r;
			}
		return null;
	}

	@Override
	protected ResourceDescriptor getResourceDescriptor(ResourceDescriptor ru) {
		return getQuery(ru);
	}

	@Override
	public boolean isPageComplete() {
		boolean b = super.isPageComplete();
		if (b) {
			ResourceDescriptor rd = resRD;
			String[] qvc = rd.getQueryVisibleColumns();
			b = qvc != null && qvc.length > 0 && !Misc.isNullOrEmpty(rd.getQueryValueColumn());
		}
		return b;
	}

	@Override
	protected String[] getIncludeTypes() {
		return new String[] { ResourceMediaType.QUERY_CLIENT_TYPE };
	}

	@Override
	protected String[] getExcludeTypes() {
		return null;
	}

}
