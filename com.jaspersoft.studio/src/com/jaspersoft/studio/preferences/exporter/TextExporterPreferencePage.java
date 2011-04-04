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

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.preferences.editor.FloatFieldEditor;
import com.jaspersoft.studio.preferences.editor.NStringFieldEditor;
import com.jaspersoft.studio.preferences.editor.TextFieldEditor;

/**
 * 
 */
public class TextExporterPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public static final String NSF_EXPORT_TEXT_CHAR_HEIGHT = "net.sf.jasperreports.export.text.character.height";
	public static final String NSF_EXPORT_TEXT_CHAR_WIDTH = "net.sf.jasperreports.export.text.character.width";
	public static final String NSF_EXPORT_TEXT_PAGE_HEIGHT = "net.sf.jasperreports.export.text.page.height";
	public static final String NSF_EXPORT_TEXT_PAGE_WIDTH = "net.sf.jasperreports.export.text.page.width";

	public static final String NSF_EXPORT_TEXT_LINE_SEPARATOR = "net.sf.jasperreports.export.text.line.separator";
	public static final String NSF_EXPORT_TEXT_BETWEEN_PAGE_TEXT = "net.sf.jasperreports.export.text.between.page.text";

	public TextExporterPreferencePage() {
		super(GRID);
		setPreferenceStore(JaspersoftStudioPlugin.getInstance().getPreferenceStore());
		setDescription("Text Exporter Parameters");
	}

	/**
	 *
	 */
	public void createFieldEditors() {
		addField(new FloatFieldEditor(NSF_EXPORT_TEXT_CHAR_WIDTH, "Char Width", getFieldEditorParent()));
		addField(new FloatFieldEditor(NSF_EXPORT_TEXT_CHAR_HEIGHT, "Char Height", getFieldEditorParent()));

		addField(new IntegerFieldEditor(NSF_EXPORT_TEXT_PAGE_WIDTH, "Page Width In Chars", getFieldEditorParent()));
		addField(new IntegerFieldEditor(NSF_EXPORT_TEXT_PAGE_HEIGHT, "Page Height In Chars", getFieldEditorParent()));

		addField(new NStringFieldEditor(NSF_EXPORT_TEXT_LINE_SEPARATOR, "Line Separator", 4, getFieldEditorParent()));

		Composite c = new Composite(getFieldEditorParent(), SWT.NONE);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		c.setLayoutData(gd);
		c.setLayout(new GridLayout());

		TextFieldEditor te = new TextFieldEditor(NSF_EXPORT_TEXT_BETWEEN_PAGE_TEXT, "Between Page Text", c);
		te.getTextControl(c).setLayoutData(new GridData(GridData.FILL_BOTH));
		addField(te);

	}

	public static void getDefaults(IPreferenceStore store) {
		store.setDefault(NSF_EXPORT_TEXT_CHAR_HEIGHT, JRProperties.getProperty(NSF_EXPORT_TEXT_CHAR_HEIGHT));
		store.setDefault(NSF_EXPORT_TEXT_CHAR_WIDTH, JRProperties.getProperty(NSF_EXPORT_TEXT_CHAR_WIDTH));
		store.setDefault(NSF_EXPORT_TEXT_PAGE_HEIGHT, JRProperties.getProperty(NSF_EXPORT_TEXT_PAGE_HEIGHT));
		store.setDefault(NSF_EXPORT_TEXT_PAGE_WIDTH, JRProperties.getProperty(NSF_EXPORT_TEXT_PAGE_WIDTH));

		store.setDefault(NSF_EXPORT_TEXT_LINE_SEPARATOR, "\n");
		store.setDefault(NSF_EXPORT_TEXT_BETWEEN_PAGE_TEXT, JRProperties.getProperty(NSF_EXPORT_TEXT_BETWEEN_PAGE_TEXT));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

}