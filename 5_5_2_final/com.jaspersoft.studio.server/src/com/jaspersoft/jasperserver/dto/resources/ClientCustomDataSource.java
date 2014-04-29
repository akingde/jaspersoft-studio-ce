/*
* Copyright (C) 2005 - 2009 Jaspersoft Corporation. All rights  reserved.
* http://www.jaspersoft.com.
*
* Unless you have purchased  a commercial license agreement from Jaspersoft,
* the following license terms  apply:
*
* This program is free software: you can redistribute it and/or  modify
* it under the terms of the GNU Affero General Public License  as
* published by the Free Software Foundation, either version 3 of  the
* License, or (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU Affero  General Public License for more details.
*
* You should have received a copy of the GNU Affero General Public  License
* along with this program.&nbsp; If not, see <http://www.gnu.org/licenses/>.
*/
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
