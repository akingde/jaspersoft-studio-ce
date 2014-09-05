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

import com.jaspersoft.jasperserver.api.JSException;
import com.jaspersoft.jasperserver.api.JasperServerAPI;
import com.jaspersoft.jasperserver.api.engine.scheduling.domain.reportjobmodel.ReportJobModel;

/**
 * Exception type used when a report job is not found for a specified ID.
 * 
 * @author Ivan Chan
 * @version $Id: ReportJobRuntimeInfoException.java 22122 2012-02-08 23:42:49Z ichan $
 * @since 4.7
 */
@JasperServerAPI
public class ReportJobRuntimeInfoException extends JSException {

	private final ReportJobModel.ReportJobSortType sortType;

	/**
	 * Creates a report job runtime info exception
	 *
	 */
	public ReportJobRuntimeInfoException(ReportJobModel.ReportJobSortType sortType) {
		super("jsexception.reportjob.runtime.information: not able to sort by runtime information [" + sortType.name()
                + "] using ReportJobPersistenceService.  Please use ReportSchedulingService.getScheduledJobSummaries() instead");
		this.sortType = sortType;
	}

	public ReportJobModel.ReportJobSortType getSortType() {
		return sortType;
	}

}
