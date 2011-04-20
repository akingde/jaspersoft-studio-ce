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

import net.sf.jasperreports.engine.util.JRProperties;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbench;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.editor.number.FloatFieldEditor;
import com.jaspersoft.studio.preferences.util.FieldEditorOverlayPage;
import com.jaspersoft.studio.utils.Misc;
/*
 * 
 */
public class G2DExporterPreferencePage extends FieldEditorOverlayPage {

	public static final String NSF_EXPORT_G2D_MINJOBSIZE = "net.sf.jasperreports.export.graphics2d.min.job.size"; //$NON-NLS-1$
	public static final String NSF_EXPORT_G2D_ZOOM_RATIO = "net.sf.jasperreports.export.graphics2d.zoom.ratio"; //$NON-NLS-1$

	public G2DExporterPreferencePage() {
		super(GRID);
		setPreferenceStore(JaspersoftStudioPlugin.getInstance().getPreferenceStore());
		setDescription(Messages.G2DExporterPreferencePage_2);
	}

	/**
	 *
	 */
	public void createFieldEditors() {
		addField(new BooleanFieldEditor(NSF_EXPORT_G2D_MINJOBSIZE, Messages.G2DExporterPreferencePage_3, getFieldEditorParent()));
		FloatFieldEditor fe = new FloatFieldEditor(NSF_EXPORT_G2D_ZOOM_RATIO, Messages.G2DExporterPreferencePage_4, getFieldEditorParent());
		fe.setValidRange(0, Float.MAX_VALUE);
		addField(fe);
	}

	public static void getDefaults(IPreferenceStore store) {
		store.setDefault(NSF_EXPORT_G2D_MINJOBSIZE, JRProperties.getProperty(NSF_EXPORT_G2D_MINJOBSIZE));
		store.setDefault(NSF_EXPORT_G2D_ZOOM_RATIO, Misc.nvl(JRProperties.getProperty(NSF_EXPORT_G2D_ZOOM_RATIO), "1")); //$NON-NLS-1$
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
		return "com.jaspersoft.studio.preferences.exporter.G2DExporterPreferencePage.property"; //$NON-NLS-1$
	}

}
