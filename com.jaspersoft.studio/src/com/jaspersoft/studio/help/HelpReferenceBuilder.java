/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.help;

public class HelpReferenceBuilder implements IHelpRefBuilder {

	private String helpref;

	public HelpReferenceBuilder(String reference) {
		this.helpref = reference;
	}

	@Override
	public String getHelpReference() {
		return helpref;
	}

}
