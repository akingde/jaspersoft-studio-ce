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

import net.sf.jasperreports.engine.util.JRProperties;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbench;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.util.FieldEditorOverlayPage;

/**
 * 
 */
public class DOCXExporterPreferencePage extends FieldEditorOverlayPage {

	public static final String NSF_EXPORT_DOCX_FRAME_NESTED = "net.sf.jasperreports.export.docx.frames.as.nested.tables"; //$NON-NLS-1$
	public static final String NSF_EXPORT_DOCX_FLEXIBLE_ROW_HEIGHT = "net.sf.jasperreports.export.docx.flexible.row.height"; //$NON-NLS-1$

	public DOCXExporterPreferencePage() {
		super(GRID);
		setPreferenceStore(JaspersoftStudioPlugin.getInstance().getPreferenceStore());
		setDescription(Messages.DOCXExporterPreferencePage_title);
	}

	/**
	 *
	 */
	public void createFieldEditors() {
		addField(new BooleanFieldEditor(NSF_EXPORT_DOCX_FRAME_NESTED, Messages.DOCXExporterPreferencePage_3, getFieldEditorParent()));
		addField(new BooleanFieldEditor(NSF_EXPORT_DOCX_FLEXIBLE_ROW_HEIGHT, Messages.DOCXExporterPreferencePage_4, getFieldEditorParent()));
	}

	public static void getDefaults(IPreferenceStore store) {
		store.setDefault(NSF_EXPORT_DOCX_FRAME_NESTED, JRProperties.getProperty(NSF_EXPORT_DOCX_FRAME_NESTED));
		store
				.setDefault(NSF_EXPORT_DOCX_FLEXIBLE_ROW_HEIGHT, JRProperties.getProperty(NSF_EXPORT_DOCX_FLEXIBLE_ROW_HEIGHT));
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