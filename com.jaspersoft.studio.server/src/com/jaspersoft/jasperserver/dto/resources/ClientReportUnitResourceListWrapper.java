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
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * <p></p>
 *
 * @author Yaroslav.Kovalchyk
 * @version $Id$
 */
@XmlType(name = "resources")
public class ClientReportUnitResourceListWrapper {
    private List<ClientReportUnitResource> files;
    public ClientReportUnitResourceListWrapper(){}
    public ClientReportUnitResourceListWrapper(List<ClientReportUnitResource> files){
        this.files = files;
    }
    @XmlElement(name = "resource")
    public List<ClientReportUnitResource> getFiles() {
        return files;
    }

    public void setFiles(List<ClientReportUnitResource> files) {
        this.files = files;
    }
}
