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
 * Enumeration representing the issue category.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public enum Category {
	BugReport("bug","Bug report"),
	Task("task","Task"),
	FeatureRequest("feature","Feature request"),
	Enhancement("enhancement", "Enhancement request"),
	General("general","General"),
	Patch("patch","Patch");
	
	public static final String FIELD_NAME = "field_bug_category";
	private String value;
	private String text;
	
	private Category(String value,String text){
		this.value = value;
		this.text = text;
	}
	
	public String getText(){
		return this.text;
	}
	
	public String getValue() {
		return this.value;
	}
	
}
