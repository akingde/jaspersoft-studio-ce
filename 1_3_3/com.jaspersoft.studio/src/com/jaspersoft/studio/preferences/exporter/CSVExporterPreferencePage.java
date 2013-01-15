/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.preferences.exporter;

import net.sf.jasperreports.engine.export.JRCsvExporterParameter;

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
public class CSVExporterPreferencePage extends FieldEditorOverlayPage {

	public static final String PAGE_ID = "com.jaspersoft.studio.preferences.exporter.CSVExporterPreferencePage.property";

	public CSVExporterPreferencePage() {
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

		Browser lbl = new Browser(getFieldEditorParent(), SWT.MULTI);
		lbl.setText(Messages.CSVExporterPreferencePage_5);

		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		lbl.setLayoutData(gd);

	}

	public static void getDefaults(IPreferenceStore store) {
		store.setDefault(JRCsvExporterParameter.PROPERTY_RECORD_DELIMITER,
				PropertiesHelper.DPROP.getProperty(JRCsvExporterParameter.PROPERTY_RECORD_DELIMITER));
		store.setDefault(JRCsvExporterParameter.PROPERTY_FIELD_DELIMITER,
				PropertiesHelper.DPROP.getProperty(JRCsvExporterParameter.PROPERTY_FIELD_DELIMITER));
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
