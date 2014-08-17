package com.jaspersoft.studio.server.wizard.exp;

import com.jaspersoft.jasperserver.remote.services.async.StateDto;

public class ExportOptions {
	private String file;
	private boolean incRepositoryPermission = true;
	private boolean incReportJobs = true;
	private StateDto state;

	public StateDto getState() {
		return state;
	}

	public void setState(StateDto state) {
		this.state = state;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public boolean isIncRepositoryPermission() {
		return incRepositoryPermission;
	}

	public void setIncRepositoryPermission(boolean incRepositoryPermission) {
		this.incRepositoryPermission = incRepositoryPermission;
	}

	public boolean isIncReportJobs() {
		return incReportJobs;
	}

	public void setIncReportJobs(boolean incReportJobs) {
		this.incReportJobs = incReportJobs;
	}
}
