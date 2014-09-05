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

import java.util.List;

import com.jaspersoft.jasperserver.api.JasperServerAPI;
import com.jaspersoft.jasperserver.api.common.domain.ExecutionContext;
import com.jaspersoft.jasperserver.api.engine.scheduling.domain.ReportJob;
import com.jaspersoft.jasperserver.api.engine.scheduling.domain.ReportJobIdHolder;
import com.jaspersoft.jasperserver.api.engine.scheduling.domain.ReportJobSummary;
import com.jaspersoft.jasperserver.api.engine.scheduling.domain.reportjobmodel.ReportJobModel;

/**
 * A service that persists report job details.
 * 
 * <p>
 * This service would not be directly called by code that does report scheduling
 * in regular scenarios, such code would normally use {@link ReportSchedulingService}.
 * This persistence service is used internal by the central scheduling service.
 * </p>
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: ReportJobsPersistenceService.java 22767 2012-03-23 17:50:51Z ichan $
 * @since 1.0
 * @see ReportSchedulingService
 * @see ReportJobsScheduler
 */
@JasperServerAPI
public interface ReportJobsPersistenceService {

	/**
	 * Saves the details of a newly created report job.
	 * 
	 * <p>
	 * An ID for the job will be automatically generated and returned as part of
	 * the saved job.
	 * </p>
	 * 
     * @param context the caller execution context
	 * @param job the job details to save
	 * @return the job details as saved
	 * @see ReportSchedulingService#scheduleJob(ExecutionContext, ReportJob)
	 */
	ReportJob saveJob(ExecutionContext context, ReportJob job);

	/**
	 * Updates the details of an existing report job.
	 * 
	 * <p>
     * The existing job is determined by the ID of the job argument.
     * </p>
	 * 
     * @param context the caller execution context
	 * @param job the job details to update 
	 * @return the job as updated
     * @throws ReportJobNotFoundException when a job with the specified ID is 
     * not found
     * @see ReportSchedulingService#updateScheduledJob(ExecutionContext, ReportJob)
	 */
	ReportJob updateJob(ExecutionContext context, ReportJob job)
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
	 * @param jobModel the job details to update (all the modified items in the report job criteria)
     * @param replaceTriggerIgnoreType if replaceTriggerIgnoreType = true, it will replace the trigger from the model.
     *         Otherwise, all reportJobs must have a same trigger type as the model
	 * @return the job as updated
     * @throws TriggerTypeMismatchException when a job with the specified ID has a mismatch trigger type with the model
     * @throws ReportJobNotFoundException when a job with the specified ID is not found in the list
     * @throws DuplicateOutputLocationException when a job with the specified ID has a duplicate output location with other report jobs
     * @see ReportSchedulingService#updateScheduledJobsByID(com.jaspersoft.jasperserver.api.common.domain.ExecutionContext, java.util.List, com.jaspersoft.jasperserver.api.engine.scheduling.domain.reportjobmodel.ReportJobModel, boolean)
	 */
	List<ReportJobIdHolder> updateJobsByID(ExecutionContext context, List<ReportJobIdHolder> reportJobHolders, ReportJobModel jobModel, boolean replaceTriggerIgnoreType)
        throws TriggerTypeMismatchException, ReportJobNotFoundException, DuplicateOutputLocationException;

     /**
	 * Updates the details of an existing report job.
	 *
	 * <p>
     * The existing job is determined by the ID of the job argument.
     * </p>
	 *
     * @param context the caller execution context
     * @param reportJobHolders the list of job IDs to update
	 * @param jobModel the job details to update (all the modified items in the report job criteria)
     * @param replaceTriggerIgnoreType if replaceTriggerIgnoreType = true, it will replace the trigger from the model.
     *         Otherwise, all reportJobs must have a same trigger type as the model
	 * @return the job as updated
     * @throws TriggerTypeMismatchException when a job with the specified ID has a mismatch trigger type with the model
     * @throws ReportJobNotFoundException when a job with the specified ID is not found in the list
     * @throws DuplicateOutputLocationException when a job with the specified ID has a duplicate output location with other report jobs
     * @see ReportSchedulingService#updateScheduledJobs(com.jaspersoft.jasperserver.api.common.domain.ExecutionContext, java.util.List, com.jaspersoft.jasperserver.api.engine.scheduling.domain.reportjobmodel.ReportJobModel, boolean)
	 */
	List<ReportJob> updateJobs(ExecutionContext context, List<ReportJob> reportJobHolders, ReportJobModel jobModel, boolean replaceTriggerIgnoreType)
         throws TriggerTypeMismatchException, ReportJobNotFoundException, DuplicateOutputLocationException;

	/**
	 * Loads the details of a report job.
	 * 
     * @param context the caller execution context
	 * @param jobId the ID of the job to load
	 * @return the details of the requested job, or <code>null</code> if no job
	 * with the specified ID was found
	 * @see ReportSchedulingService#getScheduledJob(ExecutionContext, long)
	 */
	ReportJob loadJob(ExecutionContext context, ReportJobIdHolder jobId);

    /**
	 * Loads the details of a list of report jobs.
	 *
     * @param context the caller execution context
	 * @param reportJobHolders the list of job IDs to load
	 * @return the details of the requested job, or <code>null</code> if no job
	 * with the specified ID was found
	 * @see ReportSchedulingService#getScheduledJob(ExecutionContext, long)
	 */
    List<ReportJob> loadJobs(ExecutionContext context, List<ReportJobIdHolder> reportJobHolders);

	/**
	 * Removes a saved report job.
	 * 
	 * <p>
	 * If a job with the specified ID is not found, no operation is performed.
	 * </p>
	 * 
     * @param context the caller execution context
	 * @param jobId the ID of the job to delete
	 * @see ReportSchedulingService#removeScheduledJob(ExecutionContext, long)
	 */
	void deleteJob(ExecutionContext context, ReportJobIdHolder jobId);

	/**
     * Returns a list of jobs saved for a report.
     * 
     * @param context the caller execution context
     * @param reportUnitURI the repository URI/path of the report for which jobs
     * should be listed
     * @return a list of {@link ReportJobSummary} objects with no runtime
     * information
	 * @see ReportSchedulingService#getScheduledJobs(ExecutionContext, String)
	 */
	List listJobs(ExecutionContext context, String reportUnitURI);


  /**
   *
   * Convenience method to retrieve a List<ReportJobSummary> for a List of ReportJobs
   *
   * @param context
   * @param reportJobs
   * @return   List<ReportJobSummary>
   *
   */
  List<ReportJobSummary> listJobs(ExecutionContext context, List<ReportJob> reportJobs);


    /**
     * Returns a list of jobs saved for a report.
     *
     * @param context the caller execution context
     * @param reportJobCriteria  filter by all the non-runtime modified items in the report job criteria
     *                   (please use ReportSchedulingService.getScheduledJobSummaries#get(ExecutionContext, ReportJobModel,
     *                   int, int, ReportJobModel.ReportJobSortType, boolean) for filtering by job runtime information)
     * @param startIndex - beginning index from the search list
     * @param numberOfRows - number of rows to show in the list.  Set to -1 to show all the rows from the start index
     * @param sortType - apply sorting filter (non-runtime item) to the list
     *                   (please use ReportSchedulingService.getScheduledJobSummaries#get(ExecutionContext, ReportJobModel,
     *                   int, int, ReportJobModel.ReportJobSortType, boolean) for sorting by job runtime information)
     * @param isAscending - sort in ascending order (It only takes effect when sort type is in used)
     * @return a list of {@link com.jaspersoft.jasperserver.api.engine.scheduling.domain.ReportJobSummary} objects
	 * @see ReportSchedulingService#getScheduledJobSummaries(ExecutionContext, ReportJobModel, int, int, ReportJobModel.ReportJobSortType, boolean)
	 */
	List<ReportJobSummary> listJobs(ExecutionContext context, ReportJobModel reportJobCriteria, int startIndex, int numberOfRows,
                ReportJobModel.ReportJobSortType sortType, boolean isAscending);

	/**
     * Returns the list of all saved report jobs.
	 * 
     * @param context the caller execution context
     * @return a list of {@link ReportJobSummary} objects with no runtime
     * information
	 * @see ReportSchedulingService#getScheduledJobs(ExecutionContext)
	 */
	List listJobs(ExecutionContext context);


}
