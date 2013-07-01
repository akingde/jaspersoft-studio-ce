package com.jaspersoft.studio.preferences;

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
	protected void createFieldEditors() {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getPageId() {
		return PAGE_ID;
	}
}
