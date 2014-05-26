/*
 * Copyright (C) 2005 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com.
 *
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 *
 * This program is free software: you can redistribute it and/or  modify
 * it under the terms of the GNU Affero General Public License  as
 * published by the Free Software Foundation, either version 3 of  the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero  General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public  License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.jaspersoft.jasperserver.api.engine.scheduling.service;

import com.jaspersoft.jasperserver.api.JasperServerAPI;
import com.jaspersoft.jasperserver.api.JSException;
import com.jaspersoft.jasperserver.api.engine.scheduling.domain.ReportJob;

/**
 * Exception type used when a report job is not found for a specified ID.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: ReportJobNotFoundException.java 19921 2010-12-11 14:52:49Z tmatyashovsky $
 * @since 1.0
 * @see ReportJob#getId()
 */
@JasperServerAPI
public class ReportJobNotFoundException extends JSException {

	private final long jobId;
	
	/**
	 * Creates a report job not found exception.
	 * 
	 * @param jobId the ID fow which finding a job failed
	 */
	public ReportJobNotFoundException(long jobId) {
		super("jsexception.report.job.not.found", new Object[] {new Long(jobId)});
		
		this.jobId = jobId;
	}
	
	/**
	 * Returns the ID for which a job was not found.
	 * 
	 * @return the ID for which a job was not found
	 */
	public long getJobId() {
		return jobId;
	}
	
}
