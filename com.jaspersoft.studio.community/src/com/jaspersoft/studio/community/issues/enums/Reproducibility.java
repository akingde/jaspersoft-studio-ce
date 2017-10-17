/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.community.issues.enums;

import com.jaspersoft.studio.community.messages.Messages;

/**
 * Enumeration representing the issue reproducibility.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public enum Reproducibility {
	Always(10,Messages.Reproducibility_Always),
	Sometimes(30,Messages.Reproducibility_Sometimes),
	Random(50,Messages.Reproducibility_Random),
	NotAttempted(70,Messages.Reproducibility_NotAttempted),
	UnableToReproduce(90,Messages.Reproducibility_UnableToReproduce),
	NotAvailable(100,Messages.Reproducibility_NotAvailable);
	
	public static final String FIELD_NAME = "field_bug_reproducibility"; //$NON-NLS-1$
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
