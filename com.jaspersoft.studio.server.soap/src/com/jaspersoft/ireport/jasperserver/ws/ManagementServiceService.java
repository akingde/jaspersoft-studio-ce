/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.ireport.jasperserver.ws;

public interface ManagementServiceService extends javax.xml.rpc.Service {
    public java.lang.String getrepositoryAddress();

    public com.jaspersoft.ireport.jasperserver.ws.ManagementService getrepository() throws javax.xml.rpc.ServiceException;

    public com.jaspersoft.ireport.jasperserver.ws.ManagementService getrepository(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
