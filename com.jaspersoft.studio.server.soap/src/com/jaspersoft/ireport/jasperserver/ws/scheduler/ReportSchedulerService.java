/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
/**
 * ReportSchedulerService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 09, 2010 (01:02:43 CEST) WSDL2Java emitter.
 */

package com.jaspersoft.ireport.jasperserver.ws.scheduler;

public interface ReportSchedulerService extends javax.xml.rpc.Service {
    public java.lang.String getReportSchedulerAddress();

    public com.jaspersoft.ireport.jasperserver.ws.scheduler.ReportScheduler getReportScheduler() throws javax.xml.rpc.ServiceException;

    public com.jaspersoft.ireport.jasperserver.ws.scheduler.ReportScheduler getReportScheduler(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
