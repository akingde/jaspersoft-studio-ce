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
