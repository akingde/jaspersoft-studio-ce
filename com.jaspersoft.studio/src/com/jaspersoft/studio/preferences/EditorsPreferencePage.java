/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.preferences;

import org.eclipse.core.resources.IResource;
import org.eclipse.ui.IWorkbench;

import com.jaspersoft.studio.preferences.util.FieldEditorOverlayPage;

public class EditorsPreferencePage extends FieldEditorOverlayPage {
	public static final String PAGE_ID = "com.jaspersoft.studio.preferences.EditorsPreferencePage.property"; //$NON-NLS-1$

	public EditorsPreferencePage() {
		super(GRID);
	}

	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getPageId() {
		return PAGE_ID;
	}

	@Override
	public boolean isPropertyPage() {
		return false;
	}

	@Override
	protected IResource getResource() {
		return null;
	}
}
