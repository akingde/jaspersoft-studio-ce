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

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * User is the interface which represents the user in the JasperServer. It extends {@link AttributedObject} and
 * {@link TenantQualified} interfaces.
 *
 * @author swood
 * @version $Id: User.java 30161 2013-03-22 19:20:15Z inesterenko $
 * 
 * @see com.jaspersoft.jasperserver.api.metadata.user.domain.client.UserImpl
 * @since 1.0.1
 */
@JasperServerAPI
public interface User extends AttributedObject, TenantQualified {
    public static String URI_PROTOCOL = "user";

    /**
     * Returns the name of the user (see {@link #setUsername(String)}).
     *
     * @return the name of the user.
     */
	public String getUsername();

    /**
     * Sets the name of the user. It should be unique and is used to identify the user.
     *
     * @param username the name of the user.
     */
	public void setUsername(String username);

    /**
     * Returns the full name of the user (see {@link #setFullName(String)}).
     *
     * @return the full name of the user.
     */
	public String getFullName();

    /**
     * Sets the full name of the user. It is not unique.
     *
     * @param fullName the full name of the user.
     */
	public void setFullName(String fullName);
	
	/**
	 * Returns the password of the user if we are using our own authentication (user is not externally defined).
	 * 
	 * @return the password of the user.
	 */
	public String getPassword();

    /**
     * Sets the password of the user (see {@link #getPassword()}).
     *
     * @param password the password of the user.
     */
	public void setPassword(String password);

    /**
     * Returns the email address of the user.
     *
     * @return the email address of the user.
     */
	public String getEmailAddress();

    /**
     * Sets the email address of the user.
     *
     * @param emailAddress the email address of the user.
     */
	public void setEmailAddress(String emailAddress);

    /**
     * Shows if the user came from external authentication and authorization system (such as LDAP, external DB, etc.).
     *
     * @return <code>true</code> if user is externally defined, <code>false</code> otherwise.
     */
	public boolean isExternallyDefined();

    /**
     * Sets the value of externally defined field of the user (see {@link #isExternallyDefined}).
     *
     * @param externallyDefined the the value of externally defined field of the user.
     */
	public void setExternallyDefined(boolean externallyDefined);

    /**
     * Shows if the user is enabled (can log in to the JasperServer).
     *
     * @return <code>true</code> if the user is enabled, <code>false</code> otherwise.
     */
	public boolean isEnabled();

    /**
     * Sets the value of enable field of the user. If the value is set to <code>true</code>, then user can log in to the
     * JasperServer. If it is set to <code>true</code>, then user cannot log in.
     *
     * @param enabled the value of enable field of the user.
     */
	public void setEnabled(boolean enabled);

    /**
     * Returns the set of {@link Role} object which are assigned to the user.
     *
     * @return the set of roles.
     */
	public Set getRoles();

    /**
     * Sets the set of {@link Role} objects which are assigned to the user.
     *
     * @param newRoles the set of roles.
     */
	public void setRoles(Set newRoles);

    /**
     * Adds the role to the set of {@link Role} objects which are assigned to the user.
     *
     * @param aRole the role to be added.
     */
	public void addRole(Role aRole);

    /**
     * Removes the role from the set of {@link Role} objects which are assigned to the user.
     *
     * @param aRole the role to be removed.
     */
	public void removeRole(Role aRole);

    /**
     * Returns the previous time when the password was changed.
     *
     * @return the previous time when the password was changed.
     */
	public Date getPreviousPasswordChangeTime();

    /**
     * Sets the time when the password was changed.
     *
     * @param timeStamp the time when the password was changed.
     */
	public void setPreviousPasswordChangeTime(Date timeStamp);
	
	public List getAttributes();
	public void setAttributes(List attrs);
	
}
