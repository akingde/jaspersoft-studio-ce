/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.ireport.jasperserver.ws;

public interface ManagementServiceService extends javax.xml.rpc.Service {
    public java.lang.String getrepositoryAddress();

    public com.jaspersoft.ireport.jasperserver.ws.ManagementService getrepository() throws javax.xml.rpc.ServiceException;

    public com.jaspersoft.ireport.jasperserver.ws.ManagementService getrepository(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
