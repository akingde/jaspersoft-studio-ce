/*
 * Copyright (C) 2005 - 2011 Jaspersoft Corporation. All rights reserved.
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
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.jasperserver.api.metadata.user.domain.client;

import com.jaspersoft.jasperserver.api.metadata.user.domain.ObjectPermission;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author swood
 *
 * @hibernate.class table="ObjectPermission"
 */
@XmlRootElement(name = "permission")
public class ObjectPermissionImpl implements ObjectPermission {

	private String uri;
	private Object permissionRecipient;
	private int permissionMask;

    /**
	 *  (non-Javadoc)
	 * @see com.jaspersoft.jasperserver.api.metadata.user.domain.ObjectPermission#getURI()
	 */
	public String getURI() {
		return uri;
	}

	/* (non-Javadoc)
	 * @see com.jaspersoft.jasperserver.api.metadata.user.domain.ObjectPermission#setObjectIdentity(java.lang.String)
	 */
	public void setURI(String URI) {
		this.uri = URI;
	}

	/**
	 *  (non-Javadoc)
	 * @see com.jaspersoft.jasperserver.api.metadata.user.domain.ObjectPermission#getPermissionRecipient()
	 */
	public Object getPermissionRecipient() {
		return permissionRecipient;
	}

	/* (non-Javadoc)
	 * @see com.jaspersoft.jasperserver.api.metadata.user.domain.ObjectPermission#setPermissionRecipient(java.lang.String)
	 */
	public void setPermissionRecipient(Object permissionRecipient) {
		this.permissionRecipient = permissionRecipient; 
	}
	
	/* (non-Javadoc)
	 * @see com.jaspersoft.jasperserver.api.metadata.user.domain.ObjectPermission#getPermissionMask()
	 */
	public int getPermissionMask() {
		return permissionMask;
	}

	/* (non-Javadoc)
	 * @see com.jaspersoft.jasperserver.api.metadata.user.domain.ObjectPermission#setPermissionMask(java.lang.String)
	 */
	public void setPermissionMask(int newPermissionMask) {
		this.permissionMask = newPermissionMask; 
	}
	
	public String toString() {
		return new ToStringBuilder(this)
			.append("URI", getURI())
			.append("permissionRecipient", getPermissionRecipient())
			.toString();
	}

    public boolean equals(Object other) {
        if ( !(other instanceof ObjectPermissionImpl) ) return false;
        ObjectPermissionImpl castOther = (ObjectPermissionImpl) other;
        return new EqualsBuilder()
			.append(getURI(), castOther.getURI())
			.append(getPermissionRecipient(), castOther.getPermissionRecipient())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
			.append(getURI())
			.append(getPermissionRecipient())
            .toHashCode();
    }

}
