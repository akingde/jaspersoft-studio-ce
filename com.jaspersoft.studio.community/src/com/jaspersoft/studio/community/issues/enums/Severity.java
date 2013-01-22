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
 * Enumeration representing the issue severity.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public enum Severity {
	Feature(10,"Feature"),
	Trivial(20,"Trivial"),
	Text(30,"Text"),
	Tweak(40,"Tweak"),
	Minor(50,"Minor"),
	Major(60,"Major"),
	Critical(70,"Critical"),
	Block(80,"Block");
	
	public static final String FIELD_NAME = "field_bug_severity";
	private Integer value;
	private String text;
	
	private Severity(Integer value,String text){
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
