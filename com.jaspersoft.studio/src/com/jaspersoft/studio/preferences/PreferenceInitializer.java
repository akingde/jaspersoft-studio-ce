/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.preferences;

import java.util.List;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.backward.JRVersionPreferencesPages;
import com.jaspersoft.studio.editor.toolitems.ToolItemsPreferencePage;
import com.jaspersoft.studio.preferences.execution.ReportExecutionPreferencePage;
import com.jaspersoft.studio.preferences.exporter.CSVExporterPreferencePage;
import com.jaspersoft.studio.preferences.exporter.DOCXExporterPreferencePage;
import com.jaspersoft.studio.preferences.exporter.G2DExporterPreferencePage;
import com.jaspersoft.studio.preferences.exporter.HTMLExporterPreferencePage;
import com.jaspersoft.studio.preferences.exporter.JRExporterPreferencePage;
import com.jaspersoft.studio.preferences.exporter.ODSExporterPreferencePage;
import com.jaspersoft.studio.preferences.exporter.PDFExporterPreferencePage;
import com.jaspersoft.studio.preferences.exporter.TextExporterPreferencePage;
import com.jaspersoft.studio.preferences.exporter.XMLExporterPreferencePage;
import com.jaspersoft.studio.preferences.theme.ThemesPreferencePage;

import net.sf.jasperreports.eclipse.preferences.IPreferencePageExtension;
import net.sf.jasperreports.eclipse.preferences.PreferencesExtensionsManager;

/*
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#
	 * initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = JaspersoftStudioPlugin.getInstance().getPreferenceStore();

		initDefaultProperties(store);
	}

	public static void initDefaultProperties(IPreferenceStore store) {
		GlobalPreferencePage.getDefaults(store);
		PropertiesPreferencePage.getDefaults(store);

		DesignerPreferencePage.getDefaults(store);
		ExpressionEditorPreferencePage.getDefaults(store);
		RulersGridPreferencePage.getDefaults(store);
		JRExporterPreferencePage.getDefaults(store);
		CSVExporterPreferencePage.getDefaults(store);
		HTMLExporterPreferencePage.getDefaults(store);
		PDFExporterPreferencePage.getDefaults(store);
		XMLExporterPreferencePage.getDefaults(store);
		TextExporterPreferencePage.getDefaults(store);

		ODSExporterPreferencePage.getDefaults(store);
		DOCXExporterPreferencePage.getDefaults(store);
		G2DExporterPreferencePage.getDefaults(store);

		StudioPreferencePage.getDefaults(store);
		ReportExecutionPreferencePage.getDefaults(store);
		ToolItemsPreferencePage.getDefaults(store);
		PalettePreferencePage.getDefaults(store);

		ThemesPreferencePage.getDefaults(store);

		JRVersionPreferencesPages.getDefaults(store);
		
		//initialize contributed default properties
		List<IPreferencePageExtension> contributedPages = PreferencesExtensionsManager.getContributedPreferencePages();
		if (contributedPages != null){
			for(IPreferencePageExtension page : contributedPages){
				page.initDefaultProperties(store);
			}
		}
	}

}
