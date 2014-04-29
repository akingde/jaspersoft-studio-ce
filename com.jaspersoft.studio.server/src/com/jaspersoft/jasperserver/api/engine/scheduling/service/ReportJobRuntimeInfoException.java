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
