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

/**
 * ObjectPermission is the interface which is used to give {@link User} or {@link Role} permission to the
 * {@link com.jaspersoft.jasperserver.api.metadata.common.domain.Resource Resource}. The URI of the resource is used
 * to identify the resource for which permission should be set.
 *
 * @author swood
 * @version $Id: ObjectPermission.java 21806 2011-12-27 09:24:01Z ykovalchyk $
 * @see com.jaspersoft.jasperserver.api.metadata.user.domain.client.ObjectPermissionImpl
 * @since 1.0.1 
 */
@JasperServerAPI
public interface ObjectPermission {

    /**
     * Returns the URI of the resource for which permission should be set.
     *
     * @return  the URI of the resource.
     */
	public String getURI();

    /**
     * Sets the URI of the resource for which permission should be set. The URI should include resource
     *      {@link com.jaspersoft.jasperserver.api.metadata.common.domain.Resource#URI_PROTOCOL Resource.URI_PROTOCOL}.
     *
     * @param URI the URI of the resource.   
     */
	public void setURI(String URI);

    /**
     * Returns the permission recipient object ({@link User} or {@link Role}) to which permission is given.
     *
     * @return the permission recipient object.
     */
	public Object getPermissionRecipient();

    /**
     * Sets the permission recipient object ({@link User} or {@link Role}) to which permission is given.
     *
     * @param permissionRecipient   the permission recipient object.
     */
	public void setPermissionRecipient(Object permissionRecipient);

    /**
     * Returns the permission mask. It contains permissions which are set to the resource for the specified
     * permission recipient object.
     *
     * @return the permission mask.
     */
	public int getPermissionMask();

    /**
     * Sets the permission mask which is an integer value:
     * <ul>
     * <li>0 - No Access</li>
     * <li>1 - Administer</li>
     * <li>2 - Read Only</li>
     * <li>18 - Read + Delete</li>
     * <li>30 - Read + Write + Delete</li>
     * </ul>
     *
     * @param permissionMask the permission mask.
     */
	public void setPermissionMask(int permissionMask);
}
