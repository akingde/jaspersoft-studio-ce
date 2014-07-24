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

import com.jaspersoft.jasperserver.api.JasperServerAPI;

/**
 * Listener for report job scheduling execution events.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: ReportSchedulerListener.java 19921 2010-12-11 14:52:49Z tmatyashovsky $
 * @since 1.0
 * @see ReportJobsScheduler#addReportSchedulerListener(ReportSchedulerListener)
 */
@JasperServerAPI
public interface ReportSchedulerListener {
	
	/**
	 * Called when a job trigger has completed all scheduled executions.
	 * 
	 * @param jobId the ID of the completed job
	 */
	void reportJobFinalized(long jobId);
	
}
