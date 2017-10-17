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

/**
 * @author achan
 *
 * The base interface for tenants
 *
 */
@JasperServerAPI
public interface Tenant extends AttributedObject {
    /**
     * Returns the tenant ID
     * @return
     */
	public String getId();

    /**
     * Sets tenant ID
     * @param pid
     */
	public void setId(String pid);

    /**
     * Returns tenant alias. Alias is mutable part while id is immutable.
     * @return
     */
    public String getAlias();

    /**
     * Set tenant alias
     * @param alias
     */
    public void setAlias(String alias);

    /**
     * Returns the ID of parent tenant, or null
     * @return
     */
	public String getParentId();

    /**
     * Sets parent ID
     * @param pid
     */
	public void setParentId(String pid);

    /**
     * Returns tenant name
     * @return
     */
	public String getTenantName();

    /**
     * Sets tenant name
     * @param tName
     */
	public void setTenantName(String tName);

    /**
     * Returns tenant description
     * @return
     */
	public String getTenantDesc();

    /**
     * Sets tenant description
     * @param desc
     */
	public void setTenantDesc(String desc);

    /**
     * Returns tenant note
     * @return
     */
	public String getTenantNote();

    /**
     * Sets tenant note
     * @param note
     */
	public void setTenantNote(String note);

    /**
     * Returns full tenant path (something like "/grandParentTenantId/parentTenantId/tenantId")
     * @return
     */
	public String getTenantUri();

    /**
     * Sets tenant path
     * @param note
     */
	public void setTenantUri(String note);

    /**
     * returns a repository folder URI which is going to be a root folder for the tenant
     * @return
     */
	public String getTenantFolderUri();

    /**
     * Sets tenant root folder URI
     * @param folderUri
     */
	public void setTenantFolderUri(String folderUri);

    /**
     * Sets the theme name for a tenant
     * @return
     */
    public String getTheme();

    /**
     * Returns a theme name for a tenant
     * @param theme
     */
    public void setTheme(String theme);
}
