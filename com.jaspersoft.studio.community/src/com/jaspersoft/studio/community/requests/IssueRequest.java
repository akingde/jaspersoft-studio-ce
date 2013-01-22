/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.community.requests;

import com.jaspersoft.studio.community.issues.IssueBody;
import com.jaspersoft.studio.community.issues.IssueField;

/**
 * Request data for new issue submission to the community tracker.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class IssueRequest {
	
	private static final String FIELD_SEPARATOR = ","; 

	private String nodeTitle;
	private String nodeStatus;
	private String nodeComment;
	private String nodePromote;
	private String nodeSticky;
	private String nodeType;
	private String nodeLanguage;
	private IssueBody nodeBody;
	
	// Fields related to field_bug_XXX
	private IssueField category;
	private IssueField priority;
	private IssueField severity;
	private IssueField reproducibility;
	private IssueField resolution;
	private IssueField status;
	private IssueField project;
	private IssueField assigned_user;
	private IssueField attachments;
	
	public IssueRequest(String title,String text){
		this.nodeTitle = title;
		this.nodeBody = new IssueBody(text);
		// default values for some node properties
		this.nodeStatus = "1";
		this.nodeComment = "2";
		this.nodePromote = "0";
		this.nodeSticky = "0";
		this.nodeType = "bug";
		this.nodeLanguage = "und";
	}

	public IssueField getCategory() {
		return category;
	}

	public void setCategory(IssueField category) {
		this.category = category;
	}

	public IssueField getPriority() {
		return priority;
	}

	public void setPriority(IssueField priority) {
		this.priority = priority;
	}

	public IssueField getSeverity() {
		return severity;
	}

	public void setSeverity(IssueField severity) {
		this.severity = severity;
	}

	public IssueField getReproducibility() {
		return reproducibility;
	}

	public void setReproducibility(IssueField reproducibility) {
		this.reproducibility = reproducibility;
	}

	public IssueField getResolution() {
		return resolution;
	}

	public void setResolution(IssueField resolution) {
		this.resolution = resolution;
	}

	public IssueField getStatus() {
		return status;
	}

	public void setStatus(IssueField status) {
		this.status = status;
	}

	public IssueField getProject() {
		return project;
	}

	public void setProject(IssueField project) {
		this.project = project;
	}

	public IssueField getAssigned_user() {
		return assigned_user;
	}

	public void setAssigned_user(IssueField assigned_user) {
		this.assigned_user = assigned_user;
	}

	public IssueField getAttachments() {
		return attachments;
	}

	public void setAttachments(IssueField attachments) {
		this.attachments = attachments;
	}

	public String getAsJSON() {
		StringBuffer jsonBuf = new StringBuffer();
		jsonBuf.append("{");
		jsonBuf.append("\"node\":{");
		jsonBuf.append("\"title\": \"").append(nodeTitle).append("\",");
		jsonBuf.append("\"status\": \"").append(nodeStatus).append("\",");
		jsonBuf.append("\"comment\": \"").append(nodeComment).append("\",");
		jsonBuf.append("\"promote\": \"").append(nodePromote).append("\",");
		jsonBuf.append("\"sticky\": \"").append(nodeSticky).append("\",");
		jsonBuf.append("\"type\": \"").append(nodeType).append("\",");
		jsonBuf.append("\"language\": \"").append(nodeLanguage).append("\",");
		jsonBuf.append(nodeBody.asJsonString()).append(FIELD_SEPARATOR);
		jsonBuf.append(category.asJsonString()).append(FIELD_SEPARATOR);
		jsonBuf.append(priority.asJsonString()).append(FIELD_SEPARATOR);
		jsonBuf.append(severity.asJsonString()).append(FIELD_SEPARATOR);
		jsonBuf.append(reproducibility.asJsonString()).append(FIELD_SEPARATOR);
		jsonBuf.append(resolution.asJsonString()).append(FIELD_SEPARATOR);
		jsonBuf.append(status.asJsonString()).append(FIELD_SEPARATOR);
		jsonBuf.append(project.asJsonString());
		if(assigned_user!=null && !assigned_user.asJsonString().isEmpty()){
			jsonBuf.append(FIELD_SEPARATOR).append(assigned_user.asJsonString());
		}
		if(attachments!=null && !attachments.asJsonString().isEmpty()){
			jsonBuf.append(FIELD_SEPARATOR).append(attachments.asJsonString());
		}
		jsonBuf.append("}");
		jsonBuf.append("}");
		return jsonBuf.toString();
	}
	
}
