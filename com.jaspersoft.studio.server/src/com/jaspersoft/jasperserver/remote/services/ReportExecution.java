/*
 * Copyright (C) 2005 - 2009 Jaspersoft Corporation. All rights  reserved.
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
 * along with this program.&nbsp; If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.jasperserver.remote.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.jaspersoft.jasperserver.api.JSException;
import com.jaspersoft.jasperserver.api.engine.jasperreports.domain.impl.ReportUnitResult;
import com.jaspersoft.jasperserver.remote.exception.xml.ErrorDescriptor;

/**
 * <p>
 * </p>
 * 
 * @author Yaroslav.Kovalchyk
 * @version $Id: ReportExecution.java 26599 2012-12-10 13:04:23Z ykovalchyk $
 */
@XmlRootElement
public class ReportExecution {
	private ExecutionStatus status;
	private Integer totalPages;
	private Integer currentPage;
	private ErrorDescriptor errorDescriptor;
	private Map<ExportExecutionOptions, ExportExecution> exports = new ConcurrentHashMap<ExportExecutionOptions, ExportExecution>();
	private ReportUnitResult reportUnitResult;
	private Map<String, String[]> rawParameters;
	private String requestId;
	private String reportURI;
	private ReportExecutionOptions options;
	private final Lock resultLock;
	private final Condition resultExist;

	public ReportExecution() {
		resultLock = new ReentrantLock();
		resultExist = resultLock.newCondition();
	}

	@XmlTransient
	public Map<String, String[]> getRawParameters() {
		return rawParameters;
	}

	public void setRawParameters(Map<String, String[]> rawParameters) {
		this.rawParameters = rawParameters;
	}

	@XmlTransient
	public ReportExecutionOptions getOptions() {
		return options;
	}

	public void setOptions(ReportExecutionOptions options) {
		this.options = options;
	}

	@XmlTransient
	public ReportUnitResult getFinalReportUnitResult() {
		resultLock.lock();
		try {
			while (reportUnitResult == null && status != ExecutionStatus.cancelled && status != ExecutionStatus.failed) {
				resultExist.await();
			}
		} catch (InterruptedException e) {
			throw new JSException(e);
		} finally {
			resultLock.unlock();
		}

		return reportUnitResult;
	}

	@XmlTransient
	public ReportUnitResult getReportUnitResult() {
		return reportUnitResult;
	}

	public void setReportUnitResult(ReportUnitResult reportUnitResult) {
		resultLock.lock();
		try {
			this.reportUnitResult = reportUnitResult;
			resultExist.signalAll();
		} finally {
			resultLock.unlock();
		}
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	@XmlElement(name = "export")
	@XmlElementWrapper(name = "exports")
	public Set<ExportExecution> getExportsSet() {
		return exports != null && !exports.isEmpty() ? new HashSet<ExportExecution>(exports.values()) : null;
	}

	public void setExportsSet(Set<ExportExecution> exportsSet) {
		if (exportsSet != null) {
			Map<ExportExecutionOptions, ExportExecution> exports = new HashMap<ExportExecutionOptions, ExportExecution>();
			for (ExportExecution currentExport : exportsSet) {
				exports.put(currentExport.getOptions(), currentExport);
			}
			setExports(exports);
		} else {
			setExports(null);
		}
	}

	@XmlTransient
	public Map<ExportExecutionOptions, ExportExecution> getExports() {
		return exports;
	}

	public void setExports(Map<ExportExecutionOptions, ExportExecution> exports) {
		this.exports = exports;
	}

	public ErrorDescriptor getErrorDescriptor() {
		return errorDescriptor;
	}

	public void setErrorDescriptor(ErrorDescriptor errorDescriptor) {
		this.errorDescriptor = errorDescriptor;
	}

	public String getReportURI() {
		return reportURI;
	}

	public void setReportURI(String reportURI) {
		this.reportURI = reportURI;
	}

	public ExecutionStatus getStatus() {
		return status;
	}

	public void setStatus(ExecutionStatus status) {
		resultLock.lock();
		try {
			this.status = status;
			// status has been changed. Let's signal result condition to check the
			// updated status
			// and stop waiting if cancelled or failed
			resultExist.signalAll();
		} finally {
			resultLock.unlock();
		}

	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
}
