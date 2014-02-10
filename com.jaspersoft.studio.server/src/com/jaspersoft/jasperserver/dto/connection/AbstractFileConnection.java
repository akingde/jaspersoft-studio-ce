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
package com.jaspersoft.jasperserver.dto.connection;

import com.jaspersoft.jasperserver.dto.common.ResourceLocation;
import com.jaspersoft.jasperserver.dto.resources.ClientReference;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

/**
 * <p></p>
 *
 * @author yaroslav.kovalchyk
 * @version $Id: AbstractFileConnection.java 39224 2013-11-01 11:27:34Z ykovalchyk $
 */
public class AbstractFileConnection<BuilderType extends AbstractFileConnection> {
    @XmlElement(name = "hasHeaderLine")
    private boolean hasHeaderLine;
    private ResourceLocation location;

    public AbstractFileConnection() {
    }

    public AbstractFileConnection(AbstractFileConnection source) {
        hasHeaderLine = source.hasHeaderLine;
        if (source.location instanceof ClientReference){
            location = new ClientReference(((ClientReference) source.location).getUri());
        }
        if (source.location instanceof FtpConnection) location = new FtpConnection((FtpConnection) source.location);
        if (source.location instanceof LfsConnection) location = new LfsConnection((LfsConnection) source.location);
    }

    public boolean hasHeaderLine() {
        return hasHeaderLine;
    }

    // unchecked cast to BuilderType safety assured by the rule of usage BuilderType generic parameter.
    @SuppressWarnings("unchecked")
    public BuilderType setHasHeaderLine(boolean hasHeaderLine) {
        this.hasHeaderLine = hasHeaderLine;
        return (BuilderType) this;
    }

    @XmlElements({
            @XmlElement(name = "repositoryLocation", type = ClientReference.class),
            @XmlElement(name = "ftpLocation", type = FtpConnection.class),
            @XmlElement(name = "lfcLocation", type = LfsConnection.class)
    })
    public ResourceLocation getLocation() {
        return location;
    }

    // unchecked cast to BuilderType safety assured by the rule of usage BuilderType generic parameter.
    @SuppressWarnings("unchecked")
    public BuilderType setLocation(ResourceLocation location) {
        this.location = location;
        return (BuilderType) this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractFileConnection that = (AbstractFileConnection) o;

        if (hasHeaderLine != that.hasHeaderLine) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (hasHeaderLine ? 1 : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AbstractFileConnection{" +
                "hasHeaderLine=" + hasHeaderLine +
                ", location=" + location +
                "} " + super.toString();
    }
}
