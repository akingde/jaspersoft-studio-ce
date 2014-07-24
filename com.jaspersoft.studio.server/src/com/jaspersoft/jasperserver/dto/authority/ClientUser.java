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
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Date;
import java.util.Set;

/**
 * @author: Zakhar.Tomchenco
 */

@XmlRootElement(name = "user")
public class ClientUser {
    private Set<ClientRole> roleSet;
    private String fullName;
    private String password;
    private String emailAddress;
    private Boolean externallyDefined;
    private Boolean enabled;
    private Date previousPasswordChangeTime;
    private String tenantId;
    private String username;

    @XmlElementWrapper(name = "roles")
    @XmlElement(name = "role")
    public Set<ClientRole> getRoleSet() {
        return roleSet;
    }

    public ClientUser setRoleSet(Set<ClientRole> roleSet) {
        this.roleSet = roleSet;
        return this;
    }

    @XmlElement(name = "fullName")
    public String getFullName() {
        return fullName;
    }

    public ClientUser setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    @XmlElement(name = "password")
    public String getPassword() {
        return password;
    }

    public ClientUser setPassword(String password) {
        this.password = password;
        return this;
    }

    @XmlElement(name = "emailAddress")
    public String getEmailAddress() {
        return emailAddress;
    }

    public ClientUser setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    @XmlElement(name = "externallyDefined")
    public Boolean isExternallyDefined() {
        return externallyDefined;
    }

    public ClientUser setExternallyDefined(Boolean externallyDefined) {
        this.externallyDefined = externallyDefined;
        return this;
    }

    @XmlElement(name = "enabled")
    public Boolean isEnabled() {
        return enabled;
    }

    public ClientUser setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    @XmlElement(name = "previousPasswordChangeTime")
    public Date getPreviousPasswordChangeTime() {
        return previousPasswordChangeTime;
    }

    public ClientUser setPreviousPasswordChangeTime(Date previousPasswordChangeTime) {
        this.previousPasswordChangeTime = previousPasswordChangeTime;
        return this;
    }

    @XmlElement(name = "tenantId")
    public String getTenantId() {
        return tenantId;
    }

    public ClientUser setTenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    @XmlElement(name = "username")
    public String getUsername() {
        return username;
    }

    public ClientUser setUsername(String username) {
        this.username = username;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientUser that = (ClientUser) o;

        if (emailAddress != null ? !emailAddress.equals(that.emailAddress) : that.emailAddress != null) return false;
        if (enabled != null ? !enabled.equals(that.enabled) : that.enabled != null) return false;
        if (externallyDefined != null ? !externallyDefined.equals(that.externallyDefined) : that.externallyDefined != null)
            return false;
        if (fullName != null ? !fullName.equals(that.fullName) : that.fullName != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (previousPasswordChangeTime != null ? !previousPasswordChangeTime.equals(that.previousPasswordChangeTime) : that.previousPasswordChangeTime != null)
            return false;
        if (roleSet != null ? !roleSet.equals(that.roleSet) : that.roleSet != null) return false;
        if (tenantId != null ? !tenantId.equals(that.tenantId) : that.tenantId != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleSet != null ? roleSet.hashCode() : 0;
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (emailAddress != null ? emailAddress.hashCode() : 0);
        result = 31 * result + (externallyDefined != null ? externallyDefined.hashCode() : 0);
        result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
        result = 31 * result + (previousPasswordChangeTime != null ? previousPasswordChangeTime.hashCode() : 0);
        result = 31 * result + (tenantId != null ? tenantId.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ClientUser{" +
                "roleSet=" + roleSet +
                ", fullName='" + fullName + '\'' +
                ", password='" + password + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", externallyDefined=" + externallyDefined +
                ", enabled=" + enabled +
                ", previousPasswordChangeTime=" + previousPasswordChangeTime +
                ", tenantId='" + tenantId + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
