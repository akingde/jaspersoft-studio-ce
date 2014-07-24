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
 * @version $Id: ClientXmlaConnection.java 35226 2013-08-09 07:08:53Z inesterenko $
 */
@XmlRootElement(name = ResourceMediaType.XMLA_CONNECTION_CLIENT_TYPE)
public class ClientXmlaConnection extends ClientResource<ClientXmlaConnection> implements ClientReferenciableOlapConnection {
    private String url;
    private String dataSource;
    private String catalog;
    private String username;
    private String password;

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ClientXmlaConnection that = (ClientXmlaConnection) o;

        if (catalog != null ? !catalog.equals(that.catalog) : that.catalog != null) return false;
        if (dataSource != null ? !dataSource.equals(that.dataSource) : that.dataSource != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (dataSource != null ? dataSource.hashCode() : 0);
        result = 31 * result + (catalog != null ? catalog.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    public ClientXmlaConnection setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getDataSource() {
        return dataSource;
    }

    public ClientXmlaConnection setDataSource(String dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    public String getCatalog() {
        return catalog;
    }

    public ClientXmlaConnection setCatalog(String catalog) {
        this.catalog = catalog;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public ClientXmlaConnection setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public ClientXmlaConnection setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "ClientXmlaConnection{" +
                "url='" + url + '\'' +
                ", dataSource='" + dataSource + '\'' +
                ", catalog='" + catalog + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", version=" + getVersion() +
                ", permissionMask=" + getPermissionMask() +
                ", uri='" + getUri() + '\'' +
                ", label='" + getLabel() + '\'' +
                '}';
    }
}
