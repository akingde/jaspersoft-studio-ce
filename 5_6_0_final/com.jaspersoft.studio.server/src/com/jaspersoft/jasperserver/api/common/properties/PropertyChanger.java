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
package com.jaspersoft.jasperserver.api.common.properties;

/**
 * PropertyChanger
 *
 * This helper interface is used by PropertiesManagementServiceImpl
 * to apply configuration properties to the different services.
 * 
 * @author udavidovich
 */
public interface PropertyChanger {

    /**
     * setProperty
     * @param key should be fully qualified, must not be null
     * @param val value as String
     */
    public void setProperty(String key, String val);

    /**
     * getProperty
     * @param key must not be null
     * @return associated value or null
     */
    public String getProperty(String key);

    /**
     * Called when property was removed from prop file.
     *
     * @param key should be fully qualified, must not be null
     * @param val value as String
     */
    public void removeProperty(String key, String val);


}
