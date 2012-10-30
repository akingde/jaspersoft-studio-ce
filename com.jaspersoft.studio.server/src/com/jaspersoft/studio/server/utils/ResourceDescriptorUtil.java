package com.jaspersoft.studio.server.utils;

import java.util.List;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceProperty;

public class ResourceDescriptorUtil {
	public static ResourceProperty getProperty(String name,
			List<ResourceProperty> props) {
		for (ResourceProperty rp : props) {
			if (rp.getName().equals(name)) //$NON-NLS-1$
				return rp;
		}
		return null;
	}
}
