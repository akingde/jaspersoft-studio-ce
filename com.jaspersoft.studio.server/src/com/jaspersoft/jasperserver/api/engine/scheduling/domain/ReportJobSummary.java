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

package com.jaspersoft.jasperserver.api.engine.scheduling.domain;

import com.jaspersoft.jasperserver.api.JasperServerAPI;
import com.jaspersoft.jasperserver.api.engine.scheduling.service.ReportSchedulingService;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Report job summary information returned by the scheduler service when
 * listing report jobs.
 * 
 * <p>
 * The job summary information also includes job execution status determined
 * at the moment the jobs are listed.
 * </p>
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: ReportJobSummary.java 22152 2012-02-10 13:55:42Z ykovalchyk $
 * @see ReportSchedulingService#getScheduledJobs(com.jaspersoft.jasperserver.api.common.domain.ExecutionContext, String)
 * @see ReportSchedulingService#getScheduledJobs(com.jaspersoft.jasperserver.api.common.domain.ExecutionContext)
 * @see ReportJobRuntimeInformation
 * @since 1.0
 */
@JasperServerAPI
@XmlRootElement(name = "jobsummary")
public class ReportJobSummary implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	private int version;
	private String reportUnitURI;
	private String username;
	private String label;
	
	private ReportJobRuntimeInformation runtimeInformation;

	/**
	 * Creates an empty object.
	 */
	public ReportJobSummary() {
	}
	
	/**
	 * Returns the ID of the job for which this summary has been created.
	 * 
	 * @return the job ID
	 * @see ReportJob#getId()
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Sets the ID of the job to which this summary is associated.
	 * 
	 * @param id the job ID
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * Returns the job label.
	 * 
	 * @return the job label
	 * @see ReportJob#getLabel()
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * Sets the job label.
	 * 
	 * @param label the label of the job
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	
	/**
	 * Returns the persisted version of the job.
	 * 
	 * @return the job version
	 * @see ReportJob#getVersion()
	 */
	public int getVersion() {
		return version;
	}
	
	/**
	 * Sets the job version.
	 * 
	 * @param version the version of the job
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * Returns the job execution status at the moment the job was listed
	 * by the scheduling service.
	 * 
	 * @return the job execution status
	 */
    @XmlElement(name = "state")
	public ReportJobRuntimeInformation getRuntimeInformation() {
		return runtimeInformation;
	}

	/**
	 * Sets the job execution status.
	 * 
	 * @param runtimeInformation the execution status of the job
	 */
	public void setRuntimeInformation(ReportJobRuntimeInformation runtimeInformation) {
		this.runtimeInformation = runtimeInformation;
	}

	/**
	 * Returns the username of the job owner.
	 * 
	 * @return the job owner
	 * @see ReportJob#getUsername()
	 */
    @XmlElement(name = "owner")
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the job owner.
	 * 
	 * @param username the username of the job owner
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Returns the report that the job executes.
	 * 
	 * @return the repository URI/path of the job report
	 * @see ReportJobSource#getReportUnitURI()
	 */
	public String getReportUnitURI() {
		return reportUnitURI;
	}

	/**
	 * Sets the report that the job executes.
	 * 
	 * @param reportUnitURI the repository URI/path of the job report
	 */
	public void setReportUnitURI(String reportUnitURI) {
		this.reportUnitURI = reportUnitURI;
	}

}
