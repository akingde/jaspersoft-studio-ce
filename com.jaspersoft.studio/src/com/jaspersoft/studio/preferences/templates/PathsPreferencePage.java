/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.preferences.templates;

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
	protected String getPageId() {
		return PAGE_ID;
	}

	@Override
	protected void createFieldEditors() {
	}

}
