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

package com.jaspersoft.studio.preferences.fonts;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.fonts.FontFace;
import net.sf.jasperreports.engine.fonts.FontFamily;
import net.sf.jasperreports.engine.fonts.SimpleFontExtensionHelper;
import net.sf.jasperreports.engine.fonts.SimpleFontFamily;

import org.eclipse.core.commands.operations.OperationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Widget;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.preferences.editor.table.TableFieldEditor;
import com.jaspersoft.studio.preferences.fonts.utils.SimpleFontFamilyExport;
import com.jaspersoft.studio.preferences.fonts.wizard.FontConfigWizard;

public class FontListFieldEditor extends TableFieldEditor {

	private Button editButton;
	private Button exportButton;

	public FontListFieldEditor() {
		super();
	}

	public FontListFieldEditor(String name, String labelText, Composite parent) {
		super(name, labelText, new String[] { "Font Name" }, new int[] { 100 }, parent);
	}

	@Override
	protected String createList(String[][] items) {
		return SimpleFontExtensionHelper.getFontsXml(fontFamily);
	}

	List<FontFamily> fontFamily = new ArrayList<FontFamily>();

	@Override
	protected String[][] parseString(String string) {
		String[][] res = null;
		if (string != null && !string.isEmpty()) {
			fontFamily = SimpleFontExtensionHelper.getInstance()
					.loadFontFamilies(new ByteArrayInputStream(string.getBytes()));

			res = new String[fontFamily.size()][1];
			for (int i = 0; i < fontFamily.size(); i++) {
				res[i][0] = fontFamily.get(i).getName();
			}
		} else {
			fontFamily = new ArrayList<FontFamily>();
			res = new String[0][0];
		}
		return res;
	}

	@Override
	protected String[] getNewInputObject() {
		// run dialog wizard
		SimpleFontFamily font2 = new SimpleFontFamily();
		font2.setName("NewFontFamily");
		FontFamily font = runDialog(font2);
		if (font != null) {
			fontFamily.add(font);
			return new String[] { font.getName() };
		}
		return null;
	}

	protected void editPressed() {
		setPresentsDefaultValue(false);
		int index = table.getSelectionIndex();
		if (index >= 0) {
			TableItem titem = table.getItem(index);
			FontFamily font = fontFamily.get(index);
			if (font != null) {
				font = runDialog(font);
				if (font != null) {
					titem.setText(font.getName());
					fontFamily.set(index, font);
				}
			}
		}
	}

	protected void exportPressed() {
		int[] selection = table.getSelectionIndices();
		if (selection != null && selection.length > 0) {
			List<FontFamily> lst = new ArrayList<FontFamily>(selection.length);
			for (int i = 0; i < selection.length; i++) {
				lst.add(fontFamily.get(i));
			}
			FileDialog fd = new FileDialog(Display.getCurrent().getActiveShell(), SWT.SAVE);
			fd.setText("Export font to jar");
			fd.setFilterExtensions(new String[] { "*.jar", "*.zip" });
			String selected = fd.open();
			if (selected != null) {
				try {
					exportJAR(lst, selected);
				} catch (final Exception e) {
					e.printStackTrace();
					Display.getCurrent().asyncExec(new Runnable() {
						public void run() {
							IStatus status = new OperationStatus(IStatus.ERROR, JaspersoftStudioPlugin.getUniqueIdentifier(), 1,
									"Error saving file.", e.getCause()); //$NON-NLS-1$
							ErrorDialog.openError(Display.getDefault().getActiveShell(), "Error saving file.", null, status);
						}
					});
				}
			}
		}
	}

	private void exportJAR(List<FontFamily> lst, String selected) throws IOException, JRException {
		FileOutputStream fos = new FileOutputStream(selected);
		try {
			ZipOutputStream zipos = new java.util.zip.ZipOutputStream(fos);
			zipos.setMethod(ZipOutputStream.DEFLATED);

			String prefix = "family" + (new Date()).getTime();
			String fontXmlFile = "fonts" + prefix + ".xml";

			ZipEntry propsEntry = new ZipEntry("jasperreports_extension.properties");
			zipos.putNextEntry(propsEntry);

			PrintWriter pw = new PrintWriter(zipos);

			pw.println("net.sf.jasperreports.extension.registry.factory.fonts=net.sf.jasperreports.engine.fonts.SimpleFontExtensionsRegistryFactory");
			pw.println("net.sf.jasperreports.extension.simple.font.families.ireport" + prefix + "=fonts/" + fontXmlFile);

			pw.flush();

			List<FontFamily> newfonts = new ArrayList<FontFamily>(lst.size());
			for (FontFamily f : lst) {
				SimpleFontFamilyExport newf = new SimpleFontFamilyExport((SimpleFontFamily) f);
				if (f.getNormalFace() != null)
					newf.setNormal(writeFont2zip(zipos, f.getNormalFace()));
				if (f.getBoldFace() != null)
					newf.setBold(writeFont2zip(zipos, f.getBoldFace()));
				if (f.getItalicFace() != null)
					newf.setItalic(writeFont2zip(zipos, f.getItalicFace()));
				if (f.getBoldItalicFace() != null)
					newf.setBoldItalic(writeFont2zip(zipos, f.getBoldItalicFace()));
				newfonts.add(newf);
			}

			ZipEntry fontsXmlEntry = new ZipEntry("fonts/" + fontXmlFile);
			zipos.putNextEntry(fontsXmlEntry);

			SimpleFontExtensionHelper.writeFontsXml(zipos, newfonts);

			zipos.finish();
		} finally {
			fos.close();
		}
	}

	private String writeFont2zip(ZipOutputStream zipos, FontFace font) throws IOException, FileNotFoundException {
		byte[] buffer = new byte[4096]; // Create a buffer for copying
		int bytesRead;

		String name = "fonts/" + font.getName();
		ZipEntry ttfZipEntry = new ZipEntry(name);
		zipos.putNextEntry(ttfZipEntry);

		FileInputStream in = new FileInputStream(font.getFile()); // Stream to read file
		while ((bytesRead = in.read(buffer)) != -1)
			zipos.write(buffer, 0, bytesRead);
		in.close();

		return name;
	}

	private FontFamily runDialog(FontFamily font) {
		FontConfigWizard wizard = new FontConfigWizard();
		WizardDialog dialog = new WizardDialog(Display.getCurrent().getActiveShell(), wizard);
		wizard.setFont(font);
		dialog.create();
		if (dialog.open() == Dialog.OK) {
			return wizard.getFont();
		}
		return null;
	}

	@Override
	protected void createButtons(Composite box) {
		super.createButtons(box);

		editButton = createPushButton(box, "Edit");

		exportButton = createPushButton(box, "Export");
	}

	public void createSelectionListener() {
		selectionListener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				Widget widget = event.widget;
				if (widget == addButton) {
					addPressed();
				} else if (widget == duplicateButton) {
					duplicatePressed();
				} else if (widget == removeButton) {
					removePressed();
				} else if (widget == upButton) {
					upPressed();
				} else if (widget == downButton) {
					downPressed();
				} else if (widget == editButton) {
					editPressed();
				} else if (widget == exportButton) {
					exportPressed();
				} else if (widget == table) {
					selectionChanged();
				}
			}
		};
	}

	protected void selectionChanged() {
		super.selectionChanged();
		int index = table.getSelectionIndex();
		int size = table.getItemCount();
		if (editButton != null)
			editButton.setEnabled(size >= 1 && index >= 0 && index < size && isEditable(index));
		if (exportButton != null)
			exportButton.setEnabled(size >= 1 && index >= 0 && index < size);
	}

	public void setEnabled(boolean enabled, Composite parent) {
		super.setEnabled(enabled, parent);
		editButton.setEnabled(enabled);
		exportButton.setEnabled(enabled);
	}

	protected boolean isEditable(int row) {
		return true;
	}

	@Override
	public int getNumberOfControls() {
		return 1;
	}

	@Override
	protected boolean isFieldEditable(int col, int row) {
		return false;
	}

	@Override
	protected boolean isRemovable(int row) {
		return super.isRemovable(row);
	}

	@Override
	protected boolean isSortable(int row) {
		return false;
	}
}
