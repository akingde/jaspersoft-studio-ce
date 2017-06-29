/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
/**
 * ReportScheduler_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 09, 2010 (01:02:43 CEST) WSDL2Java emitter.
 */

package com.jaspersoft.ireport.jasperserver.ws.scheduler;

public interface ReportScheduler extends java.rmi.Remote {
    public void deleteJob(long id) throws java.rmi.RemoteException;
    public void deleteJobs(long[] ids) throws java.rmi.RemoteException;
    public com.jaspersoft.ireport.jasperserver.ws.scheduler.Job getJob(long id) throws java.rmi.RemoteException;
    public com.jaspersoft.ireport.jasperserver.ws.scheduler.Job scheduleJob(com.jaspersoft.ireport.jasperserver.ws.scheduler.Job job) throws java.rmi.RemoteException;
    public com.jaspersoft.ireport.jasperserver.ws.scheduler.Job updateJob(com.jaspersoft.ireport.jasperserver.ws.scheduler.Job job) throws java.rmi.RemoteException;
    public com.jaspersoft.ireport.jasperserver.ws.scheduler.JobSummary[] getAllJobs() throws java.rmi.RemoteException;
    public com.jaspersoft.ireport.jasperserver.ws.scheduler.JobSummary[] getReportJobs(java.lang.String reportURI) throws java.rmi.RemoteException;
}
