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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

/**
 * <p></p>
 *
 * @author Yaroslav.Kovalchyk
 * @version $Id: AbstractClientDataSourceHolder.java 35226 2013-08-09 07:08:53Z inesterenko $
 */
public abstract class AbstractClientDataSourceHolder<BuilderType extends AbstractClientDataSourceHolder<BuilderType>> extends ClientResource<BuilderType> {

    private ClientReferenceableDataSource dataSource;

    @XmlElements({
            /*ClientReference is included here to serve as resource reference*/
            @XmlElement(type = ClientReference.class, name = "dataSourceReference"),
            @XmlElement(type = ClientAwsDataSource.class, name = "awsDataSource"),
            @XmlElement(type = ClientBeanDataSource.class, name = "beanDataSource"),
            @XmlElement(type = ClientCustomDataSource.class, name = "customDataSource"),
            @XmlElement(type = ClientJdbcDataSource.class, name = "jdbcDataSource"),
            @XmlElement(type = ClientJndiJdbcDataSource.class, name = "jndiJdbcDataSource"),
            @XmlElement(type = ClientVirtualDataSource.class, name = "virtualDataSource"),
            @XmlElement(type = ClientSemanticLayerDataSource.class, name = "semanticLayerDataSource"),
            @XmlElement(type = ClientAdhocDataView.class, name = "advDataSource")
    })
    public ClientReferenceableDataSource getDataSource() {
        return dataSource;
    }

    // unchecked cast to BuilderType safety assured by the rule of usage BuilderType generic parameter.
    @SuppressWarnings("unchecked")
    public BuilderType setDataSource(ClientReferenceableDataSource dataSource) {
        this.dataSource = dataSource;
        return (BuilderType) this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

		AbstractClientDataSourceHolder<?> that = (AbstractClientDataSourceHolder<?>) o;

        if (dataSource != null ? !dataSource.equals(that.dataSource) : that.dataSource != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (dataSource != null ? dataSource.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AbstractClientDataSourceHolder{" +
                "dataSource=" + dataSource +
                ", version=" + getVersion() +
                ", permissionMask=" + getPermissionMask() +
                ", uri='" + getUri() + '\'' +
                ", label='" + getLabel() + '\'' +
                '}';
    }
}
