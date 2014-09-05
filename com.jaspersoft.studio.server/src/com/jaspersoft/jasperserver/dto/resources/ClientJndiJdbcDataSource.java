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
 * @version $Id: ClientJndiJdbcDataSource.java 35226 2013-08-09 07:08:53Z inesterenko $
 */
@XmlRootElement(name = ResourceMediaType.JNDI_JDBC_DATA_SOURCE_CLIENT_TYPE)
public class ClientJndiJdbcDataSource extends ClientResource<ClientJndiJdbcDataSource> implements ClientReferenceableDataSource {

    private String jndiName;
    private String timezone;

    public String getJndiName() {
        return jndiName;
    }

    public ClientJndiJdbcDataSource setJndiName(String jndiName) {
        this.jndiName = jndiName;
        return this;
    }

    public String getTimezone() {
        return timezone;
    }

    public ClientJndiJdbcDataSource setTimezone(String timezone) {
        this.timezone = timezone;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ClientJndiJdbcDataSource that = (ClientJndiJdbcDataSource) o;

        if (jndiName != null ? !jndiName.equals(that.jndiName) : that.jndiName != null) return false;
        if (timezone != null ? !timezone.equals(that.timezone) : that.timezone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (jndiName != null ? jndiName.hashCode() : 0);
        result = 31 * result + (timezone != null ? timezone.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ClientJndiJdbcDataSource{" +
                "jndiName='" + jndiName + '\'' +
                ", timezone='" + timezone + '\'' +
                ", version=" + getVersion() +
                ", permissionMask=" + getPermissionMask() +
                ", uri='" + getUri() + '\'' +
                ", label='" + getLabel() + '\'' +
                '}';
    }
}
