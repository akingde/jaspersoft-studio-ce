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
