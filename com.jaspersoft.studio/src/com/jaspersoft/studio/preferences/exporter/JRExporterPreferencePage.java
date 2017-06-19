/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.preferences.exporter;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.help.HelpSystem;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.StudioPreferencePage;
import com.jaspersoft.studio.preferences.editor.CEncodingFieldEditor;
import com.jaspersoft.studio.preferences.editor.JSSComboFieldEditor;
import com.jaspersoft.studio.preferences.util.FieldEditorOverlayPage;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;

import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.export.CommonExportConfiguration;
import net.sf.jasperreports.export.ReportExportConfiguration;
import net.sf.jasperreports.export.WriterExporterOutput;

/*
 * 
 */
public class JRExporterPreferencePage extends FieldEditorOverlayPage {

	public static final String PAGE_ID = "com.jaspersoft.studio.preferences.exporter.JRExporterPreferencePage.property";

	/**
	 * Enumeration used to choose what to do when the export action should do
	 * when the target file already exist
	 * 
	 * @author Orlandin Marco
	 * 
	 */
	public enum OVERWRITE_STATE {
		OVERWRITE_TARGET, STOP_OPERATION, ASK_EVERYTIME
	};

	public static final String COM_JASPERSOFT_STUDIO_EXPORTER_SHOW_HTML = "com.jaspersoft.studio.exporter.show.html"; //$NON-NLS-1$

	public static final String EXPORTER_OVERWRITE = "exporterOverwrite"; //$NON-NLS-1$

	public JRExporterPreferencePage() {
		super(GRID);
		setPreferenceStore(JaspersoftStudioPlugin.getInstance().getPreferenceStore());
		setDescription(Messages.JRExporterPreferencePage_6);
	}

	@Override
	public void createFieldEditors() {
		CEncodingFieldEditor cefe = new CEncodingFieldEditor(WriterExporterOutput.PROPERTY_CHARACTER_ENCODING,
				Messages.JRExporterPreferencePage_7, Messages.JRExporterPreferencePage_8, getFieldEditorParent());
		addField(cefe);
		for (Control c : cefe.getControls())
			HelpSystem.setHelp(c, StudioPreferencePage.REFERENCE_PREFIX + cefe.getPreferenceName());

		BooleanFieldEditor bf = new BooleanFieldEditor(
				CommonExportConfiguration.PROPERTY_EXPORT_CONFIGURATION_OVERRIDE_REPORT_HINTS,
				Messages.JRExporterPreferencePage_14, getFieldEditorParent());
		addField(bf);
		HelpSystem.setHelp(bf.getDescriptionControl(getFieldEditorParent()),
				StudioPreferencePage.REFERENCE_PREFIX + bf.getPreferenceName());

		JSSComboFieldEditor expOverwrite = new JSSComboFieldEditor(EXPORTER_OVERWRITE,
				Messages.JRExporterPreferencePage_fileExistingOption,
				new String[][] {
						{ Messages.JRExporterPreferencePage_askTheUser, OVERWRITE_STATE.ASK_EVERYTIME.toString() },
						{ Messages.JRExporterPreferencePage_alwaysOverwrite,
								OVERWRITE_STATE.OVERWRITE_TARGET.toString() },
						{ Messages.JRExporterPreferencePage_abortOperation,
								OVERWRITE_STATE.STOP_OPERATION.toString() } },
				getFieldEditorParent());
		addField(expOverwrite);

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		new Label(getFieldEditorParent(), SWT.SEPARATOR | SWT.HORIZONTAL).setLayoutData(gd);

		bf = new BooleanFieldEditor(COM_JASPERSOFT_STUDIO_EXPORTER_SHOW_HTML, Messages.JRExporterPreferencePage_0,
				getFieldEditorParent());
		addField(bf);

		// Eventually create the extensions for the page
		super.createFieldEditors();
	}

	public static void getDefaults(IPreferenceStore store) {
		store.setDefault(WriterExporterOutput.PROPERTY_CHARACTER_ENCODING,
				Misc.nvl(PropertiesHelper.DPROP.getProperty(WriterExporterOutput.PROPERTY_CHARACTER_ENCODING),
						FileUtils.UTF8_ENCODING)); // $NON-NLS-1$
		store.setDefault(ReportExportConfiguration.PROPERTY_IGNORE_PAGE_MARGINS,
				PropertiesHelper.DPROP.getBooleanProperty(ReportExportConfiguration.PROPERTY_IGNORE_PAGE_MARGINS));
		store.setDefault(CommonExportConfiguration.PROPERTY_EXPORT_CONFIGURATION_OVERRIDE_REPORT_HINTS,
				PropertiesHelper.DPROP.getBooleanProperty(
						CommonExportConfiguration.PROPERTY_EXPORT_CONFIGURATION_OVERRIDE_REPORT_HINTS));

		store.setDefault(COM_JASPERSOFT_STUDIO_EXPORTER_SHOW_HTML, false);

		store.setDefault(EXPORTER_OVERWRITE, OVERWRITE_STATE.ASK_EVERYTIME.toString());
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
		return PAGE_ID;
	}

}
