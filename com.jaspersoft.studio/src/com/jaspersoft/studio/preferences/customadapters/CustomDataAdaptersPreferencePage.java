/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.preferences.customadapters;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbench;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.preferences.util.FieldEditorOverlayPage;

/**
 * Preference page to add a custom data adapter definition file
 * 
 * @author Orlandin Marco
 */
public class CustomDataAdaptersPreferencePage extends FieldEditorOverlayPage {
	
	public static final String CUSTOMDATAADAPTERS = "om.jaspersoft.studio.data.customadapters.adapters"; //$NON-NLS-1$

	public CustomDataAdaptersPreferencePage() {
		super(GRID);
		setPreferenceStore(JaspersoftStudioPlugin.getInstance().getPreferenceStore());
		setDescription("Choose a location for the Data Adapter Configurations Definitions");
	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common
	 * GUI blocks needed to manipulate various types of preferences. Each field
	 * editor knows how to save and restore itself.
	 */
	public void createFieldEditors() {
		addField(new FileEditor(CUSTOMDATAADAPTERS, "Configurations Location", "Select a Configuration File", getFieldEditorParent())); //$NON-NLS-1$ //$NON-NLS-2$
		super.createFieldEditors();
	}

	public static void getDefaults(IPreferenceStore store) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

	@Override
	public String getPageId() {
		return "com.jaspersoft.studio.preferences.customadapters.CustomDataAdaptersPreferencePage"; //$NON-NLS-1$
	}

}
