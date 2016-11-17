/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.report;

import org.eclipse.gef.EditDomain;

public class EditorContributor {
	private EditDomain editDomain;

	public EditDomain getEditDomain() {
		return editDomain;
	}

	public EditorContributor(EditDomain editDomain) {
		super();
		this.editDomain = editDomain;
	}

}
