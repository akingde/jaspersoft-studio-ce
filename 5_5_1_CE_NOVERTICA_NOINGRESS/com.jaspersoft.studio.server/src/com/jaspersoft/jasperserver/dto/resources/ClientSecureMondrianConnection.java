/*
 * Copyright (C) 2005 - 2013 Jaspersoft Corporation. All rights reserved.
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
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.jasperserver.dto.resources;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * <p></p>
 *
 * @author Zakhar.Tomchenco
 * @version $Id: ClientSecureMondrianConnection.java 35226 2013-08-09 07:08:53Z inesterenko $
 */
@XmlRootElement(name = ResourceMediaType.SECURE_MONDRIAN_CONNECTION_CLIENT_TYPE)
public class ClientSecureMondrianConnection extends AbstractClientMondrianConnection<ClientSecureMondrianConnection> {
    private List<ClientReferenceableFile> accessGrants;

    @XmlElementWrapper(name = "accessGrantSchemas")
    @XmlElements({
            @XmlElement(name = "accessGrantSchemaReference", type = ClientReference.class),
            @XmlElement(name = "accessGrantSchema", type = ClientFile.class)
    })
    public List<ClientReferenceableFile> getAccessGrants() {
        return accessGrants;
    }

    public ClientSecureMondrianConnection setAccessGrants(List<ClientReferenceableFile> accessGrants) {
        this.accessGrants = accessGrants;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ClientSecureMondrianConnection that = (ClientSecureMondrianConnection) o;

        if (accessGrants != null ? !accessGrants.equals(that.accessGrants) : that.accessGrants != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (accessGrants != null ? accessGrants.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ClientSecureMondrianConnection{" +
                "accessGrants=" + accessGrants +
                ", schema=" + getSchema() +
                ", version=" + getVersion() +
                ", permissionMask=" + getPermissionMask() +
                ", uri='" + getUri() + '\'' +
                ", label='" + getLabel() + '\'' +
                '}';
    }
}
