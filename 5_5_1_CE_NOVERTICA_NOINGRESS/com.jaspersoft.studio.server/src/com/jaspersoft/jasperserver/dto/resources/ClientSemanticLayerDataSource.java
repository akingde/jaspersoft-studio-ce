/*
* Copyright (C) 2005 - 2013 Jaspersoft Corporation. All rights  reserved.
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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * <p>SemanticLayerDataSource belongs to PRO codebase, but ClientSemanticLayerDataSource should be placed to CE because of usage in AbstractClientDataSourceHolder</p>
 *
 * @author Yaroslav.Kovalchyk
 * @version $Id: ClientSemanticLayerDataSource.java 27624 2013-03-01 09:55:15Z ykovalchyk $
 */
@XmlRootElement(name = ResourceMediaType.SEMANTIC_LAYER_DATA_SOURCE_CLIENT_TYPE)
public class ClientSemanticLayerDataSource extends AbstractClientDataSourceHolder<ClientSemanticLayerDataSource> implements ClientReferenceableDataSource {
    private ClientReferenceableFile schema;
    private ClientReferenceableFile securityFile;
    private List<ClientBundle> bundles;

    @XmlElements({
            @XmlElement(name = "schemaFileReference", type = ClientReference.class),
            @XmlElement(name = "schemaFile", type = ClientFile.class)
    })
    public ClientReferenceableFile getSchema() {
        return schema;
    }

    public ClientSemanticLayerDataSource setSchema(ClientReferenceableFile schema) {
        this.schema = schema;
        return this;
    }

    @XmlElements({
            @XmlElement(name = "securityFileReference", type = ClientReference.class),
            @XmlElement(name = "securityFile", type = ClientFile.class)
    })
    public ClientReferenceableFile getSecurityFile() {
        return securityFile;
    }

    public ClientSemanticLayerDataSource setSecurityFile(ClientReferenceableFile securityFile) {
        this.securityFile = securityFile;
        return this;
    }

    @XmlElementWrapper(name = "bundles")
    @XmlElement(name = "bundle")
    public List<ClientBundle> getBundles() {
        return bundles;
    }

    public ClientSemanticLayerDataSource setBundles(List<ClientBundle> bundles) {
        this.bundles = bundles;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ClientSemanticLayerDataSource that = (ClientSemanticLayerDataSource) o;

        if (bundles != null ? !bundles.equals(that.bundles) : that.bundles != null) return false;
        if (schema != null ? !schema.equals(that.schema) : that.schema != null) return false;
        if (securityFile != null ? !securityFile.equals(that.securityFile) : that.securityFile != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (schema != null ? schema.hashCode() : 0);
        result = 31 * result + (securityFile != null ? securityFile.hashCode() : 0);
        result = 31 * result + (bundles != null ? bundles.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ClientSemanticLayerDataSource{" +
                "schema=" + (getSchema() == null ? "null" : getSchema().getUri()) +
                ", version=" + getVersion() +
                ", permissionMask=" + getPermissionMask() +
                ", uri='" + getUri() + '\'' +
                ", label='" + getLabel() + '\'' +
                '}';
    }
}
