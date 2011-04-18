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
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.ui.IWorkbench;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.editor.CEncodingFieldEditor;
import com.jaspersoft.studio.preferences.editor.PagesFieldEditor;
import com.jaspersoft.studio.preferences.util.FieldEditorOverlayPage;
import com.jaspersoft.studio.utils.Misc;

/**
 * 
 */
public class JRExporterPreferencePage extends FieldEditorOverlayPage {

	public static final String NSF_EXPORT_IGNORE_PAGE_MARGINS = "net.sf.jasperreports.export.ignore.page.margins"; //$NON-NLS-1$
	public static final String NSF_EXPORT_PARAMETERS_OVERRIDE_REPORT_HINTS = "net.sf.jasperreports.export.parameters.override.report.hints"; //$NON-NLS-1$
	public static final String NSF_EXPORT_LEGACY_BORDER_OFFSET = "net.sf.jasperreports.export.legacy.border.offset"; //$NON-NLS-1$
	public static final String EXPPARAM_OFFSET_X = "expparam.offset.x"; //$NON-NLS-1$
	public static final String EXPPARAM_OFFSET_Y = "expparam.offset.y"; //$NON-NLS-1$
	public static final String EXPPARAM_INDEX_PAGE = "expparam.index.page"; //$NON-NLS-1$

	public JRExporterPreferencePage() {
		super(GRID);
		setPreferenceStore(JaspersoftStudioPlugin.getInstance().getPreferenceStore());
		setDescription(Messages.JRExporterPreferencePage_6);
	}

	public void createFieldEditors() {
		addField(new CEncodingFieldEditor(JRExporterParameter.PROPERTY_CHARACTER_ENCODING, Messages.JRExporterPreferencePage_7,
				Messages.JRExporterPreferencePage_8, getFieldEditorParent()));
		addField(new ComboFieldEditor(NSF_EXPORT_LEGACY_BORDER_OFFSET, Messages.JRExporterPreferencePage_9, new String[][] {
				{ Messages.JRExporterPreferencePage_10, Messages.JRExporterPreferencePage_11 },
				{ Messages.JRExporterPreferencePage_12, Messages.JRExporterPreferencePage_13 } }, getFieldEditorParent()));
		addField(new BooleanFieldEditor(NSF_EXPORT_PARAMETERS_OVERRIDE_REPORT_HINTS, Messages.JRExporterPreferencePage_14,
				getFieldEditorParent()));
		addField(new BooleanFieldEditor(NSF_EXPORT_IGNORE_PAGE_MARGINS, Messages.JRExporterPreferencePage_15, getFieldEditorParent()));

		addField(new IntegerFieldEditor(EXPPARAM_OFFSET_X, Messages.JRExporterPreferencePage_16, getFieldEditorParent()));
		addField(new IntegerFieldEditor(EXPPARAM_OFFSET_Y, Messages.JRExporterPreferencePage_17, getFieldEditorParent()));

		addField(new PagesFieldEditor(EXPPARAM_INDEX_PAGE, Messages.JRExporterPreferencePage_18, getFieldEditorParent()));
	}

	public static void getDefaults(IPreferenceStore store) {
		store.setDefault(JRExporterParameter.PROPERTY_CHARACTER_ENCODING,
				Misc.nvl(JRProperties.getProperty(JRExporterParameter.PROPERTY_CHARACTER_ENCODING), "UTF-8")); //$NON-NLS-1$
		store.setDefault(NSF_EXPORT_IGNORE_PAGE_MARGINS, JRProperties.getBooleanProperty(NSF_EXPORT_IGNORE_PAGE_MARGINS));
		store.setDefault(NSF_EXPORT_PARAMETERS_OVERRIDE_REPORT_HINTS,
				JRProperties.getBooleanProperty(NSF_EXPORT_PARAMETERS_OVERRIDE_REPORT_HINTS));
		store.setDefault(NSF_EXPORT_LEGACY_BORDER_OFFSET,
				Misc.nvl(JRProperties.getProperty(NSF_EXPORT_LEGACY_BORDER_OFFSET), "DEFAULT")); //$NON-NLS-1$

		store.setDefault(EXPPARAM_OFFSET_X, 0);
		store.setDefault(EXPPARAM_OFFSET_Y, 0);

		store.setDefault(EXPPARAM_INDEX_PAGE, "all"); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

	@Override
	protected String getPageId() {
		return "com.jaspersoft.studio.preferences.exporter.JRExporterPreferencePage.property"; //$NON-NLS-1$
	}

}