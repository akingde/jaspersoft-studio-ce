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
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbench;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.util.FieldEditorOverlayPage;
import com.jaspersoft.studio.utils.Misc;

/**
 * 
 */
public class XMLExporterPreferencePage extends FieldEditorOverlayPage {
	public static final String NSF_EXPORT_XML_VALIDATION = "net.sf.jasperreports.export.xml.validation"; //$NON-NLS-1$
	public static final String NSF_EXPORT_XML_IS_EMBEDDING_IMAGES = "net.sf.jasperreports.export.xml.is.embedding.images"; //$NON-NLS-1$
	public static final String NSF_EXPORT_XML_DTD_LOCATION = "net.sf.jasperreports.export.xml.dtd.location"; //$NON-NLS-1$

	public XMLExporterPreferencePage() {
		super(GRID);
		setPreferenceStore(JaspersoftStudioPlugin.getInstance().getPreferenceStore());
		setDescription(Messages.XMLExporterPreferencePage_3);
	}

	/**
	 *
	 */
	public void createFieldEditors() {
		addField(new BooleanFieldEditor(NSF_EXPORT_XML_IS_EMBEDDING_IMAGES, Messages.XMLExporterPreferencePage_4,
				getFieldEditorParent()));
		addField(new BooleanFieldEditor(NSF_EXPORT_XML_VALIDATION, Messages.XMLExporterPreferencePage_5,
				getFieldEditorParent()));
		FileFieldEditor ffe = new FileFieldEditor(NSF_EXPORT_XML_DTD_LOCATION, Messages.XMLExporterPreferencePage_6,
				getFieldEditorParent());
		ffe.setFileExtensions(new String[] { ".dtd" }); //$NON-NLS-1$
		addField(ffe);
	}

	public static void getDefaults(IPreferenceStore store) {
		store.setDefault(NSF_EXPORT_XML_VALIDATION, JRProperties.getProperty(NSF_EXPORT_XML_VALIDATION));
		store.setDefault(NSF_EXPORT_XML_IS_EMBEDDING_IMAGES,
				Misc.nvl(JRProperties.getProperty(NSF_EXPORT_XML_IS_EMBEDDING_IMAGES), "false")); //$NON-NLS-1$
		store.setDefault(NSF_EXPORT_XML_DTD_LOCATION, Misc.nvl(JRProperties.getProperty(NSF_EXPORT_XML_DTD_LOCATION), "")); //$NON-NLS-1$
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
		return "com.jaspersoft.studio.preferences.exporter.XMLExporterPreferencePage.property"; //$NON-NLS-1$
	}

}