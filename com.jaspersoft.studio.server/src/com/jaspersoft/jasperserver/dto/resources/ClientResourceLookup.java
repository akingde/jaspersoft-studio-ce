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

package com.jaspersoft.jasperserver.dto.resources;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p></p>
 *
 * @author Yaroslav.Kovalchyk
 * @version $Id: ClientResourceLookup.java 35226 2013-08-09 07:08:53Z inesterenko $
 */
@XmlRootElement(name = ResourceMediaType.RESOURCE_LOOKUP_CLIENT_TYPE)
public class ClientResourceLookup extends ClientResource<ClientResourceLookup> {
    private String resourceType;


    public String getResourceType() {
        return resourceType;
    }

    public ClientResourceLookup setResourceType(String resourceType) {
        this.resourceType = resourceType;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ClientResourceLookup that = (ClientResourceLookup) o;

        if (resourceType != null ? !resourceType.equals(that.resourceType) : that.resourceType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (resourceType != null ? resourceType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ClientResourceLookup{" +
                "version=" + getVersion() +
                ", permissionMask=" + getPermissionMask() +
                ", creationDate='" + getCreationDate() + '\'' +
                ", updateDate='" + getUpdateDate() + '\'' +
                ", label='" + getLabel() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", uri='" + getUri() + '\'' +
                ", resourceType='" + resourceType + '\'' +
                '}';
    }
}
