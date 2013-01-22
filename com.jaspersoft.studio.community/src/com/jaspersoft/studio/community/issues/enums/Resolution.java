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
 * Enumeration representing the issue resolution.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public enum Resolution {
	Open(10,"Open"),
	Fixed(20,"Fixed"),
	Reopened(30,"Reopened"),
	UnableToReproduce(40,"Unable to Reproduce"),
	NotFixable(50,"Not Fixable"),
	Duplicate(60,"Duplicate"),
	NoChangeRequired(70,"NoChangeRequired"),
	WorksAsDesigned(75,"Works as Designed"),
	Suspended(80,"Suspended"),
	WontFix(90,"Won't Fix");
	
	public static final String FIELD_NAME = "field_bug_resolution";
	private Integer value;
	private String text;
	
	private Resolution(Integer value,String text){
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
