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
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p></p>
 *
 * @author Yaroslav.Kovalchyk
 * @version $Id: ClientReportUnitResource.java 35226 2013-08-09 07:08:53Z inesterenko $
 */
@XmlRootElement(name = "reportUnitFile")
public class ClientReportUnitResource {
    private String name;
    private ClientReferenceableFile file;

    public ClientReportUnitResource() {
    }

    public ClientReportUnitResource(String name, ClientReferenceableFile file) {
        this.name = name;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public ClientReportUnitResource setName(String name) {
        this.name = name;
        return this;
    }

    @XmlElements({
            @XmlElement(name = "fileReference", type = ClientReference.class),
            @XmlElement(name = "fileResource", type = ClientFile.class)
    })
    public ClientReferenceableFile getFile() {
        return file;
    }

    public ClientReportUnitResource setFile(ClientReferenceableFile file) {
        this.file = file;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientReportUnitResource that = (ClientReportUnitResource) o;

        if (file != null ? !file.equals(that.file) : that.file != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (file != null ? file.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ClientReportUnitResource{" +
                "name='" + name + '\'' +
                ", file=" + file +
                '}';
    }
}
