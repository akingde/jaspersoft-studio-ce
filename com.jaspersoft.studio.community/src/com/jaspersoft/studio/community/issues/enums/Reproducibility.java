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
 * Enumeration representing the issue reproducibility.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public enum Reproducibility {
	Always(10,"Always"),
	Sometimes(30,"Sometimes"),
	Random(50,"Random"),
	NotAttempted(70,"Not Attempted"),
	UnableToReproduce(90,"Unable to Reproduce"),
	NotAvailable(100,"N/A");
	
	public static final String FIELD_NAME = "field_bug_reproducibility";
	private Integer value;
	private String text;
	
	private Reproducibility(Integer value,String text){
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
