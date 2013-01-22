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
package com.jaspersoft.studio.community.issues.enums;

/**
 * Enumeration representing the issue status.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public enum Status {
	New(10,"New"),
	FeedbackRequested(20,"Feedback Requested"),
	Acknowledged(30,"Acknowledged"),
	Confirmed(40,"Confirmed"),
	Assigned(50,"Assigned"),
	Resolved(80,"Resolved"),
	Closed(90,"Closed");
	
	public static final String FIELD_NAME = "field_bug_status";
	private Integer value;
	private String text;
	
	private Status(Integer value,String text){
		this.value = value;
		this.text = text;
	}
	
	public String getText(){
		return this.text;
	}
	
	public Integer getValue() {
		return this.value;
	}
	
	public String getStringValue(){
		return Integer.toString(this.value);
	}
	
}
