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
