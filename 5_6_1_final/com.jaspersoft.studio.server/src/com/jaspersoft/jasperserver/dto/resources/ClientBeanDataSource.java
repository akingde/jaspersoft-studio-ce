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

package com.jaspersoft.jasperserver.dto.resources;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p></p>
 *
 * @author Yaroslav.Kovalchyk
 * @version $Id: ClientBeanDataSource.java 35226 2013-08-09 07:08:53Z inesterenko $
 */
@XmlRootElement(name = ResourceMediaType.BEAN_DATA_SOURCE_CLIENT_TYPE)
public class ClientBeanDataSource extends ClientResource<ClientBeanDataSource> implements ClientReferenceableDataSource {
    private String beanName;
    private String beanMethod;

    public String getBeanName() {
        return beanName;
    }

    public ClientBeanDataSource setBeanName(String beanName) {
        this.beanName = beanName;
        return this;
    }

    public String getBeanMethod() {
        return beanMethod;
    }

    public ClientBeanDataSource setBeanMethod(String beanMethod) {
        this.beanMethod = beanMethod;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ClientBeanDataSource that = (ClientBeanDataSource) o;

        if (beanMethod != null ? !beanMethod.equals(that.beanMethod) : that.beanMethod != null) return false;
        if (beanName != null ? !beanName.equals(that.beanName) : that.beanName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (beanName != null ? beanName.hashCode() : 0);
        result = 31 * result + (beanMethod != null ? beanMethod.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ClientBeanDataSource{" +
                "beanName='" + beanName + '\'' +
                ", beanMethod='" + beanMethod + '\'' +
                ", version=" + getVersion() +
                ", permissionMask=" + getPermissionMask() +
                ", uri='" + getUri() + '\'' +
                ", label='" + getLabel() + '\'' +
                '}';
    }
}
