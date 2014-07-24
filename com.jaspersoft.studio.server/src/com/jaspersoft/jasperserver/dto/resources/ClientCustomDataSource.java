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
import java.util.HashSet;
import java.util.List;

/**
 * <p></p>
 *
 * @author Yaroslav.Kovalchyk
 * @version $Id: ClientCustomDataSource.java 35226 2013-08-09 07:08:53Z inesterenko $
 */
@XmlRootElement(name = ResourceMediaType.CUSTOM_DATA_SOURCE_CLIENT_TYPE)
public class ClientCustomDataSource extends ClientResource<ClientCustomDataSource> implements ClientReferenceableDataSource {
    private String serviceClass;
    private String dataSourceName;
    private List<ClientProperty> properties;

    public String getDataSourceName() {
        return dataSourceName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ClientCustomDataSource that = (ClientCustomDataSource) o;

        if (dataSourceName != null ? !dataSourceName.equals(that.dataSourceName) : that.dataSourceName != null)
            return false;
        if (properties != null ? !new HashSet(properties).equals(new HashSet(that.properties)) : that.properties != null) return false;
        if (serviceClass != null ? !serviceClass.equals(that.serviceClass) : that.serviceClass != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (serviceClass != null ? serviceClass.hashCode() : 0);
        result = 31 * result + (dataSourceName != null ? dataSourceName.hashCode() : 0);
        result = 31 * result + (properties != null ? new HashSet(properties).hashCode() : 0);
        return result;
    }

    public ClientCustomDataSource setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
        return this;
    }

    public String getServiceClass() {
        return serviceClass;
    }

    public ClientCustomDataSource setServiceClass(String serviceClass) {
        this.serviceClass = serviceClass;
        return this;
    }

    public List<ClientProperty> getProperties() {
        return properties;
    }

    public ClientCustomDataSource setProperties(List<ClientProperty> properties) {
        this.properties = properties;
        return this;
    }

    @Override
    public String toString() {
        return "ClientCustomDataSource{" +
                "serviceClass='" + serviceClass + '\'' +
                ", dataSourceName='" + dataSourceName + '\'' +
                ", properties=" + properties +
                ", version=" + getVersion() +
                ", permissionMask=" + getPermissionMask() +
                ", uri='" + getUri() + '\'' +
                ", label='" + getLabel() + '\'' +
                '}';
    }
}
