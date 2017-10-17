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

import java.util.Date;
import java.util.List;

import com.jaspersoft.jasperserver.api.JasperServerAPI;
import com.jaspersoft.jasperserver.api.common.domain.ExecutionContext;
import com.jaspersoft.jasperserver.api.common.domain.ValidationErrors;
import com.jaspersoft.jasperserver.api.engine.scheduling.domain.ReportJob;
import com.jaspersoft.jasperserver.api.engine.scheduling.domain.ReportJobIdHolder;
import com.jaspersoft.jasperserver.api.engine.scheduling.domain.ReportJobRuntimeInformation;
import com.jaspersoft.jasperserver.api.engine.scheduling.domain.ReportJobSummary;
import com.jaspersoft.jasperserver.api.engine.scheduling.domain.reportjobmodel.ReportJobModel;


/**
 * The central report scheduling service.
 *
 * <p>
 * This interface contains report job management methods that should be used by
 * code which deals with report scheduling.
 * </p>
 *
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: ReportSchedulingService.java 22767 2012-03-23 17:50:51Z ichan $
 * @since 1.0
 */
@JasperServerAPI
public interface ReportSchedulingService {

    /**
     * Schedules a new report job.
     *
     * <p>
     * The job contains details on which report to execute, when to execute it
     * and what to do with the job output.
     * This method will persisted the job details and will register a trigger
     * in the scheduler to fire at the required points in time.
     * </p>
	 * 
	 * <p>
	 * An ID for the job will be automatically generated and returned as part of
	 * the scheduled job.
	 * </p>
     *
     * @param context the caller execution context
     * @param job the details of the job to schedule
     * @return the job details as scheduled
     */
	ReportJob scheduleJob(ExecutionContext context, ReportJob job);


  /**
   *   Take input List<ReportJob>  schedule each job as a cloned job to be
   *   run once and immediately.  The run-once Job's Trigger will have its misfire instruction
   *   set to MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY
   *   No attempt is made to retry the run-once job in the event of failure during execution
   *
   *
   * @param context
   * @param jobs
   * @return     List<ReportJob>  list of jobs successfully scheduled
   */
  public List<ReportJob> scheduleJobsOnceNow(ExecutionContext context, List<ReportJob> jobs);

  /**
   *   Take input List<ReportJobIdHolder>  schedule each job as a cloned job to be
   *   run once and immediately.  The run-once Job's Trigger will have its misfire instruction
   *   set to MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY
   *   No attempt is made to retry the run-once job in the event of failure during execution
   *
   *
   * @param context
   * @param jobs
   * @return     List<ReportJobIdHolder>  list of jobs successfully scheduled
   */
  public List<ReportJobIdHolder> scheduleJobsOnceNowById(ExecutionContext context, List<ReportJobIdHolder> jobs);

    /**
     * Updates an existing report job.
     *
     * <p>
     * The existing job is determined by the ID of the job argument.
     * The details of the existing job will be updated according to the report
     * job object passed as argument.
     * </p>
     *
     * <p>
     * If any attribute of the job trigger has changed, the scheduler will drop
     * and recreate the trigger used to fire the job.
     * </p>
     *
     * @param context the caller execution context
     * @param job the job details to update
     * @throws ReportJobNotFoundException when a job with the specified ID is 
     * not found
     * @see ReportJob#getId()
     * @see ReportJob#getTrigger()
     * @see ReportJob#getVersion()
     */
	void updateScheduledJob(ExecutionContext context, ReportJob job)
			throws ReportJobNotFoundException;

    /**
	 * Updates the details of an existing report job.
	 *
	 * <p>
     * The existing job is determined by the ID of the job argument.
     * </p>
	 *
     * @param context the caller execution context
     * @param reportJobHolders the list of job IDs to update
	 * @param jobModel the job details to update (all the non-null items in the report job criteria)
     * @param replaceTriggerIgnoreType if replaceTriggerIgnoreType = true, it will replace the trigger from the model.
     *         Otherwise, all reportJobs must have a same trigger type as the model
	 * @return the job as updated
     * @throws TriggerTypeMismatchException when a job with the specified ID has a mismatch trigger type with the model
     * @throws ReportJobNotFoundException when a job with the specified ID is not found in the list
     * @throws DuplicateOutputLocationException when a job with the specified ID has a duplicate output location with other report jobs
	 */
	List<ReportJobIdHolder> updateScheduledJobsByID(ExecutionContext context, List<ReportJobIdHolder> reportJobHolders, ReportJobModel jobModel, boolean replaceTriggerIgnoreType)
        throws TriggerTypeMismatchException, ReportJobNotFoundException, DuplicateOutputLocationException;

     /**
	 * Updates the details of an existing report job.
	 *
	 * <p>
     * The existing job is determined by the ID of the job argument.
     * </p>
	 *
     * @param context the caller execution context
     * @param reportJobs the list of jobs to update
	 * @param jobModel the job details to update (all the non-null items in the report job criteria)
     * @param replaceTriggerIgnoreType if replaceTriggerIgnoreType = true, it will replace the trigger from the model.
     *         Otherwise, all reportJobs must have a same trigger type as the model
	 * @return the job as updated
     * @throws TriggerTypeMismatchException when a job with the specified ID has a mismatch trigger type with the model
     * @throws DuplicateOutputLocationException when a job with the specified ID has a duplicate output location with other report jobs
	 */
	List<ReportJob> updateScheduledJobs(ExecutionContext context, List<ReportJob> reportJobs, ReportJobModel jobModel, boolean replaceTriggerIgnoreType)
        throws TriggerTypeMismatchException, DuplicateOutputLocationException;

    /**
     * Returns a list of active jobs defined for a report.
     *
     * <p>
     * The method will return a list of {@link com.jaspersoft.jasperserver.api.engine.scheduling.domain.ReportJobSummary} objects which
     * contain summary information about jobs along with runtime information
     * related to the execution status of the jobs.
     * </p>
     *
     * <p>
     * Only active jobs will be listed, no information is available for jobs
     * that have completed.
     * </p>
     *
     * @param context the caller execution context
     * @param reportUnitURI the repository URI/path of the report for which jobs
     * should be listed
     * @return a list of {@link com.jaspersoft.jasperserver.api.engine.scheduling.domain.ReportJobSummary} objects
     * @deprecated use #getScheduledJobSummaries(ExecutionContext context, String reportUnitURI) with parametrized list output
     */
	List getScheduledJobs(ExecutionContext context, String reportUnitURI);
	
    /**
     * Returns the list of all active report jobs defined in the scheduler.
     *
     * <p>
     * All jobs that are accessible in the current context will be returned by
     * this method. 
     * </p>
     *
     * @param context the caller execution context
     * @return a list of {@link com.jaspersoft.jasperserver.api.engine.scheduling.domain.ReportJobSummary} objects
     * @since 2.1.0
     * @see #getScheduledJobs(ExecutionContext, String)
     * @deprecated use #getScheduledJobSummaries(ExecutionContext context) with parametrized list output
     */
	List getScheduledJobs(ExecutionContext context);
/**
     * Returns a list of active jobs defined for a report.
     *
     * <p>
     * The method will return a list of {@link com.jaspersoft.jasperserver.api.engine.scheduling.domain.ReportJobSummary} objects which
     * contain summary information about jobs along with runtime information
     * related to the execution status of the jobs.
     * </p>
     *
     * <p>
     * Only active jobs will be listed, no information is available for jobs
     * that have completed.
     * </p>
     *
     * @param context the caller execution context
     * @param reportUnitURI the repository URI/path of the report for which jobs
     * should be listed
     * @return a list of {@link com.jaspersoft.jasperserver.api.engine.scheduling.domain.ReportJobSummary} objects
     */
	List<ReportJobSummary> getScheduledJobSummaries(ExecutionContext context, String reportUnitURI);

    /**
     * Returns the list of all active report jobs defined in the scheduler.
     *
     * <p>
     * All jobs that are accessible in the current context will be returned by
     * this method.
     * </p>
     *
     * @param context the caller execution context
     * @return a list of {@link com.jaspersoft.jasperserver.api.engine.scheduling.domain.ReportJobSummary} objects
     * @since 2.1.0
     * @see #getScheduledJobSummaries(ExecutionContext, String)
     */
	List<ReportJobSummary> getScheduledJobSummaries(ExecutionContext context);

    /**
     * Returns a list of active jobs defined for a report.
     *
     * <p>
     * The method will return a list of {@link com.jaspersoft.jasperserver.api.engine.scheduling.domain.ReportJobSummary} objects which
     * contain summary information about jobs along with runtime information
     * related to the execution status of the jobs.
     * </p>
     *
     * <p>
     * Only active jobs will be listed, no information is available for jobs
     * that have completed.
     * </p>
     *
     * @param context the caller execution context
     * @param reportJobCriteria - filter by all the non-null items in the report job criteria should be listed
     * @param startIndex - beginning index from the search list
     * @param numberOfRows - number of rows to show in the list.  Set to -1 to show all the rows from the start index
     * @param sortType - apply sorting filter to the list
     * @param isAscending - sort in ascending order.  (It only takes effect when sort type is in used)
     * @return a list of {@link com.jaspersoft.jasperserver.api.engine.scheduling.domain.ReportJobSummary} objects
     */
	List<ReportJobSummary> getScheduledJobSummaries(ExecutionContext context, ReportJobModel reportJobCriteria, int startIndex, int numberOfRows,
                ReportJobModel.ReportJobSortType sortType, boolean isAscending);

  /**
   *
   * Get ReportJobSummary List for all ReportJobs whose trigger's nextFireTime is set to
   * between the startDate and endDate inclusive.
   *
   * You may also filter to include only those Triggers are in the
   * com.jaspersoft.jasperserver.api.engine.scheduling.domain.ReportJobRuntimeInformation
   * states listed in includeTriggerStates
   *
   *
   * @param context
   * @param searchList
   * @param startDate
   * @param endDate
   * @param includeTriggerStates
   * @return
   */
  List<ReportJobSummary> getJobsByNextFireTime(ExecutionContext context,
                                                      List<ReportJob> searchList,
                                                      Date startDate,
                                                      Date endDate,
                                                      List<Byte> includeTriggerStates);

    /**
     * Returns runtime information (i.e. status) of job with given ID
     *
     * @param context the caller execution context
     * @param jobId the ID of requested gob
     * @return {@link ReportJobRuntimeInformation} of job with given ID
     * @since 4.7
     */
    public ReportJobRuntimeInformation getJobRuntimeInformation(ExecutionContext context, long jobId);

	/**
	 * Removes a scheduled job.
	 * 
	 * <p>
	 * This method deletes the persisted job details and unregisters the trigger
	 * from the scheduler.
	 * If a job with the specified ID is not found, no operation is performed.
	 * </p>
	 * 
     * @param context the caller execution context
	 * @param jobId the ID of the job to delete
	 * @see ReportJob#getId()
	 */
	void removeScheduledJob(ExecutionContext context, long jobId);

	/**
	 * Removes a list of scheduled jobs.
	 * 
	 * <p>
	 * The IDs which do not correspond to jobs will be ignored.
	 * </p>
	 * 
     * @param context the caller execution context
	 * @param jobIds the IDs of the jobs to delete
	 * @see #removeScheduledJob(ExecutionContext, long)
	 */
	void removeScheduledJobs(ExecutionContext context, long[] jobIds);
	
	/**
	 * Loads the details of a report job.
	 * 
     * @param context the caller execution context
	 * @param jobId the ID of the job to load
	 * @return the details of the requested job, or <code>null</code> if no job
	 * with the specified ID was found
	 * @see ReportJob#getId()
	 */
	ReportJob getScheduledJob(ExecutionContext context, long jobId);


	/**
	 * Checks a report job for validation errors.
	 * 
	 * <p>
	 * This method can be used to validate a report job prior to saving it.
	 * The job will be automatically validated on save, and the operation will
	 * fail if any validation errors are encountered.
	 * </p>
	 * 
     * @param context the caller execution context
	 * @param job the job details
	 * @return a validation object which contains errors if any found
	 * @see ValidationErrors#isError()
	 */
	ValidationErrors validateJob(ExecutionContext context, ReportJob job);

  /**
   *  Pause currently scheduled jobs execution.
   *  Does not delete the jobs
   *
   *  Throws JSException if any pause commands are rejected.
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
   *  Throws JSException if any resume commands are rejected.
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
