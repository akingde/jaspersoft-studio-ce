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
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PathEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.editor.PDFPermissionFieldEditor;
import com.jaspersoft.studio.preferences.editor.text.TextFieldEditor;
import com.jaspersoft.studio.preferences.util.FieldEditorOverlayPage;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.ModelUtils;

/**
 * 
 */
public class PDFExporterPreferencePage extends FieldEditorOverlayPage {
	public static final String NSF_EXPORT_PDF_VERSION = "net.sf.jasperreports.export.pdf.version"; //$NON-NLS-1$
	public static final String NSF_EXPORT_PDF_COMPRESSED = "net.sf.jasperreports.export.pdf.compressed"; //$NON-NLS-1$

	public static final String NSF_EXPORT_PDF_CREATE_BATCH_MODE_BOOKMARKS = "net.sf.jasperreports.export.pdf.create.batch.mode.bookmarks"; //$NON-NLS-1$
	public static final String NSF_EXPORT_PDF_COLLAPSE_MISSING_BOOKMARK_LEVELS = "net.sf.jasperreports.export.pdf.collapse.missing.bookmark.levels"; //$NON-NLS-1$

	public static final String NSF_EXPORT_PDF_JAVASCRIPT = "net.sf.jasperreports.export.pdf.javascript"; //$NON-NLS-1$
	public static final String NSF_EXPORT_PDF_FORCE_SVG_SHAPES = "net.sf.jasperreports.export.pdf.force.svg.shapes"; //$NON-NLS-1$
	public static final String NSF_EXPORT_PDF_FORCE_LINEBREAK_POLICY = "net.sf.jasperreports.export.pdf.force.linebreak.policy"; //$NON-NLS-1$
	public static final String NSF_EXPORT_PDF_PRINT_SCALING = "net.sf.jasperreports.export.pdf.print.scaling"; //$NON-NLS-1$
	public static final String NSF_EXPORT_PDF_TAG_LANGUAGE = "net.sf.jasperreports.export.pdf.tag.language"; //$NON-NLS-1$
	public static final String NSF_EXPORT_PDF_TAGGED = "net.sf.jasperreports.export.pdf.tagged"; //$NON-NLS-1$
	// fonts
	public static final String NSF_EXPORT_PDF_EMBEDDED = "net.sf.jasperreports.default.pdf.embedded"; //$NON-NLS-1$
	public static final String NSF_EXPORT_PDF_ENCODING = "net.sf.jasperreports.default.pdf.encoding"; //$NON-NLS-1$
	public static final String NSF_EXPORT_PDF_FONT_NAME = "net.sf.jasperreports.default.pdf.font.name"; //$NON-NLS-1$
	public static final String NSF_EXPORT_PDF_FONTDIR = "net.sf.jasperreports.export.pdf.fontdir"; //$NON-NLS-1$
	// security
	public static final String NSF_EXPORT_PDF_ENCRYPTED = "net.sf.jasperreports.export.pdf.encrypted"; //$NON-NLS-1$
	public static final String NSF_EXPORT_PDF_128BITKEY = "net.sf.jasperreports.export.pdf.128.bit.key"; //$NON-NLS-1$
	public static final String NSF_EXPORT_PDF_USER_PASS = "net.sf.jasperreports.export.pdf.user.password"; //$NON-NLS-1$
	public static final String NSF_EXPORT_PDF_OWNER_PASS = "net.sf.jasperreports.export.pdf.owner.password"; //$NON-NLS-1$
	public static final String NSF_EXPORT_PDF_PERMISSION = "export.pdf.PERMISSIONS"; //$NON-NLS-1$
	// metadata
	public static final String NSF_EXPORT_PDF_METADATA_TITLE = "net.sf.jasperreports.export.pdf.metadata.title"; //$NON-NLS-1$
	public static final String NSF_EXPORT_PDF_METADATA_AUTHOR = "net.sf.jasperreports.export.pdf.metadata.author"; //$NON-NLS-1$
	public static final String NSF_EXPORT_PDF_METADATA_SUBJECT = "net.sf.jasperreports.export.pdf.metadata.subject"; //$NON-NLS-1$
	public static final String NSF_EXPORT_PDF_METADATA_KEYWORDS = "net.sf.jasperreports.export.pdf.metadata.keywords"; //$NON-NLS-1$
	public static final String NSF_EXPORT_PDF_METADATA_CREATOR = "net.sf.jasperreports.export.pdf.metadata.creator"; //$NON-NLS-1$

	public PDFExporterPreferencePage() {
		super(GRID);
		setPreferenceStore(JaspersoftStudioPlugin.getInstance().getPreferenceStore());
		setDescription(Messages.PDFExporterPreferencePage_24);
	}

	public static void getDefaults(IPreferenceStore store) {
		store.setDefault(NSF_EXPORT_PDF_VERSION, Misc.nvl(JRProperties.getProperty(NSF_EXPORT_PDF_VERSION), Messages.PDFExporterPreferencePage_25));
		store.setDefault(NSF_EXPORT_PDF_COMPRESSED, JRProperties.getProperty(NSF_EXPORT_PDF_COMPRESSED));
		store.setDefault(NSF_EXPORT_PDF_CREATE_BATCH_MODE_BOOKMARKS,
				JRProperties.getProperty(NSF_EXPORT_PDF_CREATE_BATCH_MODE_BOOKMARKS));
		store.setDefault(NSF_EXPORT_PDF_JAVASCRIPT, Misc.nvl(JRProperties.getProperty(NSF_EXPORT_PDF_JAVASCRIPT), Messages.PDFExporterPreferencePage_26));
		store.setDefault(NSF_EXPORT_PDF_FORCE_SVG_SHAPES, JRProperties.getProperty(NSF_EXPORT_PDF_FORCE_SVG_SHAPES));
		store.setDefault(NSF_EXPORT_PDF_FORCE_LINEBREAK_POLICY,
				JRProperties.getProperty(NSF_EXPORT_PDF_FORCE_LINEBREAK_POLICY));
		store.setDefault(NSF_EXPORT_PDF_PRINT_SCALING, JRProperties.getProperty(NSF_EXPORT_PDF_PRINT_SCALING));
		store.setDefault(NSF_EXPORT_PDF_TAG_LANGUAGE, Misc.nvl(JRProperties.getProperty(NSF_EXPORT_PDF_TAG_LANGUAGE), Messages.PDFExporterPreferencePage_27));
		store.setDefault(NSF_EXPORT_PDF_COLLAPSE_MISSING_BOOKMARK_LEVELS,
				JRProperties.getProperty(NSF_EXPORT_PDF_COLLAPSE_MISSING_BOOKMARK_LEVELS));
		store.setDefault(NSF_EXPORT_PDF_TAGGED, Misc.nvl(JRProperties.getProperty(NSF_EXPORT_PDF_TAGGED), Messages.PDFExporterPreferencePage_28));
		// FONTS
		store.setDefault(NSF_EXPORT_PDF_EMBEDDED, JRProperties.getProperty(NSF_EXPORT_PDF_EMBEDDED));
		store.setDefault(NSF_EXPORT_PDF_ENCODING, JRProperties.getProperty(NSF_EXPORT_PDF_ENCODING));
		store.setDefault(NSF_EXPORT_PDF_FONT_NAME, JRProperties.getProperty(NSF_EXPORT_PDF_FONT_NAME));
		store.setDefault(NSF_EXPORT_PDF_FONTDIR, Misc.nvl(JRProperties.getProperty(NSF_EXPORT_PDF_FONTDIR), Messages.PDFExporterPreferencePage_29));
		// SECURITY
		store.setDefault(NSF_EXPORT_PDF_ENCRYPTED, JRProperties.getProperty(NSF_EXPORT_PDF_ENCRYPTED));
		store.setDefault(NSF_EXPORT_PDF_128BITKEY, JRProperties.getProperty(NSF_EXPORT_PDF_128BITKEY));
		store.setDefault(NSF_EXPORT_PDF_USER_PASS, Misc.nvl(JRProperties.getProperty(NSF_EXPORT_PDF_USER_PASS), Messages.PDFExporterPreferencePage_30));
		store.setDefault(NSF_EXPORT_PDF_OWNER_PASS, Misc.nvl(JRProperties.getProperty(NSF_EXPORT_PDF_OWNER_PASS), Messages.PDFExporterPreferencePage_31));
		store.setDefault(NSF_EXPORT_PDF_PERMISSION, Misc.nvl(JRProperties.getProperty(NSF_EXPORT_PDF_PERMISSION), Messages.PDFExporterPreferencePage_32));
		// metadata
		store.setDefault(NSF_EXPORT_PDF_METADATA_TITLE,
				Misc.nvl(JRProperties.getProperty(NSF_EXPORT_PDF_METADATA_TITLE), Messages.PDFExporterPreferencePage_33));
		store.setDefault(NSF_EXPORT_PDF_METADATA_AUTHOR,
				Misc.nvl(JRProperties.getProperty(NSF_EXPORT_PDF_METADATA_AUTHOR), Messages.PDFExporterPreferencePage_34));
		store.setDefault(NSF_EXPORT_PDF_METADATA_SUBJECT,
				Misc.nvl(JRProperties.getProperty(NSF_EXPORT_PDF_METADATA_SUBJECT), Messages.PDFExporterPreferencePage_35));
		store.setDefault(NSF_EXPORT_PDF_METADATA_KEYWORDS,
				Misc.nvl(JRProperties.getProperty(NSF_EXPORT_PDF_METADATA_KEYWORDS), Messages.PDFExporterPreferencePage_36));
		store.setDefault(NSF_EXPORT_PDF_METADATA_CREATOR,
				Misc.nvl(JRProperties.getProperty(NSF_EXPORT_PDF_METADATA_CREATOR), Messages.PDFExporterPreferencePage_37));
	}

	/**
	 *
	 */
	public void createFieldEditors() {
		CTabFolder tabFolder = new CTabFolder(getFieldEditorParent(), SWT.TOP);
		tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));

		createTabCommons(tabFolder);
		createTabFonts(tabFolder);
		createTabMetadata(tabFolder);
		createTabSecurity(tabFolder);

		tabFolder.setSelection(0);
	}

	private void createTabMetadata(CTabFolder tabFolder) {
		CTabItem ptab = new CTabItem(tabFolder, SWT.NONE);
		ptab.setText(Messages.PDFExporterPreferencePage_38);

		Composite sc = new Composite(tabFolder, SWT.NONE);

		addField(new StringFieldEditor(NSF_EXPORT_PDF_METADATA_TITLE, Messages.PDFExporterPreferencePage_39, sc));
		addField(new StringFieldEditor(NSF_EXPORT_PDF_METADATA_AUTHOR, Messages.PDFExporterPreferencePage_40, sc));
		addField(new StringFieldEditor(NSF_EXPORT_PDF_METADATA_SUBJECT, Messages.PDFExporterPreferencePage_41, sc));
		addField(new StringFieldEditor(NSF_EXPORT_PDF_METADATA_KEYWORDS, Messages.PDFExporterPreferencePage_42, sc));
		addField(new StringFieldEditor(NSF_EXPORT_PDF_METADATA_CREATOR, Messages.PDFExporterPreferencePage_43, sc));

		ptab.setControl(sc);
	}

	private void createTabSecurity(CTabFolder tabFolder) {
		CTabItem ptab = new CTabItem(tabFolder, SWT.NONE);
		ptab.setText(Messages.PDFExporterPreferencePage_44);

		Composite sc = new Composite(tabFolder, SWT.NONE);

		addField(new BooleanFieldEditor(NSF_EXPORT_PDF_ENCRYPTED, Messages.PDFExporterPreferencePage_45, sc));
		addField(new BooleanFieldEditor(NSF_EXPORT_PDF_128BITKEY, Messages.PDFExporterPreferencePage_46, sc));
		StringFieldEditor se = new StringFieldEditor(NSF_EXPORT_PDF_USER_PASS, Messages.PDFExporterPreferencePage_47, sc);
		((Text) se.getTextControl(sc)).setEchoChar('*');
		addField(se);
		se = new StringFieldEditor(NSF_EXPORT_PDF_OWNER_PASS, Messages.PDFExporterPreferencePage_48, sc);
		((Text) se.getTextControl(sc)).setEchoChar('*');
		addField(se);

		addField(new PDFPermissionFieldEditor(NSF_EXPORT_PDF_PERMISSION, Messages.PDFExporterPreferencePage_49, sc));

		ptab.setControl(sc);
	}

	private void createTabFonts(CTabFolder tabFolder) {
		CTabItem ptab = new CTabItem(tabFolder, SWT.NONE);
		ptab.setText(Messages.PDFExporterPreferencePage_50);

		Composite sc = new Composite(tabFolder, SWT.NONE);

		addField(new ComboFieldEditor(NSF_EXPORT_PDF_ENCODING, Messages.PDFExporterPreferencePage_51, ModelUtils.getPdfEncodings2(), sc));
		addField(new BooleanFieldEditor(NSF_EXPORT_PDF_EMBEDDED, Messages.PDFExporterPreferencePage_52, sc));
		addField(new ComboFieldEditor(NSF_EXPORT_PDF_FONT_NAME, Messages.PDFExporterPreferencePage_53, ModelUtils.getPDFFontNames2(), sc));

		Composite fp = new Composite(sc, SWT.NONE);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		fp.setLayoutData(gd);
		addField(new PathEditor(NSF_EXPORT_PDF_FONTDIR, Messages.PDFExporterPreferencePage_54, Messages.PDFExporterPreferencePage_55, fp));

		ptab.setControl(sc);
	}

	private void createTabCommons(CTabFolder tabFolder) {
		CTabItem ptab = new CTabItem(tabFolder, SWT.NONE);
		ptab.setText(Messages.PDFExporterPreferencePage_56);

		Composite sc = new Composite(tabFolder, SWT.NONE);

		addField(new ComboFieldEditor(NSF_EXPORT_PDF_VERSION, Messages.PDFExporterPreferencePage_57, new String[][] { { Messages.PDFExporterPreferencePage_58, Messages.PDFExporterPreferencePage_59 },
				{ Messages.PDFExporterPreferencePage_60, Messages.PDFExporterPreferencePage_61 }, { Messages.PDFExporterPreferencePage_62, Messages.PDFExporterPreferencePage_63 }, { Messages.PDFExporterPreferencePage_64, Messages.PDFExporterPreferencePage_65 }, { Messages.PDFExporterPreferencePage_66, Messages.PDFExporterPreferencePage_67 }, { Messages.PDFExporterPreferencePage_68, Messages.PDFExporterPreferencePage_69 }, { Messages.PDFExporterPreferencePage_70, Messages.PDFExporterPreferencePage_71 } }, sc));

		addField(new BooleanFieldEditor(NSF_EXPORT_PDF_COMPRESSED, Messages.PDFExporterPreferencePage_72, sc));
		addField(new BooleanFieldEditor(NSF_EXPORT_PDF_CREATE_BATCH_MODE_BOOKMARKS, Messages.PDFExporterPreferencePage_73, sc));
		addField(new BooleanFieldEditor(NSF_EXPORT_PDF_COLLAPSE_MISSING_BOOKMARK_LEVELS,
				Messages.PDFExporterPreferencePage_74, sc));
		addField(new BooleanFieldEditor(NSF_EXPORT_PDF_FORCE_SVG_SHAPES, Messages.PDFExporterPreferencePage_75, sc));
		addField(new BooleanFieldEditor(NSF_EXPORT_PDF_FORCE_LINEBREAK_POLICY, Messages.PDFExporterPreferencePage_76, sc));
		addField(new BooleanFieldEditor(NSF_EXPORT_PDF_TAGGED, Messages.PDFExporterPreferencePage_77, sc));
		addField(new ComboFieldEditor(NSF_EXPORT_PDF_PRINT_SCALING, Messages.PDFExporterPreferencePage_78, new String[][] {
				{ "Default", "default" }, { "None", "none" } }, sc)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		addField(new StringFieldEditor(NSF_EXPORT_PDF_TAG_LANGUAGE, Messages.PDFExporterPreferencePage_83, sc));
		addField(new TextFieldEditor(NSF_EXPORT_PDF_JAVASCRIPT, Messages.PDFExporterPreferencePage_84, sc));

		ptab.setControl(sc);
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
		return "com.jaspersoft.studio.preferences.exporter.PDFExporterPreferencePage.property"; //$NON-NLS-1$
	}

}