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


package com.jaspersoft.jasperserver.ws.scheduling;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * JobSummary.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */


@XmlRootElement(name = "jobsummary")
public class JobSummary  implements java.io.Serializable {
    private long id;

    private int version;

    private java.lang.String reportUnitURI;

    @XmlTransient
    private java.lang.String username;

    private java.lang.String label;

    private com.jaspersoft.jasperserver.ws.scheduling.RuntimeJobState state;

    private java.util.Calendar previousFireTime;

    private java.util.Calendar nextFireTime;

    public JobSummary() {
    }

    public JobSummary(
           long id,
           int version,
           java.lang.String reportUnitURI,
           java.lang.String username,
           java.lang.String label,
           com.jaspersoft.jasperserver.ws.scheduling.RuntimeJobState state,
           java.util.Calendar previousFireTime,
           java.util.Calendar nextFireTime) {
           this.id = id;
           this.version = version;
           this.reportUnitURI = reportUnitURI;
           this.username = username;
           this.label = label;
           this.state = state;
           this.previousFireTime = previousFireTime;
           this.nextFireTime = nextFireTime;
    }


    /**
     * Gets the id value for this JobSummary.
     *
     * @return id
     */
    public long getId() {
        return id;
    }


    /**
     * Sets the id value for this JobSummary.
     *
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }


    /**
     * Gets the version value for this JobSummary.
     *
     * @return version
     */
    public int getVersion() {
        return version;
    }


    /**
     * Sets the version value for this JobSummary.
     *
     * @param version
     */
    public void setVersion(int version) {
        this.version = version;
    }


    /**
     * Gets the reportUnitURI value for this JobSummary.
     *
     * @return reportUnitURI
     */
    public java.lang.String getReportUnitURI() {
        return reportUnitURI;
    }


    /**
     * Sets the reportUnitURI value for this JobSummary.
     *
     * @param reportUnitURI
     */
    public void setReportUnitURI(java.lang.String reportUnitURI) {
        this.reportUnitURI = reportUnitURI;
    }


    /**
     * Gets the username value for this JobSummary.
     *
     * @return username
     */
    @XmlTransient
    public java.lang.String getUsername() {
        return username;
    }


    /**
     * Sets the username value for this JobSummary.
     *
     * @param username
     */
    public void setUsername(java.lang.String username) {
        this.username = username;
    }


    /**
     * Gets the label value for this JobSummary.
     *
     * @return label
     */
    public java.lang.String getLabel() {
        return label;
    }


    /**
     * Sets the label value for this JobSummary.
     *
     * @param label
     */
    public void setLabel(java.lang.String label) {
        this.label = label;
    }


    /**
     * Gets the state value for this JobSummary.
     *
     * @return state
     */
    public com.jaspersoft.jasperserver.ws.scheduling.RuntimeJobState getState() {
        return state;
    }


    /**
     * Sets the state value for this JobSummary.
     *
     * @param state
     */
    public void setState(com.jaspersoft.jasperserver.ws.scheduling.RuntimeJobState state) {
        this.state = state;
    }


    /**
     * Gets the previousFireTime value for this JobSummary.
     *
     * @return previousFireTime
     */
    public java.util.Calendar getPreviousFireTime() {
        return previousFireTime;
    }


    /**
     * Sets the previousFireTime value for this JobSummary.
     *
     * @param previousFireTime
     */
    public void setPreviousFireTime(java.util.Calendar previousFireTime) {
        this.previousFireTime = previousFireTime;
    }


    /**
     * Gets the nextFireTime value for this JobSummary.
     *
     * @return nextFireTime
     */
    public java.util.Calendar getNextFireTime() {
        return nextFireTime;
    }


    /**
     * Sets the nextFireTime value for this JobSummary.
     *
     * @param nextFireTime
     */
    public void setNextFireTime(java.util.Calendar nextFireTime) {
        this.nextFireTime = nextFireTime;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof JobSummary)) return false;
        JobSummary other = (JobSummary) obj; 
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            this.id == other.getId() &&
            this.version == other.getVersion() &&
            ((this.reportUnitURI==null && other.getReportUnitURI()==null) ||
             (this.reportUnitURI!=null &&
              this.reportUnitURI.equals(other.getReportUnitURI()))) &&
            ((this.username==null && other.getUsername()==null) ||
             (this.username!=null &&
              this.username.equals(other.getUsername()))) &&
            ((this.label==null && other.getLabel()==null) ||
             (this.label!=null &&
              this.label.equals(other.getLabel()))) &&
            ((this.state==null && other.getState()==null) ||
             (this.state!=null &&
              this.state.equals(other.getState()))) &&
            ((this.previousFireTime==null && other.getPreviousFireTime()==null) ||
             (this.previousFireTime!=null &&
              this.previousFireTime.equals(other.getPreviousFireTime()))) &&
            ((this.nextFireTime==null && other.getNextFireTime()==null) ||
             (this.nextFireTime!=null &&
              this.nextFireTime.equals(other.getNextFireTime())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += new Long(getId()).hashCode();
        _hashCode += getVersion();
        if (getReportUnitURI() != null) {
            _hashCode += getReportUnitURI().hashCode();
        }
        if (getUsername() != null) {
            _hashCode += getUsername().hashCode();
        }
        if (getLabel() != null) {
            _hashCode += getLabel().hashCode();
        }
        if (getState() != null) {
            _hashCode += getState().hashCode();
        }
        if (getPreviousFireTime() != null) {
            _hashCode += getPreviousFireTime().hashCode();
        }
        if (getNextFireTime() != null) {
            _hashCode += getNextFireTime().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
