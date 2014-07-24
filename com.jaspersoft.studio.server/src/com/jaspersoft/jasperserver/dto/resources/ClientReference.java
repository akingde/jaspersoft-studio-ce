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

import com.jaspersoft.jasperserver.dto.common.ResourceLocation;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p></p>
 *
 * @author Yaroslav.Kovalchyk
 * @version $Id: ClientReference.java 39215 2013-10-31 07:59:03Z ykovalchyk $
 */
@XmlRootElement(name = "reference")
public class ClientReference implements ClientReferenceableDataSource, ClientReferenceableDataType,
        ClientReferenceableFile, ClientReferenceableQuery, ClientReferenceableInputControl, ClientReferenceableListOfValues,
        ClientReferenceableMondrianConnection, ClientReferenciableOlapConnection, ResourceLocation{

    private String uri;

    public ClientReference() {
    }

    public ClientReference(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public ClientReference setUri(String referenceUri) {
        this.uri = referenceUri;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientReference that = (ClientReference) o;

        if (uri != null ? !uri.equals(that.uri) : that.uri != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return uri != null ? uri.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ClientReference{" +
                "uri='" + uri + '\'' +
                '}';
    }
}
