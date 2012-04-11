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

package com.jaspersoft.studio.preferences.editor.properties;

import java.util.Collections;
import java.util.List;

import net.sf.jasperreports.engine.JRPropertiesUtil.PropertySuffix;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TableItem;

import com.jaspersoft.studio.preferences.editor.table.TableFieldEditor;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;

public class PropertyListFieldEditor extends TableFieldEditor {

	public PropertyListFieldEditor() {
		super();
	}

	public PropertyListFieldEditor(String name, String labelText, Composite parent) {
		super(name, labelText, new String[] { "Property", "Value" }, new int[] { 400, 30 }, parent);
	}

	@Override
	protected String createList(String[][] items) {
		return "";
	}

	@Override
	protected String[][] parseString(String string) {
		return new String[0][0];
	}

	@Override
	protected String[] getNewInputObject() {

		return new String[] { "prop", "value" };
	}

	protected void doStore() {
		TableItem[] items = getTable().getItems();
		for (int i = 0; i < items.length; i++) {
			TableItem item = items[i];
			getPreferenceStore().setValue(item.getText(0), item.getText(1));
		}
	}

	/*
	 * (non-Javadoc) Method declared on FieldEditor.
	 */
	protected void doLoad() {
		if (getTable() != null) {
			List<PropertySuffix> lst = PropertiesHelper.DPROP.getProperties("");
			Collections.sort(lst, new PropertyComparator());
			for (PropertySuffix ps : lst) {
				String s = getPreferenceStore().getString(ps.getKey());

				TableItem tableItem = new TableItem(getTable(), SWT.NONE);
				tableItem.setText(new String[] { ps.getKey(), s });
			}
		}
	}

	/*
	 * (non-Javadoc) Method declared on FieldEditor.
	 */
	protected void doLoadDefault() {
		if (getTable() != null) {
			getTable().removeAll();

			List<PropertySuffix> lst = PropertiesHelper.DPROP.getProperties("");
			Collections.sort(lst, new PropertyComparator());
			for (PropertySuffix ps : lst) {
				String s = getPreferenceStore().getDefaultString(ps.getKey());

				TableItem tableItem = new TableItem(getTable(), SWT.NONE);
				tableItem.setText(new String[] { ps.getKey(), s });
			}
		}
	}

	@Override
	protected void doFillIntoGrid(Composite parent, int numColumns) {
		Control control = getLabelControl(parent);
		GridData gd = new GridData();
		gd.horizontalSpan = numColumns;
		control.setLayoutData(gd);

		Composite composite = new Composite(parent, SWT.NONE);
		GridData gridData = new GridData(GridData.FILL_BOTH);
		// gridData.horizontalSpan = 2;
		gridData.widthHint = 600;
		gridData.heightHint = 500;
		composite.setLayoutData(gridData);
		composite.setLayout(new GridLayout(1, false));

		table = getTableControl(composite);
		gd = new GridData(GridData.FILL_BOTH);
		// gd.horizontalSpan = numColumns - 1;
		// gd.grabExcessHorizontalSpace = true;
		table.setLayoutData(gd);

		// buttonBox = getButtonBoxControl(composite);
		// gd = new GridData();
		// gd.verticalAlignment = GridData.BEGINNING;
		// buttonBox.setLayoutData(gd);
	}

	@Override
	public int getNumberOfControls() {
		return 1;
	}

	@Override
	protected boolean isFieldEditable(int col, int row) {
		if (col == 0)
			return false;
		return super.isFieldEditable(col, row);
	}
}
