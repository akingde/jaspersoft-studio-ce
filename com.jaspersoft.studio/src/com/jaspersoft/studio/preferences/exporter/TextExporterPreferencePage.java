/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.preferences.exporter;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbench;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.help.HelpSystem;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.StudioPreferencePage;
import com.jaspersoft.studio.preferences.editor.number.FloatFieldEditor;
import com.jaspersoft.studio.preferences.editor.number.JSSIntegerFieldEditor;
import com.jaspersoft.studio.preferences.editor.text.NStringFieldEditor;
import com.jaspersoft.studio.preferences.editor.text.TextFieldEditor;
import com.jaspersoft.studio.preferences.util.FieldEditorOverlayPage;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.export.TextExporterConfiguration;
import net.sf.jasperreports.export.TextReportConfiguration;

/*
 * 
 */
public class TextExporterPreferencePage extends FieldEditorOverlayPage {

	public TextExporterPreferencePage() {
		super(GRID);
		setPreferenceStore(JaspersoftStudioPlugin.getInstance().getPreferenceStore());
		setDescription(Messages.TextExporterPreferencePage_6);
	}

	/**
	 *
	 */
	public void createFieldEditors() {
		FloatFieldEditor ffe = new FloatFieldEditor(TextReportConfiguration.PROPERTY_CHARACTER_WIDTH,
				Messages.TextExporterPreferencePage_7, getFieldEditorParent());
		ffe.setErrorMessage("Invalid float value for field: " + Messages.TextExporterPreferencePage_7);
		addField(ffe);
		HelpSystem.setHelp(ffe.getTextControl(getFieldEditorParent()),
				StudioPreferencePage.REFERENCE_PREFIX + ffe.getPreferenceName());

		ffe = new FloatFieldEditor(TextReportConfiguration.PROPERTY_CHARACTER_HEIGHT,
				Messages.TextExporterPreferencePage_8, getFieldEditorParent());
		ffe.setErrorMessage("Invalid float value for field: " + Messages.TextExporterPreferencePage_8);
		addField(ffe);
		HelpSystem.setHelp(ffe.getTextControl(getFieldEditorParent()),
				StudioPreferencePage.REFERENCE_PREFIX + ffe.getPreferenceName());

		JSSIntegerFieldEditor ife = new JSSIntegerFieldEditor(TextReportConfiguration.PROPERTY_PAGE_WIDTH,
				Messages.TextExporterPreferencePage_9, getFieldEditorParent());
		ife.setErrorMessage("Invalid integer value for field: " + Messages.TextExporterPreferencePage_9);
		addField(ife);
		HelpSystem.setHelp(ife.getTextControl(getFieldEditorParent()),
				StudioPreferencePage.REFERENCE_PREFIX + ife.getPreferenceName());

		ife = new JSSIntegerFieldEditor(TextReportConfiguration.PROPERTY_PAGE_HEIGHT,
				Messages.TextExporterPreferencePage_10, getFieldEditorParent());
		ife.setErrorMessage("Invalid integer value for field: " + Messages.TextExporterPreferencePage_10);
		addField(ife);
		HelpSystem.setHelp(ife.getTextControl(getFieldEditorParent()),
				StudioPreferencePage.REFERENCE_PREFIX + ife.getPreferenceName());

		NStringFieldEditor sfe = new NStringFieldEditor(TextExporterConfiguration.PROPERTY_LINE_SEPARATOR,
				Messages.TextExporterPreferencePage_11, 4, getFieldEditorParent());
		addField(sfe);

		TextFieldEditor te = new TextFieldEditor(TextExporterConfiguration.PROPERTY_PAGE_SEPARATOR,
				Messages.TextExporterPreferencePage_12, true, getFieldEditorParent());
		te.setEmptyStringAllowed(true);
		addField(te);

		// Eventually create the extensions for the page
		super.createFieldEditors();
	}

	public static void getDefaults(IPreferenceStore store) {
		if (!store.contains(TextReportConfiguration.PROPERTY_CHARACTER_HEIGHT)) {
			// we can't store null values in the store, but for this one we have null
			// a workaround is to remove the property for null values
			// so we initialise the default only if no properties are initialised
			store.setDefault(TextExporterConfiguration.PROPERTY_PAGE_SEPARATOR, Misc
					.nvl(PropertiesHelper.DPROP.getProperty(TextExporterConfiguration.PROPERTY_PAGE_SEPARATOR), "")); //$NON-NLS-1$
		}

		store.setDefault(TextReportConfiguration.PROPERTY_CHARACTER_HEIGHT,
				Misc.nvl(PropertiesHelper.DPROP.getProperty(TextReportConfiguration.PROPERTY_CHARACTER_HEIGHT), "0")); //$NON-NLS-1$
		store.setDefault(TextReportConfiguration.PROPERTY_CHARACTER_WIDTH,
				Misc.nvl(PropertiesHelper.DPROP.getProperty(TextReportConfiguration.PROPERTY_CHARACTER_WIDTH), "0")); //$NON-NLS-1$
		store.setDefault(TextReportConfiguration.PROPERTY_PAGE_HEIGHT,
				Misc.nvl(PropertiesHelper.DPROP.getProperty(TextReportConfiguration.PROPERTY_PAGE_HEIGHT), "0")); //$NON-NLS-1$
		store.setDefault(TextReportConfiguration.PROPERTY_PAGE_WIDTH,
				Misc.nvl(PropertiesHelper.DPROP.getProperty(TextReportConfiguration.PROPERTY_PAGE_WIDTH), "0")); //$NON-NLS-1$

		store.setDefault(TextExporterConfiguration.PROPERTY_LINE_SEPARATOR, "\n"); //$NON-NLS-1$
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
		return "com.jaspersoft.studio.preferences.exporter.TextExporterPreferencePage.property"; //$NON-NLS-1$
	}

}
