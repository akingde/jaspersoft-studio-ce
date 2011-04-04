/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.preferences.exporter;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.util.JRProperties;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.preferences.editor.CEncodingFieldEditor;
import com.jaspersoft.studio.preferences.editor.PagesFieldEditor;

/**
 * 
 */
public class JRExporterPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public static final String NSF_EXPORT_IGNORE_PAGE_MARGINS = "net.sf.jasperreports.export.ignore.page.margins";
	public static final String NSF_EXPORT_PARAMETERS_OVERRIDE_REPORT_HINTS = "net.sf.jasperreports.export.parameters.override.report.hints";
	public static final String NSF_EXPORT_LEGACY_BORDER_OFFSET = "net.sf.jasperreports.export.legacy.border.offset";
	public static final String EXPPARAM_OFFSET_X = "expparam.offset.x";
	public static final String EXPPARAM_OFFSET_Y = "expparam.offset.y";
	public static final String EXPPARAM_INDEX_PAGE = "expparam.index.page";

	public JRExporterPreferencePage() {
		super(GRID);
		setPreferenceStore(JaspersoftStudioPlugin.getInstance().getPreferenceStore());
		setDescription("Properties common to all exporters");
	}

	public void createFieldEditors() {
		addField(new CEncodingFieldEditor(JRExporterParameter.PROPERTY_CHARACTER_ENCODING, "Character Encoding",
				"Character Encoding", getFieldEditorParent()));
		addField(new ComboFieldEditor(NSF_EXPORT_LEGACY_BORDER_OFFSET, "Legacy Border Offset", new String[][] {
				{ "DEFAULT", "net.sf.jasperreports.export.legacy.BorderOffset.DEFAULT" },
				{ "LEGACY", "net.sf.jasperreports.export.legacy.BorderOffset.LEGACY" } }, getFieldEditorParent()));
		addField(new BooleanFieldEditor(NSF_EXPORT_PARAMETERS_OVERRIDE_REPORT_HINTS, "Override Report Hints",
				getFieldEditorParent()));
		addField(new BooleanFieldEditor(NSF_EXPORT_IGNORE_PAGE_MARGINS, "Ignore Page Margins", getFieldEditorParent()));

		addField(new IntegerFieldEditor(EXPPARAM_OFFSET_X, "Offset X", getFieldEditorParent()));
		addField(new IntegerFieldEditor(EXPPARAM_OFFSET_Y, "Offset Y", getFieldEditorParent()));

		addField(new PagesFieldEditor(EXPPARAM_INDEX_PAGE, "Export Pages", getFieldEditorParent()));
	}

	public static void getDefaults(IPreferenceStore store) {
		String pr = JRProperties.getProperty(JRExporterParameter.PROPERTY_CHARACTER_ENCODING);
		store.setDefault(JRExporterParameter.PROPERTY_CHARACTER_ENCODING, pr == null ? "UTF-8" : pr);
		store.setDefault(NSF_EXPORT_IGNORE_PAGE_MARGINS, JRProperties.getBooleanProperty(NSF_EXPORT_IGNORE_PAGE_MARGINS));
		store.setDefault(NSF_EXPORT_PARAMETERS_OVERRIDE_REPORT_HINTS,
				JRProperties.getBooleanProperty(NSF_EXPORT_PARAMETERS_OVERRIDE_REPORT_HINTS));
		pr = JRProperties.getProperty(NSF_EXPORT_LEGACY_BORDER_OFFSET);
		store.setDefault(NSF_EXPORT_LEGACY_BORDER_OFFSET, pr == null ? "DEFAULT" : pr);

		store.setDefault(EXPPARAM_OFFSET_X, 0);
		store.setDefault(EXPPARAM_OFFSET_Y, 0);

		store.setDefault(EXPPARAM_INDEX_PAGE, "all");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

}