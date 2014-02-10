/*
* Copyright (C) 2005 - 2013 Jaspersoft Corporation. All rights  reserved.
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
* along with this program.&nbsp; If not, see <http://www.gnu.org/licenses/>.
*/
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
