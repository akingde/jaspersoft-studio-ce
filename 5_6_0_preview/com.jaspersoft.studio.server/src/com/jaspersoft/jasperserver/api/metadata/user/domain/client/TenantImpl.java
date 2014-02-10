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
