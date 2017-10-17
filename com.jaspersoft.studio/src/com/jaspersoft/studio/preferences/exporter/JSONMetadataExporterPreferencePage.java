/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.preferences.exporter;

import net.sf.jasperreports.export.JsonMetadataReportConfiguration;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbench;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.help.HelpSystem;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.StudioPreferencePage;
import com.jaspersoft.studio.preferences.util.FieldEditorOverlayPage;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;

/*
 * 
 */
public class JSONMetadataExporterPreferencePage extends FieldEditorOverlayPage {

	public static final String PAGE_ID = "com.jaspersoft.studio.preferences.exporter.JSONExporterPreferencePage.property"; //$NON-NLS-1$

	public JSONMetadataExporterPreferencePage() {
		super(GRID);
		setPreferenceStore(JaspersoftStudioPlugin.getInstance().getPreferenceStore());
		setDescription(Messages.JSONMetadataExporterPreferencePage_0);
	}

	/**
	 *
	 */
	public void createFieldEditors() {
		BooleanFieldEditor bf = new BooleanFieldEditor(JsonMetadataReportConfiguration.JSON_EXPORTER_ESCAPE_MEMBERS,
				Messages.JSONMetadataExporterPreferencePage_1, getFieldEditorParent());
		addField(bf);
		HelpSystem.setHelp(bf.getDescriptionControl(getFieldEditorParent()),
				StudioPreferencePage.REFERENCE_PREFIX + bf.getPreferenceName());
		
		//Eventually create the extensions for the page
		super.createFieldEditors();
	}

	public static void getDefaults(IPreferenceStore store) {
		store.setDefault(JsonMetadataReportConfiguration.JSON_EXPORTER_ESCAPE_MEMBERS,
				PropertiesHelper.DPROP.getProperty(JsonMetadataReportConfiguration.JSON_EXPORTER_ESCAPE_MEMBERS));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

	@Override
	public String getPageId() {
		return PAGE_ID; //$NON-NLS-1$
	}

}
