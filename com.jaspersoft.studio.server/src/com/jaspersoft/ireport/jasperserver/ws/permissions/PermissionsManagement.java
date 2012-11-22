/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
/**
 * PermissionsManagement.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 09, 2010 (01:02:43 CEST) WSDL2Java emitter.
 */

package com.jaspersoft.ireport.jasperserver.ws.permissions;

public interface PermissionsManagement extends java.rmi.Remote {
    public com.jaspersoft.ireport.jasperserver.ws.permissions.WSObjectPermission[] getPermissionsForObject(java.lang.String targetURI) throws java.rmi.RemoteException;
    public com.jaspersoft.ireport.jasperserver.ws.permissions.WSObjectPermission putPermission(com.jaspersoft.ireport.jasperserver.ws.permissions.WSObjectPermission objectPermission) throws java.rmi.RemoteException;
    public void deletePermission(com.jaspersoft.ireport.jasperserver.ws.permissions.WSObjectPermission objectPermission) throws java.rmi.RemoteException;
}
