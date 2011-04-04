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

import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.util.JRProperties;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.editor.TextFieldEditor;

/**
 * 
 */
public class HTMLExporterPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	public static final String NSF_EXPORT_HTML_ACCESSIBLE = "net.sf.jasperreports.export.html.accessible";
	public static final String NSF_EXPORT_HTML_FLUSH_OUTPUT = "net.sf.jasperreports.export.html.flush.output";
	public static final String NSF_EXPORT_HTML_FRAMES_AS_NESTED_TABLES = "net.sf.jasperreports.export.html.frames.as.nested.tables";
	public static final String NSF_EXPORT_HTML_REMOVE_EMPTY_SPACE_BETWEEN_ROWS = "net.sf.jasperreports.export.html.remove.emtpy.space.between.rows";
	public static final String NSF_EXPORT_HTML_SIZE_UNIT = "net.sf.jasperreports.export.html.size.unit";
	public static final String NSF_EXPORT_HTML_USING_IMAGES_TO_ALIGN = "net.sf.jasperreports.export.html.using.images.to.align";
	public static final String NSF_EXPORT_HTML_WHITE_PAGE_BACKGROUND = "net.sf.jasperreports.export.html.white.page.background";
	public static final String NSF_EXPORT_HTML_WRAP_BREAK_WORD = "net.sf.jasperreports.export.html.wrap.break.word";

	public static final String NSF_EXPORT_HTML_HEADER = "net.sf.jasperreports.export.html.header";
	public static final String NSF_EXPORT_HTML_FOOTER = "net.sf.jasperreports.export.html.footer";
	public static final String NSF_EXPORT_HTML_BETWEEN_PAGES = "net.sf.jasperreports.export.html.between.pages";

	public static final String NSF_EXPORT_HTML_IS_OUTPUT_IMAGES_TO_DIR = "net.sf.jasperreports.export.html.is.output.images.to.dir";
	public static final String NSF_EXPORT_HTML_IMAGES_DIR_NAME = "net.sf.jasperreports.export.html.images.dir.name";
	public static final String NSF_EXPORT_HTML_IMAGES_URI = "net.sf.jasperreports.export.html.images.uri";

	public HTMLExporterPreferencePage() {
		super(GRID);
		setPreferenceStore(JaspersoftStudioPlugin.getInstance().getPreferenceStore());
		setDescription("HTML Exporter Parameters");
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
		ptab.setText("HTML");

		ScrolledComposite scompo = new ScrolledComposite(tabFolder, SWT.V_SCROLL | SWT.H_SCROLL);
		scompo.setExpandHorizontal(true);
		scompo.setExpandVertical(true);

		Composite sc = new Composite(scompo, SWT.NONE);
		sc.setLayout(new GridLayout(2, false));

		addField(new BooleanFieldEditor(NSF_EXPORT_HTML_ACCESSIBLE, "Produce Accessible HTML", sc));
		addField(new BooleanFieldEditor(NSF_EXPORT_HTML_FLUSH_OUTPUT, "Flush Output", sc));
		addField(new BooleanFieldEditor(NSF_EXPORT_HTML_FRAMES_AS_NESTED_TABLES, "Frames As Nested Tables", sc));
		addField(new BooleanFieldEditor(NSF_EXPORT_HTML_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, "Remove Empty Space Between Rows",
				sc));
		addField(new BooleanFieldEditor(NSF_EXPORT_HTML_USING_IMAGES_TO_ALIGN, "Use Images To Align", sc));
		addField(new BooleanFieldEditor(NSF_EXPORT_HTML_WHITE_PAGE_BACKGROUND, "White Page Background", sc));
		addField(new BooleanFieldEditor(NSF_EXPORT_HTML_WRAP_BREAK_WORD, "Wrap Break Word", sc));
		addField(new ComboFieldEditor(NSF_EXPORT_HTML_SIZE_UNIT, "Size Unit", new String[][] { { "px", "pt" },
				{ "pt", "pt" } }, sc));

		scompo.setMinSize(sc.getSize());
		scompo.setContent(sc);
		ptab.setControl(scompo);
	}

	private void createTabPageHB(CTabFolder tabFolder) {
		CTabItem ptab = new CTabItem(tabFolder, SWT.NONE);
		ptab.setText("Headader And Footer");

		Composite sc = new Composite(tabFolder, SWT.NONE);
		sc.setLayout(new GridLayout());

		TextFieldEditor se = new TextFieldEditor(NSF_EXPORT_HTML_HEADER, "Header", sc);
		se.getTextControl(sc).setLayoutData(new GridData(GridData.FILL_BOTH));
		addField(se);

		TextFieldEditor scf = new TextFieldEditor(NSF_EXPORT_HTML_FOOTER, "Footer", sc);
		scf.getTextControl(sc).setLayoutData(new GridData(GridData.FILL_BOTH));
		addField(scf);

		ptab.setControl(sc);
	}

	private void createTabPageBP(CTabFolder tabFolder) {
		CTabItem ptab = new CTabItem(tabFolder, SWT.NONE);
		ptab.setText("Between Pages");

		Composite sc = new Composite(tabFolder, SWT.NONE);
		sc.setLayout(new GridLayout());

		TextFieldEditor scf = new TextFieldEditor(NSF_EXPORT_HTML_BETWEEN_PAGES, "Between Pages", sc);
		scf.getTextControl(sc).setLayoutData(new GridData(GridData.FILL_BOTH));
		addField(scf);

		ptab.setControl(sc);
	}

	private void createTabPageImages(CTabFolder tabFolder) {
		CTabItem ptab = new CTabItem(tabFolder, SWT.NONE);
		ptab.setText("Images");

		ScrolledComposite scompo = new ScrolledComposite(tabFolder, SWT.V_SCROLL | SWT.H_SCROLL);
		scompo.setExpandHorizontal(true);
		scompo.setExpandVertical(true);

		Composite sc = new Composite(scompo, SWT.NONE);
		sc.setLayout(new GridLayout(3, false));

		addField(new BooleanFieldEditor(NSF_EXPORT_HTML_IS_OUTPUT_IMAGES_TO_DIR, "Save Images To Disk", sc));
		addField(new StringFieldEditor(NSF_EXPORT_HTML_IMAGES_URI, "Images URI", sc));
		addField(new DirectoryFieldEditor(NSF_EXPORT_HTML_IMAGES_DIR_NAME, "Images Directory Name", sc));

		scompo.setMinSize(sc.getSize());
		scompo.setContent(sc);
		ptab.setControl(scompo);
	}

	public static void getDefaults(IPreferenceStore store) {
		store.setDefault(NSF_EXPORT_HTML_ACCESSIBLE, JRProperties.getProperty(NSF_EXPORT_HTML_ACCESSIBLE));
		store.setDefault(NSF_EXPORT_HTML_FLUSH_OUTPUT, JRProperties.getProperty(NSF_EXPORT_HTML_FLUSH_OUTPUT));
		store.setDefault(NSF_EXPORT_HTML_FRAMES_AS_NESTED_TABLES,
				JRProperties.getProperty(NSF_EXPORT_HTML_FRAMES_AS_NESTED_TABLES));
		store.setDefault(NSF_EXPORT_HTML_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
				JRProperties.getProperty(NSF_EXPORT_HTML_REMOVE_EMPTY_SPACE_BETWEEN_ROWS));
		store.setDefault(NSF_EXPORT_HTML_SIZE_UNIT, JRProperties.getProperty(NSF_EXPORT_HTML_SIZE_UNIT));
		store.setDefault(NSF_EXPORT_HTML_USING_IMAGES_TO_ALIGN,
				JRProperties.getProperty(NSF_EXPORT_HTML_USING_IMAGES_TO_ALIGN));
		store.setDefault(NSF_EXPORT_HTML_WHITE_PAGE_BACKGROUND,
				JRProperties.getProperty(NSF_EXPORT_HTML_WHITE_PAGE_BACKGROUND));
		store.setDefault(NSF_EXPORT_HTML_WRAP_BREAK_WORD, JRProperties.getProperty(NSF_EXPORT_HTML_WRAP_BREAK_WORD));

		store.setDefault(NSF_EXPORT_HTML_HEADER, JRProperties.getProperty(NSF_EXPORT_HTML_HEADER));
		store.setDefault(NSF_EXPORT_HTML_FOOTER, JRProperties.getProperty(NSF_EXPORT_HTML_FOOTER));
		store.setDefault(NSF_EXPORT_HTML_BETWEEN_PAGES, JRProperties.getProperty(NSF_EXPORT_HTML_BETWEEN_PAGES));

		store.setDefault(NSF_EXPORT_HTML_IS_OUTPUT_IMAGES_TO_DIR,
				JRProperties.getProperty(NSF_EXPORT_HTML_IS_OUTPUT_IMAGES_TO_DIR));
		store.setDefault(NSF_EXPORT_HTML_IMAGES_DIR_NAME, JRProperties.getProperty(NSF_EXPORT_HTML_IMAGES_DIR_NAME));
		store.setDefault(NSF_EXPORT_HTML_IMAGES_URI, JRProperties.getProperty(NSF_EXPORT_HTML_IMAGES_URI));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

}