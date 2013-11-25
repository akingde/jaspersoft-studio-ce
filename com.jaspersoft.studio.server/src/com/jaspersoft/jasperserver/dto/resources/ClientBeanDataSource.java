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

/**
 * <p></p>
 *
 * @author Yaroslav.Kovalchyk
 * @version $Id: ClientBeanDataSource.java 35226 2013-08-09 07:08:53Z inesterenko $
 */
@XmlRootElement(name = ResourceMediaType.BEAN_DATA_SOURCE_CLIENT_TYPE)
public class ClientBeanDataSource extends ClientResource<ClientBeanDataSource> implements ClientReferenceableDataSource {
    private String beanName;
    private String beanMethod;

    public String getBeanName() {
        return beanName;
    }

    public ClientBeanDataSource setBeanName(String beanName) {
        this.beanName = beanName;
        return this;
    }

    public String getBeanMethod() {
        return beanMethod;
    }

    public ClientBeanDataSource setBeanMethod(String beanMethod) {
        this.beanMethod = beanMethod;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ClientBeanDataSource that = (ClientBeanDataSource) o;

        if (beanMethod != null ? !beanMethod.equals(that.beanMethod) : that.beanMethod != null) return false;
        if (beanName != null ? !beanName.equals(that.beanName) : that.beanName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (beanName != null ? beanName.hashCode() : 0);
        result = 31 * result + (beanMethod != null ? beanMethod.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ClientBeanDataSource{" +
                "beanName='" + beanName + '\'' +
                ", beanMethod='" + beanMethod + '\'' +
                ", version=" + getVersion() +
                ", permissionMask=" + getPermissionMask() +
                ", uri='" + getUri() + '\'' +
                ", label='" + getLabel() + '\'' +
                '}';
    }
}
