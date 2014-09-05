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

import com.jaspersoft.jasperserver.api.metadata.user.domain.Tenant;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author achan
 *
 */
@XmlRootElement(name = "tenant")
public class TenantImpl implements Tenant, Serializable {
	
	private String id = null;
	private String alias = null;
	private String parentId = null;
	private String tenantName = null;
    private List attributes = new ArrayList();
    private String tenantDesc = null;
    private String tenantNote = null;
    private String tenantUri = null;
    private String tenantFolderUri = null;
    private String theme = null;


	/**
	 * @return Returns the tenantDesc.
	 */
	public String getTenantDesc() {
		return tenantDesc;
	}
	/**
	 * @param tenantDesc The tenantDesc to set.
	 */
	public void setTenantDesc(String tenantDesc) {
		this.tenantDesc = tenantDesc;
	}
	/**
	 * @return Returns the tenantNote.
	 */
	public String getTenantNote() {
		return tenantNote;
	}
	/**
	 * @param tenantNote The tenantNote to set.
	 */
	public void setTenantNote(String tenantNote) {
		this.tenantNote = tenantNote;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String pid) {
		id = pid;
	}

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getTenantName() {
		return tenantName;
	}
	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public List getAttributes() {
	    return attributes;
	}

    public void setAttributes(List attrs) {
	    attributes = attrs;
    }

	/**
	 * @return Returns the tenantUri.
	 */
	public String getTenantUri() {
		return tenantUri;
	}
	/**
	 * @param tenantUri The tenantUri to set.
	 */
	public void setTenantUri(String tenantUri) {
		this.tenantUri = tenantUri;
	}
	public String getTenantFolderUri() {
		return tenantFolderUri;
	}
	public void setTenantFolderUri(String tenantFolderUri) {
		this.tenantFolderUri = tenantFolderUri;
	}

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
