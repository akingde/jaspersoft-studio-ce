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
package com.jaspersoft.studio.preferences.exporter;

import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.editor.text.TextFieldEditor;
import com.jaspersoft.studio.preferences.util.FieldEditorOverlayPage;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;
import com.jaspersoft.studio.utils.Misc;

/*
 * 
 */
public class HTMLExporterPreferencePage extends FieldEditorOverlayPage {
	public static final String NSF_EXPORT_HTML_ACCESSIBLE = "net.sf.jasperreports.export.html.accessible"; //$NON-NLS-1$

	public static final String NSF_EXPORT_HTML_HEADER = "net.sf.jasperreports.export.html.header"; //$NON-NLS-1$
	public static final String NSF_EXPORT_HTML_FOOTER = "net.sf.jasperreports.export.html.footer"; //$NON-NLS-1$
	public static final String NSF_EXPORT_HTML_BETWEEN_PAGES = "net.sf.jasperreports.export.html.between.pages"; //$NON-NLS-1$

	public static final String NSF_EXPORT_HTML_IS_OUTPUT_IMAGES_TO_DIR = "net.sf.jasperreports.export.html.is.output.images.to.dir"; //$NON-NLS-1$
	public static final String NSF_EXPORT_HTML_IMAGES_DIR_NAME = "net.sf.jasperreports.export.html.images.dir.name"; //$NON-NLS-1$
	public static final String NSF_EXPORT_HTML_IMAGES_URI = "net.sf.jasperreports.export.html.images.uri"; //$NON-NLS-1$

	public HTMLExporterPreferencePage() {
		super(GRID);
		setPreferenceStore(JaspersoftStudioPlugin.getInstance().getPreferenceStore());
		setDescription(Messages.HTMLExporterPreferencePage_14);
	}

	/**
	 *
	 */
	public void createFieldEditors() {

		CTabFolder tabFolder = new CTabFolder(getFieldEditorParent(), SWT.TOP);
		tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));

		createTabPageHTML(tabFolder);
		createTabPageImages(tabFolder);
		createTabPageHB(tabFolder);
		createTabPageBP(tabFolder);

		tabFolder.setSelection(0);
	}

	private void createTabPageHTML(CTabFolder tabFolder) {
		CTabItem ptab = new CTabItem(tabFolder, SWT.NONE);
		ptab.setText(Messages.HTMLExporterPreferencePage_15);

		Composite sc = new Composite(tabFolder, SWT.NONE);

		addField(new ComboFieldEditor(JRHtmlExporterParameter.PROPERTY_SIZE_UNIT, Messages.HTMLExporterPreferencePage_16,
				new String[][] { { Messages.HTMLExporterPreferencePage_17, Messages.HTMLExporterPreferencePage_18 },
						{ Messages.HTMLExporterPreferencePage_19, Messages.HTMLExporterPreferencePage_20 } }, sc));

		addField(new BooleanFieldEditor(NSF_EXPORT_HTML_ACCESSIBLE, Messages.HTMLExporterPreferencePage_21, sc));
		addField(new BooleanFieldEditor(JRHtmlExporterParameter.PROPERTY_FLUSH_OUTPUT,
				Messages.HTMLExporterPreferencePage_22, sc));
		addField(new BooleanFieldEditor(JRHtmlExporterParameter.PROPERTY_FRAMES_AS_NESTED_TABLES,
				Messages.HTMLExporterPreferencePage_23, sc));
		addField(new BooleanFieldEditor(JRHtmlExporterParameter.PROPERTY_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
				Messages.HTMLExporterPreferencePage_24, sc));
		addField(new BooleanFieldEditor(JRHtmlExporterParameter.PROPERTY_USING_IMAGES_TO_ALIGN,
				Messages.HTMLExporterPreferencePage_25, sc));
		addField(new BooleanFieldEditor(JRHtmlExporterParameter.PROPERTY_WHITE_PAGE_BACKGROUND,
				Messages.HTMLExporterPreferencePage_26, sc));
		addField(new BooleanFieldEditor(JRHtmlExporterParameter.PROPERTY_WRAP_BREAK_WORD,
				Messages.HTMLExporterPreferencePage_27, sc));

		ptab.setControl(sc);
	}

	private void createTabPageHB(CTabFolder tabFolder) {
		CTabItem ptab = new CTabItem(tabFolder, SWT.NONE);
		ptab.setText(Messages.HTMLExporterPreferencePage_28);

		Composite sc = new Composite(tabFolder, SWT.NONE);
		sc.setLayout(new GridLayout());

		TextFieldEditor se = new TextFieldEditor(NSF_EXPORT_HTML_HEADER, Messages.HTMLExporterPreferencePage_29, sc);
		se.getTextControl(sc).setLayoutData(new GridData(GridData.FILL_BOTH));
		addField(se);

		TextFieldEditor scf = new TextFieldEditor(NSF_EXPORT_HTML_FOOTER, Messages.HTMLExporterPreferencePage_30, sc);
		scf.getTextControl(sc).setLayoutData(new GridData(GridData.FILL_BOTH));
		addField(scf);

		ptab.setControl(sc);
	}

	private void createTabPageBP(CTabFolder tabFolder) {
		CTabItem ptab = new CTabItem(tabFolder, SWT.NONE);
		ptab.setText(Messages.HTMLExporterPreferencePage_31);

		Composite sc = new Composite(tabFolder, SWT.NONE);
		sc.setLayout(new GridLayout());

		TextFieldEditor scf = new TextFieldEditor(NSF_EXPORT_HTML_BETWEEN_PAGES, Messages.HTMLExporterPreferencePage_32, sc);
		scf.getTextControl(sc).setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL));
		addField(scf);

		ptab.setControl(sc);
	}

	private void createTabPageImages(CTabFolder tabFolder) {
		CTabItem ptab = new CTabItem(tabFolder, SWT.NONE);
		ptab.setText(Messages.HTMLExporterPreferencePage_33);

		Composite sc = new Composite(tabFolder, SWT.NONE);
		sc.setLayout(new GridLayout(3, false));

		addField(new BooleanFieldEditor(NSF_EXPORT_HTML_IS_OUTPUT_IMAGES_TO_DIR, Messages.HTMLExporterPreferencePage_34, sc));
		addField(new StringFieldEditor(NSF_EXPORT_HTML_IMAGES_URI, Messages.HTMLExporterPreferencePage_35, sc));
		addField(new DirectoryFieldEditor(NSF_EXPORT_HTML_IMAGES_DIR_NAME, Messages.HTMLExporterPreferencePage_36, sc));

		ptab.setControl(sc);
	}

	public static void getDefaults(IPreferenceStore store) {
		store.setDefault(NSF_EXPORT_HTML_ACCESSIBLE, Misc.nvl(PropertiesHelper.DPROP.getProperty(NSF_EXPORT_HTML_ACCESSIBLE), "false")); //$NON-NLS-1$
		store.setDefault(JRHtmlExporterParameter.PROPERTY_FLUSH_OUTPUT,
				PropertiesHelper.DPROP.getProperty(JRHtmlExporterParameter.PROPERTY_FLUSH_OUTPUT));
		store.setDefault(JRHtmlExporterParameter.PROPERTY_FRAMES_AS_NESTED_TABLES,
				PropertiesHelper.DPROP.getProperty(JRHtmlExporterParameter.PROPERTY_FRAMES_AS_NESTED_TABLES));
		store.setDefault(JRHtmlExporterParameter.PROPERTY_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
				Misc.nvl(PropertiesHelper.DPROP.getProperty(JRHtmlExporterParameter.PROPERTY_REMOVE_EMPTY_SPACE_BETWEEN_ROWS), "false")); //$NON-NLS-1$
		store.setDefault(JRHtmlExporterParameter.PROPERTY_SIZE_UNIT,
				PropertiesHelper.DPROP.getProperty(JRHtmlExporterParameter.PROPERTY_SIZE_UNIT));
		store.setDefault(JRHtmlExporterParameter.PROPERTY_USING_IMAGES_TO_ALIGN,
				PropertiesHelper.DPROP.getProperty(JRHtmlExporterParameter.PROPERTY_USING_IMAGES_TO_ALIGN));
		store.setDefault(JRHtmlExporterParameter.PROPERTY_WHITE_PAGE_BACKGROUND,
				PropertiesHelper.DPROP.getProperty(JRHtmlExporterParameter.PROPERTY_WHITE_PAGE_BACKGROUND));
		store.setDefault(JRHtmlExporterParameter.PROPERTY_WRAP_BREAK_WORD,
				PropertiesHelper.DPROP.getProperty(JRHtmlExporterParameter.PROPERTY_WRAP_BREAK_WORD));

		store.setDefault(NSF_EXPORT_HTML_HEADER, Misc.nvl(PropertiesHelper.DPROP.getProperty(NSF_EXPORT_HTML_HEADER), "")); //$NON-NLS-1$
		store.setDefault(NSF_EXPORT_HTML_FOOTER, Misc.nvl(PropertiesHelper.DPROP.getProperty(NSF_EXPORT_HTML_FOOTER), "")); //$NON-NLS-1$
		store.setDefault(NSF_EXPORT_HTML_BETWEEN_PAGES, Misc.nvl(PropertiesHelper.DPROP.getProperty(NSF_EXPORT_HTML_BETWEEN_PAGES), "")); //$NON-NLS-1$

		store.setDefault(NSF_EXPORT_HTML_IS_OUTPUT_IMAGES_TO_DIR,
				Misc.nvl(PropertiesHelper.DPROP.getProperty(NSF_EXPORT_HTML_IS_OUTPUT_IMAGES_TO_DIR), "")); //$NON-NLS-1$
		store.setDefault(NSF_EXPORT_HTML_IMAGES_DIR_NAME, Misc.nvl(PropertiesHelper.DPROP.getProperty(NSF_EXPORT_HTML_IMAGES_DIR_NAME), "")); //$NON-NLS-1$
		store.setDefault(NSF_EXPORT_HTML_IMAGES_URI, Misc.nvl(PropertiesHelper.DPROP.getProperty(NSF_EXPORT_HTML_IMAGES_URI), "")); //$NON-NLS-1$
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
		return "com.jaspersoft.studio.preferences.exporter.HTMLExporterPreferencePage.property"; //$NON-NLS-1$
	}

}
