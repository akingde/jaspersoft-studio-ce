/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbench;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.util.FieldEditorOverlayPage;
import com.jaspersoft.studio.statistics.BooleanLinkFieldEditor;

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
	 * Creates the field editors. Field editors are abstractions of the common GUI blocks needed to manipulate various
	 * types of preferences. Each field editor knows how to save and restore itself.
	 */
	public void createFieldEditors() {

		addField(new BooleanFieldEditor(CHECK_FOR_UPDATE, Messages.StudioPreferencePage_checkForUpdates,
				getFieldEditorParent()));
		addField(new BooleanLinkFieldEditor(JSS_SEND_USAGE_STATISTICS, Messages.StudioPreferencePage_collectUsageStatistics,
				getFieldEditorParent()));
		
		//Eventually create the extensions for the page
		super.createFieldEditors();
	}

	public void init(IWorkbench workbench) {
	}

	public static void getDefaults(IPreferenceStore store) {
		store.setDefault(CHECK_FOR_UPDATE, "true"); //$NON-NLS-1$
		store.setDefault(JSS_SEND_USAGE_STATISTICS, "false"); //$NON-NLS-1$
	}

	@Override
	public String getPageId() {
		return PAGE_ID;
	}

}
