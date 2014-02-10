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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p></p>
 *
 * @author Yaroslav.Kovalchyk
 * @version $Id: ClientReportUnitResource.java 35226 2013-08-09 07:08:53Z inesterenko $
 */
@XmlRootElement(name = "reportUnitFile")
public class ClientReportUnitResource {
    private String name;
    private ClientReferenceableFile file;

    public ClientReportUnitResource() {
    }

    public ClientReportUnitResource(String name, ClientReferenceableFile file) {
        this.name = name;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public ClientReportUnitResource setName(String name) {
        this.name = name;
        return this;
    }

    @XmlElements({
            @XmlElement(name = "fileReference", type = ClientReference.class),
            @XmlElement(name = "fileResource", type = ClientFile.class)
    })
    public ClientReferenceableFile getFile() {
        return file;
    }

    public ClientReportUnitResource setFile(ClientReferenceableFile file) {
        this.file = file;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientReportUnitResource that = (ClientReportUnitResource) o;

        if (file != null ? !file.equals(that.file) : that.file != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (file != null ? file.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ClientReportUnitResource{" +
                "name='" + name + '\'' +
                ", file=" + file +
                '}';
    }
}
