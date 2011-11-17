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

import net.sf.jasperreports.engine.export.JRTextExporterParameter;
import net.sf.jasperreports.engine.util.JRProperties;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.editor.number.FloatFieldEditor;
import com.jaspersoft.studio.preferences.editor.text.NStringFieldEditor;
import com.jaspersoft.studio.preferences.editor.text.TextFieldEditor;
import com.jaspersoft.studio.preferences.util.FieldEditorOverlayPage;
import com.jaspersoft.studio.utils.Misc;
/*
 * 
 */
public class TextExporterPreferencePage extends FieldEditorOverlayPage {

	public static final String NSF_EXPORT_TEXT_LINE_SEPARATOR = "net.sf.jasperreports.export.text.line.separator"; //$NON-NLS-1$
	public static final String NSF_EXPORT_TEXT_BETWEEN_PAGE_TEXT = "net.sf.jasperreports.export.text.between.page.text"; //$NON-NLS-1$

	public TextExporterPreferencePage() {
		super(GRID);
		setPreferenceStore(JaspersoftStudioPlugin.getInstance().getPreferenceStore());
		setDescription(Messages.TextExporterPreferencePage_6);
	}

	/**
	 *
	 */
	public void createFieldEditors() {
		addField(new FloatFieldEditor(JRTextExporterParameter.PROPERTY_CHARACTER_WIDTH,
				Messages.TextExporterPreferencePage_7, getFieldEditorParent()));
		addField(new FloatFieldEditor(JRTextExporterParameter.PROPERTY_CHARACTER_HEIGHT,
				Messages.TextExporterPreferencePage_8, getFieldEditorParent()));

		addField(new IntegerFieldEditor(JRTextExporterParameter.PROPERTY_PAGE_WIDTH, Messages.TextExporterPreferencePage_9,
				getFieldEditorParent()));
		addField(new IntegerFieldEditor(JRTextExporterParameter.PROPERTY_PAGE_HEIGHT,
				Messages.TextExporterPreferencePage_10, getFieldEditorParent()));

		addField(new NStringFieldEditor(NSF_EXPORT_TEXT_LINE_SEPARATOR, Messages.TextExporterPreferencePage_11, 4,
				getFieldEditorParent()));

		Composite c = new Composite(getFieldEditorParent(), SWT.NONE);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		c.setLayoutData(gd);
		c.setLayout(new GridLayout());

		TextFieldEditor te = new TextFieldEditor(NSF_EXPORT_TEXT_BETWEEN_PAGE_TEXT, Messages.TextExporterPreferencePage_12,
				c);
		te.getTextControl(c).setLayoutData(new GridData(GridData.FILL_BOTH));
		addField(te);

	}

	public static void getDefaults(IPreferenceStore store) {
		store.setDefault(JRTextExporterParameter.PROPERTY_CHARACTER_HEIGHT,
				Misc.nvl(JRProperties.getProperty(JRTextExporterParameter.PROPERTY_CHARACTER_HEIGHT), "0")); //$NON-NLS-1$
		store.setDefault(JRTextExporterParameter.PROPERTY_CHARACTER_WIDTH,
				Misc.nvl(JRProperties.getProperty(JRTextExporterParameter.PROPERTY_CHARACTER_WIDTH), "0")); //$NON-NLS-1$
		store.setDefault(JRTextExporterParameter.PROPERTY_PAGE_HEIGHT,
				Misc.nvl(JRProperties.getProperty(JRTextExporterParameter.PROPERTY_PAGE_HEIGHT), "0")); //$NON-NLS-1$
		store.setDefault(JRTextExporterParameter.PROPERTY_PAGE_WIDTH,
				Misc.nvl(JRProperties.getProperty(JRTextExporterParameter.PROPERTY_PAGE_WIDTH), "0")); //$NON-NLS-1$

		store.setDefault(NSF_EXPORT_TEXT_LINE_SEPARATOR, "\n"); //$NON-NLS-1$
		store.setDefault(NSF_EXPORT_TEXT_BETWEEN_PAGE_TEXT,
				Misc.nvl(JRProperties.getProperty(NSF_EXPORT_TEXT_BETWEEN_PAGE_TEXT), "")); //$NON-NLS-1$
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
		return "com.jaspersoft.studio.preferences.exporter.TextExporterPreferencePage.property"; //$NON-NLS-1$
	}

}
