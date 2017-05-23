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
 * This helper class is used to avoid implementation of methods that are not used
 *
 * @author scubar
 */
public abstract class PropertyChangerAdapter implements PropertyChanger {
    @Override
    public void setProperty(String key, String val) { }

    @Override
    public String getProperty(String key) { return null; }

    @Override
    public void removeProperty(String key, String val) {}
}
