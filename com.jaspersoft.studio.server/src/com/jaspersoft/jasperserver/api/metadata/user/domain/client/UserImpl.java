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
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author swood
 *
 */
@XmlRootElement(name = "user")
public class UserImpl implements User, InternalURI, Serializable {
	@XmlTransient private Set roleSet = new HashSet();
	private String username = null;
	private String fullName = null;
    @XmlTransient private String password = null;
	@XmlTransient private String emailAddress = null;
	private boolean externallyDefined = false;
	@XmlTransient private boolean enabled = false;
	@XmlTransient private Date previousPasswordChangeTime = null;
    @XmlTransient private List attributes = null;
    private String tenantId = null;

	/**
	 * @return Returns the username.
	 * 
	 * (non-Javadoc)
	 * @see com.jaspersoft.jasperserver.api.metadata.user.domain.User#getUsername()
	 */

    public UserImpl(){}

    @XmlElement(name = "username")
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String newUsername) {
		if (newUsername == null || newUsername.trim().length() == 0) {
			throw new RuntimeException("No user name");
		}
		username = newUsername;
	}

	/**
	 * @return Returns the fullName.
	 * 
	 * (non-Javadoc)
	 * @see com.jaspersoft.jasperserver.api.metadata.user.domain.User#getFullName()
	 */
    @XmlElement(name = "fullName")
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName The fullName to set.
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetails#getPassword()
	 */
    @XmlTransient
	public String getPassword() {
		return password;
	}

	/**
	 * @param password The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return Returns the emailAddress.
	 */
    @XmlTransient
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress The emailAddress to set.
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * 
	 * @return Set
	 */
    @XmlTransient
	public Set getRoles() {
		return roleSet;
	}
	
	public void setRoles(Set newRoleSet) {
		roleSet = newRoleSet;
	}

	public void addRole(final Role newRole) {
		/*
		Predicate findRolePredicate = new Predicate() {
			public boolean evaluate(Object o) {
				Role r = (Role) o;
				if (r == null || newRole == null || r.getRoleName() == null || newRole.getRoleName() == null) {
					return false;
				}
				return r.getRoleName().equalsIgnoreCase(newRole.getRoleName());
			}
		};
		Object found = CollectionUtils.find(getRoles(), findRolePredicate);
		*/
		if (newRole != null && !getRoles().contains(newRole)) {
			getRoles().add(newRole);
			// Not for DTO? newRole.getUsers().add(this);
		}
	}

	public void removeRole(final Role removedRole) {
		getRoles().remove(removedRole);
//		 Not for DTO? removedRole.getUsers().remove(this);
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

	/* (non-Javadoc)
	 * @see com.jaspersoft.jasperserver.api.metadata.user.domain.User#isExternallyDefined()
	 */
    @XmlElement(name = "externallyDefined")
	public boolean isExternallyDefined() {
		return externallyDefined;
	}

	/* (non-Javadoc)
	 * @see com.jaspersoft.jasperserver.api.metadata.user.domain.User#setExternallyDefined(boolean)
	 */
	public void setExternallyDefined(boolean externallyDefined) {
		this.externallyDefined = externallyDefined;
	}

	/** 
     *  (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetails#isEnabled()
	 */
    @XmlTransient
	public boolean isEnabled() {
		return enabled;
	}

	/* (non-Javadoc)
	 * @see com.jaspersoft.jasperserver.api.metadata.user.domain.User#setEnabled(boolean)
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.append("username", getUsername());
		if (getTenantId() != null) {
			// only include tenant if not null
			builder.append("tenantId", getTenantId());
		}
		return builder.toString();
	}

    public boolean equals(Object other) {
        if ( !(other instanceof UserImpl) ) return false;
        UserImpl castOther = (UserImpl) other;
        return new EqualsBuilder()
            .append(this.getUsername(), castOther.getUsername())
            .append(this.getTenantId(), castOther.getTenantId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getUsername())
            .append(getTenantId())
            .toHashCode();
    }
    
    @XmlTransient
	public Date getPreviousPasswordChangeTime() {
		return previousPasswordChangeTime;
	}

	public void setPreviousPasswordChangeTime(Date previousPasswordChangeTime) {
		this.previousPasswordChangeTime = previousPasswordChangeTime;
	}

    @XmlElement(name = "tenantId")
	public String getTenantId() {
	    return tenantId;
	}
	    
	public void setTenantId(String tid) {
	    tenantId = tid;
	}

    @XmlTransient
    public String getURI() {
        StringBuilder res = new StringBuilder(URI_PROTOCOL).append(":");
        if (this.getTenantId() != null) {
            res.append("/").append(this.getTenantId());
        }
        return res.append("/").append(this.getUsername()).toString();
    }

    @XmlTransient
    public String getPath() {
        StringBuilder res = new StringBuilder();
        if (this.getTenantId() != null) {
            res.append("/").append(this.getTenantId());
        }
        return res.append("/").append(this.getUsername()).toString();
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
