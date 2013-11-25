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

/**
 * <p></p>
 *
 * @param <BuilderType> - concrete type of builder, usually it is concrete resource type like this:
 *                     public class SomeConcreteClientResource extends ClientResource<SomeConcreteClientResource>
 *                     This parameter is needed for convenient usage of client DTO as builders.
 *                     Even superclass' setters returns correct type of resource.
 *
 * @author Yaroslav.Kovalchyk
 * @version $Id: ClientResource.java 35226 2013-08-09 07:08:53Z inesterenko $
 */
public abstract class ClientResource<BuilderType extends ClientResource<BuilderType>> implements ClientUriHolder {
    private Integer version;
    private Integer permissionMask;
    private String creationDate;
    private String updateDate;
    private String label;
    private String description;
    private String uri;

    public Integer getVersion() {
        return version;
    }

    // unchecked cast to BuilderType safety assured by the rule of usage BuilderType generic parameter.
    @SuppressWarnings("unchecked")
    public BuilderType setVersion(Integer version) {
        this.version = version;
        return (BuilderType) this;
    }

    public Integer getPermissionMask() {
        return permissionMask;
    }

    // unchecked cast to BuilderType safety assured by the rule of usage BuilderType generic parameter.
    @SuppressWarnings("unchecked")
    public BuilderType setPermissionMask(Integer permissionMask) {
        this.permissionMask = permissionMask;
        return (BuilderType) this;
    }

    public String getCreationDate() {
        return creationDate;
    }

    // unchecked cast to BuilderType safety assured by the rule of usage BuilderType generic parameter.
    @SuppressWarnings("unchecked")
    public BuilderType setCreationDate(String creationDate) {
        this.creationDate = creationDate;
        return (BuilderType) this;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    // unchecked cast to BuilderType safety assured by the rule of usage BuilderType generic parameter.
    @SuppressWarnings("unchecked")
    public BuilderType setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
        return (BuilderType) this;
    }

    public String getLabel() {
        return label;
    }

    // unchecked cast to BuilderType safety assured by the rule of usage BuilderType generic parameter.
    @SuppressWarnings("unchecked")
    public BuilderType setLabel(String label) {
        this.label = label;
        return (BuilderType) this;
    }

    public String getDescription() {
        return description;
    }

    // unchecked cast to BuilderType safety assured by the rule of usage BuilderType generic parameter.
    @SuppressWarnings("unchecked")
    public BuilderType setDescription(String description) {
        this.description = description;
        return (BuilderType) this;
    }

    public String getUri() {
        return uri;
    }

    // unchecked cast to BuilderType safety assured by the rule of usage BuilderType generic parameter.
    @SuppressWarnings("unchecked")
    public BuilderType setUri(String uri) {
        this.uri = uri;
        return (BuilderType) this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientResource that = (ClientResource) o;

        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (label != null ? !label.equals(that.label) : that.label != null) return false;
        if (permissionMask != null ? !permissionMask.equals(that.permissionMask) : that.permissionMask != null)
            return false;
        if (updateDate != null ? !updateDate.equals(that.updateDate) : that.updateDate != null) return false;
        if (uri != null ? !uri.equals(that.uri) : that.uri != null) return false;
        if (version != null ? !version.equals(that.version) : that.version != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = version != null ? version.hashCode() : 0;
        result = 31 * result + (permissionMask != null ? permissionMask.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (updateDate != null ? updateDate.hashCode() : 0);
        result = 31 * result + (label != null ? label.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (uri != null ? uri.hashCode() : 0);
        return result;
    }
}
