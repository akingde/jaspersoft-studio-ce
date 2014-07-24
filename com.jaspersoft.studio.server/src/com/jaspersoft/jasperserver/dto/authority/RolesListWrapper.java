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
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: Zakhar.Tomchenco
 */

@XmlRootElement(name = "roles")
public class RolesListWrapper {
    private List<ClientRole> roleList;

    public RolesListWrapper(){}

    public RolesListWrapper(List<ClientRole> roles){
        roleList = new ArrayList<ClientRole>(roles.size());
        for (ClientRole r : roles){
            roleList.add(r);
        }
    }

    @XmlElement(name = "role")
    public List<ClientRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<ClientRole> roleList) {
        this.roleList = roleList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolesListWrapper that = (RolesListWrapper) o;

        if (roleList != null ? !roleList.equals(that.roleList) : that.roleList != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return roleList != null ? roleList.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "RolesListWrapper{" +
                "roleList=" + roleList +
                '}';
    }
}
