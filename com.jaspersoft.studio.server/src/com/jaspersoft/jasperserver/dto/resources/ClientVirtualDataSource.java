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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.List;

/**
 * <p></p>
 *
 * @author Yaroslav.Kovalchyk
 * @version $Id: ClientVirtualDataSource.java 35226 2013-08-09 07:08:53Z inesterenko $
 */
@XmlRootElement(name = ResourceMediaType.VIRTUAL_DATA_SOURCE_CLIENT_TYPE)
public class ClientVirtualDataSource extends ClientResource<ClientVirtualDataSource> implements ClientReferenceableDataSource {
    private List<ClientSubDataSourceReference> subDataSources;

    @XmlElementWrapper(name = "subDataSources")
    @XmlElement(name = "subDataSource")
    public List<ClientSubDataSourceReference> getSubDataSources() {
        return subDataSources;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ClientVirtualDataSource that = (ClientVirtualDataSource) o;

        if (subDataSources != null ? !new HashSet(subDataSources).equals(new HashSet(that.subDataSources)) : that.subDataSources != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (subDataSources != null ? new HashSet(subDataSources).hashCode() : 0);
        return result;
    }

    public ClientVirtualDataSource setSubDataSources(List<ClientSubDataSourceReference> subDataSources) {
        this.subDataSources = subDataSources;
        return this;
    }

    @Override
    public String toString() {
        return "ClientVirtualDataSource{" +
                "subDataSources=" + subDataSources +
                ", version=" + getVersion() +
                ", permissionMask=" + getPermissionMask() +
                ", uri='" + getUri() + '\'' +
                ", label='" + getLabel() + '\'' +
                '}';
    }
}
