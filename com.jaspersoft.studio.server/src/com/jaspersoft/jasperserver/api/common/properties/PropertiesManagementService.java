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
/*
 * Copyright (C) 2005 - 2012 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.jasperserver.api.common.properties;

import java.util.Map;
import java.util.Set;

import com.jaspersoft.jasperserver.api.common.domain.ExecutionContext;
import com.jaspersoft.jasperserver.api.common.domain.ValidationResult;

/**
 * PropertiesManagementService
 *
 * This service manages setting, retrieving and storing of
 * configurable properties.  by convention, the property keys
 * should be qualified with dots to avoid namespace conflict.
 *   for example:  mondrian.query.limit
 *
 * @author sbirney (sbirney@users.sourceforge.net)
 * @author udavidovich
 */
public interface PropertiesManagementService {

    /**
     * setProperty
     * @param key should be fully qualified, must not be null
     * @param val must be serializable
     */
    public void setProperty(String key, String val);

    /**
     * getProperty
     * @param key must not be null
     * @return associated value or null
     */
    public String getProperty(String key);

    /**
     * saveProperties
     * call saveProperties after setting one or more properties
     */
    public void saveProperties();

    public void reloadProperties();
    
    public String remove(String key);
    public Map<String, String> removeByValue(String val);
    public int size();
    public Set entrySet();

}
