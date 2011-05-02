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

package com.jaspersoft.studio.preferences.qexecutor;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.preferences.editor.table.TableFieldEditor;
import com.jaspersoft.studio.utils.ModelUtils;

public class QExecutorFieldEditor extends TableFieldEditor {

	public QExecutorFieldEditor() {
		super();
	}

	public QExecutorFieldEditor(String name, String labelText, Composite parent) {
		super(name, labelText, new String[] { "Language", "Query Executor Factory", "Fields Provider Class" }, new int[] {
				100, 250, 400 }, parent);
	}

	@Override
	protected String createList(String[][] items) {
		String str = "";
		for (int i = 0; i < items.length; i++) {
			for (int j = 0; j < items[i].length; j++) {
				str += items[i][j];
				if (j < items[i].length - 1)
					str += ";";
			}
			str += "\n";
		}
		return str;
	}

	@Override
	protected String[][] parseString(String string) {
		String[] rows = string.split("\\n");
		String[][] res = new String[rows.length][3];
		for (int i = 0; i < rows.length; i++) {
			String[] cols = rows[i].split(";");
			if (cols.length == 3) {
				res[i][0] = cols[0];
				res[i][1] = cols[1];
				res[i][2] = cols[2];
			} else {
				res[i][0] = "...";
				res[i][1] = "...";
				res[i][2] = "...";
			}

		}
		return res;
	}

	@Override
	protected String[] getNewInputObject() {
		return new String[] { "<LANGUAGE>", "...", "..." };
	}

	@Override
	public int getNumberOfControls() {
		return 1;
	}

	@Override
	protected boolean isFieldEditable(int col, int row) {
		if (row >= 0 && row < ModelUtils.getQueryExecuters().length)
			return false;
		return super.isFieldEditable(col, row);
	}

	@Override
	protected boolean isRemovable(int row) {
		if (row >= 0 && row < ModelUtils.getQueryExecuters().length)
			return false;
		return super.isRemovable(row);
	}

	@Override
	protected boolean isSortable(int row) {
		if (row >= 0 && row < ModelUtils.getQueryExecuters().length)
			return false;
		return super.isRemovable(row);
	}
}
