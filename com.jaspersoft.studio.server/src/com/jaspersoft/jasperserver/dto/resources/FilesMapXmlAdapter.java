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

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 *
 * @author Yaroslav.Kovalchyk
 * @version $Id: FilesMapXmlAdapter.java 35226 2013-08-09 07:08:53Z inesterenko $
 */
public class FilesMapXmlAdapter extends XmlAdapter<ClientReportUnitResourceListWrapper, Map<String, ClientReferenceableFile>> {
    @Override
    public ClientReportUnitResourceListWrapper marshal(Map<String, ClientReferenceableFile> files) throws Exception {
        List<ClientReportUnitResource> result = null;
        if (files != null) {
            result = new ArrayList<ClientReportUnitResource>();
            for (String name : files.keySet()) {
                result.add(new ClientReportUnitResource(name, files.get(name)));
            }
        }
        return result== null ? null : new ClientReportUnitResourceListWrapper(result);
    }

    @Override
    public Map<String, ClientReferenceableFile> unmarshal(ClientReportUnitResourceListWrapper v) throws Exception {
        Map<String, ClientReferenceableFile> result = null;
        if(v != null && v.getFiles() != null){
            result = new HashMap<String, ClientReferenceableFile>();
            for(ClientReportUnitResource currentResource : v.getFiles()){
                result.put(currentResource.getName(), currentResource.getFile());
            }
        }
        return result;
    }
}
