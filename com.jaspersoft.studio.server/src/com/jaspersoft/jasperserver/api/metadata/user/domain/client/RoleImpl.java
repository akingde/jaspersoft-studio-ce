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

package com.jaspersoft.jasperserver.api.metadata.user.domain.client;

import com.jaspersoft.jasperserver.api.metadata.common.domain.InternalURI;
import com.jaspersoft.jasperserver.api.metadata.user.domain.Role;
import com.jaspersoft.jasperserver.api.metadata.user.domain.User;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author swood
 *
 */
@XmlRootElement(name = "role")
public class RoleImpl implements Role, InternalURI, Serializable {
    private String roleName;
    private Set userSet = new HashSet();
    private boolean externallyDefined = false;
    private List attributes = null;
    private String tenantId;

    public RoleImpl(){}

    @XmlElement(name = "roleName")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String newRoleName) {
//		if (newRoleName == null || newRoleName.trim().length() == 0) {
//			throw new RuntimeException("No role name");
//		}
        roleName = newRoleName;
    }

    /* (non-Javadoc)
      * @see com.jaspersoft.jasperserver.api.metadata.user.domain.Role#getUsers()
      */
    @XmlTransient
    public Set getUsers() {
        return userSet;
    }

    /* (non-Javadoc)
      * @see com.jaspersoft.jasperserver.api.metadata.user.domain.Role#setUsers(java.util.Set)
      */
    public void setUsers(Set userSet) {
        this.userSet = userSet;
    }

    /* (non-Javadoc)
      * @see com.jaspersoft.jasperserver.api.metadata.user.domain.Role#isExternallyDefined()
      */
    @XmlElement(name = "externallyDefined")
    public boolean isExternallyDefined() {
        return externallyDefined;
    }

    /**
     * @param externallyDefined The externallyDefined to set.
     */
    public void setExternallyDefined(boolean externallyDefined) {
        this.externallyDefined = externallyDefined;
    }

    /* (non-Javadoc)
      * @see com.jaspersoft.jasperserver.api.common.domain.AttributedObject#getAttributes()
      */
    @XmlTransient
    public List getAttributes() {
        return attributes;
    }

    public void setAttributes(List attrs) {
        attributes = attrs;
    }


    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        builder.append("roleName", getRoleName());
        if (getTenantId() != null) {
            // only include tenant if not null
            builder.append("tenantId", getTenantId());
        }
        return builder.toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof RoleImpl) ) return false;
        RoleImpl castOther = (RoleImpl) other;
        return new EqualsBuilder()
                .append(this.getRoleName(), castOther.getRoleName())
                .append(this.getTenantId(), castOther.getTenantId())
                .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(getRoleName())
                .append(getTenantId())
                .toHashCode();
    }

    public void addUser(User aUser)
    {
        userSet.add(aUser);
    }

    public void removeUser(User aUser)
    {
        userSet.remove(aUser);
    }

    @XmlElement(name = "tenantId")
    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    @XmlTransient
    public String getURI() {
        StringBuilder res = new StringBuilder(URI_PROTOCOL).append(":");
        if (this.getTenantId() != null) {
            res.append("/").append(this.getTenantId());
        }
        return res.append("/").append(this.getRoleName()).toString();
    }

    @XmlTransient
    public String getPath() {
        StringBuilder res = new StringBuilder();
        if (this.getTenantId() != null) {
            res.append("/").append(this.getTenantId());
        }
        return res.append("/").append(this.getRoleName()).toString();
    }

    @XmlTransient
    public String getProtocol() {
        return URI_PROTOCOL;
    }

    @XmlTransient
    public String getParentURI() {
        return null;
    }

    @XmlTransient
    public String getParentPath() {
        return null;
    }
}
