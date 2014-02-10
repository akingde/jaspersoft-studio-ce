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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

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
	private ReportExecutionOptions options;

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
	public ReportUnitResult getReportUnitResult() {
		return reportUnitResult;
	}

	public void setReportUnitResult(ReportUnitResult reportUnitResult) {
		this.reportUnitResult = reportUnitResult;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	@XmlElement
	public String getRequestId() {
		return reportUnitResult != null ? reportUnitResult.getRequestId() : null;
	}

	public void setRequestId(String id) {
		if (reportUnitResult == null)
			reportUnitResult = new ReportUnitResult(getReportURI(), null);
		reportUnitResult.setRequestId(id);
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

	@XmlElement
	public String getReportURI() {
		return reportUnitResult != null ? reportUnitResult.getReportUnitURI() : null;
	}

	public void setReportURI(String uri) {
		String id = getRequestId();
		reportUnitResult = new ReportUnitResult(uri, null);
		reportUnitResult.setRequestId(id);
	}

	public ExecutionStatus getStatus() {
		return status;
	}

	public void setStatus(ExecutionStatus status) {
		this.status = status;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
}
