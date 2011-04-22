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

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.compatibility.JRXmlWriterHelper;
import com.jaspersoft.studio.preferences.util.FieldEditorOverlayPage;

public class StudioPreferencePage extends FieldEditorOverlayPage {
	public static final String JSS_COMPATIBILITY_SHOW_DIALOG = "com.jaspersoft.studio.compatibility.showdialog"; //$NON-NLS-1$
	public static final String JSS_COMPATIBILITY_VERSION = "com.jaspersoft.studio.compatibility.version"; //$NON-NLS-1$

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

		addField(new ComboFieldEditor(JSS_COMPATIBILITY_VERSION, "Version", JRXmlWriterHelper.getVersions(),
				getFieldEditorParent()));

		addField(new BooleanFieldEditor(JSS_COMPATIBILITY_SHOW_DIALOG, "Show Compatibility warning dialog",
				getFieldEditorParent()));

		Label label = new Label(getFieldEditorParent(), SWT.WRAP);
		label.setText("Set the compatibility of the produced jrxml source code when the report saved.");
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		label.setLayoutData(gd);

		label = new Label(getFieldEditorParent(), SWT.WRAP);
		label.setText("Warning! If you don't use the last version, you risk to lose part of the report content.");
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		label.setLayoutData(gd);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

	public static void getDefaults(IPreferenceStore store) {
		store.setDefault(JSS_COMPATIBILITY_SHOW_DIALOG, "false"); //$NON-NLS-1$
		store.setDefault(JSS_COMPATIBILITY_VERSION, "last"); //$NON-NLS-1$
	}

	@Override
	protected String getPageId() {
		return "com.jaspersoft.studio.preferences.StudioPreferencePage.property";
	}

}
