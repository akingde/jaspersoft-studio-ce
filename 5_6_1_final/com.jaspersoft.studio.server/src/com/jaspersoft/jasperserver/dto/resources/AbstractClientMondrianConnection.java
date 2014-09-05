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
 * @version $Id: AbstractClientMondrianConnection.java 35226 2013-08-09 07:08:53Z inesterenko $
 */
public abstract class AbstractClientMondrianConnection <BuilderType extends AbstractClientMondrianConnection<BuilderType>> extends AbstractClientDataSourceHolder<BuilderType> implements ClientReferenceableMondrianConnection, ClientReferenciableOlapConnection {
    private ClientReferenceableFile schema;

    @XmlElements({
            @XmlElement(name = "schemaReference", type = ClientReference.class),
            @XmlElement(name = "schema", type = ClientFile.class)
    })
    public ClientReferenceableFile getSchema() {
        return schema;
    }

    // unchecked cast to BuilderType safety assured by the rule of usage BuilderType generic parameter.
    @SuppressWarnings("unchecked")
    public BuilderType setSchema(ClientReferenceableFile schema) {
        this.schema = schema;
        return (BuilderType) this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AbstractClientMondrianConnection that = (AbstractClientMondrianConnection) o;

        if (schema != null ? !schema.equals(that.schema) : that.schema != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (schema != null ? schema.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "schema=" + schema +
                ", version=" + getVersion() +
                ", permissionMask=" + getPermissionMask() +
                ", uri='" + getUri() + '\'' +
                ", label='" + getLabel() + '\'' +
                '}';
    }
}
