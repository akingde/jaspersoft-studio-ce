/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.jasperserver.dto;

import javax.xml.bind.annotation.XmlRootElement;

import com.jaspersoft.jasperserver.dto.resources.ClientResource;

@XmlRootElement(name = "unknown")
public class ClientUnknown extends ClientResource<ClientUnknown> {

	@Override
	public String toString() {
		return "ClientUnknown{" + "version=" + getVersion() + ", permissionMask=" + getPermissionMask() + ", uri='" + getUri() + '\'' + ", label='" + getLabel() + '\'' + '}';
	}
}
