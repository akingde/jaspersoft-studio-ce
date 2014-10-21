package com.jaspersoft.studio.server.wizard.resource.page.selector;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.dto.resources.ResourceMediaType;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.model.MResourceBundle;
import com.jaspersoft.studio.server.wizard.resource.page.selector.ASelector;

public class ResourceBundleSelector extends ASelector {

	@Override
	protected String[] getIncludeTypes() {
		return new String[] { ResourceMediaType.FILE_CLIENT_TYPE };
	}

	@Override
	protected String[] getExcludeTypes() {
		return null;
	}

	@Override
	protected boolean isResCompatible(MResource r) {
		return r instanceof MResourceBundle;
	}

	@Override
	protected ResourceDescriptor createLocal(MResource res) {
		ResourceDescriptor rd = MResourceBundle.createDescriptor(res);
		rd.setName(MResourceBundle.getIconDescriptor().getTitle());
		rd.setLabel(rd.getName());
		return rd;
	}

	@Override
	protected ResourceDescriptor getResourceDescriptor(ResourceDescriptor ru) {
		for (Object obj : ru.getChildren()) {
			ResourceDescriptor r = (ResourceDescriptor) obj;
			if (r.getWsType().equals(ResourceDescriptor.TYPE_RESOURCE_BUNDLE))
				return r;
		}
		return null;
	}

}
