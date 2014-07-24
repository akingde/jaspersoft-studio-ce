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
