/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.preferences;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbench;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.preferences.util.FieldEditorOverlayPage;

public class StudioPreferencePage extends FieldEditorOverlayPage {
	public static final String PAGE_ID = "com.jaspersoft.studio.preferences.StudioPreferencePage.property"; //$NON-NLS-1$

	public static final String REFERENCE_PREFIX = "net.sf.jasperreports.doc/docs/config.reference.html?cp=0_2#"; //$NON-NLS-1$
	public static final String JSS_SEND_USAGE_STATISTICS = "com.jaspersoft.studio.send_usage"; //$NON-NLS-1$
	public static final String CHECK_FOR_UPDATE = "show_update_dialog"; //$NON-NLS-1$

	public StudioPreferencePage() {
		super(GRID);
		setPreferenceStore(JaspersoftStudioPlugin.getInstance().getPreferenceStore());
		// setDescription("A demonstration of a preference page implementation");
	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common GUI
	 * blocks needed to manipulate various types of preferences. Each field editor
	 * knows how to save and restore itself.
	 */
	@Override
	public void createFieldEditors() {
		//statistic disabled for server shutdown
		/*addField(new BooleanFieldEditor(CHECK_FOR_UPDATE, Messages.StudioPreferencePage_checkForUpdates,
				getFieldEditorParent()));
		addField(new BooleanLinkFieldEditor(JSS_SEND_USAGE_STATISTICS,
				Messages.StudioPreferencePage_collectUsageStatistics, getFieldEditorParent()));*/

		// Eventually create the extensions for the page
		super.createFieldEditors();
	}

	public void init(IWorkbench workbench) {
	}

	public static void getDefaults(IPreferenceStore store) {
		store.setDefault(CHECK_FOR_UPDATE, "false"); //$NON-NLS-1$
		store.setDefault(JSS_SEND_USAGE_STATISTICS, "false"); //$NON-NLS-1$
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
