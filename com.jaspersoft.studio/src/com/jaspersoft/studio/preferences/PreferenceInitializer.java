/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.preferences.exporter.CSVExporterPreferencePage;
import com.jaspersoft.studio.preferences.exporter.DOCXExporterPreferencePage;
import com.jaspersoft.studio.preferences.exporter.ExcelExporterPreferencePage;
import com.jaspersoft.studio.preferences.exporter.G2DExporterPreferencePage;
import com.jaspersoft.studio.preferences.exporter.HTMLExporterPreferencePage;
import com.jaspersoft.studio.preferences.exporter.JRExporterPreferencePage;
import com.jaspersoft.studio.preferences.exporter.PDFExporterPreferencePage;
import com.jaspersoft.studio.preferences.exporter.TextExporterPreferencePage;
import com.jaspersoft.studio.preferences.exporter.XMLExporterPreferencePage;
import com.jaspersoft.studio.preferences.qexecutor.QueryExecutersPreferencePage;
import com.jaspersoft.studio.preferences.virtualizer.VirtualizerPreferencePage;

/*
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = JaspersoftStudioPlugin.getInstance().getPreferenceStore();

		initDefaultProperties(store);
	}

	public static void initDefaultProperties(IPreferenceStore store) {
		PropertiesPreferencePage.getDefaults(store);

		DesignerPreferencePage.getDefaults(store);
		RulersGridPreferencePage.getDefaults(store);
		JRExporterPreferencePage.getDefaults(store);
		CSVExporterPreferencePage.getDefaults(store);
		ExcelExporterPreferencePage.getDefaults(store);
		HTMLExporterPreferencePage.getDefaults(store);
		PDFExporterPreferencePage.getDefaults(store);
		XMLExporterPreferencePage.getDefaults(store);
		TextExporterPreferencePage.getDefaults(store);

		DOCXExporterPreferencePage.getDefaults(store);
		G2DExporterPreferencePage.getDefaults(store);

		StudioPreferencePage.getDefaults(store);
		VirtualizerPreferencePage.getDefaults(store);

		QueryExecutersPreferencePage.getDefaults(store);
	}

}
