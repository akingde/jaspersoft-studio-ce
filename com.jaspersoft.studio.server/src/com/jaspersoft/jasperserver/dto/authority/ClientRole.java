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
package com.jaspersoft.jasperserver.dto.authority;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p></p>
 *
 * @author Zakhar.Tomchenco
 * @version $Id$
 */
@XmlRootElement(name = "role")
public class ClientRole {
    private String name;
    private boolean externallyDefined = false;
    private String tenantId;

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public ClientRole setName(String name) {
        this.name = name;
        return this;
    }

    @XmlElement(name = "externallyDefined")
    public boolean isExternallyDefined() {
        return externallyDefined;
    }

    public ClientRole setExternallyDefined(boolean externallyDefined) {
        this.externallyDefined = externallyDefined;
        return this;
    }

    @XmlElement(name = "tenantId")
    public String getTenantId() {
        return tenantId;
    }

    public ClientRole setTenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientRole that = (ClientRole) o;

        if (externallyDefined != that.externallyDefined) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (tenantId != null ? !tenantId.equals(that.tenantId) : that.tenantId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (externallyDefined ? 1 : 0);
        result = 31 * result + (tenantId != null ? tenantId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ClientRole{" +
                "name='" + name + '\'' +
                ", externallyDefined=" + externallyDefined +
                ", tenantId='" + tenantId + '\'' +
                '}';
    }
}
