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
package com.jaspersoft.studio.preferences.exporter;

import net.sf.jasperreports.engine.export.JRCsvExporterParameter;
import net.sf.jasperreports.engine.export.JRCsvMetadataExporterParameter;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.GridData;
import org.eclipse.ui.IWorkbench;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.editor.text.NStringFieldEditor;
import com.jaspersoft.studio.preferences.util.FieldEditorOverlayPage;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;

/*
 * 
 */
public class CSVMetadataExporterPreferencePage extends FieldEditorOverlayPage {

	public static final String PAGE_ID = "com.jaspersoft.studio.preferences.exporter.CSVExporterPreferencePage.property";

	public CSVMetadataExporterPreferencePage() {
		super(GRID);
		setPreferenceStore(JaspersoftStudioPlugin.getInstance().getPreferenceStore());
		setDescription(Messages.CSVExporterPreferencePage_title);
	}

	/**
	 *
	 */
	public void createFieldEditors() {
		NStringFieldEditor tf = new NStringFieldEditor(JRCsvExporterParameter.PROPERTY_RECORD_DELIMITER,
				Messages.CSVExporterPreferencePage_3, 4, getFieldEditorParent());
		tf.setEmptyStringAllowed(false);
		tf.setTextLimit(10);
		addField(tf);

		NStringFieldEditor fd = new NStringFieldEditor(JRCsvExporterParameter.PROPERTY_FIELD_DELIMITER,
				Messages.CSVExporterPreferencePage_4, 4, getFieldEditorParent());
		fd.setEmptyStringAllowed(false);
		fd.setTextLimit(10);
		addField(fd);

		Browser browser = new Browser(getFieldEditorParent(), SWT.MULTI);
		browser.setText(Messages.CSVExporterPreferencePage_5);

		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		browser.setLayoutData(gd);

		addField(new BooleanFieldEditor(JRCsvMetadataExporterParameter.PROPERTY_WRITE_HEADER, "Write Header",
				getFieldEditorParent()));
		addField(new NStringFieldEditor(JRCsvMetadataExporterParameter.PROPERTY_COLUMN_NAMES_PREFIX, "Column Names",
				getFieldEditorParent()));
	}

	public static void getDefaults(IPreferenceStore store) {
		store.setDefault(JRCsvExporterParameter.PROPERTY_RECORD_DELIMITER,
				PropertiesHelper.DPROP.getProperty(JRCsvExporterParameter.PROPERTY_RECORD_DELIMITER));
		store.setDefault(JRCsvExporterParameter.PROPERTY_FIELD_DELIMITER,
				PropertiesHelper.DPROP.getProperty(JRCsvExporterParameter.PROPERTY_FIELD_DELIMITER));

		store.setDefault(JRCsvMetadataExporterParameter.PROPERTY_COLUMN_NAMES_PREFIX,
				PropertiesHelper.DPROP.getProperty(JRCsvMetadataExporterParameter.PROPERTY_COLUMN_NAMES_PREFIX));
		store.setDefault(JRCsvMetadataExporterParameter.PROPERTY_WRITE_HEADER,
				PropertiesHelper.DPROP.getProperty(JRCsvMetadataExporterParameter.PROPERTY_WRITE_HEADER));
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
		return PAGE_ID; //$NON-NLS-1$
	}

}
