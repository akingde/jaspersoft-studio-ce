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

import net.sf.jasperreports.engine.type.RunDirectionEnum;
import net.sf.jasperreports.engine.util.JRProperties;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.editor.text.TextFieldEditor;
import com.jaspersoft.studio.preferences.util.FieldEditorOverlayPage;
import com.jaspersoft.studio.utils.Misc;

/**
 * 
 */
public class ExcelExporterPreferencePage extends FieldEditorOverlayPage {
	// jexcelapi
	public static final String NSF_EXPORT_XLS_CREATE_CUSTOM_PALETTE = "net.sf.jasperreports.export.xls.create.custom.palette"; //$NON-NLS-1$
	public static final String NSF_EXPORT_XLS_PASSWORD = "net.sf.jasperreports.export.xls.password"; //$NON-NLS-1$
	public static final String NSF_EXPORT_XLS_USE_TMP_FILE = "net.sf.jasperreports.export.xls.use.temp.file"; //$NON-NLS-1$
	public static final String NSF_EXPORT_XLS_CELL_COMPLEX_FORMAT = "net.sf.jasperreports.export.xls.cell.complex.format"; //$NON-NLS-1$
	// common
	public static final String NSF_EXPORT_XLS_COLLAPSE_ROW_SPAN = "net.sf.jasperreports.export.xls.collapse.row.span"; //$NON-NLS-1$
	public static final String NSF_EXPORT_XLS_EMPTY_SPACE_ROW = "net.sf.jasperreports.export.xls.remove.empty.space.between.rows"; //$NON-NLS-1$
	public static final String NSF_EXPORT_XLS_EMPTY_SPACE_COL = "net.sf.jasperreports.export.xls.remove.empty.space.between.columns"; //$NON-NLS-1$

	public static final String NSF_EXPORT_XLS_IGNORE_GRAPHICS = "net.sf.jasperreports.export.xls.ignore.graphics"; //$NON-NLS-1$
	public static final String NSF_EXPORT_XLS_IMAGE_BORDER_FIX = "net.sf.jasperreports.export.xls.image.border.fix.enabled"; //$NON-NLS-1$
	// page
	public static final String NSF_EXPORT_XLS_WHITE_PAGE_BACKGROUND = "net.sf.jasperreports.export.xls.white.page.background"; //$NON-NLS-1$
	// sheet
	public static final String NSF_EXPORT_XLS_SHEET_DIRECTION = "net.sf.jasperreports.export.xls.sheet.direction"; //$NON-NLS-1$
	public static final String NSF_EXPORT_XLS_MAX_ROWS_PER_SHEET = "net.sf.jasperreports.export.xls.max.rows.per.sheet"; //$NON-NLS-1$
	public static final String NSF_EXPORT_XLS_ONE_PAGE_PER_SHEET = "net.sf.jasperreports.export.xls.one.page.per.sheet"; //$NON-NLS-1$
	public static final String NSF_EXPORT_XLS_SHEET_FOOTER_CENTER = "net.sf.jasperreports.export.xls.sheet.footer.center"; //$NON-NLS-1$
	public static final String NSF_EXPORT_XLS_SHEET_FOOTER_LEFT = "net.sf.jasperreports.export.xls.sheet.footer.left"; //$NON-NLS-1$
	public static final String NSF_EXPORT_XLS_SHEET_FOOTER_RIGHT = "net.sf.jasperreports.export.xls.sheet.footer.right"; //$NON-NLS-1$
	public static final String NSF_EXPORT_XLS_SHEET_HEADER_CENTER = "net.sf.jasperreports.export.xls.sheet.header.center"; //$NON-NLS-1$
	public static final String NSF_EXPORT_XLS_SHEET_HEADER_LEFT = "net.sf.jasperreports.export.xls.sheet.header.left"; //$NON-NLS-1$
	public static final String NSF_EXPORT_XLS_SHEET_HEADER_RIGHT = "net.sf.jasperreports.export.xls.sheet.header.right"; //$NON-NLS-1$

	public static final String NSF_EXPORT_XLS_FIT_HEIGHT = "net.sf.jasperreports.export.xls.fit.height"; //$NON-NLS-1$
	public static final String NSF_EXPORT_XLS_FIT_WIDTH = "net.sf.jasperreports.export.xls.fit.width"; //$NON-NLS-1$
	// cell
	public static final String NSF_EXPORT_XLS_CELL_HIDDEN = "net.sf.jasperreports.export.xls.cell.hidden"; //$NON-NLS-1$
	public static final String NSF_EXPORT_XLS_CELL_LOCKED = "net.sf.jasperreports.export.xls.cell.locked"; //$NON-NLS-1$
	public static final String NSF_EXPORT_XLS_CELL_DETECT_TYPE = "net.sf.jasperreports.export.xls.detect.cell.type"; //$NON-NLS-1$
	public static final String NSF_EXPORT_XLS_CELL_IGNORE_BACKGROUND = "net.sf.jasperreports.export.xls.ignore.cell.background"; //$NON-NLS-1$
	public static final String NSF_EXPORT_XLS_CELL_IGNORE_BORDER = "net.sf.jasperreports.export.xls.ignore.cell.border"; //$NON-NLS-1$
	public static final String NSF_EXPORT_XLS_CELL_WRAP_TEXT = "net.sf.jasperreports.export.xls.wrap.text"; //$NON-NLS-1$
	public static final String NSF_EXPORT_XLS_CELL_FONT_SIZE_FIX_ENABLED = "net.sf.jasperreports.export.xls.font.size.fix.enabled"; //$NON-NLS-1$

	public ExcelExporterPreferencePage() {
		super(GRID);
		setPreferenceStore(JaspersoftStudioPlugin.getInstance().getPreferenceStore());
		setDescription(Messages.ExcelExporterPreferencePage_title);
	}

	/**
	 *
	 */
	public void createFieldEditors() {
		CTabFolder tabFolder = new CTabFolder(getFieldEditorParent(), SWT.TOP);
		tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));

		createTabCommons(tabFolder);
		createSheet(tabFolder);
		createTabCells(tabFolder);
		createJExcelAPI(tabFolder);

		tabFolder.setSelection(0);
	}

	private void createTabCommons(CTabFolder tabFolder) {
		CTabItem ptab = new CTabItem(tabFolder, SWT.NONE);
		ptab.setText(Messages.ExcelExporterPreferencePage_29);

		Composite sc = new Composite(tabFolder, SWT.NONE);

		addField(new BooleanFieldEditor(NSF_EXPORT_XLS_WHITE_PAGE_BACKGROUND, Messages.ExcelExporterPreferencePage_30, sc));

		addField(new BooleanFieldEditor(NSF_EXPORT_XLS_COLLAPSE_ROW_SPAN, Messages.ExcelExporterPreferencePage_31, sc));
		addField(new BooleanFieldEditor(NSF_EXPORT_XLS_EMPTY_SPACE_ROW, Messages.ExcelExporterPreferencePage_32, sc));
		addField(new BooleanFieldEditor(NSF_EXPORT_XLS_EMPTY_SPACE_COL, Messages.ExcelExporterPreferencePage_33, sc));

		addField(new BooleanFieldEditor(NSF_EXPORT_XLS_IGNORE_GRAPHICS, Messages.ExcelExporterPreferencePage_34, sc));
		addField(new BooleanFieldEditor(NSF_EXPORT_XLS_IMAGE_BORDER_FIX, Messages.ExcelExporterPreferencePage_35, sc));

		StringFieldEditor se = new StringFieldEditor(NSF_EXPORT_XLS_PASSWORD, Messages.ExcelExporterPreferencePage_36, sc);
		((Text) se.getTextControl(sc)).setEchoChar('*');
		addField(se);

		ptab.setControl(sc);
	}

	private void createSheet(CTabFolder tabFolder) {
		CTabItem ptab = new CTabItem(tabFolder, SWT.NONE);
		ptab.setText(Messages.ExcelExporterPreferencePage_37);

		Composite sc = new Composite(tabFolder, SWT.NONE);
		sc.setLayout(new GridLayout());

		CTabFolder tFolder = new CTabFolder(sc, SWT.TOP);
		tFolder.setLayoutData(new GridData(GridData.FILL_BOTH));

		CTabItem cTab = new CTabItem(tFolder, SWT.NONE);
		cTab.setText(Messages.ExcelExporterPreferencePage_38);

		Composite parent = new Composite(tFolder, SWT.NONE);
		sc.setLayout(new GridLayout(2, false));

		addField(new ComboFieldEditor(NSF_EXPORT_XLS_SHEET_DIRECTION, Messages.ExcelExporterPreferencePage_39, new String[][] {
				{ RunDirectionEnum.LTR.getName(), RunDirectionEnum.LTR.getName() },
				{ RunDirectionEnum.RTL.getName(), RunDirectionEnum.RTL.getName() } }, parent));
		IntegerFieldEditor iedit = new IntegerFieldEditor(NSF_EXPORT_XLS_MAX_ROWS_PER_SHEET, Messages.ExcelExporterPreferencePage_40, parent);
		iedit.setValidRange(0, Integer.MAX_VALUE);
		addField(iedit);
		addField(new BooleanFieldEditor(NSF_EXPORT_XLS_ONE_PAGE_PER_SHEET, Messages.ExcelExporterPreferencePage_41, parent));

		iedit = new IntegerFieldEditor(NSF_EXPORT_XLS_FIT_HEIGHT, Messages.ExcelExporterPreferencePage_42, parent);
		iedit.setValidRange(0, Integer.MAX_VALUE);
		addField(iedit);

		iedit = new IntegerFieldEditor(NSF_EXPORT_XLS_FIT_WIDTH, Messages.ExcelExporterPreferencePage_43, parent);
		iedit.setValidRange(0, Integer.MAX_VALUE);
		addField(iedit);

		cTab.setControl(parent);

		cTab = new CTabItem(tFolder, SWT.NONE);
		cTab.setText(Messages.ExcelExporterPreferencePage_44);

		parent = new Composite(tFolder, SWT.NONE);
		sc.setLayout(new GridLayout(2, false));

		addField(new TextFieldEditor(NSF_EXPORT_XLS_SHEET_HEADER_LEFT, Messages.ExcelExporterPreferencePage_45, parent));
		addField(new TextFieldEditor(NSF_EXPORT_XLS_SHEET_HEADER_CENTER, Messages.ExcelExporterPreferencePage_46, parent));
		addField(new TextFieldEditor(NSF_EXPORT_XLS_SHEET_HEADER_RIGHT, Messages.ExcelExporterPreferencePage_47, parent));

		cTab.setControl(parent);

		cTab = new CTabItem(tFolder, SWT.NONE);
		cTab.setText(Messages.ExcelExporterPreferencePage_48);

		parent = new Composite(tFolder, SWT.NONE);
		sc.setLayout(new GridLayout(2, false));

		addField(new TextFieldEditor(NSF_EXPORT_XLS_SHEET_FOOTER_LEFT, Messages.ExcelExporterPreferencePage_49, parent));
		addField(new TextFieldEditor(NSF_EXPORT_XLS_SHEET_FOOTER_CENTER, Messages.ExcelExporterPreferencePage_50, parent));
		addField(new TextFieldEditor(NSF_EXPORT_XLS_SHEET_FOOTER_RIGHT, Messages.ExcelExporterPreferencePage_51, parent));

		cTab.setControl(parent);
		tFolder.setSelection(0);

		ptab.setControl(sc);
	}

	private void createTabCells(CTabFolder tabFolder) {
		CTabItem ptab = new CTabItem(tabFolder, SWT.NONE);
		ptab.setText(Messages.ExcelExporterPreferencePage_52);

		Composite sc = new Composite(tabFolder, SWT.NONE);

		addField(new BooleanFieldEditor(NSF_EXPORT_XLS_CELL_HIDDEN, Messages.ExcelExporterPreferencePage_53, sc));
		addField(new BooleanFieldEditor(NSF_EXPORT_XLS_CELL_LOCKED, Messages.ExcelExporterPreferencePage_54, sc));
		addField(new BooleanFieldEditor(NSF_EXPORT_XLS_CELL_DETECT_TYPE, Messages.ExcelExporterPreferencePage_55, sc));
		addField(new BooleanFieldEditor(NSF_EXPORT_XLS_CELL_IGNORE_BACKGROUND, Messages.ExcelExporterPreferencePage_56, sc));
		addField(new BooleanFieldEditor(NSF_EXPORT_XLS_CELL_IGNORE_BORDER, Messages.ExcelExporterPreferencePage_57, sc));
		addField(new BooleanFieldEditor(NSF_EXPORT_XLS_CELL_WRAP_TEXT, Messages.ExcelExporterPreferencePage_58, sc));
		addField(new BooleanFieldEditor(NSF_EXPORT_XLS_CELL_FONT_SIZE_FIX_ENABLED, Messages.ExcelExporterPreferencePage_59, sc));

		ptab.setControl(sc);
	}

	private void createJExcelAPI(CTabFolder tabFolder) {
		CTabItem ptab = new CTabItem(tabFolder, SWT.NONE);
		ptab.setText(Messages.ExcelExporterPreferencePage_60);

		ScrolledComposite scompo = new ScrolledComposite(tabFolder, SWT.V_SCROLL | SWT.H_SCROLL);
		scompo.setExpandHorizontal(true);
		scompo.setExpandVertical(true);

		Composite sc = new Composite(scompo, SWT.NONE);
		sc.setLayout(new GridLayout(3, false));

		addField(new BooleanFieldEditor(NSF_EXPORT_XLS_CREATE_CUSTOM_PALETTE, Messages.ExcelExporterPreferencePage_61, sc));
		addField(new BooleanFieldEditor(NSF_EXPORT_XLS_USE_TMP_FILE, Messages.ExcelExporterPreferencePage_62, sc));
		addField(new BooleanFieldEditor(NSF_EXPORT_XLS_CELL_COMPLEX_FORMAT, Messages.ExcelExporterPreferencePage_63, sc));

		scompo.setMinSize(sc.getSize());
		scompo.setContent(sc);
		ptab.setControl(scompo);
	}

	public static void getDefaults(IPreferenceStore store) {
		// JEXCELAPI
		store.setDefault(NSF_EXPORT_XLS_CREATE_CUSTOM_PALETTE,
				JRProperties.getProperty(NSF_EXPORT_XLS_CREATE_CUSTOM_PALETTE));
		store.setDefault(NSF_EXPORT_XLS_PASSWORD, Misc.nvl(JRProperties.getProperty(NSF_EXPORT_XLS_PASSWORD), "")); //$NON-NLS-1$
		store.setDefault(NSF_EXPORT_XLS_USE_TMP_FILE, Misc.nvl(JRProperties.getProperty(NSF_EXPORT_XLS_USE_TMP_FILE), "")); //$NON-NLS-1$
		store.setDefault(NSF_EXPORT_XLS_CELL_COMPLEX_FORMAT,
				Misc.nvl(JRProperties.getProperty(NSF_EXPORT_XLS_CELL_COMPLEX_FORMAT), "")); //$NON-NLS-1$
		// COMMON
		store.setDefault(NSF_EXPORT_XLS_COLLAPSE_ROW_SPAN, JRProperties.getProperty(NSF_EXPORT_XLS_COLLAPSE_ROW_SPAN));
		store.setDefault(NSF_EXPORT_XLS_IGNORE_GRAPHICS, JRProperties.getProperty(NSF_EXPORT_XLS_IGNORE_GRAPHICS));
		store.setDefault(NSF_EXPORT_XLS_IMAGE_BORDER_FIX,
				Misc.nvl(JRProperties.getProperty(NSF_EXPORT_XLS_IMAGE_BORDER_FIX), "false")); //$NON-NLS-1$
		store.setDefault(NSF_EXPORT_XLS_EMPTY_SPACE_ROW, JRProperties.getProperty(NSF_EXPORT_XLS_EMPTY_SPACE_ROW));
		store.setDefault(NSF_EXPORT_XLS_EMPTY_SPACE_COL, JRProperties.getProperty(NSF_EXPORT_XLS_EMPTY_SPACE_COL));
		// page
		store.setDefault(NSF_EXPORT_XLS_WHITE_PAGE_BACKGROUND,
				JRProperties.getProperty(NSF_EXPORT_XLS_WHITE_PAGE_BACKGROUND));
		// sheet
		store.setDefault(NSF_EXPORT_XLS_SHEET_DIRECTION,
				Misc.nvl(JRProperties.getProperty(NSF_EXPORT_XLS_SHEET_DIRECTION), RunDirectionEnum.LTR.getName()));
		store.setDefault(NSF_EXPORT_XLS_MAX_ROWS_PER_SHEET, JRProperties.getProperty(NSF_EXPORT_XLS_MAX_ROWS_PER_SHEET));
		store.setDefault(NSF_EXPORT_XLS_ONE_PAGE_PER_SHEET, JRProperties.getProperty(NSF_EXPORT_XLS_ONE_PAGE_PER_SHEET));

		store.setDefault(NSF_EXPORT_XLS_SHEET_FOOTER_CENTER,
				Misc.nvl(JRProperties.getProperty(NSF_EXPORT_XLS_SHEET_FOOTER_CENTER), "")); //$NON-NLS-1$
		store.setDefault(NSF_EXPORT_XLS_SHEET_FOOTER_LEFT,
				Misc.nvl(JRProperties.getProperty(NSF_EXPORT_XLS_SHEET_FOOTER_LEFT), "")); //$NON-NLS-1$
		store.setDefault(NSF_EXPORT_XLS_SHEET_FOOTER_RIGHT,
				Misc.nvl(JRProperties.getProperty(NSF_EXPORT_XLS_SHEET_FOOTER_RIGHT), "")); //$NON-NLS-1$
		store.setDefault(NSF_EXPORT_XLS_SHEET_HEADER_CENTER,
				Misc.nvl(JRProperties.getProperty(NSF_EXPORT_XLS_SHEET_HEADER_CENTER), "")); //$NON-NLS-1$
		store.setDefault(NSF_EXPORT_XLS_SHEET_HEADER_LEFT,
				Misc.nvl(JRProperties.getProperty(NSF_EXPORT_XLS_SHEET_HEADER_LEFT), "")); //$NON-NLS-1$
		store.setDefault(NSF_EXPORT_XLS_SHEET_HEADER_RIGHT,
				Misc.nvl(JRProperties.getProperty(NSF_EXPORT_XLS_SHEET_HEADER_RIGHT), "")); //$NON-NLS-1$

		store.setDefault(NSF_EXPORT_XLS_FIT_HEIGHT, Misc.nvl(JRProperties.getProperty(NSF_EXPORT_XLS_FIT_HEIGHT), "")); //$NON-NLS-1$
		store.setDefault(NSF_EXPORT_XLS_FIT_WIDTH, Misc.nvl(JRProperties.getProperty(NSF_EXPORT_XLS_FIT_WIDTH), "")); //$NON-NLS-1$
		// CELL
		store.setDefault(NSF_EXPORT_XLS_CELL_HIDDEN,
				Misc.nvl(JRProperties.getProperty(NSF_EXPORT_XLS_CELL_HIDDEN), "false")); //$NON-NLS-1$
		store.setDefault(NSF_EXPORT_XLS_CELL_LOCKED,
				Misc.nvl(JRProperties.getProperty(NSF_EXPORT_XLS_CELL_LOCKED), "false")); //$NON-NLS-1$
		store.setDefault(NSF_EXPORT_XLS_CELL_DETECT_TYPE, JRProperties.getProperty(NSF_EXPORT_XLS_CELL_DETECT_TYPE));
		store.setDefault(NSF_EXPORT_XLS_CELL_IGNORE_BACKGROUND,
				JRProperties.getProperty(NSF_EXPORT_XLS_CELL_IGNORE_BACKGROUND));
		store.setDefault(NSF_EXPORT_XLS_CELL_IGNORE_BORDER, JRProperties.getProperty(NSF_EXPORT_XLS_CELL_IGNORE_BORDER));
		store.setDefault(NSF_EXPORT_XLS_CELL_WRAP_TEXT, JRProperties.getProperty(NSF_EXPORT_XLS_CELL_WRAP_TEXT));
		store.setDefault(NSF_EXPORT_XLS_CELL_FONT_SIZE_FIX_ENABLED,
				Misc.nvl(JRProperties.getProperty(NSF_EXPORT_XLS_CELL_FONT_SIZE_FIX_ENABLED), "false")); //$NON-NLS-1$
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
		return "com.jaspersoft.studio.preferences.exporter.ExcelExporterPreferencePage.property"; //$NON-NLS-1$
	}

}