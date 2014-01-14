/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.resource.page.selector;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.dto.resources.ResourceMediaType;
import com.jaspersoft.studio.server.model.MContentResource;
import com.jaspersoft.studio.server.model.MResource;

public class SelectorFile extends ASelector {

	@Override
	protected ResourceDescriptor createLocal(MResource res) {
		ResourceDescriptor rd = MContentResource.createDescriptor(res);
		rd.setName(MContentResource.getIconDescriptor().getTitle());
		rd.setLabel(rd.getName());
		return rd;
	}

	@Override
	protected boolean isResCompatible(MResource r) {
		return r instanceof MContentResource;
	}

	protected ResourceDescriptor getResourceDescriptor(ResourceDescriptor ru) {
		for (Object obj : ru.getChildren()) {
			ResourceDescriptor r = (ResourceDescriptor) obj;
			if (r.getWsType().equals(ResourceDescriptor.TYPE_CONTENT_RESOURCE) || r.getWsType().equals(ResourceDescriptor.TYPE_REFERENCE))
				return r;
		}
		return null;
	}

	@Override
	protected String[] getIncludeTypes() {
		return new String[] { ResourceMediaType.FILE_CLIENT_TYPE };
	}

	@Override
	protected String[] getExcludeTypes() {
		return null;
	}

}
