/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.preferences.templates;

import org.eclipse.core.resources.IResource;
import org.eclipse.ui.IWorkbench;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.preferences.util.FieldEditorOverlayPage;

/**
 * Preference page for the locations of the JRXML templates.
 * 
 * @author Veaceslav Chicu (schicu@users.sourceforge.net)
 * 
 */
public class PathsPreferencePage extends FieldEditorOverlayPage {
	public static final String PAGE_ID = "com.jaspersoft.studio.preferences.templates"; //$NON-NLS-1$

	public PathsPreferencePage() {
		super(GRID);
		setPreferenceStore(JaspersoftStudioPlugin.getInstance().getPreferenceStore());
		setDescription("Location where you could have different resources");
	}

	@Override
	public void init(IWorkbench workbench) {
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
