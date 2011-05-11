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
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.fonts.FontFamily;
import net.sf.jasperreports.engine.fonts.SimpleFontExtensionHelper;
import net.sf.jasperreports.engine.fonts.SimpleFontFamily;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Widget;

import com.jaspersoft.studio.preferences.editor.table.TableFieldEditor;
import com.jaspersoft.studio.preferences.fonts.wizard.FontConfigWizard;

public class FontListFieldEditor extends TableFieldEditor {

	private Button editButton;

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
	}

	public void setEnabled(boolean enabled, Composite parent) {
		super.setEnabled(enabled, parent);
		editButton.setEnabled(enabled);
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
