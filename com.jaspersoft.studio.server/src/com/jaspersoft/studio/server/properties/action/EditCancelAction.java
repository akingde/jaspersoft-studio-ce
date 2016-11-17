/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.properties.action;

import com.jaspersoft.studio.server.properties.ASection;

public class EditCancelAction extends AAction {
	public static final String ID = "cancelproperties-js";

	public EditCancelAction() {
		super("Cancel");
		setId(ID);
	}

	@Override
	public void run() {
		for (ASection s : sections)
			s.cancelEditProperties();
	}
}
