/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.protocol;

import java.util.List;
import java.util.Map;

import org.apache.http.cookie.Cookie;

import com.jaspersoft.ireport.jasperserver.ws.FileContent;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.Argument;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.dto.common.ErrorDescriptor;

import net.sf.jasperreports.eclipse.util.Misc;

public class ReportExecution {
	// in
	private ResourceDescriptor rd;

	public ResourceDescriptor getResourceDescriptor() {
		return rd;
	}

	public void setResourceDescriptor(ResourceDescriptor rd) {
		this.rd = rd;
	}

	public Map<String, Object> getPrm() {
		return prm;
	}

	public void setPrm(Map<String, Object> prm) {
		this.prm = prm;
	}

	public List<Argument> getArgs() {
		return args;
	}

	public void setArgs(List<Argument> args) {
		this.args = args;
	}

	private Map<String, Object> prm;
	private List<Argument> args;

	// out
	private Integer currentPage;
	private String reportURI;
	private String reportURIFull;

	public String getReportURIFull() {
		return reportURIFull;
	}

	public void setReportURIFull(String reportURIFull) {
		this.reportURIFull = reportURIFull;
	}

	private String requestId;
	private String status;
	private Integer totalPages;
	private ErrorDescriptor errorDescriptor;

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public String getReportURI() {
		return reportURI;
	}

	public void setReportURI(String reportURI) {
		this.reportURI = reportURI;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public ErrorDescriptor getErrorDescriptor() {
		return errorDescriptor;
	}

	public void setErrorDescriptor(ErrorDescriptor errorDescriptor) {
		this.errorDescriptor = errorDescriptor;
	}

	public Map<String, FileContent> getFiles() {
		return files;
	}

	public void setFiles(Map<String, FileContent> files) {
		this.files = files;
	}

	private String baseUrl;
	private String pathUrl;
	// private String reportOutputURL;
	private List<Cookie> reportOutputCookie;

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public String getPathUrl() {
		return pathUrl;
	}

	public void setPathUrl(String pathUrl) {
		this.pathUrl = pathUrl;
	}

	public String getReportOutputURL() {
		String url = null;
		if (baseUrl != null)
			url = baseUrl;
		if (pathUrl != null)
			url = Misc.nvl(url) + pathUrl;
		return url;
	}

	// public void setReportOutputURL(String reportURL) {
	// this.reportOutputURL = reportURL;
	// }

	public List<Cookie> getReportOutputCookie() {
		return reportOutputCookie;
	}

	public void setReportOutputCookie(List<Cookie> reportOutputCookie) {
		this.reportOutputCookie = reportOutputCookie;
	}

	private Map<String, FileContent> files;
}
