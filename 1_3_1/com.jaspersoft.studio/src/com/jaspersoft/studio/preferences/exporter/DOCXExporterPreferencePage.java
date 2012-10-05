/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.preferences.exporter;

import net.sf.jasperreports.engine.export.ooxml.JRDocxExporterParameter;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbench;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.util.FieldEditorOverlayPage;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;
/*
 * 
 */
public class DOCXExporterPreferencePage extends FieldEditorOverlayPage {

	public DOCXExporterPreferencePage() {
		super(GRID);
		setPreferenceStore(JaspersoftStudioPlugin.getInstance().getPreferenceStore());
		setDescription(Messages.DOCXExporterPreferencePage_title);
	}

	/**
	 *
	 */
	public void createFieldEditors() {
		addField(new BooleanFieldEditor(JRDocxExporterParameter.PROPERTY_FRAMES_AS_NESTED_TABLES,
				Messages.DOCXExporterPreferencePage_3, getFieldEditorParent()));
		addField(new BooleanFieldEditor(JRDocxExporterParameter.PROPERTY_FLEXIBLE_ROW_HEIGHT,
				Messages.DOCXExporterPreferencePage_4, getFieldEditorParent()));
	}

	public static void getDefaults(IPreferenceStore store) {
		store.setDefault(JRDocxExporterParameter.PROPERTY_FRAMES_AS_NESTED_TABLES,
				PropertiesHelper.DPROP.getProperty(JRDocxExporterParameter.PROPERTY_FRAMES_AS_NESTED_TABLES));
		store.setDefault(JRDocxExporterParameter.PROPERTY_FLEXIBLE_ROW_HEIGHT,
				PropertiesHelper.DPROP.getProperty(JRDocxExporterParameter.PROPERTY_FLEXIBLE_ROW_HEIGHT));
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
		return "com.jaspersoft.studio.preferences.exporter.DOCXExporterPreferencePage.property"; //$NON-NLS-1$
	}

}
