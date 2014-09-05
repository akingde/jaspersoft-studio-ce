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
 * @version $Id: ClientSubDataSourceReference.java 35226 2013-08-09 07:08:53Z inesterenko $
 */
@XmlRootElement(name = "subDataSource")
public class ClientSubDataSourceReference {
    private String id;
    private String uri;

    public String getId() {
        return id;
    }

    public ClientSubDataSourceReference setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientSubDataSourceReference that = (ClientSubDataSourceReference) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (uri != null ? !uri.equals(that.uri) : that.uri != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (uri != null ? uri.hashCode() : 0);
        return result;
    }

    public String getUri() {
        return uri;
    }

    public ClientSubDataSourceReference setUri(String uri) {
        this.uri = uri;
        return this;
    }

    @Override
    public String toString() {
        return "ClientSubDataSourceReference{" +
                "id='" + id + '\'' +
                ", uri='" + uri + '\'' +

                '}';
    }
}
