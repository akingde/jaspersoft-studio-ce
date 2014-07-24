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

package com.jaspersoft.jasperserver.api.metadata.user.domain;

import com.jaspersoft.jasperserver.api.JasperServerAPI;
import com.jaspersoft.jasperserver.api.common.domain.AttributedObject;

import java.util.Collection;
import java.util.Set;

/**
 * Role is the interface which represents the user role in the JasperServer. It extends {@link AttributedObject} and
 * {@link TenantQualified} interfaces. The role can be assigned to the user.
 *
 * @author swood
 * @version $Id: Role.java 30161 2013-03-22 19:20:15Z inesterenko $
 * @see com.jaspersoft.jasperserver.api.metadata.user.domain.client.RoleImpl
 * @since 1.0.1
 */
@JasperServerAPI
public interface Role extends AttributedObject, TenantQualified {
    public static String URI_PROTOCOL = "role";
    /**
     * Return the name of the role.
     *
     * @return the name of the role.
     */
	public String getRoleName();

    /**
     * Sets the name of the role
     *
     * @param roleName the name of the role. This parameter should not be <code>null</code>.
     */
	public void setRoleName(String roleName);

    /**
     * Returns a set of {@link User} objects for which the role is set. If there is no user with such role assigned
     * then the result set should be empty.
     *
     * @return a set of {@link User} objects.
     */
	public Set<User> getUsers();
	//public void setUsers(Set userList);

    /**
     * Shows if the role came from external authentication and authorization system (such as LDAP, external DB, etc.).
     *
     * @return <code>true</code> if role is externally defined, <code>false</code> otherwise.
     */
	public boolean isExternallyDefined();

    /**
     * Sets the value of externally defined field of the role (see {@link Role#isExternallyDefined}).
     *
     * @param isExternallyDefined the the value of externally defined field of the role.
     */
	public void setExternallyDefined(boolean isExternallyDefined);

    /**
     * Adds {@link User} object to the set of users for which the role is set.
     *
     * @param aUser the user to be added to the set of users.
     */
	public void addUser(User aUser);

    /**
     * Removes {@link User} object from the set of users for which the role is set.
     *
     * @param aUser the user to be removed from the set of users.
     */
	public void removeUser(User aUser);
}
