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
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Job.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

@XmlRootElement(name = "job")
public class Job  implements java.io.Serializable {
    private long id;
    private int version;
    private java.lang.String reportUnitURI;


    @XmlTransient private java.lang.String username;
    private java.lang.String label;
    private java.lang.String description;
    private com.jaspersoft.jasperserver.ws.scheduling.JobSimpleTrigger simpleTrigger;
    private com.jaspersoft.jasperserver.ws.scheduling.JobCalendarTrigger calendarTrigger;
    private com.jaspersoft.jasperserver.ws.scheduling.JobParameter[] parameters;
    private java.lang.String baseOutputFilename;
    private java.lang.String[] outputFormats;
    private java.lang.String outputLocale;
    private com.jaspersoft.jasperserver.ws.scheduling.JobRepositoryDestination repositoryDestination;
    private com.jaspersoft.jasperserver.ws.scheduling.JobMailNotification mailNotification;

    public Job() {
    }

    public Job(
           long id,
           int version,
           java.lang.String reportUnitURI,
           java.lang.String username,
           java.lang.String label,
           java.lang.String description,
           com.jaspersoft.jasperserver.ws.scheduling.JobSimpleTrigger simpleTrigger,
           com.jaspersoft.jasperserver.ws.scheduling.JobCalendarTrigger calendarTrigger,
           com.jaspersoft.jasperserver.ws.scheduling.JobParameter[] parameters,
           java.lang.String baseOutputFilename,
           java.lang.String[] outputFormats,
           java.lang.String outputLocale,
           com.jaspersoft.jasperserver.ws.scheduling.JobRepositoryDestination repositoryDestination,
           com.jaspersoft.jasperserver.ws.scheduling.JobMailNotification mailNotification) {
           this.id = id;
           this.version = version;
           this.reportUnitURI = reportUnitURI;
           this.username = username;
           this.label = label;
           this.description = description;
           this.simpleTrigger = simpleTrigger;
           this.calendarTrigger = calendarTrigger;
           this.parameters = parameters;
           this.baseOutputFilename = baseOutputFilename;
           this.outputFormats = outputFormats;
           this.outputLocale = outputLocale;
           this.repositoryDestination = repositoryDestination;
           this.mailNotification = mailNotification;
    }


    /**
     * Gets the id value for this Job.
     * 
     * @return id
     */
    public long getId() {
        return id;
    }


    /**
     * Sets the id value for this Job.
     * 
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }


    /**
     * Gets the version value for this Job.
     * 
     * @return version
     */
    public int getVersion() {
        return version;
    }


    /**
     * Sets the version value for this Job.
     * 
     * @param version
     */
    public void setVersion(int version) {
        this.version = version;
    }


    /**
     * Gets the reportUnitURI value for this Job.
     * 
     * @return reportUnitURI
     */
    public java.lang.String getReportUnitURI() {
        return reportUnitURI;
    }


    /**
     * Sets the reportUnitURI value for this Job.
     * 
     * @param reportUnitURI
     */
    public void setReportUnitURI(java.lang.String reportUnitURI) {
        this.reportUnitURI = reportUnitURI;
    }


    /**
     * Gets the username value for this Job.
     * 
     * @return username
     */
    @XmlTransient
    public java.lang.String getUsername() {
        return username;
    }


    /**
     * Sets the username value for this Job.
     * 
     * @param username
     */
    public void setUsername(java.lang.String username) {
        this.username = username;
    }


    /**
     * Gets the label value for this Job.
     * 
     * @return label
     */
    public java.lang.String getLabel() {
        return label;
    }


    /**
     * Sets the label value for this Job.
     * 
     * @param label
     */
    public void setLabel(java.lang.String label) {
        this.label = label;
    }


    /**
     * Gets the description value for this Job.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this Job.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the simpleTrigger value for this Job.
     * 
     * @return simpleTrigger
     */
    public com.jaspersoft.jasperserver.ws.scheduling.JobSimpleTrigger getSimpleTrigger() {
        return simpleTrigger;
    }


    /**
     * Sets the simpleTrigger value for this Job.
     * 
     * @param simpleTrigger
     */
    public void setSimpleTrigger(com.jaspersoft.jasperserver.ws.scheduling.JobSimpleTrigger simpleTrigger) {
        this.simpleTrigger = simpleTrigger;
    }


    /**
     * Gets the calendarTrigger value for this Job.
     * 
     * @return calendarTrigger
     */
    public com.jaspersoft.jasperserver.ws.scheduling.JobCalendarTrigger getCalendarTrigger() {
        return calendarTrigger;
    }


    /**
     * Sets the calendarTrigger value for this Job.
     * 
     * @param calendarTrigger
     */
    public void setCalendarTrigger(com.jaspersoft.jasperserver.ws.scheduling.JobCalendarTrigger calendarTrigger) {
        this.calendarTrigger = calendarTrigger;
    }


    /**
     * Gets the parameters value for this Job.
     * 
     * @return parameters
     */
    @XmlJavaTypeAdapter(ParameterValueXmlAdapter.class)
    public com.jaspersoft.jasperserver.ws.scheduling.JobParameter[] getParameters() {
        return parameters;
    }


    /**
     * Sets the parameters value for this Job.
     * 
     * @param parameters
     */
    public void setParameters(com.jaspersoft.jasperserver.ws.scheduling.JobParameter[] parameters) {
        this.parameters = parameters;
    }


    /**
     * Gets the baseOutputFilename value for this Job.
     * 
     * @return baseOutputFilename
     */
    public java.lang.String getBaseOutputFilename() {
        return baseOutputFilename;
    }


    /**
     * Sets the baseOutputFilename value for this Job.
     * 
     * @param baseOutputFilename
     */
    public void setBaseOutputFilename(java.lang.String baseOutputFilename) {
        this.baseOutputFilename = baseOutputFilename;
    }


    /**
     * Gets the outputFormats value for this Job.
     * 
     * @return outputFormats
     */
    public java.lang.String[] getOutputFormats() {
        return outputFormats;
    }


    /**
     * Sets the outputFormats value for this Job.
     * 
     * @param outputFormats
     */
    public void setOutputFormats(java.lang.String[] outputFormats) {
        this.outputFormats = outputFormats;
    }


    /**
     * Gets the outputLocale value for this Job.
     * 
     * @return outputLocale
     */
    public java.lang.String getOutputLocale() {
        return outputLocale;
    }


    /**
     * Sets the outputLocale value for this Job.
     * 
     * @param outputLocale
     */
    public void setOutputLocale(java.lang.String outputLocale) {
        this.outputLocale = outputLocale;
    }


    /**
     * Gets the repositoryDestination value for this Job.
     * 
     * @return repositoryDestination
     */
    public com.jaspersoft.jasperserver.ws.scheduling.JobRepositoryDestination getRepositoryDestination() {
        return repositoryDestination;
    }


    /**
     * Sets the repositoryDestination value for this Job.
     * 
     * @param repositoryDestination
     */
    public void setRepositoryDestination(com.jaspersoft.jasperserver.ws.scheduling.JobRepositoryDestination repositoryDestination) {
        this.repositoryDestination = repositoryDestination;
    }


    /**
     * Gets the mailNotification value for this Job.
     * 
     * @return mailNotification
     */
    public com.jaspersoft.jasperserver.ws.scheduling.JobMailNotification getMailNotification() {
        return mailNotification;
    }


    /**
     * Sets the mailNotification value for this Job.
     * 
     * @param mailNotification
     */
    public void setMailNotification(com.jaspersoft.jasperserver.ws.scheduling.JobMailNotification mailNotification) {
        this.mailNotification = mailNotification;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Job)) return false;
        Job other = (Job) obj; 
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
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.simpleTrigger==null && other.getSimpleTrigger()==null) || 
             (this.simpleTrigger!=null &&
              this.simpleTrigger.equals(other.getSimpleTrigger()))) &&
            ((this.calendarTrigger==null && other.getCalendarTrigger()==null) || 
             (this.calendarTrigger!=null &&
              this.calendarTrigger.equals(other.getCalendarTrigger()))) &&
            ((this.parameters==null && other.getParameters()==null) || 
             (this.parameters!=null &&
              java.util.Arrays.equals(this.parameters, other.getParameters()))) &&
            ((this.baseOutputFilename==null && other.getBaseOutputFilename()==null) || 
             (this.baseOutputFilename!=null &&
              this.baseOutputFilename.equals(other.getBaseOutputFilename()))) &&
            ((this.outputFormats==null && other.getOutputFormats()==null) || 
             (this.outputFormats!=null &&
              java.util.Arrays.equals(this.outputFormats, other.getOutputFormats()))) &&
            ((this.outputLocale==null && other.getOutputLocale()==null) || 
             (this.outputLocale!=null &&
              this.outputLocale.equals(other.getOutputLocale()))) &&
            ((this.repositoryDestination==null && other.getRepositoryDestination()==null) || 
             (this.repositoryDestination!=null &&
              this.repositoryDestination.equals(other.getRepositoryDestination()))) &&
            ((this.mailNotification==null && other.getMailNotification()==null) || 
             (this.mailNotification!=null &&
              this.mailNotification.equals(other.getMailNotification())));
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
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getSimpleTrigger() != null) {
            _hashCode += getSimpleTrigger().hashCode();
        }
        if (getCalendarTrigger() != null) {
            _hashCode += getCalendarTrigger().hashCode();
        }
        if (getParameters() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getParameters());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getParameters(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getBaseOutputFilename() != null) {
            _hashCode += getBaseOutputFilename().hashCode();
        }
        if (getOutputFormats() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOutputFormats());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOutputFormats(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getOutputLocale() != null) {
            _hashCode += getOutputLocale().hashCode();
        }
        if (getRepositoryDestination() != null) {
            _hashCode += getRepositoryDestination().hashCode();
        }
        if (getMailNotification() != null) {
            _hashCode += getMailNotification().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
