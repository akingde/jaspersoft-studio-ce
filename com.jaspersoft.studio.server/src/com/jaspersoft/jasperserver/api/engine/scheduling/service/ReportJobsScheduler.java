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

package com.jaspersoft.jasperserver.api.engine.scheduling.service;

import java.util.Calendar;
import java.util.List;

import com.jaspersoft.jasperserver.api.JSException;
import com.jaspersoft.jasperserver.api.JasperServerAPI;
import com.jaspersoft.jasperserver.api.common.domain.ExecutionContext;
import com.jaspersoft.jasperserver.api.common.domain.ValidationErrors;
import com.jaspersoft.jasperserver.api.engine.scheduling.domain.ReportJob;
import com.jaspersoft.jasperserver.api.engine.scheduling.domain.ReportJobIdHolder;
import com.jaspersoft.jasperserver.api.engine.scheduling.domain.ReportJobRuntimeInformation;
import com.jaspersoft.jasperserver.api.engine.scheduling.domain.ReportJobSummary;


/**
 * A service that provides methods related to the execution of report jobs.
 *  
 * <p>
 * This service would not be directly called by code that does report scheduling
 * in regular scenarios, such code would normally use {@link ReportSchedulingService}.
 * This report job execution service is used internal by the central scheduling service.
 * </p>
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: ReportJobsScheduler.java 22586 2012-03-16 16:18:50Z tchow $
 * @since 1.0
 * @see ReportSchedulingService
 * @see ReportJobsPersistenceService
 */
@JasperServerAPI
public interface ReportJobsScheduler {

	/**
	 * Registers a trigger for a new report job.
	 * 
     * @param context the caller execution context
	 * @param job the report job details
	 * @see ReportSchedulingService#scheduleJob(ExecutionContext, ReportJob)
	 */
	void scheduleJob(ExecutionContext context, ReportJob job);

	/**
	 * Recreates the trigger for an existing report job.
	 * 
	 * <p>
	 * This action is performed when the schedule part of a report job is
	 * updated.
	 * </p>
	 * 
     * @param context the caller execution context
	 * @param job the updated report job details
	 * @see ReportSchedulingService#updateScheduledJob(ExecutionContext, ReportJob)
	 * @see ReportJob#getTrigger()
	 */
	void rescheduleJob(ExecutionContext context, ReportJob job);

	/**
	 * Removes the trigger for a scheduled job.
	 * 
     * @param context the caller execution context
	 * @param jobId the ID of the job whose trigger is to be removed
	 * @see ReportSchedulingService#removeScheduledJob(ExecutionContext, long)
	 * @see ReportSchedulingService#removeScheduledJobs(ExecutionContext, long[])
	 */
	void removeScheduledJob(ExecutionContext context, long jobId);
	
	/**
	 * Registers a scheduler listener.
	 * 
	 * The listener callback methods will be invoked when the corresponding
	 * actions are performed by this services.
	 * 
	 * @param listener the listener to register with the service
	 * @see #removeReportSchedulerListener(ReportSchedulerListener)
	 */
	void addReportSchedulerListener(ReportSchedulerListener listener);
	
	/**
	 * Unregisters a scheduler listener.
	 * 
	 * <p>
	 * If the listener was not previously registered, no action is performed.
	 * </p>
	 * 
	 * @param listener the listener to unregister
	 */
	void removeReportSchedulerListener(ReportSchedulerListener listener);
	
	/**
	 * Retrieves runtime information for a list of jobs.
	 * 
	 * <p>
	 * The services locates the trigger for each job in the list, and uses it
	 * to fill information related to the execution state of the job.
	 * </p>
	 * 
     * @param context the caller execution context
	 * @param jobIds the job IDs
	 * @return runtime information for the jobs
	 * @see ReportSchedulingService#getScheduledJobs(ExecutionContext, String)
	 * @see ReportSchedulingService#getScheduledJobs(ExecutionContext)
	 * @see ReportJobSummary#getRuntimeInformation()
	 */
	ReportJobRuntimeInformation[] getJobsRuntimeInformation(ExecutionContext context, long[] jobIds);
	
	/**
	 * Performs validation checks related to the job trigger for a report job.
	 * 
	 * @param job the report job to validate
	 * @param errors the validation object in which errors are collected
	 * @see ReportSchedulingService#validateJob(ExecutionContext, ReportJob)
	 * @since 1.2.0
	 */
	void validate(ReportJob job, ValidationErrors errors);

    /**
	 * Add (register) the given Calendar to the Scheduler.
	 *
	 * @param updateTriggers - whether or not to update existing triggers that referenced the already existing calendar so that they are 'correct' based on the new trigger.
     * @throws JSException - if there is an internal Scheduler error, or a Calendar with the same name already exists, and replace is false.
     * @see org.quartz.Scheduler#addCalendar(String, org.quartz.Calendar, boolean, boolean)
	 * @since 4.7
	 */
    public void addCalendar(String calName, Calendar calendar, boolean replace, boolean updateTriggers) throws JSException;

    /**
	 * Delete the identified Calendar from the Scheduler.
	 *
	 * @return true if the Calendar was found and deleted.
     * @throws JSException - if there is an internal Scheduler error.
     * @see org.quartz.Scheduler#deleteCalendar(String)
	 * @since 4.7
	 */
    public boolean deleteCalendar(java.lang.String calName) throws JSException;

    /**
	 * Add the Calendar by name
	 *
	 * @param calName - name of the calendar
     * @throws JSException - if calendar is not found, throw exception
     * @see org.quartz.Scheduler#getCalendar(String)
	 * @since 4.7
	 */
    public Calendar getCalendar(java.lang.String calName) throws JSException;

    /**
	 * Get the names of all registered Calendars.
	 *
	 * @return the names of all registered Calendars
     * @see org.quartz.Scheduler#getCalendarNames()
	 * @since 4.7
	 */
    public List<String> getCalendarNames() throws JSException;

  /**
   *  Pause currently scheduled jobs execution.
   *  Does not delete the jobs
   *
   *  Throws JSException if Quartz rejects any pause commands.
   *  If the method was called with a jobs List, then the exception
   *  will list which jobs succeeded and which failed.
   *
   * @param jobs   List of individual jobs to pause
   * @param all    true = pause all jobs (disregard the input jobs list)
   *               false = pause individual jobs in the input jobs list
   */
  void pause(List<ReportJob> jobs, boolean all);

  /**
   *
   *  Resume currently scheduled jobs execution.
   *
   *  Throws JSException if Quartz rejects any resume commands.
   *  If the method was called with a jobs List, then the exception
   *  will list which jobs succeeded and which failed.
   *
   * @param jobs   List of individual jobs to resume
   * @param all    true  = resume all jobs (disregard the input jobs list)
   *               false = resume individual jobs in the input jobs list
   */
  void resume(List<ReportJob> jobs, boolean all);

  /**
   *  Pause currently scheduled jobs execution.
   *  Does not delete the jobs
   *
   *  Throws JSException if any pause commands are rejected.
   *  If the method was called with a jobs List, then the exception
   *  will list which jobs succeeded and which failed.
   *
   * @param jobs   List of individual job ids to pause
   * @param all    true = pause all jobs (disregard the input jobs list)
   *               false = pause individual jobs in the input jobs list
   */
  void pauseById(List<ReportJobIdHolder> jobs, boolean all);

  /**
   *
   *  Resume currently scheduled jobs execution.
   *
   *  Throws JSException if any resume commands are rejected.
   *  If the method was called with a jobs List, then the exception
   *  will list which jobs succeeded and which failed.
   *
   * @param jobs   List of individual job ids to resume
   * @param all    true  = resume all jobs (disregard the input jobs list)
   *               false = resume individual jobs in the input jobs list
   */
  void resumeById(List<ReportJobIdHolder> jobs, boolean all);
}
