/*
 * Copyright (C) 2005 - 2011 Jaspersoft Corporation. All rights reserved.
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
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.jasperserver.api.metadata.xml.domain.impl;

/**
 * @author tkavanagh
 * @version $Id: OperationResult.java 4724 2006-09-25 08:23:22Z tdanciu $
 */

import java.util.List;
import java.util.ArrayList;

public class OperationResult {

    public static final int SUCCESS = 0;
    
    private int returnCode = 0;
    private String message;
    private List resourceDescriptors = new ArrayList();
    private String version = "1.2.1";
    
    /**
     * Creates a new instance of OperationResult
     */
    public OperationResult() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List getResourceDescriptors() {
        return resourceDescriptors;
    }

    public void setResourceDescriptors(List resourceDescriptors) {
        this.resourceDescriptors = resourceDescriptors;
    }

    public void addResourceDescriptor(ResourceDescriptor descriptor) {
    	resourceDescriptors.add(descriptor);
    }
    
    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
	
}
