/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.community.issues.enums;

import com.jaspersoft.studio.community.messages.Messages;

/**
 * Enumeration representing the issue priority.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public enum Priority {
	None(10,Messages.Priority_None),
	Low(20,Messages.Priority_Low),
	Normal(30,Messages.Priority_Normal),
	High(40,Messages.Priority_High),
	Urgent(50,Messages.Priority_Urgent),
	Immediate(60,Messages.Priority_Immediate);
	
	public static final String FIELD_NAME = "field_bug_priority"; //$NON-NLS-1$
	private Integer value;
	private String text;
	
	private Priority(Integer value,String text){
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
